package daily_0403;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_2631_줄세우기
 * 난이도: G4
 * 수행결과: 맞았습니다!!
 * 메모리: 11432 KB
 * 시간: 80 ms
 * 코드길이: 1051 B
 * 풀이 전략: 최장 증가 수열(LIS) 문제!!!
 * 
 * 문제를 읽어보면 어떤 아이를 어디로 옮겨야되냐에 초점을 맞추게 된다. 
 * 그러나 문제를 읽어보면 원하는 것은 옮겨야하는 아이의 "최소수"지, 이동하는 위치는 중요하지 않다!!
 * 예제에서 옮긴아이들을 모두 제해보면 남는 것은 3,5,6 -> 즉 최장 증가 수열이다.
 * 최장 증가 수열을 구해야 최소로 아이들을 옮길 수 있다.
 * 
 * <최장 증가 수열(LIS)>
 * DP로 풀 수 있다. 
 * 		
 * 현재 i 번째의 최장 증가 수열 길이를 구한다 한다.
 * i번째 앞의 index 들을 j라고 한다.
 * 			
 * arr[i]>arr[j] 일때(증가 부분 수열의 일부가 될 수 있다면)
 * SIL[i]=Math.max(SIL[i], SIL[j]+1);
 * 
 * 그 후 SIL에서 최댓값을 구하면 그것이 해당 수열의 최장 증가 부분 수열의 길이
 * 
 *  */

public class BJ_2631_줄세우기 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[] num=new int[n]; //아이들 순서 담는 배열
		int[] dp=new int[n]; //배열의 각 위치의 sil 담는 배열
		
		for(int i=0;i<n;i++) {
			num[i]=Integer.parseInt(br.readLine());
		}
		
		int sil=dp(num,dp,n);
		System.out.println(n-sil); //학생 수 중 최장 증가수열을 빼면 옮겨야 할 최소 학생수가 나온다. 
		
	}
	
	
	public static int dp(int[] arr,int[] dp,int n) {
		
		for(int i=0;i<n;i++) {
			dp[i]=1; //현재 index에서의 LIS값 초기화. 자기자신이므로 1
			for(int j=0;j<i;j++) { //자기 자신 전까지 탐색하면서
				if(arr[i]>arr[j]) { //이 것을 현재의 증가 수열에 추가할 수 있다면
					dp[i]=Math.max(1+dp[j], dp[i]); //최장을 비교
				}
			}
		}
		
		//구한 sil중 가장 큰값이 최종 최장 증가 수열의 길이이다. 
		int max=0;
		for(int i=0;i<n;i++) {
			if(max<dp[i]) max=dp[i];
		}
		
		
		return max;
		
	}

}
