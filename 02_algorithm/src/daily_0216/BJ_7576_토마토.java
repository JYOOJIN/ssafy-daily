package daily_0216;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_7576_토마토
 * 난이도: G5
 * 수행결과: 맞았습니다!!
 * 메모리:  KB
 * 시간:  ms
 * 코드길이:  B
 * 풀이 전략: <최소날짜 -> bfs>: 재귀 쓰면 안된다. queue 사용. 
 * 			
 */

public class BJ_7576_토마토 {
	public static int n,m;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		m=Integer.parseInt(st.nextToken());
		n=Integer.parseInt(st.nextToken());
		
		int[][] tom=new int[n][m]; 
		int[][] visited=new int[n][m];
		
		int flag=1;
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				tom[i][j]=Integer.parseInt(st.nextToken());
				flag*=tom[i][j];
			}
		}
		
		if(flag!=0) System.out.println(0);
		else bfs(tom,visited);
	}
	
	public static void bfs(int[][] tom,int[][] visited) {
		
		int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};
		Arrays.fill(visited, -1);
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(tom[i][j]==1 && visited[i][j]<0) {
					
				}
			}
		}
		
		
		
		
	}

}
