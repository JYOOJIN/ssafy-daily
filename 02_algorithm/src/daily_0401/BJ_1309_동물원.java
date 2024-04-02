package daily_0401;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_1309_동물원
 * 난이도: S1
 * 수행결과: 맞았습니다!!
 * 메모리: 15780 KB
 * 시간: 96 ms
 * 코드길이: 783 B
 * 풀이 전략: dp
 * 			n의 범위가 10만 까지이기 때문에 완전탐색 시 시간초과가 난다
 * 			dp를 이용하여 점화식을 도출 해야한다.
 * 			또한 크기가 매우 크므로 계산 마다 mod를 나눠 나머지를 구해주어야 한다.
 *  
 *  */

public class BJ_1309_동물원 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int mod=9901;
		//dp[n][0]: n번째 줄에 한마리도 넣지 않는 경우의 수
		//dp[n][1]: n번째 줄의 첫번째칸에 넣는 경우의 수
		//dp[n][2]: n번째 줄의 두번째칸에 넣는 경우의 수
		
		long[][] dp=new long[n][3];
		dp[0][0]=dp[0][1]=dp[0][2]=1;
		
		for(int i=1;i<n;i++) {
			//점화식
			dp[i][0]=(dp[i-1][0]+dp[i-1][1]+dp[i-1][2])%mod; //현재 줄에 아무것도 넣지 않는다면 그 전줄에 영향을 받지 않는다.
			dp[i][1]=(dp[i-1][0]+dp[i-1][2])%mod; //현재 줄의 첫번째 칸에 넣는다면 그 전줄에 아무것도 넣지 않거나, 두번째 칸에 넣는 경우만 가능하다.
			dp[i][2]=(dp[i-1][0]+dp[i-1][1])%mod; //현재 줄의 두번째 칸에 넣는다면 그 전줄에 아무것도 넣지 않거나, 첫번째 칸에 넣는 경우만 가능하다.
		}
		
		long total=(dp[n-1][0]+dp[n-1][1]+dp[n-1][2])%mod; //최종 사자를 배치하는 경우의 수를 모두 더하고 mod로 나눠 나머지를 구한다.
		System.out.println(total);
		
		
		
	}

}
