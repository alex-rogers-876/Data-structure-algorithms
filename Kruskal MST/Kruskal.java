import java.io.*;
import java.util.*;

public class Kruskal {
  private final int NODES = 12;
  private HashSet nodes[];              
  private TreeSet allEdges;              
  private Vector allNewEdges;            

  public static void main(String args[]) 
  {
    if (args.length != 1) 
    {
      System.out.println("You need to run with file");
      System.exit(0);
    }
    Kruskal krusky = new Kruskal();
    krusky.transformGraph(args[0]);
    krusky.kruskalAlgo();
    krusky.printEdges();
  }

  Kruskal() {
    // Constructor
    nodes = new HashSet[NODES];      // Create array for components
    allEdges = new TreeSet(new Edge());  // Create empty priority queue
    allNewEdges = new Vector(NODES); // Create vector for MST edges
  }

  private void transformGraph(String fileName) {
    try {
      FileReader file = new FileReader(fileName);
      BufferedReader buff = new BufferedReader(file);
      buff.readLine();
      String line = buff.readLine();
      while (line != null) {
        StringTokenizer tok = new StringTokenizer(line, " ");
        int from = Integer.valueOf(tok.nextToken()).intValue();
        int to   = Integer.valueOf(tok.nextToken()).intValue();
        int cost = Integer.valueOf(tok.nextToken()).intValue();

        allEdges.add(new Edge(from, to, cost));  // Update priority queue
        if (nodes[from] == null) {
          // Create set of connect components [singleton] for this node
          nodes[from] = new HashSet(2*NODES);
          nodes[from].add(new Integer(from));
        }

        if (nodes[to] == null) {
          // Create set of connect components [singleton] for this node
          nodes[to] = new HashSet(2*NODES);
          nodes[to].add(new Integer(to));
        }

        line = buff.readLine();
      }
      buff.close();
    } catch (IOException e) {
      //
    }
  }

  private void kruskalAlgo() {
    int size = allEdges.size();
    for (int i=0; i<size; i++) {
      Edge curEdge = (Edge) allEdges.first();
      if (allEdges.remove(curEdge)) {
        // successful removal from priority queue: allEdges

        if (nodesAreInDifferentSets(curEdge.zero, curEdge.one)) {
          HashSet src, dst;
          int dstHashSetIndex;
          
          if (nodes[curEdge.zero].size() > nodes[curEdge.one].size()) 
          {
            src = nodes[curEdge.one];
            dst = nodes[dstHashSetIndex = curEdge.zero];
          }
          else
          {
            src = nodes[curEdge.zero];
            dst = nodes[dstHashSetIndex = curEdge.one];
          }
          
          Object srcArray[] = src.toArray();
          int transferSize = srcArray.length;
          for (int j=0; j<transferSize; j++) 
          {
            if (src.remove(srcArray[j])) 
            {
              dst.add(srcArray[j]);
              nodes[((Integer) srcArray[j]).intValue()] = nodes[dstHashSetIndex];
            } 
            else 
            {
              System.out.println("Error: set union");
              System.exit(1);
            }
          }
          allNewEdges.add(curEdge);
        } 
      }
      else 
      {

        System.out.println("TreeSet should have contained this element!!");
        System.exit(1);
      }
    }
  }

  private boolean nodesAreInDifferentSets(int a, int b) 
  {
    return(!nodes[a].equals(nodes[b]));
  }

  private void printEdges() 
  {
	int sum = 0;
    while (!allNewEdges.isEmpty()) 
    {
      Edge e = (Edge) allNewEdges.firstElement();
      System.out.println("Nodes: (" + e.zero + ", " + e.one +
        ") cost: " + e.cost);
      sum = e.cost + sum;
      allNewEdges.remove(e);
    }
    System.out.println("Total edge Sum: "+sum);
  }

  class Edge implements Comparator 
  {
    public int zero, one, cost;
    public Edge() 
    {

    }
    public Edge(int a, int s, int d) 
    {
      zero = a; one = s; cost =d;
    }
    public int compare(Object o1, Object o2) 
    {
      int cost1 = ((Edge) o1).cost;
      int cost2 = ((Edge) o2).cost;
      int zero1 = ((Edge) o1).zero;
      int zero2 = ((Edge) o2).zero;
      int one1   = ((Edge) o1).one;
      int one2   = ((Edge) o2).one;

      if (cost1<cost2)
        return(-1);
      else if (cost1 == cost2 && zero1 == zero2 && one1 == one2)
        return(0);
      else if (cost1 == cost2)
        return(-1);
      else if (cost1 > cost2)
        return(1); 
      else
        return(0);
    }
    public boolean equals(Object obj) 
    {
      Edge e = (Edge) obj;
      return (cost == e.cost && zero == e.zero && one == e.one);
    }
  }

}