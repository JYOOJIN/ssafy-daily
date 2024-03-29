package daily_0214;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_2630_색종이 만들기
 * 난이도: S2
 * 수행결과: 맞았습니다!
 * 메모리: 13428 KB
 * 시간: 112 ms
 * 코드길이: 1700 B
 * 풀이 전략: <분할정복>
 * 			해당 구역의 색이 모두 같아질때까지 1/4로 분할한다.
 * 			분할정복의 기본적인 문제. 
 * 			근데 z를 이런 방식으로 풀면 시간초과, 메모리 초과난다. 
 * 			그거는 재귀를 통해 찾고자 하는 구역을 미리 구해야 한다.
 * 
 */

public class BJ_2630 {
	
	public static int[][] arr;
	public static int white=0;
	public static int blue=0;
	
	//구역의 색이 모두 같은지 판별하는 함수
	public static boolean sameColor(int x,int y,int len) {
		int color=arr[x][y]; //기준이 되는 color
		
		//해당 범위 탐색
		for(int i=x;i<x+len;i++) {
			for(int j=y;j<y+len;j++) {
				if(arr[i][j]!=color) return false; //하나라도 다른 색이 있다면 false
			}
		}
		return true; //모두 같은 색이면 true
	}
	
	
	//분할하는 재귀함수
	public static void seperate(int x,int y,int len) {
		
		if(sameColor(x,y,len)) { //현재 구역의 색이 모두 같다면 return
			if(arr[x][y]==1) blue+=1; 
			else white+=1;
			return;
		}
		
		seperate(x,y,len/2); //2사분면
		seperate(x,y+len/2,len/2); //1사분면
		seperate(x+len/2, y, len/2); //3사분면
		seperate(x+len/2, y+len/2, len/2); //4사분면
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine()); //배열의 길이
		
		//배열 값 입력받기
		arr=new int[n][n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		//재귀함수 호출
		//첫 기준좌표는 (0,0)
		seperate(0,0,n);
		
		//형식에 맞춰 출력
		bw.write(white+""+"\n"+blue+"");
		bw.flush();
		bw.close();

	}

}
