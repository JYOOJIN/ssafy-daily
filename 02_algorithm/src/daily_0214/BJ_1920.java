package daily_0214;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_1920_수 찾기
 * 난이도: S4
 * 수행결과: 맞았습니다!!
 * 메모리: 96860 KB
 * 시간: 2128 ms
 * 코드길이: 1019 B
 * 풀이 전략: <binarySearch>
 * 			이진탐색. Arrays의 메서드를 사용한다.
 * 주의사항: 이진탐색 전에는 반드시 정렬이 되어있어야 한다.
 */

public class BJ_1920 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		//input 배열
		int n=Integer.parseInt(br.readLine());
		int[] num=new int[n];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			num[i]=Integer.parseInt(st.nextToken());
		}
		
		//key 값 담은 배열
		int m=Integer.parseInt(br.readLine());
		int[] key=new int[m];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			key[i]=Integer.parseInt(st.nextToken());
		}
		
		//이진탐색 위해서는 반드시 정렬이 되어있어야 한다!!!!
		Arrays.sort(num);
		
		for(int i=0;i<m;i++) {
			if(Arrays.binarySearch(num,key[i])>=0) { //반환값: 해당 key의 index
				System.out.println(1);
			}else {
				System.out.println(0);
			}
		}
		
		

	}

}
