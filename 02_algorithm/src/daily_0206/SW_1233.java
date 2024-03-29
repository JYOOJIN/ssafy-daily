package daily_0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: SW_1233_사칙연산 유효성 검사
 * 난이도: D4
 * 수행결과: Pass
 * 메모리: 21732 KB
 * 시간: 126 ms
 * 코드길이: 1039 B
 * 풀이 전략: <연산 가능한 경우>
 * 			1. 단말 노드가 숫자여야한다
 * 			-> 단말노드인지 확인: 자식의 num가 노드의 개수보다 크다면 자식이 존재하지 않으므로 단말노드이다
 * 			-> 숫자인지 확인: parsInt를 통해 오류가 나오면 연산자,안나오면 숫자
 * 			
 */

public class SW_1233 {
	
	/**
	 * 현재 string이 숫자인지 판별하는 함수
	 * @param s
	 * @return true,false
	 */
	public static boolean isNum(String s) {
		try {
			Integer.parseInt(s);
			return true;
		}catch(Exception e) {
			return false;
		}
		
	} 

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String[] tree;
		
		for(int t=1;t<=10;t++) { 
			int n=Integer.parseInt(br.readLine());
			int result=1;
			
			for(int i=0;i<n;i++) { //명령어 개수만큼 반복
				st=new StringTokenizer(br.readLine());
				int num=Integer.parseInt(st.nextToken()); //현재 노드 숫자
				String s=st.nextToken(); //현재 노드 원소
				if(num*2>n) { //현재 노드의 자식이 존재 할수없다면 단말노드
					if(!isNum(s)) { //현재 노드의 원소가 숫자가 아니라면
						result=0; //불가능한 연산이다
					}
				}
			}
			
			System.out.println("#"+t+" "+result);
		
		}
		
		
	}

	
}


