import java.io.*;
import java.util.*;

import javax.security.auth.Refreshable;

public class BJ_5567_결혼식 {

	static int n,m;
	static boolean[] visited;
	static ArrayList<Integer>[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		m=Integer.parseInt(br.readLine());

		visited=new boolean[n+1]; //1부터 n까지
		list=new ArrayList[n+1];
		
		for(int i=1;i<=n;i++) { //1부터 n까지
			list[i]=new ArrayList<>();
		}
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			
			list[from].add(to);
			list[to].add(from);
		}
		
		visited[1]=true;
		for(int i=0;i<list[1].size();i++) {
			int friend=list[1].get(i);
			visited[friend]=true;
			for(int j=0;j<list[friend].size();j++) {
				visited[list[friend].get(j)]=true;
			}
		}
		
		int cnt=0;
		for(int i=1;i<=n;i++) {
			if(visited[i]) {
				cnt+=1;
			}
		}
		cnt-=1;
		System.out.println(cnt);
				
	}

}
