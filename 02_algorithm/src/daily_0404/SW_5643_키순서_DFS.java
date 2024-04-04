package daily_0404;

import java.io.*;
import java.util.*;

public class SW_5643_키순서_DFS {

	static int n,m,adj[][],cnt;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			n=Integer.parseInt(br.readLine().trim());
			m=Integer.parseInt(br.readLine().trim());
			
			adj=new int[n+1][n+1];
			StringTokenizer st=null;
			for(int i=0;i<m;i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				adj[a][b]=1; //a<b
				
			}
			
			int ans=0;
			//정점마다 확인
			for(int i=1;i<=n;i++) {
				cnt=0; //초기화
				gtDFS(i, new boolean[n+1]);
				ltDFS(i, new boolean[n+1]);
				
				if(cnt==n-1) {
					ans++; 
				}
			}
			System.out.println("#"+t+" "+ans);
			
			
		}
	}
	
	//나보다 큰 학생 따라감
	public static void gtDFS(int cur,boolean[] visited) {
		visited[cur]=true; //현재 정점 탐색 완료
		
		for(int i=1;i<=n;i++) {
			if(adj[cur][i]==1 && !visited[i]) { //나보다 큰 학생이 아직 탐색되지 않았다면
				cnt++; //나보다 큰게 몇명인지 확인
				gtDFS(i,visited);
			}
		}
	}
	
	//나보다 작은 학생 따라감
	public static void ltDFS(int cur,boolean[] visited) {
		visited[cur]=true; //현재 정점 탐색 완료
		
		for(int i=1;i<=n;i++) {
			if(adj[i][cur]==1 && !visited[i]) { //나보다 작은 학생이 아직 탐색되지 않았다면
				cnt++; //나보다 작은게 몇명인지 확인
				ltDFS(i,visited);
			}
		}
	}

}
