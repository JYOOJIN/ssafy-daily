package daily_0401;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_5607_조합
 * 난이도: D3
 * 수행결과: Pass
 * 메모리: 88316 KB
 * 시간: 439 ms
 * 코드길이: 1129 B
 * 풀이 전략: 페르마의 소정리 이용. 
 * 			a^p 와 a 는 합동이므로 각자 p로 나눈 나머지는 같다. 
 * 
 * 			a^(p-2) = a/1 (mod p) 를 이용한다. 
 * 			nCr % p  = ((r!(n-r)!)^(p-2)* (n!)) % p 를 이용한다.
 * 
 *  */

public class SW_5607_조합 {
	
	public static final int p=1234567891; //변하지 않는 값

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			//nCr
			StringTokenizer st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int r=Integer.parseInt(st.nextToken());
			
			//1! 부터 10! 배열에 저장해두기
			long[] fact=new long[n+1]; 
			fact[1]=1;
			for(int i=2;i<=n;i++) {
				fact[i]=(fact[i-1]*i)%p;
			}
			
			
			long bottom = (fact[r]*fact[n-r]) % p; // 이것이 바로 a이다. 
			bottom = pow(bottom, p-2); //a^(p-2) = a/1 (mod p)
			 
			System.out.println("#"+t+" "+(fact[n]*bottom)%p); //((r!(n-r)!)^(p-2)* (n!)) % p
			
		}
	}
	
	public static long pow(long a, int b) { //a^b. a의 b승.
		
		if(b==0) return 1;
		else if(b==1) return a;
		
		if(b%2 == 0) { //짝수일때
			long tmp=pow(a,b/2);
			return (tmp*tmp)%p; //숫자가 크므로 한번에 %p를 구하는 것이 아니라 단계마다 %p를 해주는 것
		}
		
		long tmp=pow(a,b-1)%p; //홀수면 짝수로 만들기
		return (tmp*a) %p; //다시 홀수로 만들기
		
	}

}
