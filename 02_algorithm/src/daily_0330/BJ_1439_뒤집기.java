package daily_0330;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_1439_뒤집기
 * 난이도: S5
 * 수행결과: 맞았습니다!!
 * 메모리: 11496 KB
 * 시간: 80 ms
 * 코드길이: 550 B
 * 풀이 전략: 0과 1로 시작하는 연결된 문자열 개수 구해서 둘 중 최소 개수가 정답
 * 
 *  */

public class BJ_1439_뒤집기 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		char[] c=s.toCharArray(); 
		
		int cnt=0;
		int cntZero=0;
		int cntOne=0;
		char now=c[0]; //기준문자 설정
		
		if(now=='0') cntZero++;
		else cntOne++;
		
		for(int i=1;i<s.length();i++) {
			if(now!=c[i]) { //문자가 달라진다면
				if(c[i]=='0') cntZero++;
				else cntOne++;
				
				now=c[i]; //기준문자 change
			}
			
		}
		
		System.out.println(Math.min(cntOne, cntZero));
	}

}