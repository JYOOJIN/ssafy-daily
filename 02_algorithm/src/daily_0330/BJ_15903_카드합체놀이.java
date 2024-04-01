package daily_0330;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_15903_카드합체놀이
 * 난이도: S1
 * 수행결과: 맞았습니다!!
 * 메모리: 13096 KB
 * 시간: 124 ms
 * 코드길이: 737 B
 * 풀이 전략: 1) Arrays.sort 이용 -> 메모리/시간 훨씬 더 오래걸린다
 * 			2) long 사용해야함
 * 			3) pq 사용해서 넣을때마다 자동 정렬
 * 
 *  
 *  */

public class BJ_15903_카드합체놀이 {
	

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		PriorityQueue<Long> pq=new PriorityQueue<>();
		st=new StringTokenizer(br.readLine());
		
		for(int i=0;i<n;i++) {
			pq.add(Long.parseLong(st.nextToken()));
		}
		
		for(int i=0;i<m;i++) {
			long a=pq.poll();
			long b=pq.poll();
			long sum=a+b;
			pq.add(sum);
			pq.add(sum);
	
		}
		
		long total=0;
		for(long e:pq) {
			total+=e;
		}
		
		System.out.println(total);
	
	}

}
