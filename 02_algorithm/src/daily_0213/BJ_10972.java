package daily_0213;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_10972_다음 순열
 * 난이도: S3
 * 수행결과: 맞았습니다!
 * 메모리: 15848 KB
 * 시간: 276 ms
 * 코드길이: 1521 B
 * 풀이 전략: 1. list를 이용해서 모든 순열을 넣고 처음부터 끝까지 돌면서 같은지 확인 -> 메모리 초과
 * 			2. stack을 이용해서 순열 구할 때마다 직전 순열과 비교해서 같으면 출력 -> 시간 초과 
 * 			=> nextPermutation을 써야한다!!!!!!!!
 * 			nextPermutation은 현재 순열의 다음 순열을 구하는 방법이다.
 * 			이걸 반복하면 전체 순열을 구할 수 있는 거고. 하지만 여기서는 주어진 순열의 다음 순열만 구한다.
 * 			다음 순열을 구하기 위해서, 총 3단계를 반복한다.
 * 			1) 맨뒤부터 탐색하며, 교환할 위치를 찾는다.
 * 			   	i보다 값이 작은 i-1 인덱스를 발견하면, 거기가 교환할 위치 i-1이다.
 * 			
 * 			   	arr[i-1]<a[i]를 만족하는 가장 큰 i를 찾음. 즉 순열의 마지막수에서 끝나는 가장 긴 내림차순을 찾아야한다
 * 				ex) 7 2 3 6 5 4 1 -> 6 5 4 1  // arr[i-1]=3, a[i]=6
 *  			
 * 			2) 내가 찾은 i-1  인덱스의 배열 값과 교환할 i-1보다 큰 위치 j를 찾는다
 * 			   	이렇게 찾는 i-1자리와 j의 값을 교환한다.
 * 				
 * 				j>=i이면서 arr[j]>arr[i-1]을 만족하는 가장 큰 j를 찾는다
 * 				ex) arr[i-1]보다 큰 수 중 가장 작은 수 arr[j]=4
 * 					arr[i-1]과 arr[j] swap
 * 					7 2 4 6 5 3 1
 * 
 * 			3) 순열의 사전순 오름차순을 위해 i번째 인덱스부터 마지막 인덱스 배열 값을 오름차순으로 바꿔준다.
 * 				
 * 				arr[i]부터 순열을 뒤집는다.
 * 				ex) arr[i] = 6
 * 					7 2 4 1 3 5 6
 * 
 * 			=> 이 3단계를 거치면 다음 순열을 찾을 수 있다.
 * 
 */


public class BJ_10972 {
	
	public static int[] arr;
	
	
	//index를 받아서 배열의 값 swap하는 메서드
	public static void swap(int i,int j) {
		int tmp=arr[i];
		arr[i]=arr[j];
		arr[j]=tmp;
	}
	
	public static boolean nextPermutation(int[] arr) {
		
		int i=arr.length-1;
		int j=arr.length-1;
		int k=arr.length-1;
		
		//step1) 뒤에서부터 꼭대기를 찾는다. 현재 인덱스의 배열값보다 작은 값을 찾는다.
		while(i>0 && arr[i-1] >= arr[i]) i-=1;
		
		//i의 위치가 0이면 마지막 순열이라는 뜻이므로 false를 return
		if(i==0) return false;
		
		//step2) j>=i 이면서 arr[j]>arr[i-1]을 만족하는 가장 큰 j를 찾는다
		while(arr[i-1]>arr[j]) j-=1;
		//그 값을 swap
		swap(i-1,j);
		
		//step3) 순열을 뒤집는다
		while(i<k) {
			swap(i,k);
			i+=1;
			k-=1;
		}
		
		return true;

	}


	public static void main(String[] args) throws IOException{

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		int n=Integer.parseInt(br.readLine()); //1부터 N까지 이루어진 순열
		
		arr=new int[n]; //순열담을 배열 생성
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken()); //현재 순열 입력받기
		}
		
		
		//현재 순열의 다음 순열을 찾는다
		if(nextPermutation(arr)) {
			//for-each 사용해서 배열 출력
			for(int e:arr) {
				System.out.print(e+" ");
			}
		}else {
			System.out.println(-1);
		}

		
	}

}
