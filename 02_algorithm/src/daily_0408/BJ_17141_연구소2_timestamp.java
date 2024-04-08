package daily_0408;

import java.io.*;
import java.util.*;

public class BJ_17141_연구소2_timestamp {
	
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
	static Queue<Point> q;
	static int[] arr;
	static int[][] map;
	static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; //방향벡터

	public static void main(String[] args) throws IOException{
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
					virus.add(new Point(i,j)); 
					map[i][j]=0;
				}
				
			}
		}
		
		
		combination(0, 0);
		
		if(min==Integer.MAX_VALUE) {
			bw.write(-1+"");
		}else {
			bw.write(min+"");
		}
		

		bw.flush();
		bw.close();
		
	}
	
	public static int bfs(int[] arr) {
		
		int time=0;
		q=new ArrayDeque<>();
		//int[][] copy=copy(map);
		boolean[][] visited=new boolean[n][n];
		
		//초기 바이러스 위치 q에 넣기
		for(int i=0;i<m;i++) {
			q.add(new Point(virus.get(arr[i]).x,virus.get(arr[i]).y ));
			//copy[virus.get(arr[i]).x][virus.get(arr[i]).y]=2; //바이러스 존재
			visited[virus.get(arr[i]).x][virus.get(arr[i]).y]=true;
		}
		
		
		while(!q.isEmpty()){
			int size=q.size();
			for(int timeStep=0;timeStep<size;timeStep++) {
				Point tmp=q.poll();
				
				for(int d=0;d<4;d++) {
					int dx=tmp.x+dir[d][0];
					int dy=tmp.y+dir[d][1];
					
					//배열을 벗어나거나 벽이거나 바이러스가 이미 퍼져있다면 패스
					if(dx<0 || dx>=n || dy<0 || dy>=n || map[dx][dy]==1 || visited[dx][dy] ) continue;
				
					q.add(new Point(dx,dy));
					//copy[dx][dy]=2;
					visited[dx][dy]=true;
				
				}
			}
			time++;
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!visited[i][j] && map[i][j]==0) return -1;
			}
		}

		return time-1;
		
		
	}
	
	public static void combination(int depth, int start) {
		
		if(depth==m) {

			//arr에 바이러스 m개 넣는 좌표 들어있음
			//virus.get(arr[0]).x, virus.get(arr[0]).y
			int time=bfs(arr);
			if(min>time && time>-1) min=time;
			
			return;
		}
		
		for(int i=start;i<virus.size();i++) {
			arr[depth]=i;
			combination(depth+1, i+1);
		}
		
		
		
	}
	
	public static void print(int[][] arr) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
	
//	//arr을 깊은 복사해서 반환하는 함수
//	public static int[][] copy(int[][] arr){
//		int[][] copy=new int[n][n];
//		for(int i=0;i<n;i++) {
//			for(int j=0;j<n;j++) {
//				copy[i][j]=arr[i][j];
//			}
//		}
//		
//		return copy;
//	}

}
