public class HashFile 
{	
	private int[] bucketMap;
	private DirectAccessFile file;
	private int numBuckets, numRecords;
	
	public HashFile(int n, int r) throws Exception
	{
		numBuckets = n;
		numRecords = r;
		bucketMap = new int[numBuckets];
		for(int i = 0; i < bucketMap.length; i++)
			bucketMap[i] = 0;
		file = new DirectAccessFile(numBuckets, numRecords);
	}
	
	public int store(Record r) throws Exception
	{
		int key = hash(r.getId());
		while(true)
		{
			if(bucketMap[key] < numRecords)
			{
				file.writeToBucket(r, key);
				bucketMap[key]++;
				return key;
			}
			else
			{
				key = (key+1) % numBuckets;
			}
			if(key == hash(r.getId())) return -1;
		}
	}
	
	public Record retrieve(int id) throws Exception
	{
		int key = hash(id);
		if(bucketMap[key] == 0) 
			return null;
		while(true)
		{
			if(bucketMap[key] > 0)
			{ 
				Bucket b = file.getBucket(key, numRecords);
				for(int i = 0; i < bucketMap[key]; i++)
				{ 
					if(b.getRecord(i).getId() == id)
						return b.getRecord(i);
				}
			}
			key = (key+1) % numBuckets;
			if(key == hash(id)) 
				break;
		}
		return null;
	}
	
	private int hash(int k)
	{
		return k % numBuckets;
	}
	
	public void printBucket(int bucket) throws Exception
	{
		file.printBucket(bucket);
	}
}
