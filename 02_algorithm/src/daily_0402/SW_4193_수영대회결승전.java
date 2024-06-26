package daily_0402;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_4193_수영대회결승전
 * 난이도: D4
 * 수행결과: Pass
 * 메모리: 22632 KB
 * 시간: 112 ms
 * 코드길이: 2198 B
 * 풀이 전략: 1) bfs(Queue)를 이용 - 이때 time 단위로 자르므로 while문 앞에 현재 q.size()만큼 반복
 * 		    2) bfs(PriorityQueue)를 이용 - pq 이용 안하면 먼저 도착하지만 시간이 큰 경우가 있을 수 있다. comparable 사용.
 * 
 *  */

public class SW_4193_수영대회결승전 {

	//좌표+시간 담을 class
	//pq를 사용하기 위해 comparable 설정
	public static class Node implements Comparable<Node>{
		int x,y,time;

		public Node(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return this.time-o.time; //오름차순
		}
		
	}
	
	public static int n; //지도 크기
	public static int[][] map; //지도 배열
	public static boolean[][] visited; //방문 확인 배열
	public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; //방향 벡터
	public static Node start,end; //시작, 끝 노드

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			
			n=Integer.parseInt(br.readLine());
			map=new int[n][n];
			visited=new boolean[n][n];
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			st=new StringTokenizer(br.readLine());
			start=new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),0);
			
			st=new StringTokenizer(br.readLine());
			end=new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),0);
			
			//int time=pq_bfs();
			int time=bfs();
			System.out.println("#"+t+" "+time);
			
		}

	}
	
	//1) bfs(queue)를 이용
	public static int bfs() {
		Queue<Node> q=new ArrayDeque<>();
		int time=0; //현재 전체 시간
		
		//q에 넣기+방문확인
		q.add(start);
		visited[start.x][start.y]=true;
		
		while(!q.isEmpty()) {
			
			int size=q.size(); //현재 q의 size -> 동시에 움직일 수 있는 위치들의 갯수
			
			for(int step=0;step<size;step++) {
				Node tmp=q.poll();
				if(tmp.x==end.x && tmp.y==end.y) return time; //도착하면 현재 시간 return
				
				for(int d=0;d<4;d++) {
					int dx=tmp.x+dir[d][0];
					int dy=tmp.y+dir[d][1];
					
					//배열을 벗어나거나, 방문했었거나, 벽이라면 pass
					if(dx<0 || dx>=n || dy<0 || dy>=n || map[dx][dy]==1 || visited[dx][dy])  continue;
					
					//map[dx][dy]==2라면 소용돌이가 사라질때까지 기다려야 하는데
					//소용돌이가 사라지는 시간(2초,5초,8초...)
					//map[dx][dy]==2인 곳에서 time%3==2 면 지나갈 수 있다.
					//map[dx][dy]==2인 곳에서 time%3!=2 면 지나갈 수 없으니 현재 위치에서 대기
					
					if(map[dx][dy]==2 && time%3 !=2) { //소용돌이가 있고 움직일 수 없는 시간이라면
						q.add(new Node(tmp.x,tmp.y,0)); //현재 위치 다시 q에 넣는다
					}else { //벽 제외하고 모두 움직일 수 있다
						//q에 넣기+방문확인
						q.add(new Node(dx,dy,0)); 
						visited[dx][dy]=true;
						
					}
				}
	
			}
			time++; //한 텀이 모두 움직이면 시간 증가

		}
		
		return -1; //도착하지 못하면 -1
		
	}
	
	
	public static int pq_bfs() {
		
		PriorityQueue<Node> pq=new PriorityQueue<>(); //class를 만드는 이유가 pq를 하기 위해서
		
		//pq에 넣고 + 방문 확인
		pq.add(start);
		visited[start.x][start.y]=true;
		
		while(!pq.isEmpty()) {
			Node tmp=pq.poll();
			
			if(tmp.x==end.x && tmp.y==end.y) { //도착하면 도착 노드 시간 return
				return tmp.time;
			}
			
			for(int d=0;d<4;d++) {
				int dx=tmp.x+dir[d][0];
				int dy=tmp.y+dir[d][1];
								
				//배열 범위 벗어나거나, 방문했거나, 벽이라면 pass
				if(dx<0 || dx>=n || dy<0 || dy>=n || map[dx][dy]==1 || visited[dx][dy]) continue;
				
				
				if(map[dx][dy]==0) { //이동가능한 길
					//pq에 넣고 + 방문 확인
					//그 전 시간보다 +1 해주면 된다
					pq.offer(new Node(dx,dy,tmp.time+1));
					visited[dx][dy]=true;
				}else if(map[dx][dy]==2) { //소용돌이가 있다면 -> 그냥 지나가는데 이때 시간 페널티는 있다. 
					//pq에 넣고 + 방문 확인
					//그 전 시간을 확인하는데, 그 전시간에 따라 소용돌이에서 머물러 있는 시간이 다르다.
					//기본적으로 이동했으므로 +1은 기본이다. 
					//그 전시간이 tmp.time%3 ==2 라면 바로 이동할 수있으므로 +1 되도록 설정해준다. 
					pq.offer(new Node(dx,dy,tmp.time+(3-tmp.time%3))); 
					visited[dx][dy]=true;
				}		
				
			}
		}
		
		return -1; //도착하지 못하면 -1

	}
	

	


}
