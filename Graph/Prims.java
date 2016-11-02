import java.util.*;
//Prims Algorithm
//Problem Description : https://www.hackerrank.com/challenges/primsmstsub

//NodeDis : Represent edge
class NodeDis
{
	int u, v, d;
	NodeDis(int u, int v, int d)
	{
		this.u = u;
		this.v = v;
		this.d = d;
	}
	public String toString()
	{
		return u + " " + v + " " + d;
	}
}

//Comparator for comparing edges
class NodeDisCom implements Comparator<NodeDis>
{
    public int compare(NodeDis n1, NodeDis n2)
    {
        if(n1.d < n2.d)
            return -1;
        else if(n1.d == n2.d)
            return 0;	
        else 
            return 1;
    }
}

class Prims
{
	public static void main(String arg[])
	{
		Scanner sc = new Scanner(System.in);
		int v = sc.nextInt(),i,j,x,y,d,s,len;
		int e = sc.nextInt(),countVisited=0,minDis=0;
		//store the detail of whether node is visited or not
		boolean[] isVisited = new boolean[v+1];
		//Store the parent node
		int[] parent = new int[v+1];
		ArrayList<LinkedList<NodeDis>> adjll = new ArrayList<LinkedList<NodeDis>>();
		for(i=0; i<=v ;i++)
			adjll.add(i,new LinkedList<NodeDis>());
		for(i=0;i<e;i++)	
		{
			x = sc.nextInt();
			y = sc.nextInt();
			d = sc.nextInt();
			NodeDis nd1 = new NodeDis(x,y,d);
			NodeDis nd2 = new NodeDis(y,x,d);
			adjll.get(x).add(nd1);
			adjll.get(y).add(nd2);
		}
		//Testing: Checking the input...
		/*for(i=1;i<=v;i++)
		{
			x = adjll.get(i).size();
			for(j = 0; j < x;j++)
			{
				System.out.println(i + " " + adjll.get(i).get(j));
			}
		}*/
		//reading the source vertex 
		s = sc.nextInt();
        Comparator<NodeDis> com = new NodeDisCom();
		PriorityQueue<NodeDis> pq = new PriorityQueue<NodeDis>(e,com);
		//Temporary LinkedList, NodeDis
		LinkedList<NodeDis> ll_temp;
		NodeDis nd_temp;
		while(countVisited != v)
		{
			if(isVisited[s] == false)
			{
				isVisited[s] = true;
				countVisited++;
				ll_temp = adjll.get(s);
				len = ll_temp.size();
				for(i=0;i<len;i++)
					pq.add(ll_temp.get(i));
			}
			else
			{
				nd_temp = pq.poll();
				if(nd_temp != null && isVisited[nd_temp.v] == false)
				{
					//pr("Node Removed : " + nd_temp);
					minDis += nd_temp.d;
					s = nd_temp.v;
					//pr("s = " + s);
				}
			}
			/*Iterator<NodeDis> itr = pq.iterator();
			while(itr.hasNext())
			{
				pr(countVisited + " " + itr.next());
			}
			*/
		}
		if(!pq.isEmpty())
		{
			nd_temp = pq.poll();
			if(isVisited[nd_temp.v] == false)
			{
				pr("Node Removed : " + nd_temp);
				minDis += nd_temp.d;
				//s = nd_temp.v;
				//pr("s = " + s);
			}
		}
		pr(minDis+"");
	}
  static void pr(String s)
	{
		System.out.println(s);
	}
}
