package daily_0130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: D4_1210. [S/W 문제해결 기본] 2일차 - Ladder1
 * 수행결과: Pass
 * 풀이 전략: 
 */

public class SW_1210 {

	static int[][] ladder; //사다리 배열
	static int[][] dir= {{0,-1},{0,1},{-1,0}}; //좌,우,상 방향 벡터 배열
	static int start; //출발점의 x좌표
	static int SIZE=100; //배열의 크기
	static boolean[][] visit;
	
	public static void move(int i,int j) { //현재 위치를 나타내는 좌표 입력값으로 받는다
		
		if(i==0) {
			start=j;
			return;
		}
		visit[i][j]=true;
		int di=0;
		int dj=0;
		for(int d=0;d<3;d++) {
			di=i+dir[d][0];
			dj=j+dir[d][1];
			
			if(di>=0 && di<SIZE && dj>=0 && dj<SIZE && (visit[di][dj]==false)) {
				if(ladder[di][dj]==1) {
					//System.out.println("이동한좌표: "+di+" "+dj);
					break;
				}else {
					continue;
				}
			}
		}
		move(di,dj);
		return;
		
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t=0;t<10;t++) {
			
			ladder=new int[SIZE][SIZE]; //ladder 배열 선언
			int test=Integer.parseInt(br.readLine()); //testcase의 번호
			int startX = 0;
			int startY=0;
			
			for(int i=0;i<SIZE;i++) { //2차원 배열에 값 입력하기 -> st생성 위치 주의!!!!
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<SIZE;j++) { 
					ladder[i][j]=Integer.parseInt(st.nextToken());
					if(ladder[i][j]==2) { //도착 지점을 탐색 시작 좌표로 설정
						startX=i;
						startY=j;
					}
				}
			}
			visit=new boolean[SIZE][SIZE];
			//System.out.println("start좌표: "+startX+" "+startY);
			move(startX,startY);
			System.out.println("#"+test+" "+start);
		}
		
		
	}

}
