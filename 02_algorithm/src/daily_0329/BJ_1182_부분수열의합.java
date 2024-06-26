package daily_0329;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_1182_부분수열의합
 * 난이도: S2
 * 수행결과: 맞았습니다!!
 * 메모리: 12584 KB
 * 시간: 176 ms
 * 코드길이: 967 B
 * 풀이 전략: dfs - 부분집합
 * 			해당 index의 원소를 포함/불포함 으로 나누어서 재귀돌기.
 * 			기저 조건은 모든 index를 보았을 때.
 * 
 *  */

public class BJ_1182_부분수열의합 {
	
	public static int[] arr;
	public static boolean[] visited;
	public static int n,s,cnt;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		s=Integer.parseInt(st.nextToken());
		cnt=0;
		
		arr=new int[n];
		visited=new boolean[n];
		
		//값 입력받기
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		sub(0); //dfs 호출
		System.out.println(cnt);
		
	}
	
	
	public static void sub(int depth) {
		
		if(depth==n) { //모든 index를 돌았다면
			int total=0; //부분집합의 합
			int flag=1; //공집합 제외하기 위한 flag
			for(int i=0;i<n;i++) {
				if(visited[i]) {
					flag=0;
					total+=arr[i];
				}
			}
			
			if(flag==0 && total==s) cnt++; //공집합이 아니면서 부분 수열의 원소 더한 값이 S와 같음
			
			return;
		}
		
		visited[depth]=true; //포함
		sub(depth+1);
		visited[depth]=false; //불포함
		sub(depth+1);

	}

}
