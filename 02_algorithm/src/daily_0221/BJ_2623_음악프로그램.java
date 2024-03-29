package daily_0221;

import java.io.*;
import java.util.*;

public class BJ_2623_음악프로그램 {
	
	public static int n,m;
	public static ArrayList<Integer>[] list;
	public static int[] Indegree;
	static StringBuilder sb=new StringBuilder();

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		list=new ArrayList[n+1];
		Indegree=new int[n+1];
		
		for(int i=1;i<=n;i++) {
			list[i]=new ArrayList<Integer>();
		}
		
		for(int i=0;i<m;i++) {
			
			st=new StringTokenizer(br.readLine());
			int singer=Integer.parseInt(st.nextToken());
			int from=Integer.parseInt(st.nextToken());
			
			while(true) {	
				if(from==singer) break;
				int to=Integer.parseInt(st.nextToken());
				list[from].add(to);
				Indegree[to]+=1;
				from=to;
			}
		}
		
		for(int i=1;i<=n;i++) {
			System.out.print(Indegree[i]+" ");
		}
		
		bfs();
		
		System.out.println(sb);
		
	}
	
	public static void bfs() {
		
		Queue<Integer> q=new ArrayDeque<>();
		
		for(int i=1;i<n;i++) {
			if(Indegree[i]==0) { //진입차수가 0이라면
				q.add(i);
			}
		}
		
		while(!q.isEmpty()) {
			int tmp=q.poll();
			sb.append(tmp).append("\n");
			
			ArrayList<Integer> t=list[tmp];
			
			if(t==null) continue;
			
			for(int e:t) {
				Indegree[e]-=1;
				if(Indegree[e]==0) {
					q.add(e);
				}
			}
			
		}
	}

}
