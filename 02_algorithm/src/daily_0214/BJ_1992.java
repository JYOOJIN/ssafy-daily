package daily_0214;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 정유진
 * 문제: BJ_1992_쿼드트리
 * 난이도: S1
 * 수행결과: 맞았습니다!!
 * 메모리: 11780 KB
 * 시간: 80 ms
 * 코드길이: 1224 B
 * 풀이 전략: 분할정복. 색종이 자르기와 똑같은 문제. 
 * 		    괄호를 어디에 두느냐가 문제 -> 하나의 4개분면을 살펴봤을 때 괄호로 감싸준다
 * 
 */

public class BJ_1992 {
	
	public static char[][] arr; //영상 담는 배열
	public static StringBuilder sb=new StringBuilder(); //결과 담는 string
	
	//해당 구역의 색이 모두 같은지 확인
	public static boolean sameColor(int x,int y,int len) {
		char color=arr[x][y]; //기준점의 색이 기준색
		for(int i=x;i<x+len;i++) {
			for(int j=y;j<y+len;j++) {
				if(arr[i][j]!=color) return false; //색이 다른게 있으면 false
			}
		}
		return true; //색이 모두 같으면 true
	}
	
	//1/4으로 쪼개는 재귀함수
	public static void seperate(int x,int y,int len) {
		
		if(sameColor(x,y,len)) { //해당 구역의 색이 모두 같으면
			//해당구역의 색을 string에 append
			if(arr[x][y]=='1') sb.append(1); 
			else sb.append(0);
			
			return; //빠져나온다
		}
		
		
		sb.append('('); //하나의 사분면을 열어준다
		seperate(x,y,len/2); //왼쪽 위
		seperate(x,y+len/2,len/2); //오른쪽 위
		seperate(x+len/2,y,len/2); //왼쪽 아래
		seperate(x+len/2, y+len/2, len/2); //오른쪽 아래
		sb.append(')'); //하나의 사분면을 닫아준다
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int n=Integer.parseInt(br.readLine()); //배열의 크기
		
		//영상 담을 배열 생성 후 값 입력받기
		arr=new char[n][n]; 
		
		for(int i=0;i<n;i++) {
			String s=br.readLine();
			arr[i]=s.toCharArray();
		}
		
		//재귀함수 호출. 처음의 기준 좌표는 (0,0)이다
		seperate(0, 0, n);
		//형식에 맞춰 출력
		System.out.println(sb);
		
		
		
	}

}
