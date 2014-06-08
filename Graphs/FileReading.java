	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.util.ArrayList;
	import java.util.Scanner;

	public class FileReading {

		private final File filey;
		private ArrayList<Edge> listy;
		private boolean isDirected;
		private boolean isWeighted;
		private int numV;


		public FileReading(String file)
		{
			filey = new File(file);  
			listy = new ArrayList<Edge>();
		}

		public final void processLineByLine() throws FileNotFoundException 
		{
			Scanner scanner = new Scanner(new FileReader(filey));
			try 
			{
				if( scanner.hasNextLine() )
					processLine1( scanner.nextLine() );
				while ( scanner.hasNextLine() )
					processLine( scanner.nextLine() );
			}
			finally
			{
				scanner.close();
			}
		}
		public ArrayList<Edge> getListy() 
		{
			return listy;
		}

		protected void processLine(String line)
		{
			Scanner scanner = new Scanner(line);
			scanner.useDelimiter(" ");
			if ( scanner.hasNext() )
			{
				int v1 = Integer.parseInt( scanner.next());
				int v2 = Integer.parseInt( scanner.next());
				double weight = 0;
				if(isWeighted)
					weight = Integer.parseInt( scanner.next());
				if(!isWeighted)
					weight = -1;
				listy.add(new Edge(v1, v2, isDirected, weight));
				 
			}

		}
		protected void processLine1(String line)
		{
			Scanner scanner = new Scanner(line);
			scanner.useDelimiter(" ");
			if ( scanner.hasNext() )
			{
				numV = Integer.parseInt( scanner.next() );
				String check = scanner.next();
				if (check.equals("U"))
					isDirected = false;
				if (check.equals("D"))
					isDirected = true;
				check = scanner.next();
				if (check.equals("W"))
					isWeighted = true;
				if (check.equals("N"))
					isWeighted = false;
			}
		}

		public boolean isDirected() 
		{
			return isDirected;
		}

		public void setDirected(boolean isDirected)
		{
			this.isDirected = isDirected;
		}

		public boolean isWeighted()
		{
			return isWeighted;
		}

		public void setWeighted(boolean isWeighted)
		{
			this.isWeighted = isWeighted;
		}

		public int getNumV() 
		{
			return numV;
		}

		public void setNumV(int numV)  
		{
			this.numV = numV;
		}  
	}

