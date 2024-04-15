package daily_0412;

import java.io.*;
import java.util.*;

public class BJ_1600_말이되고픈원숭이 {
	
	static class Point{
		int x,y;
		int horse;

		public Point(int x, int y, int horse) {
			super();
			this.x = x;
			this.y = y;
			this.horse=horse;
		}
		
	}
	
	static int[][] horse= {{-2,-1},{-2,1},{-1,2},{1,2},{2,1},{2,-1},{1,-1},{-1,-2}};
	static int[][] monkey= {{-1,0},{0,1},{1,0},{0,-1}};
	static int k,w,h;
	static int[][] map;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		k=Integer.parseInt(br.readLine()); //말처럼 이동할 수 있는 수
		st=new StringTokenizer(br.readLine());
		w=Integer.parseInt(st.nextToken());
		h=Integer.parseInt(st.nextToken());
		
		map=new int[h][w];
		
		for(int i=0;i<h;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<w;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
	}
	
	//시작좌표(0,0) 종료좌표(h-1,w-1) 
	public static void bfs() {
		Queue<Point> q=new ArrayDeque<>();  
		int[][][] visited=new int[w][h][k+1]; //좌표+말처럼 k번 이동(0~k번)
		
		q.add(new Point(0,0,0));
		visited[0][0][0]=0; //(0,0)에서 말처럼 0번 움직인 것
		
		while(!q.isEmpty()) {
			Point tmp=q.poll();
			int dx;
			int dy;
			if(tmp.horse==k) { //이미 k번 움직였다면 원숭이로만 움직일 수 있다
				for(int m=0;m<4;m++) {
					dx=tmp.x+monkey[m][0];
					dy=tmp.y+monkey[m][1];
					
					if(dx<0 || dx>=h || dy<0 || dy>=w || visited[dx][dy][k]==1) continue;
					
					
					
				}
			}
			
			
			
		}
		
		
		
		
	}

}
