package daily_0404;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_1249_보급로
 * 난이도: D4
 * 수행결과: Pass
 * 메모리: 41396 KB
 * 시간: 182 ms
 * 코드길이: 1827 B
 * 풀이 전략: 최소 비용 거리 -> pq 이용한 다익스트라!!!!!
 * 		   젤다와 똑같은 유형의 문제다. 최소 비용, 최소 시간 등 문제 외워버리기!!!
 * 			
 * 			 
 *  */

public class SW_1249_보급로 {
	
	//pq 이용하기 위한 class
	public static class Point implements Comparable<Point>{
		int x,y,time;

		public Point(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public int compareTo(Point o) {
			return this.time-o.time;
		}
		
	
	}
	
	public static int n;
	public static int[][] map,move;
	public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			n=Integer.parseInt(br.readLine());
			map=new int[n][n];
			move=new int[n][n];
			
			for(int i=0;i<n;i++) {
				String[] s=br.readLine().split("");
				for(int j=0;j<n;j++) {
					map[i][j]=Integer.parseInt(s[j]);
				}
			}
			
			int time=bfs();
			System.out.println("#"+t+" "+time);
			
			
		}
		
		
	}
	
	public static int bfs() {
		
		PriorityQueue<Point> pq=new PriorityQueue<>();
		
		//최소시간 담을 move 배열 초기화
		for(int i=0;i<n;i++) {
			Arrays.fill(move[i],Integer.MAX_VALUE);
		}
		
		//pq에 시작지점 넣기 + 시간 넣기
		pq.add(new Point(0,0,0));
		move[0][0]=map[0][0];
		
		while(!pq.isEmpty()) {
			
			Point tmp=pq.poll();
			
			//도착하면 시간 반환
			if(tmp.x==n-1 && tmp.y==n-1) { 
				return tmp.time;	
			}
			
			for(int d=0;d<4;d++) {
				int dx=tmp.x+dir[d][0];
				int dy=tmp.y+dir[d][1];
				
				//배열에서 벗어나면 pass
				if(dx<0 || dx>=n || dy<0 || dy>=n) continue;
				
				//현재 위치에서 이동해서 걸리는 시간이 현재 그 위치의 시간보다 적다면 이동한다
				if(tmp.time+map[tmp.x][tmp.y]<move[dx][dy]) {
					move[dx][dy]=tmp.time+map[tmp.x][tmp.y];
					pq.add(new Point(dx,dy,tmp.time+map[tmp.x][tmp.y]));
				}
				
			}
		}
		
		return -1; //경로를 찾지 못했을 때
		
		
		
	}

}
