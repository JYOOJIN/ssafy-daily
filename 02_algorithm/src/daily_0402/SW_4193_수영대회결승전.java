package daily_0402;

import java.io.*;
import java.util.*;

public class SW_4193_수영대회결승전 {
	
	
	public static int n;
	public static int[][] map,visited;
	public static int[] start,end;
	public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};
	public static List<int[]> list; //소용돌이 좌표 담는 리스트
	public static Queue<int[]> q;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			n=Integer.parseInt(br.readLine());
			
			map=new int[n][n];
			visited=new int[n][n];
			start=new int[2];
			end=new int[2];
			list=new ArrayList<>();
			q=new ArrayDeque<>();
			
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			//print(map);
			st=new StringTokenizer(br.readLine());
			start[0]=Integer.parseInt(st.nextToken());
			start[1]=Integer.parseInt(st.nextToken());
			q.add(new int[] {start[0],start[1]});
			
			st=new StringTokenizer(br.readLine());
			end[0]=Integer.parseInt(st.nextToken());
			end[1]=Integer.parseInt(st.nextToken());
			
			System.out.println("#"+t+" "+bfs());

		}
		
	}
	
	public static void print(int[][] arr) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println("===================");
	}
	
	public static int bfs() {
		
		while(!q.isEmpty()) {
			
			int[] tmp=q.poll();
			int x=tmp[0];
			int y=tmp[1];
			
			if(x==end[0] && y==end[1]) {
				//print(visited);
				return visited[x][y];
			}
			
			for(int d=0;d<4;d++) {
				int dx=x+dir[d][0];
				int dy=y+dir[d][1];
				
				if(dx<0 || dx>=n || dy<0 || dy>=n) continue;
				
				if(map[dx][dy]==0 && visited[dx][dy]==0) { 
					visited[dx][dy]=visited[x][y]+1;
					q.add(new int[] {dx,dy});
				}else if(map[dx][dy]==2 && visited[dx][dy]==0) {
					visited[dx][dy]=visited[x][y]+3;
					q.add(new int[] {dx,dy});
				}
	
			}
			
		}
		
		return -1; //도착할 수 없을 때
		
		
		
	}

}
