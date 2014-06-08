import java.nio.ByteBuffer;

public class Bucket 
{
	private Record[] records;
	private static int numRecords;

	public Bucket(int numRecs)
	{
		numRecords = numRecs;
		records = new Record[numRecords];
		for(int i = 0; i < records.length; i++)
			records[i] = new Record();
	}
	
	public Bucket(byte[] bytes, int numRecords)
	{
		this.records = new Record[numRecords];
		deserialize(bytes);
	}

	public Bucket(Record[] recs)
	{
		records = recs;
		numRecords = records.length;
	}
	
	public byte[] serialize()
	{
		ByteBuffer buffer = ByteBuffer.allocate(2 * 24);
		for(Record r : records)
		{
			buffer.put(r.serialize());
		}
		return buffer.array();
	}
	
	public void deserialize(byte[] b)
	{
		ByteBuffer buffer = ByteBuffer.wrap(b);
		for(int i = 0; i < 2; i++)
		{
			byte[] bytes = new byte[24];
			buffer.get(bytes);
			records[i] = new Record(bytes);
		}
	}
	
	public Record getRecord(int i)
	{
		return records[i];
	}
	
	public Record[] getRecords()
	{
		return records;
	}
}
