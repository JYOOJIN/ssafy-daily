package daily_0221;

import java.io.*;
import java.util.*;

public class BJ_13023_ABCDE {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken()); //사람의 수
		int m=Integer.parseInt(st.nextToken()); //친구 관계의 수
		
		int[][] rel=new int[n][n];
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			rel[a][b]=rel[b][a]=1; //양방향 그래프
		}
		
		
		

	}
	
	public static void dfs() {
		
		
		
	}

}
