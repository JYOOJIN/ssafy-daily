package daily_0213;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_16435_스네이크버드
 * 난이도: S5
 * 수행결과: 맞았습니다!
 * 메모리: 12032 KB
 * 시간: 88 ms
 * 코드길이: 658 B
 * 풀이 전략: 배열을 오름차순으로 정렬 후 아래부터 먹으면서 키와 과일의 높이를 비교한다.
 * 
 */

public class BJ_16435 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken()); //과일의 개수
		int l=Integer.parseInt(st.nextToken()); //스네이크버드의 초기 길이
	
		//과일 높이 입력받기
		int[] fruit=new int[n];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			fruit[i]=Integer.parseInt(st.nextToken());
		}
		
		//과일 높이 정렬
		Arrays.sort(fruit);
		
		//아래부터 과일 높이 비교한다
		for(int i=0;i<n;i++) {
			//먹을 수 있는 높이라면, 스네이크버드 높이+1
			if(l>=fruit[i]) l+=1;
		}
		
		System.out.println(l);
	
	}

}
