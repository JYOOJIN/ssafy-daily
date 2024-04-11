package daily_0411;

import java.io.*;
import java.util.*;

public class BJ_2206_벽부수고이동하기 {
	
	static class Point{
		int x,y;
		boolean breakWall;
		
		public Point(int x, int y, boolean breakWall) {
			super();
			this.x = x;
			this.y = y;
			this.breakWall = breakWall;
		}
		
	}
	
	static int n,m;
	static int[][] map;
	static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		map=new int[n][m];
		
		for(int i=0;i<n;i++) {
			String[] s=br.readLine().split("");
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(s[j]);
			}
		}
		
		//입력 완료
		
		 System.out.println(bfs());
	}
	
	//시작 좌표(0,0) time=1, breakwall=false /도착 좌표(n-1,m-1)
	public static int bfs() {
		
		Queue<Point> q=new ArrayDeque<>();
		int[][] visited=new int[n][m]; //벽돌을 깨지않은 세상
		int[][] visited2=new int[n][m]; //벽돌을 깬 세상
		
		for(int i=0;i<n;i++) {
			Arrays.fill(visited[i], Integer.MAX_VALUE);
			Arrays.fill(visited2[i], Integer.MAX_VALUE);
		}
		
		q.add(new Point(0,0,false));
		visited[0][0]=1;
		visited2[0][0]=1;
		
		while(!q.isEmpty()) {
			
			Point tmp=q.poll();
			
			if(tmp.x==n-1 && tmp.y==m-1) {
				if(tmp.breakWall) {
					return visited2[tmp.x][tmp.y];
				}else {
					return visited[tmp.x][tmp.y];
				}
			}
			
			
			for(int d=0;d<4;d++) {
				
				int dx=tmp.x+dir[d][0];
				int dy=tmp.y+dir[d][1];
				
				if(dx<0 || dx>=n || dy<0 || dy>=m) continue;
				
				if(tmp.breakWall) { //벽을 깬적이 있다
					
					if(map[dx][dy]==1) continue;
					
					if(visited2[dx][dy]>visited2[tmp.x][tmp.y]+1) {
						visited2[dx][dy]=visited2[tmp.x][tmp.y]+1;
						q.add(new Point(dx,dy,tmp.breakWall));
					}
					
					
				}else { //벽을 깬적이 없다
					
					if(map[dx][dy]==1) {
						
						if(visited2[dx][dy]>visited[tmp.x][tmp.y]+1) {
							visited2[dx][dy]=visited[tmp.x][tmp.y]+1;
							q.add(new Point(dx,dy,true));
						}
						
						
					}else {
						if(visited[dx][dy]>visited[tmp.x][tmp.y]+1) {
							visited[dx][dy]=visited[tmp.x][tmp.y]+1;
							q.add(new Point(dx,dy,tmp.breakWall));
						}
					}
					
					
				}
				
				
				
			}
			
			
		}
		
		return -1;
		
	}

}




