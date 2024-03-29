package daily_0213;

import java.io.*;
import java.util.*;


/**
 * @author 정유진
 * 문제: BJ_2138_전구와 스위치
 * 난이도: G5
 * 수행결과: 맞았습니다!
 * 메모리:  KB
 * 시간:  ms
 * 코드길이:  B
 * 풀이 전략: 
 * 
 */

public class BJ_2138 {
	
	public static char[] current;
	public static char[] curr;
	public static char[] want;
	
	
	public static void change(int k) {
		if(current[k]=='1') current[k]='0';
		else current[k]='1';
	}

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		current=new char[n]; 
		want=new char[n]; 
		
		String s=br.readLine();
		current=s.toCharArray();
		curr=s.toCharArray();
		s=br.readLine();
		want=s.toCharArray();
		

		int i=0;
		int cnt=0;

		while(!Arrays.equals(current, want)) { //배열 같아질때까지 반복
			if(i>=n) {
				i=i%n;
			}
			if(current[i]!=want[i]) {
				//System.out.println(i+" "+current[i]+" "+want[i]);
				cnt+=1;
				if(i==0) {
					change(0);
					change(1);
				}else if(i==n-1) {
					change(n-1);
					change(n-2);
				}else {
					change(i);
					change(i+1);
					change(i-1);
				}
				//System.out.println(current);
			}
			i++;
		}
		System.out.println(cnt);

		
		

	}

}
