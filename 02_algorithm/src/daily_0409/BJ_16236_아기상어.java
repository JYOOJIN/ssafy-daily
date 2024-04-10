package daily_0409;

import java.io.*;
import java.util.*;

public class BJ_16236_아기상어 {
	
	static class Point implements Comparable<Point>{
		int x,y,size;

		public Point(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}

		@Override
		public int compareTo(Point o) {
			if(this.size==o.size) {
				if(this.x==o.x) {
					return this.y-o.y; //size같고, x좌표 같으면 y좌표 순 오름차순
				}else {
					return this.x-o.x;
				}
			}else {
				return this.size-o.size; //size 다르면 size 오름차순
			}
			
			//return this.size-o.size; //size 순으로 오름차순
		}
		
	}
	
	static int n,total;
	static List<Point> list;
	static PriorityQueue<Point> pq;
	static int[][] map;
	static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};
	static Point shark;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		list=new ArrayList<>();
		pq=new PriorityQueue<>();
		total=0;
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==9) shark=new Point(i,j,2);
				else if(map[i][j]>0 && map[i][j]<9) pq.add(new Point(i,j,map[i][j]));
			}
		}
		int eat=0;
		while(!pq.isEmpty()) {
			
			list=new ArrayList<>();
			//현재 상어의 크기보다 작은 물고기를 pq에서 빼낸다
			while(!pq.isEmpty() && pq.peek().size<shark.size) {
				list.add(pq.poll());
			}
			
			if(list.isEmpty()) break;
			
			int minDis=Integer.MAX_VALUE;
			int minIndex=0;
			
			for(int i=0;i<list.size();i++) {
				int dis=bfs(shark,list.get(i));
				if(dis!=-1) continue;
				if(dis<minDis) {
					minDis=dis;
					minIndex=i;
				}
				
//				else if(dis==minDis) {
//					if(list.get(minIndex).x==list.get(i).x) {
//						if(list.get(minIndex).y>list.get(i).y) {
//							minDis=dis;
//							minIndex=i;
//						}
//					}else if(list.get(minIndex).x>list.get(i).x){
//						minDis=dis;
//						minIndex=i;
//					}
//					
//				}
 			}
			
			total+=minDis;
			
			map[shark.x][shark.y]=0; //원래 상어의 위치.
			shark.x=list.get(minIndex).x;
			shark.y=list.get(minIndex).y;
			
			
			eat++;
			if(eat==shark.size) {
				shark.size+=1;
				eat=0;
			}
			
			map[shark.x][shark.y]=shark.size;
			
			list.remove(minIndex);
			
			for(int i=0;i<list.size();i++) {
				pq.add(list.get(i));
			}
			
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			System.out.println("----" + total);
			
		}
		
		System.out.println(total);
		
		
	}
	
	public static int bfs(Point start,Point end) {
		
		int[][] visited=new int[n][n];
		Queue<Point> q=new ArrayDeque<>();
		q.add(start);
		visited[start.x][start.y]=1;
		
		while(!q.isEmpty()) {
			
			Point tmp=q.poll();
			
			if(tmp.x==end.x && tmp.y==end.y) {
				return visited[tmp.x][tmp.y]-1; //도착지에 도착하기까지의 거리
			}
			
			for(int d=0;d<4;d++) {
				int dx=tmp.x+dir[d][0];
				int dy=tmp.y+dir[d][1];
				
				if(dx<0 || dx>=n || dy<0 || dy>=n || map[dx][dy]>shark.size  || visited[dx][dy]>0) continue;
				
				q.add(new Point(dx,dy,shark.size));
				visited[dx][dy]=visited[tmp.x][tmp.y]+1;
				
			}
		}
		
		return -1; //경로가 없다
		
	}

}
