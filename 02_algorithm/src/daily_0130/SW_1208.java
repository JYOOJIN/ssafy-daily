package daily_0130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: D3_1208.[S/W 문제해결 기본] 1일차 - Flatten
 * 수행결과: Pass
 * 풀이 전략: 재귀를 이해해야한다. 상자 높이를 정렬을 해서 가장 높은 곳에서 낮은곳으로 dump를 반복한다. dump가 완료되면 가장 높은 상자 높이와 가장 낮은 상자 높이의 차를 구한다.
 */

public class SW_1208 {
	
	private static int[] dump; //가로길이 항상 100, 상자 높이 저장하는 배열
	private static int sub; //최고점과 최저점의 높이 차
	

	/**
	 * dump를 수행하는 재귀함수
	 * @param d
	 */
	public static void flatten(int d) {
		
		if(d==0) { //base case. dump 횟수가 0이라면 끝난다.
			Arrays.sort(dump); //상자 높이 정렬
			sub=dump[99]-dump[0]; //가장 높은 상자와 가장 낮은 상자의 높이차 구하기
			return; //빠져나오기
		}
		
		Arrays.sort(dump); //상자 높이 정렬
		dump[0]+=1; //상자 높이 가장 높은 곳에서 dump 받음
		dump[99]-=1; //높이 가장 높은 곳에서 상자 하나 낮은곳으로 dump
		d-=1; //dump횟수 감소
		flatten(d); //다시 flatten 호출
		
	}
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); //입력값 받을 buffer 생성
		StringTokenizer st; //tokenize 선언
		
		for(int t=1;t<=10;t++) { //10개의 testcase 반복
			
			sub=0; //초기화
			dump=new int[100]; //배열 초기화
			
			st=new StringTokenizer(br.readLine()); //한줄 읽어서 tokenize에 저장
			
			int d=Integer.parseInt(st.nextToken()); //token으로 쪼개서 dump하는 수
			
			String s=br.readLine(); //상자의 높이값 담은 string
			st=new StringTokenizer(s); //" "기준으로 tokenize
			
			for(int i=0;i<100;i++) {
				dump[i]=Integer.parseInt(st.nextToken()); //dump 배열에 상자 높이 넣기
			}
			
			flatten(d); //재귀함수 호출
			
			System.out.println("#"+t+" "+sub); //형식에 맞춰 출력
		}
		

		
	}

}
