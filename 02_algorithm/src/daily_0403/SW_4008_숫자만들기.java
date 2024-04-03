package daily_0403;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_4008_숫자만들기
 * 난이도: 모의 SW 역량 테스트
 * 수행결과: Pass
 * 메모리: 27908 KB
 * 시간: 162 ms
 * 코드길이: 1582 B
 * 풀이 전략: 처음에 순열 그자체를 만들어서 풀려고 했지만 시간초과. 숫자와 연산자 자리는 고정이므로, 연산자만 순서를 구해주면 된다.
 * 			그 연산자의 개수가 남아 있다면 순열에 넣고 아니라면 다음으로 넘어가는 형식. 
 * 
 * 주의 사항: 1) 전역변수 선언 시 지역에서는 절대 선언하면 안된다!!!!!!!!
 * 		   2) min값과 max 값이 같은 경우도 있을 것. 둘다 구하고 return을 마지막에 해주어야 한다. 
 * 
 *  */

public class SW_4008_숫자만들기 {
	
	public static int n,min,max;
	public static int[] num,op,arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			n=Integer.parseInt(br.readLine());
			StringTokenizer st;
			
			op=new int[4]; //+,-,*,/ 의 갯수 담은 배열 생성
			num=new int[n]; //숫자의 개수만큼 배열 생성
			arr=new int[n-1]; //연산자 배열 생성
			
			max=Integer.MIN_VALUE; //최댓값 초기화
			min=Integer.MAX_VALUE; //최솟값 초기화
			
			//연산자 개수 입력받기
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<4;i++) {
				op[i]=Integer.parseInt(st.nextToken());
			}
			
			//숫자 배열 입력받기
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				num[i]=Integer.parseInt(st.nextToken());
			}
			
			//재귀 실행
			permutation(0);
			//형식에 맞춰 출력
			System.out.println("#"+t+" "+(max-min));

		}
	}
	
	//왼쪽에서 오른쪽으로 차례대로 계산
	//if~else if 보다는 switch~case 가 더 빠르다.
	public static int calc() {
		 
		int prev=num[0]; //지금까지의 계산 값을 담은 변수.맨 처음엔 첫 숫자로 초기화.
		
		for(int i=0;i<n-1;i++) {
			switch(arr[i]) {
			case 0:
				prev+=num[i+1];
				break;
			case 1:
				prev-=num[i+1];
				break;
			case 2:
				prev*=num[i+1];
				break;
			case 3:
				prev/=num[i+1];
				break;
			}
		}
		
		return prev; //계산 결과값 반환
		
	}
	
	//중복순열(중복o,순서o)
	public static void permutation(int depth) {
		
		if(depth==n-1) { //자릿수
			int result=calc(); //해당 연산자 순열로 계산한 결과
			
			min=Math.min(min, result); //최솟값 찾기
			max=Math.max(max, result); //최댓값 찾기
			
			return;
		}
		
		for(int i=0;i<4;i++) { //중복순열
			
			if(op[i]>0) { //연산자가 존재한다면
				op[i]--; //연산자 사용
				arr[depth]=i; //수열에 해당 연산자 넣기
				permutation(depth+1); //다음 연산자 찾으러 가기
				op[i]++; //계산 마치고 나올때 해당  연산자 다시 복구
			}
		}
	
	}

}
