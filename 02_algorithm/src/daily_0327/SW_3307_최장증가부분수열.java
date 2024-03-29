package daily_0327;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_3307_최장 증가 부분 수열 - DP/Binary Search
 * 난이도: D3
 * 수행결과: Pass
 * 메모리: 32696 KB /29,892 KB
 * 시간: 143 ms/140 ms
 * 코드길이: 964 B
 * 풀이 전략: 
 * 
 * 최장 증가 부분 수열 - DP
 * 
 * 		현재 i 번째의 최장 증가 수열 길이를 구한다 한다.
 * 		i번째 앞의 index 들을 j라고 한다.
 * 			
 * 		arr[i]>arr[j] 일때(증가 부분 수열의 일부가 될 수 있다면)
 * 		SIL[i]=Math.max(SIL[i], SIL[j]+1);
 * 
 * 		그 후 SIL에서 최댓값을 구하면 그것이 해당 수열의 최장 증가 부분 수열의 길이
 * 		=> 그러나 시간 복잡도가 O(N^2)이므로 비효율적.
 * ---------------------------------------------------------------------
 * 최장 증가 부분 수열 - Binary Search
 * 
 * 		배열의 각 index 에서의 최장 길이를 구하는 것이 아니라 
 * 		List를 만들어 그 안에 최장 증가 부분 수열을 만들어 최장 길이를 구한다.
 * 		[Binary Search] 
 * 		: start와 end를 설정하고 middel index를 구해 값을 비교해 범위를 줄여나간다.
 * 		이 binary search를 이용하여 최장 증가 부분 수열을 만든다.
 * 		
 * 		1) 현재 arr[i]값이 list의 마지막 원소보다 크다면 add (증가 만족)
 * 		2) 현재 arr[i]값이 list의 마지막 원소보다 작다면, arr[i]값이 들어갈 수 있는 위치 
 * 		   binarySearch를 이용해 탐색. 해당 위치를 찾은 후 arr[i]로 수정한다. (set)
 * 		=> 시간 복잡도 O(NlogN) 이므로 더 효율적이다. 이진탐색 개념, 수행 방법 잘 알아두자.
 * 
 */

public class SW_3307_최장증가부분수열 {

	public static void main(String[] args) throws IOException{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc=Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			int n=Integer.parseInt(br.readLine());
			
			int[] arr=new int[n]; //주어진 순열 담을 배열
			int[] dp=new int[n]; //최장 증가 부분 수열 길이 담을 배열
			List<Integer> bs=new ArrayList<>(); //최장 증가 부분 수열 담을 리스트
			
			
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			
			//int max=LIS_DP(arr,dp,n); 
			int size=LIS_BS(arr,bs,n);
			
			//System.out.println("#"+t+" "+max);
			System.out.println("#"+t+" "+size);
			
		}
	}
	
	//dp를 이용
	public static int LIS_DP(int[] arr,int[] dp,int n) {
		
		for(int i=0;i<n;i++) {
			dp[i]=1; //현재 index에서의 LIS 값 초기화. 자기자신만 있으므로 1
			for(int j=0;j<i;j++) { //자기 앞의 index들의 LIS를 보면서
				if(arr[i]>arr[j]) {  //내가 저 증가 부분 수열에 들어갈 수 있다면
					dp[i]=Math.max(1+dp[j], dp[i]); //내가 들어간 LIS와 현재 LIS를 비교해서 최댓값으로 설정
				}
			}

		}
		
		int max=0;
		for(int i=0;i<n;i++) {
			if(dp[i]>max) max=dp[i]; //구한 LIS들 중 최대가 이 수열의 최장 증가 부분 수열의 길이이다
		}
		
		return max;
	}
	
	
	//이진탐색
	public static int binarySearch(int[] arr,List<Integer> bs,int start,int end,int x) {
		
		while(start<end) {
			int mid=(start+end)/2;
			if(x>bs.get(mid)) start=mid+1;
			else end=mid;
		}

		return end; //x가 들어갈 수 있는 index 반환
	}
	
	//이진탐색을 이용
	public static int LIS_BS(int[] arr,List<Integer> bs,int n) {
		
		bs.add(arr[0]); //최장 증가 부분 수열의 시작 넣어주기
		
		for(int i=1;i<n;i++) {
			int start=0; 
			int end=bs.size()-1;
			
			if(bs.get(bs.size()-1)<arr[i]) bs.add(arr[i]); //최장 증가 부분 수열의 마지막 원소보다 크다면 add(증가 만족)
			else {
				int index=binarySearch(arr, bs, start, end, arr[i]); //현재 arr[i]가 들어갈 수 있는 index를 구한다
				bs.set(index, arr[i]); //해당 index의 값 update
			}
	
		}
		 
		return bs.size(); //최종 최장 증가 부분 수열의 길이 반환
		
	}
	
	
	
}
