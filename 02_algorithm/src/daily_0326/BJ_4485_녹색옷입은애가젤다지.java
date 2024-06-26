package daily_0326;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_4485_녹색 옷 입은 애가 젤다지
 * 난이도: G4
 * 수행결과: 맞았습니다!!
 * 메모리: 18180 KB
 * 시간: 192 ms
 * 코드길이: 1942 B
 * 풀이 전략: 다익스트라 - DP를 이용한 최단경로
 * 			하나의 정점에서 출발해 모든 정점으로의 최단 거리를 구할 수 있다. 양수 가중치만 있을 때 가능.
 * 			우선순위 큐를 사용해 개선 가능하다.
 * 			
 * 			1) 아직 방문하지 않은 정점 중 출발지로부터 거리가 가장 짧은 정점을 방문
 * 			2) 해당 정점을 거쳐 갈 수 있는 정점의 거리가 이전에 기록한 값보다 작으면 갱신 -> 최소 가치를 구하기 때문.
 * 
 * 			<<bfs로 우선순위 큐를 사용해 구현 할 수 있다.>>
 * 			 
 *  */

public class BJ_4485_녹색옷입은애가젤다지 {
	
	//pq에 담을 Node 클래스 생성. 최소힙으로 구현해야 하므로 가치 순으로 오름차순
	public static class Node implements Comparable<Node>{
		int x,y; //좌표 
		int cost; //도둑루피 크기
		
		public Node(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost-o.cost; //도둑루피 크기 순 오름차순
		}	
		
	}
	
	public static int n; //동굴 크기
	public static int[][] map; //동굴
	public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; //방향벡터

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int t=1;
		
		while(true) {
			n=Integer.parseInt(br.readLine());
			if(n==0) break; //입력이 0이면 전체 종료
			
			map=new int[n][n]; //동굴 생성
			
			StringTokenizer st;
			
			//도둑 루피 크기 입력
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			//bfs 실행
			int min=bfs();
			
			System.out.println("Problem "+t+": "+min);
			t++;

		}
	}
	
	public static int bfs() {
		
		//Node를 담는 우선순위 큐
		PriorityQueue<Node> pq=new PriorityQueue<>();
		
		int[][] move=new int[n][n]; //가중치를 담는 배열
		
		//초기 가중치 설정
		for(int i=0;i<n;i++) {
			Arrays.fill(move[i], Integer.MAX_VALUE);
		}
		
		pq.add(new Node(0,0,map[0][0])); //첫번째 노드 정보 pq에 add
		move[0][0]=map[0][0]; //출발지 설정
		
		while(!pq.isEmpty()) {
			
			Node node=pq.poll();
			
			if(node.x==n-1 && node.y==n-1) return node.cost; //목표지점의 cost를 반환
			
			for(int d=0;d<4;d++) { //4가지 방향 이동
				int dx=node.x+dir[d][0]; //이동한 x좌표
				int dy=node.y+dir[d][1]; //이동한 y좌표
				
				if(dx<0 || dx>=n || dy<0 || dy>=n) continue; //배열을 벗어나면 continue
				
				//현재 node를 거쳐 이동하는 값이 기록되있던 값보다 작으면 갱신
				if(node.cost+map[dx][dy]<move[dx][dy]) { 
					move[dx][dy]=node.cost+map[dx][dy];
					//이동하면서 pq에 새로운 node add
					pq.add(new Node(dx,dy,node.cost+map[dx][dy]));
				}
			}
			
			
		}

		return -1;
	}

}
