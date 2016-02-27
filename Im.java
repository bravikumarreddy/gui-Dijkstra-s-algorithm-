
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Im {
	
	public static ArrayList<Integer> path(int[][] costmat,int start, int end, int n)
	{
		
		System.out.println("Enter the no of Vertices");
		
		
		int[][] back = new int[n][n];
		
		
		ArrayList<Integer> res = new ArrayList<Integer>();  
		
		Stack<Integer> vio = new Stack();
		int[] pq = new int[n];
		boolean [] visited = new boolean[n];
		int found = 0;
		
		
		
		
		for(int i=0;i<n;i++)
		{
			if(i==start)
			{
				pq[i]=0;
			}
			else{
				pq[i]=Integer.MAX_VALUE;
			}
			
		}
		
		back[0]=pq.clone();
	
		
		
		for(int i=0;i<n;i++)
		{
			int sv = min(pq,visited);
			
			if(sv==end)
			{
				found = i;
				vio.add(sv);
				break;
			}
			else{
				visited[sv] = true;
				vio.add(sv);
			}
			for(int j=0;j<n;j++)
			{
				if(visited[j]==false && costmat[sv][j] != 0)
				{
					pq[j] = Math.min(pq[j], costmat[sv][j]+pq[sv]);
				}
			}
			back[i+1] = pq.clone();
			
		
		}
		
		int track = found;
		
		System.out.println(Arrays.toString(pq)+" this is pq");
		int temp = vio.pop();
		res.add(temp);
		System.out.println(temp +"  end");
		while(!vio.isEmpty())
		{
			if(found==0)
			{
				break;
			}
			if(back[found][end] == back[found-1][end] )
			{
				vio.pop();
			}
			else
			{
				end=vio.pop();
				res.add(end);
				System.out.println(end +"  end");
			}
			found--;
			
			
		}
			
		return res;
		
		
		
	}
	
	
	static int min(int[] array,boolean []visited)
	{
		int min = 0;
		for(int i=0;i<array.length;i++)
		{
			if(visited[i] !=  true)
			{
				min = i;
			}
		}
		for(int i=0;i<array.length;i++)
		{
			if(array[i]<array[min] && visited[i] !=  true)
			{
				min = i;
			}
		}
		return min;
		
	}
	

}
