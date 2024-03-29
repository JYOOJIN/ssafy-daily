package daily_0227;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_1149_RGB거리
 * 난이도: S1
 * 수행결과: 맞았습니다!!
 * 메모리: 12228 KB
 * 시간: 96 ms
 * 코드길이: 1311 B
 * 풀이 전략: Bottom-Up 방식. 제일 아래에서 한 색을 고르면, 그 윗칸에서 그 색을 제외한 색을 골랐을 때의 누적합 중 최소를 고른다.
 * 			DP를 통해 메모이제이션을 한다. 즉, 그 전 단계의 누적합을 구하기 위해서 재귀를 도는 것이다. 그 값을 구할 때까지.
 * 
 */

public class BJ_1149_RGB거리 {
	
	public static int[][] cost; //값 입력받을 배열
	public static int[][] dp; //누적합 저장할 배열
	public static int n; //집의 갯수
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		
		//0:R, 1:G, 2:B
		cost=new int[n+1][3]; 
		dp=new int[n+1][3]; 
		      
		//값 입력받기
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			cost[i][0]=Integer.parseInt(st.nextToken());
			cost[i][1]=Integer.parseInt(st.nextToken());
			cost[i][2]=Integer.parseInt(st.nextToken());
		}
	
		//제일 첫 집은 초기화
		dp[1][0]=cost[1][0];
		dp[1][1]=cost[1][1];
		dp[1][2]=cost[1][2];
		
		int r=paint(n,0); //Red를 골랐을 때
		int g=paint(n,1); //Green을 골랐을 때
		int b=paint(n,2); //Blue를 골랐을 때
		
		System.out.println(Math.min(r, Math.min(g, b))); //최소값
		
	}
	
	
	public static int paint(int n,int color) {
		
		if(dp[n][color]==0) { //아직 누적합이 기록이 되지 않았다면
			
			if(color==0) { 
				dp[n][0]=Math.min(paint(n-1,1), paint(n-1,2))+cost[n][0]; //그 전단계의 색과 겹칠 수 없다
				
			}else if(color==1) {
				dp[n][1]=Math.min(paint(n-1,0), paint(n-1,2))+cost[n][1]; //그 전단계의 색과 겹칠 수 없다
				
			}else {
				dp[n][2]=Math.min(paint(n-1,0), paint(n-1,1))+cost[n][2]; //그 전단계의 색과 겹칠 수 없다
			}
		}

		return dp[n][color];
		
	}

}
