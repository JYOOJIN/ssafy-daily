package daily_0327;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_2239_스도쿠
 * 난이도: G4
 * 수행결과: 맞았습니다!!
 * 메모리: 16856 KB
 * 시간: 684 ms
 * 코드길이: 1797 B
 * 풀이 전략: 완전 탐색
 * 
 * 			빈칸의 좌표를 list에 저장해두고 각 좌표에 1~9까지 검증.
 * 			1) sudoku 검증 - 가로, 세로, 사각형 검증
 * 			2) 백트래킹 - 넣을 수 있는 숫자가 존재하지 않으면 그 전 빈칸으로 돌아가서 다시 설정
 * 			
 * 			생각보다 어렵진 않았다.완탐/백트래킹 두려워하지 말고 도전하기.
 *  */


public class BJ_2239_스도쿠 {
	
	public static final int N=9; //스도쿠 크기
	public static int[][] arr=new int[N][N]; //스도쿠 담을 배열
	public static List<int[]> list=new ArrayList<>(); //빈칸 좌표 담을 리스트

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		String s;
		
		//스도쿠 값 입력받기
		for(int i=0;i<N;i++) {
			s=br.readLine();
			String[] carr=s.split(""); //split 사용해서 문자 하나씩 쪼개기
			for(int j=0;j<N;j++) {
				arr[i][j]=Integer.parseInt(carr[j]); //String to Int
				if(arr[i][j]==0) {
					list.add(new int[] {i,j}); //빈칸인 좌표를 저장
				}
			}
		}
		
		dfs(0); //첫번째 빈칸부터 시작

	}
	
	public static void dfs(int index) {
		
		if(index==list.size()) { //모든 빈칸 채웠다면
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println();
			}
			System.exit(0); //답 구했으면 시스템 종료!!!!
			return;
		}
		
		int x=list.get(index)[0]; //현재 x 좌표
		int y=list.get(index)[1]; //현재 y 좌표
		
		for(int i=1;i<=9;i++) { //스도쿠에는 1~9까지 들어갈 수 있다
			if(check1(x,y,i) && check2(x,y,i) && check3(x,y,i)) { //스도쿠 검증을 통과한다면
				arr[x][y]=i;  //해당 자리에 값 넣기
				dfs(index+1); //다음 빈칸 넘어가기 
				arr[x][y]=0;  //dfs 나오면 해당 자리 다시 초기화 해야한다
			}
		
		}
		
		index--; //가능한 숫자가 없다면 그 전 빈칸으로 돌아간다
		return;  //dfs 빠져나오기
		
	}
	
	
	//가로 체크
	public static boolean check1(int x,int y,int value) {
		
		for(int i=0;i<N;i++) {
			if(i==y) continue;
			if(arr[x][i]==value) return false;
		}

		return true;
	}
	
	//세로 체크
	public static boolean check2(int x,int y,int value) {
		
		for(int i=0;i<N;i++) {
			if(i==x) continue;
			if(arr[i][y]==value) return false;
		}

		return true;		
	}
	
	//사각형 체크
	public static boolean check3(int x,int y,int value) {
		
		int sx=(x/3)*3; //기준 x 좌표
		int sy=(y/3)*3; //기준 y 좌표
		
		for(int i=sx;i<sx+3;i++) {
			for(int j=sy;j<sy+3;j++) {
				if(i==x && j==y) continue; 
				if(arr[i][j]==value) return false;
			}
		}

		return true;		
	}
	

}
