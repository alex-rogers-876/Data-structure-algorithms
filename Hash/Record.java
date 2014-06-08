
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Record 
{
	private int id;
	private String name;
	

	public Record()
	{
		this.id = 0;
		this.name = "                    ";
	}
	
	public Record(int id, String name)
	{
		this.id = id;
		this.name = name;
		if(name.length() < 20)
			for(int i = name.length(); i < 20; i++) name += " ";
	}
	
	public Record(byte[] bytes)
	{
		deserialize(bytes);
	}
	
	public byte[] serialize()
	{
		ByteBuffer buffer = ByteBuffer.allocate(24);
		buffer.putInt(this.id);
		byte[] nameBytes = name.getBytes(Charset.forName("US-ASCII"));
		buffer.put(nameBytes);
		return buffer.array();
	}
	
	public void deserialize(byte[] b)
	{
		ByteBuffer buffer = ByteBuffer.wrap(b);
		buffer.rewind();
		this.id = buffer.getInt();
		byte[] nameBytes = new byte[20];
		buffer.get(nameBytes);
		this.name = new String(nameBytes).trim();
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
}
