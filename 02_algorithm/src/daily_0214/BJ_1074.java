package daily_0214;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_1074_Z
 * 난이도: S1
 * 수행결과: 맞았습니다!!
 * 메모리: 11632 KB
 * 시간: 76 ms
 * 코드길이: 1684 B
 * 풀이 전략: 
 * <분할정복 & 재귀>
 * 	배열 사용하면 "메모리 초과" -> 2^15 * 2^15의 이중배열은 너무 크다
 * 	재귀로 전체를 돌면 "시간 초과" -> 2^15 * 2^15를 다 돌기엔 너무 크다
 * 	=> 최대한 재귀를 도는 범위를 줄인다!!!
 * 	=> r과 c가 4분면 중 어디에 있는지 파악한 후 값을 구한다
 * 	=> 길이가 1, 즉 한칸이 될때까지 1/4 조각을 한다. 
 * 
 * 	이런 문제의 경우, 내가 이해할 수 있는 작은 범위를 보고 조건문 or answer 값을 구하는게 좋다.
 * 	여기서는 4x4 배열을 통해 1,2,3,4분면 조건과 answer 값을 구했다. 
 * 
 */


public class BJ_1074 {
	
	public static int r;
	public static int c;
	public static long answer;

	//재귀함수
	public static void visit(int x,int y,int len) {
	
		if(len==1) { //(x==r && y==c) 인 구역
			return;
		}
		
		int newlen=len/2; //1/4 했을 때의 길이
		
		if(r<x+newlen && c<y+newlen) { //Z의 1사분면
			visit(x,y,newlen); //1사분면에서 다시 1/4
			
		}else if(r<x+newlen && c>=y+newlen ) { //Z의 2사분면
			answer+=newlen*newlen; //1사분면 기준점을 기준으로 +newlen^2
			visit(x,y+newlen,newlen); //2사분면에서 다시 1/4
			
		}else if(r>=x+newlen && c<y+newlen) { //Z의 3사분면
			answer+=(newlen*newlen)*2;//1사분면 기준점을 기준으로 +newlen^2 * 2
			visit(x+newlen,y,newlen); //3사분면에서 다시 1/4
			
		}else if(r>=x+newlen && c>=y+newlen) { //Z의 4사분면
			answer+=(newlen*newlen)*3; //1사분면 기준점을 기준으로 +newlen^2 *3
			visit(x+newlen,y+newlen,newlen); //4사분면에서 다시 1/4
			
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken()); //찾는 x좌표
		c=Integer.parseInt(st.nextToken()); //찾는 y좌표
		
		int len= (int) Math.pow(2, n); //2의 n승으로 배열 길이를 구한다
		
		answer=0; 
		
		visit(0,0,len); //재귀함수 호출
		
		bw.write(answer+""); //BufferedWriter는 String 형식만 출력한다
		bw.flush();
		bw.close();
		

	}
	

}
