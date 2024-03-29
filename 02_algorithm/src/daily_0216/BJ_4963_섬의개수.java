package daily_0216;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_4963_섬의 개수
 * 난이도: S2
 * 수행결과: 맞았습니다!!
 * 메모리: 13788 KB
 * 시간: 148 ms
 * 코드길이: 1946 B
 * 풀이 전략: <bfs>: 재귀 쓰면 안된다. queue 사용.
 * 			배열을 그래프로 생각해보자. 현재 좌표의 상하좌우대각선에 이동할 수 있는 곳이 있다면 이동하고, 
 * 			더이상 이동할 곳이 없으면 -> q가 비었으면 하나의 섬이다.
 * 			
 */

public class BJ_4963_섬의개수 {
	
	public static int cnt,w,h; //섬의 개수, 넓이, 높이

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st=new StringTokenizer(br.readLine());
			w=Integer.parseInt(st.nextToken()); //넓이
			h=Integer.parseInt(st.nextToken()); //높이
			
			if(w==0 && h==0) break; // 0 0 입력받으면 종료
			 
			int[][] map=new int[h][w]; //지도 생성
			boolean[][] visited=new boolean[h][w]; //방문 여부 체크
			
			//지도 값 입력받기
			for(int i=0;i<h;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<w;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			cnt=0; //섬의 개수
			
			bfs(map,visited); //bfs 호출
			System.out.println(cnt); //섬의 개수 출력

		}
	}
	
	//x,y좌표 값을 담는 클래스
	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
	
	//섬의 개수 구하는 bfs
	public static void bfs(int[][] map,boolean[][] visited) {
		
		Queue<Pair> q=new ArrayDeque<>(); //bfs는 큐 생성!!!!좌표 담는 queue
		int[][] dir= {{-1,0},{0,1},{1,0},{0,-1},{-1,-1},{-1,1},{1,1},{1,-1}}; //상우하좌 + 11시부터 시계방향 대각선
		
		//전체 map을 돌면서 bfs 시작할 지점을 찾는다
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				if(map[i][j]==1 && !visited[i][j]) { //육지면서, 방문하지 않은 곳에서 bfs 시작
					q.clear(); //q를 초기화
					q.add(new Pair(i,j)); //현재 위치 q에 넣기
					visited[i][j]=true; //현재 위치 방문 체크
					while(!q.isEmpty()) { //q 가 비어있지 않는동안 -> 즉, 이동할 육지가 남아있는 동안
						Pair tmp=q.poll(); // 첫번째로 들어가있는 좌표 pop

						for(int d=0;d<8;d++) { //전 방향을 돈다
							int dx=tmp.x+dir[d][0]; //이동할 x 좌표
							int dy=tmp.y+dir[d][1]; //이동할 y 좌표
							
							//1) map의 범위 안에 있으면서 2) 방문하지 않았고 3) 육지라면 -> 섬에 포함시킨다!!!!
							if(dx>=0 && dx<h && dy>=0 && dy<w && !visited[dx][dy] && map[dx][dy]==1) {
								visited[dx][dy]=true; //이동해서 방문 체크
								q.add(new Pair(dx,dy)); //이동가능하므로 q에 넣기
							}
						}
						
						if(q.size()==0) { //q를 다 빼서 더이상 이동할 곳이 없으면
							cnt+=1; //섬의 개수가 하나 증가한다
						}
					}
					
				}
			}
		}

	}

}
