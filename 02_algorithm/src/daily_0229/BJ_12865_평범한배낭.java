package daily_0229;

import java.io.*;
import java.util.*;

public class BJ_12865_평범한배낭 {
	
	public static int n,k,max;
	public static int[][] arr;
	public static boolean[] visited;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		
		arr=new int[n][2];
		visited=new boolean[n];
		max=0;
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			arr[i][0]=Integer.parseInt(st.nextToken()); //물건의 무게
			arr[i][1]=Integer.parseInt(st.nextToken()); //물건의 가치	
		}
		
		set(0);
		System.out.println(max);
		
		
	}
	
	public static void set(int index) {
		
		if(index==n) {
			int wCnt=0;
			int vCnt=0;
			for(int i=0;i<n;i++) {
				if(visited[i]) {
					wCnt+=arr[i][0];
					vCnt+=arr[i][1];
					if(wCnt>k) return;
				}
			}
			
			if(vCnt>max) max=vCnt;
			
			return;

		}
		
		
		
		visited[index]=true;
		set(index+1);
		visited[index]=false;
		set(index+1);
		
		
		
	}

}
