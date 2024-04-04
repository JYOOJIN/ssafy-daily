package daily_0404;

import java.io.*;
import java.util.*;


//잘 안나온다!!!
public class SW_5643_키순서_DFS_Memo {

	static int n,m,adj[][],cnt;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			n=Integer.parseInt(br.readLine().trim());
			m=Integer.parseInt(br.readLine().trim());
			
			adj=new int[n+1][n+1];
			
			for(int i=1;i<=n;i++) {
				adj[i][0]=-1; //탐색되지 않았음을 의미!!
			}
			
			
			StringTokenizer st=null;
			for(int i=0;i<m;i++) {
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());
				adj[a][b]=1; //a<b
				
			}
			
			//정점마다 확인
			for(int i=1;i<=n;i++) {
				if(adj[i][0]==-1) { //탐색이 안 된 정점에서 탐색 시작. 이 문제에서는 1번, 3번
					dfs(i); //나보다 큰애 탐색
				}
			}
			
			//나보다 작은 애 카운트
			for(int i=1;i<=n;i++) {
				for(int j=1;j<=n;j++) {
					adj[0][j]+=adj[i][j];
				}
			}
			
			int ans=0;
			for(int i=1;i<=n;i++) {
				if(adj[i][0]+adj[0][i]==n-1) ans++;
			}
			
			System.out.println("#"+t+" "+ans);
			
			
		}
	}
	
	public static void dfs(int cur) {
		
		for(int i=1;i<=n;i++) {

			if(adj[cur][i]==1) { //나보다 큰애
				if(adj[i][0]==-1) { //탐색이 안된 학생이면 탐색하기
					dfs(i);
				}
				//i 학생보다 큰 학생이 있다면 그 관계들을 자신과의 관계에 반영
				if(adj[i][0]>0) {
					for(int j=1;j<=n;j++) { //간접적으로 아는 애들 탐색
						if(adj[i][j]==1) { //i보다 큰 학생 j를 나(cur)의 관계로 표현 
							adj[cur][j]=1;
						}
					}
				}
			}
		}
		
		int cnt=0;
		for(int i=1;i<=cnt;i++) {
			cnt+=adj[cur][i]; //1인 갯수 센다 -> 자신보다 큰 학생수 카운팅(0은 덧셈에 영향 X)
		}
		
		adj[cur][0]=cnt; 
		
	}
	
}
