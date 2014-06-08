import java.io.*;

public class DirectAccessFile 
{
	private static RandomAccessFile ram;
	private int r;

	public DirectAccessFile(int n, int r) throws Exception 
	{
		this.r = r;
		File file = new File("dataFile");
		ram = new RandomAccessFile(file, "rw");
		ram.setLength(0);
	
		for(int i = 0; i < n*r; i++)
		{
			ram.seek(i * 24);
			ram.write(new Record().serialize());
		}
	}
	
	public Bucket getBucket(int i, int r) throws Exception
	{
		ram.seek(i * 48);
		byte[] bytes = new byte[48];
		ram.read(bytes);
		return new Bucket(bytes, 2);
	}
	public void writeBucket(Bucket b, int i) throws IOException
	{
		ram.seek(i*r*24);
		ram.write(b.serialize());
	}
	
	public boolean writeToBucket(Record rec, int bucket) throws Exception
	{
		Bucket b = getBucket(bucket, r);
		for(Record x : b.getRecords())
		{
			if(x.getId() == 0)
			{
				x.setId(rec.getId());
				x.setName(rec.getName());
				writeBucket(b, bucket);
				return true;
			}
		}
		return false;
	}
	
	public void printBucket(int bucket) throws Exception
	{
		Bucket b = getBucket(bucket, r);
		Record[] records = b.getRecords();
		
		System.out.println("Bucket " + bucket + ":");
		for(int i = 0; i < records.length; i++){
			System.out.println( "    Record " + i + ": id=[" + records[i].getId() + "] name=[" + records[i].getName() + "]");
		}
	}

}
