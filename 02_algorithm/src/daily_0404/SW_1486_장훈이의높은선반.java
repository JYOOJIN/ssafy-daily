package daily_0404;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_1486_장훈이의높은선반
 * 난이도: D4
 * 수행결과: Pass
 * 메모리: 26624 KB
 * 시간: 182 ms
 * 코드길이: 1038 B
 * 풀이 전략: 부분집합. 각 부분집합의 합을 구해 선반의 높이보다 높으면서 최소인 값을 구한다.
 * 
 *  */

public class SW_1486_장훈이의높은선반 {
	
	static int n,b,min;
	static int[] h;
	static boolean[] arr;

	public static void main(String[] args) throws IOException{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken()); //직원의 수
			b=Integer.parseInt(st.nextToken()); //높이
			
			h=new int[n];
			arr=new boolean[n];
			
			min=Integer.MAX_VALUE;
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				h[i]=Integer.parseInt(st.nextToken());
			}
			
			dfs(0);
			
			System.out.println("#"+t+" "+(min-b));
		}
		
		
		
		
	}
	
	public static void dfs(int depth) {
		
		if(depth==n) {
			int total=0;
			for(int i=0;i<n;i++) {
				if(arr[i]) total+=h[i];
			}
			if(total>=b && total<min) min=total; 
			
			return;
		}
		
		arr[depth]=true;
		dfs(depth+1);
		arr[depth]=false;
		dfs(depth+1);
		
		
	}

}
