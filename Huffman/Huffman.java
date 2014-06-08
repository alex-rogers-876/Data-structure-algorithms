import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;


abstract class Tree implements Comparable<Tree> 
{
    public final int frequency;
    public Tree(int freq) { frequency = freq; }

    public int compareTo(Tree tree) 
    {
        return frequency - tree.frequency;
    }
}

class Leaf extends Tree 
{
    public final char value; 
    public Leaf(int freq, char val) 
    {
        super(freq);
        value = val;
    }
}

class Node extends Tree 
{
    public final Tree left, right; 
    public Node(Tree l, Tree r) 
    {
        super(l.frequency + r.frequency);
        left = l;
        right = r;
    }
}

public class Huffman 
{
	public static int sum = 0;
	public static int sum2 = 0;

    public static Tree buildTree(ArrayList<Integer> freqsChar, char[] test2)
    {
        PriorityQueue<Tree> trees = new PriorityQueue<Tree>();
        Integer[] freqsCharArray = freqsChar.toArray(new Integer[0]);
        
        for (int i = 0; i < freqsCharArray.length; i++)
            if (freqsCharArray[i] > 0)
                trees.offer(new Leaf(freqsCharArray[i], test2[i]));
        
        while (trees.size() > 1) 
        {
            Tree a = trees.poll();
            Tree b = trees.poll();
            trees.offer(new Node(a, b));
        }
        return trees.poll();
    }

    public static void printCodes(Tree tree, StringBuffer prefix, ArrayList<Integer> freqsChar) 
    {
    	
        assert tree != null;
        if (tree instanceof Leaf) 
        {
            Leaf leaf = (Leaf)tree;
            System.out.println(leaf.value + "\t" + leaf.frequency + "\t" + prefix);
            sum2 += leaf.frequency  * prefix.length();
            sum += leaf.frequency;
            
        } 
        else if (tree instanceof Node) 
        {
            Node node = (Node)tree;
            // go left
            prefix.append('1');
            printCodes(node.left, prefix,freqsChar);
            prefix.deleteCharAt(prefix.length()-1);

            // go right
            prefix.append('0');
            printCodes(node.right, prefix,freqsChar);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }
    public static boolean isNumeric(String input) 
    {
    	  try 
    	  {
    	    Integer.parseInt(input);
    	    return true;
    	  }
    	  catch (NumberFormatException e) 
    	  {
    	    return false;
    	  }
    }

    public static void main(String[] args) {
        double bitTop = 8.0;
        double bitBottom = 8.0;
        String str = ""; 
        ArrayList<Integer> freqsChar = new ArrayList<Integer>();
        Scanner scanner;
        
		try {
			scanner = new Scanner(new File("C:\\Users\\Alex\\Documents\\huffmandata.txt"));
			scanner.useDelimiter(":|\\n|\\r");

			while(scanner.hasNext()) 
			{
				String test = scanner.next();
				if(isNumeric(test) == true)
				{
					int num1 = Integer.valueOf(test);
					//sum += num1;
					freqsChar.add(num1);
				}
				else
				{
					str = str + test;
				}   	
			}
		}catch (IOException e) 
		{
			System.err.println("Caught IOException: " + e.getMessage());
		}
		//count = str.length();
		char[] test2 = str.toCharArray();
        Tree tree = buildTree(freqsChar,test2);

        System.out.println("char\tfrequency\tHuffman Code");
        printCodes(tree, new StringBuffer(), freqsChar);
        double avg = sum2/(sum*1.0);

        
        System.out.println("Lavg = " + String.format("%.2g",avg));
        bitTop -= avg;
   
        System.out.println("Compression Rate = " + String.format("%.2g",bitTop/bitBottom ));
        
    }
}