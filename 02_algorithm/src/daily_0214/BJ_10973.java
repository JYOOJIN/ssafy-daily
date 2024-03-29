package daily_0214;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_10973_이전 순열
 * 난이도: S3
 * 수행결과: 맞았습니다!
 * 메모리: 14396 KB
 * 시간: 124 ms
 * 코드길이: 2157 B
 * 풀이 전략: nextPermuation을 이해하면 prevPermutation이 보인다!!
 * 			직접 순열을 구해서 보면서 이전 순열을 구하는 방법을 생각한다.
 * 			<prevPermutation>
 * 			step1) 바닥을 찾는다.바닥은 내려가다 갑자기 반등하는 곳이다. 
 * 			step2) 천장(i-1)과 그 뒤쪽에 있는 값들 중 천장(i-1)보다 작으면서 가장 큰값을 swap. 
 * 			step3) 바닥(i)부터 마지막(k)까지 내림차순 정렬하기
 */

public class BJ_10973 {
	
	public static int[] arr;
	
	//index 입력받으면 둘이 swap
	public static void swap(int i,int j) {
		int tmp=arr[i];
		arr[i]=arr[j];
		arr[j]=tmp;
	}
	
	//이전 순열
	//다음 순열을 참고하여 step 1,2,3 구한다.
	public static boolean prevPermutation() {
		
		int i=arr.length-1;
		int j=arr.length-1;
		int k=arr.length-1;
		
		//step 1.바닥(i)를 찾는다. 바닥은 내려가다 갑자기 반등하는 곳이다. 
		while(i>0 && arr[i]>arr[i-1]) i-=1;
		if(i==0) return false; //가장 첫번째 순열
		
		//step 2.천장(i-1)과 그 뒤쪽에 있는 값들 중 천장(i-1)보다 작으면서 가장 큰값을 swap. 
		//이미 뒤에는 오름차순 정렬 되어있기 때문에 가장 먼저 조건에 맞는 값이 가장 큰값이다.
		while(arr[i-1]<arr[j]) j-=1;
		swap(i-1,j);
		
		
		//step3.바닥(i)부터 마지막(k)까지 내림차순 정렬하기
		while(i<k) {
			swap(i,k);
			i+=1;
			k-=1;
		}
		
		return true; //모든 과정 거쳤으면 이전 순열이 존재한다
	}
	
	//다음 순열
	//step 1,2,3 만 따라가면 쉽게 구현할 수 있다. 
	public static boolean nextPermutation() {
		
		int i=arr.length-1;
		int j=arr.length-1;
		int k=arr.length-1;
		
		//step 1.뒤에서부터 꼭대기를 찾는다. 뚝 떨어지는 지점을 찾는다.
		while(arr[i-1]<arr[i] && i>0) i-=1; //최종적으로 i는 꼭대기의 index
		if(i==0) return false; //마지막 순열
		
		
		//step 2. 꼭대기(i)와 떨어진 지점(i-1)을 찾았으면, 
		//떨어진 지점(i-1)과 오르막길에 있는 값 중 떨어진 지점(i-1)보다 크면서 가장 작은 값과 swap
		while(arr[i-1]>arr[j]) j-=1;
		swap(i-1,j); 
		
		//step 3. 꼭대기(i)부터 마지막(k)까지 다시 오름차순 정렬
		while(i<k) {
			swap(i,k);
			i+=1;
			k-=1;
		}

		return true; //모든 과정 거쳤으면 다음 순열 존재한다

	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine());
		arr=new int[n];
		
		//현재 순열 값 입력받기
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		
		if(prevPermutation()) { //현재 순열의 이전 순열 존재한다면
			for(int e:arr) { 
				bw.write(e+" "); //해당 순열 출력
			}
		}else { //현재 순열의 이전 순열 존재하지 않는다면
			bw.write(-1+""); //-1 출력
		}
		
		
		//Bufferedwriter 내보내고 닫는다
		bw.flush();
		bw.close();
		
		
	}

}
