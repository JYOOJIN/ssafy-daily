package daily_0403;

import java.io.*;
import java.util.*;

public class BJ_17144_미세먼지안녕 {
	
	static class Point{
		int x,y,dust;

		public Point(int x, int y, int dust) {
			super();
			this.x = x;
			this.y = y;
			this.dust = dust;
		}
		
	}
	
	static int r,c,t;
	static Point[] start=new Point[2]; //공기청정기 위치 담을 배열
	static Queue<Point> q=new ArrayDeque<>();
	static int[][] dir1= {{0,1},{-1,0},{0,-1},{1,0}}; //반시계 방향 벡터
	static int[][] dir2= {{0,1},{1,0},{0,-1},{-1,0}}; //시계 방향 벡터
	

	public static void main(String[] args) throws Exception{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		t=Integer.parseInt(st.nextToken());
		
		int[][] map=new int[r][c];
		int index=0;
		//(0,0)부터 (n-1,n-1)까지
		for(int i=0;i<r;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<c;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) start[index++]=new Point(i,j,-1);
				if(map[i][j]>0) q.add(new Point(i,j,map[i][j]));
			}
		}
		
		//미세먼지 확산
		bfs(map);
		print(map);
		//공기청정기 작동
		move(map);
		
	}
	
	static void print(int[][] arr) {
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				System.out.print(arr[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	static void copy(int[][] map,int[][] newMap) {
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				newMap[i][j]=map[i][j];
			}
		}
	}
	
	static void move(int[][] map) {
		
		int dx=start[0].x;
		int dy=start[0].y;
		int prev=0;
		
		
		for(int d=0;d<4;d++) {
			while(true) { //배열 안에 있을동안 해당 방향으로 킵 고잉
				dx+=dir1[d][0];
				dy+=dir1[d][1];
				if(dx<0 || dx>=r || dy<0 || dy>=c) continue;
				int next=map[dx][dy];
				map[dx][dy]=prev;
				prev=next;	
			}
			
		}
		
		print(map);

	}
	
	static void bfs(int[][] map) {
		int time=0; //시간
		while(!q.isEmpty()) {
			if(time==t) break;
			int size=q.size();
			for(int step=0;step<size;step++) { //1초 동안 확산
				Point tmp=q.poll();
				int plus=tmp.dust/5;
				
				for(int d=0;d<4;d++) {
					int dx=tmp.x+dir1[d][0];
					int dy=tmp.y+dir1[d][1];
					
					//배열을 벗어났거나, 공기청정기가 있으면 확산 x
					if(dx<0 || dx>=r || dy<0 || dy>=c || map[dx][dy]==-1) continue;
					
					map[dx][dy]+=plus;
					map[tmp.x][tmp.y]-=plus;
					
				}
	
			}
			
			//현재 map에서 공기청정기 작동
			move(map);
			
			
			time++;
		}
	}
	
	

}
