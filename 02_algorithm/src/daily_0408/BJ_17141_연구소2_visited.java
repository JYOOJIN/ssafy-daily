package daily_0408;

import java.io.*;
import java.util.*;

public class BJ_17141_연구소2_visited {
	
	static class Point{
		int x,y; //좌표

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static int n,m,min;
	static List<Point> virus; //바이러스 가능한 좌표 담는 리스트
	static int[] arr;
	static int[][] map;
	static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; //방향벡터
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		map=new int[n][n];
		arr=new int[m];
		virus=new ArrayList<>();
		min=Integer.MAX_VALUE;
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==2) {
					virus.add(new Point(i,j)); //바이러스 좌표 저장
					map[i][j]=0; //바이러스 가능한 좌표 초기화
				}
				
			}
		}
		
		combination(0, 0);
		
		if(min==Integer.MAX_VALUE) {
			System.out.println(-1);
		}else {
			System.out.println(min);
		}
		
		
	}
	
	public static int bfs(int[] arr) {
		Queue<Point> q=new ArrayDeque<>();
		int[][] visited=new int[n][n];
		
		for(int i=0;i<n;i++) {
			Arrays.fill(visited[i], -1);
		}
		
		for(int i=0;i<m;i++) {
			q.add(new Point(virus.get(arr[i]).x,virus.get(arr[i]).y ));
			visited[virus.get(arr[i]).x][virus.get(arr[i]).y]=0; 
		}
			
		while(!q.isEmpty()) {
			Point tmp=q.poll();
			
			for(int d=0;d<4;d++) {
				int dx=tmp.x+dir[d][0];
				int dy=tmp.y+dir[d][1];
				
				if(dx<0 || dx>=n || dy<0 || dy>=n || map[dx][dy]==1 || visited[dx][dy]>=0) continue;
				
				q.add(new Point(dx,dy));
				visited[dx][dy]=visited[tmp.x][tmp.y]+1;
			
			}
		}
		
		//최대 시간 구함
		int max=Integer.MIN_VALUE;
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(map[i][j]==0 && visited[i][j]<0) return -1;
				if(max<visited[i][j]) max=visited[i][j];
			}
		}
		
		//근데 이러다가 
		return max;
		
		
	}
	
	
	public static void combination(int depth, int start) {
		if(depth==m) {
			int time=bfs(arr);
			if(time>-1 && min>time) min=time;
			
			return;
		}
		
		
		for(int i=start;i<virus.size();i++) {
			arr[depth]=i;
			combination(depth+1, i+1); //조합
		}
	}

}
