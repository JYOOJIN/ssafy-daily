package daily_0403;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_14888_연산자끼워넣기
 * 난이도: S1
 * 수행결과: 맞았습니다!!
 * 메모리: 12760 KB
 * 시간: 92 ms
 * 코드길이: 1609 B
 * 풀이 전략: swea의 숫자만들기와 똑같은 문제.
 * 		   중복 순열을 만든다. 첫번째 연산자부터 개수만큼 연산자의 어디에 넣을지를 정하는 방식.
 * 
 *  */

public class BJ_14888_연산자끼워넣기 {

	public static int n,min,max;
	public static int[] num,op,arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		n=Integer.parseInt(br.readLine());
		
		op=new int[4]; //+,-,*,/ 연산자 개수 담는 배열
		num=new int[n]; //숫자 담는 배열
		arr=new int[n-1]; //연산자 중복순열 배열
		
		min=Integer.MAX_VALUE; //최솟값 초기화
		max=Integer.MIN_VALUE; //최댓값 초기화
		
		//숫자 배열 입력받기
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			num[i]=Integer.parseInt(st.nextToken());
		}
		
		//각 연산자의 개수 입력받기
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			op[i]=Integer.parseInt(st.nextToken());
		}
		
		permutation(0);
		
		System.out.println(max);
		System.out.println(min);
	}
	
	//
	public static int calc() {
		int prev=num[0];
		
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
		
		return prev;

	}
	
	public static void permutation(int depth) {
		
		if(depth==n-1) { //연산자 자릿수 모두 채웠다면
			
			int result=calc();
			
			min=Math.min(min, result);
			max=Math.max(max, result);

			return;
		}
		
		
		for(int i=0;i<4;i++) {
			if(op[i]>0) {
				op[i]--;
				arr[depth]=i;
				permutation(depth+1);
				op[i]++;
			}
		}
		
		
	}

}
