package daily_0403;

import java.io.*;
import java.sql.BatchUpdateException;
import java.util.*;

public class SW_5656_벽돌깨기_정답 {
	
	static class Point{
		int r,c,cnt; //행,열,벽돌에 쓰인 수

		public Point(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
		
	}
	
	static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};
	static int n,h,w,min;

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken()); //구슬 던지는 횟수
			w=Integer.parseInt(st.nextToken()); //가로
			h=Integer.parseInt(st.nextToken()); //세로
			
			//처음 벽돌 입력받기
			int[][] map=new int[h][w];
			for(int i=0;i<h;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<w;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			min=Integer.MAX_VALUE;
			go(0,map);
			System.out.println("#"+t+" "+min);
			
		}
		
	}
	
	//구슬 던지기: 던져서 벽돌이 모두 깨졌으면 true, 아니면 false 반환
	public static boolean go(int count,int[][] map) {
		
		int remainCnt=getRemain(map);
		
		if(remainCnt==0) { //모든 구슬을 던지지 않아도 다 깨졌다면 true
			min=0;
			return true;
		}
		
		if(count==n) { //모든 구슬을 사용했을 때
			min=Math.min(min, remainCnt);
			//return min==0; //min이 0이면 true, 아니면 false (다 깨졌는지 여부 리턴)
			return false; //다 깨지는 못했다
		}
		
		
		//모든 열에 던지기 시도
		int[][] newMap=new int[h][w]; //사용할 새로운 map
		for(int c=0;c<w;c++) {
			//구슬에 맞는 가장 윗 벽돌 찾기
			int r=0;
			while(r<h && map[r][c]==0) ++r;
			
			if(r==h) continue; //현재 줄에는 깨트릴 벽돌이 존재하지 않음. 다음열로 던지기 시도.
			
			//벽돌 깨트리기 전에 벽돌 정보 복사
			//newMap에 map 복사하기
			copy(map,newMap);
			//해당 벽돌 깨트리기
			int brick=newMap[r][c];
			//연쇄 벽돌 처리
			boom(r,c,newMap);
			if(brick>1) {
				//중력 작용 처리
				down(newMap);
			}
			
			//다음 구슬 던지러 가기
			if(go(count+1,newMap)) return true;
		}
		
		return false; //전부 깨지는 못했다
		
		
	}
	
	public static void boom(int r,int c,int[][] map) {
		Queue<Point> q=new ArrayDeque<>();
		
		if(map[r][c]>1) q.add(new Point(r,c,map[r][c])); //q에 넣기
		map[r][c]=0; //방문처리(현재 위치 벽돌 깨트리기)
		
		while(!q.isEmpty()) {
			Point tmp=q.poll();
			
			//현재 벽돌에 쓰인 수 -1 만큼 4방 탐색
			for(int d=0;d<4;d++) {
				for(int k=1;k<tmp.cnt;k++) {
					int dr=tmp.r+dir[d][0]*k;
					int dc=tmp.c+dir[d][1]*k;
					if(dr>=0 && dr<h && dc>=0 && dc<w && map[dr][dc]>0) {
						if(map[dr][dc]>1) q.add(new Point(dr,dc,map[dr][dc])); //q에 넣기
						map[dr][dc]=0; //방문처리(현재 위치 벽돌 깨트리기)
					}
				}
			}
		}
		
	}
	
	
	public static Stack<Integer> stack=new Stack<>();
	public static void down(int[][] map) {
		for(int c=0;c<w;c++) {
			for(int r=0;r<h;r++) { //윗행부터 깨지지 않은 벽돌 스택에 담기
				if(map[r][c]==0) continue;
				stack.push(map[r][c]);
				map[r][c]=0;
			}
			
			int r=h-1;
			while(!stack.isEmpty()) {
				map[r--][c]=stack.pop();
			}
		}
	}
	
	//newMap에다 map 복사
	public static void copy(int[][] map,int[][] newMap) {
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				newMap[i][j]=map[i][j];
			}
		}
	}
	
	//남은 벽돌의 수 반환
	//현재 코드에서 개선하기
	//=>입력 처리할 때 벽돌의 개수를 세고 boom을 할 때마다 몇개가 깨졌는지를 알아낼 수 있다. 그 cnt를 빼면 남은 벽돌의 수.
	public static int getRemain(int[][] map) {
		int cnt=0;
		for(int r=0;r<h;r++) {
			for(int c=0;c<w;c++) {
				if(map[r][c]>0) ++cnt;
			}
		}
		
		return cnt;
	}
}






















