package daily_0404;

import java.io.*;
import java.util.*;

public class SW_5643_키순서_DFS2 {

	static int n,m,adj[][],cnt,radj[][];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			n=Integer.parseInt(br.readLine().trim()); //(2<= N <= 500)
			m=Integer.parseInt(br.readLine().trim()); //(0<= M <= N*(N-1)/2): 단방향 그래프에서 최대
			
			//행을 고정하고 열을 들여다보는 행렬로 만들기 위해 2개 선언 
			
			adj=new int[n+1][n+1]; //자신보다 큰 관계 표현
			radj=new int[n+1][n+1]; //자신보다 작은 관계 표현
			StringTokenizer st=null;
			for(int i=0;i<m;i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				radj[b][a]=adj[a][b]=1; //a<b
				
			}
			
			int ans=0;
			//정점마다 확인
			for(int i=1;i<=n;i++) {
				cnt=0; //초기화
				dfs(i, new boolean[n+1],adj);
				dfs(i, new boolean[n+1],radj);
				
				if(cnt==n-1) {
					ans++; 
				}
			}
			System.out.println("#"+t+" "+ans);
			
			
		}
	}
	
	public static void dfs(int cur,boolean[] visited,int[][] adj) {
		visited[cur]=true; 
		
		for(int i=1;i<=n;i++) {
			if(adj[cur][i]==1 && !visited[i]) { 
				cnt++; 
				dfs(i,visited,adj);
			}
		}
	}
	


}
