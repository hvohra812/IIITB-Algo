import java.util.*;

/**/

class StronglyConnectedComponent
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int t,v,e,i,src,x,y;
		t = sc.nextInt();//No. of test case
		while(t-- > 0)
		{
			v = sc.nextInt();//No. of Vertex
			e = sc.nextInt();//No. of Edge
			ArrayList<LinkedList<Integer>> adjl = new ArrayList<LinkedList<Integer>>();//Adjanceny List
			for(i=0;i<=v;i++)
				adjl.add(new LinkedList<Integer>());
			for(i=0;i<e;i++)
			{
				x = sc.nextInt();
				y = sc.nextInt();
				adjl.get(x).add(y);
			}
			src = sc.nextInt();
			Stack<Integer> dfs_fin = finishTimeSortedList(adjl,src);
			ArrayList<LinkedList<Integer>> tran_adjl = transposeAdjList(adjl);		
			int scc = stronglyConnectedComponent(tran_adjl,dfs_fin);
			pr("scc = " + scc);
			pr("------------------------------------");
		}
	}

	static int stronglyConnectedComponent(ArrayList<LinkedList<Integer>> tran, Stack<Integer> stack)
	{
		int scc = 0,i,x,y,v,x1,y1;
		v = tran.size();
		boolean isVisited[] = new boolean[v];
		Stack<Integer> temp = new Stack<Integer>();
		LinkedList<Integer> ll;
		while(!stack.isEmpty())
		{
			x = stack.pop();
			if(!isVisited[x])
			{
				temp.push(x);
				while(!temp.isEmpty())
				{
					x1 = temp.pop();
					System.out.print(x1+" ");
					ll = tran.get(x1);
					Iterator<Integer> itr = ll.iterator();
					while(itr.hasNext())
					{
						y1 = itr.next();
						if(!isVisited[y1])
							temp.push(y1);
					}
					isVisited[x1] = true;
				}
				pr(" ");
				scc++;
			}
		}
		return scc;
	}

	static ArrayList<LinkedList<Integer>> transposeAdjList(ArrayList<LinkedList<Integer>> adjl)
	{
		int x,y,i,v=0;
		v = adjl.size();
		ArrayList<LinkedList<Integer>> tran = new ArrayList<LinkedList<Integer>>();
		for(i=0;i<v;i++)
			tran.add(new LinkedList<Integer>());
		
		LinkedList<Integer> ll;
		for(i=0;i<v;i++)
		{
			ll = adjl.get(i);
			Iterator<Integer> itr = ll.iterator();
			while(itr.hasNext())
			{
				y = itr.next();
				//pr("x = " + i + " y = " + y);
				tran.get(y).add(i);
			}
		}
		/*for(i=0;i<v;i++)
			pr(tran.get(i) + "");*/
		return tran;
	}
	//Giving the sorted order 
	static Stack<Integer> finishTimeSortedList(ArrayList<LinkedList<Integer>> adjl, int src)
	{
		int i,v,visitedCount = 0,x,y,time = 0;
		v = adjl.size(); // it will return (v+1)
		int dis_time[] = new int[v];//discovery time
		Map<Integer,Integer> fin_time = new HashMap<Integer,Integer>();//finish time
		for(i=1;i<v;i++)
			fin_time.put(i,-1);
		boolean isVisited[] = new boolean[v];
		Stack<Integer> stack = new Stack<Integer>();
		Stack<Integer> topo = new Stack<Integer>();
		LinkedList<Integer> ll;
		stack.push(src);
		while(visitedCount != (v-1))
		{
			while(!stack.isEmpty())
			{
				x = stack.pop();
				if(isVisited[x] == false)
				{
					stack.push(x);
					dis_time[x] = ++time;
					ll = adjl.get(x);
					Iterator<Integer> itr = ll.iterator();
					while(itr.hasNext())
					{
						y = itr.next();
						if(!isVisited[y])
							stack.push(y);
					}
					isVisited[x] = true;
					visitedCount++;
				}
				else
				{
					if(fin_time.get(x) == -1)
					{
						fin_time.put(x,++time);
						topo.push(x);
					}	
				}
			}
			if(visitedCount != v-1 && stack.isEmpty())
			{
				for(i=1;i<v;i++)
				{
					if(!isVisited[i])
					{
						stack.push(i);
						break;
					}
				}		
			}
		}
		//pr(topo+"");
		return topo;
	}

	//Printing function
	static void pr(String s)
	{
		System.out.println(s);
	}
}

/*Sample Input
2
11 13
1 2
2 3
3 1
2 4
4 5
5 6
6 4
7 6
7 8
8 9
9 10
10 7
10 11 
1
5 5
1 2
2 3
3 1
2 4
4 5
1
*/
