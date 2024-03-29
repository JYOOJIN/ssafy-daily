package daily_0216;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_1227_미로2
 * 난이도: D4
 * 수행결과: Pass
 * 메모리: 20536 KB
 * 시간: 134 ms
 * 코드길이: 1701 B
 * 풀이 전략: <bfs>: 재귀 쓰면 안된다. queue 사용. 
 * 			queue가 텅 비어버리면 더이상 이동할 좌표가 없다는 뜻인데, 그 전에 도착점에 도착하면 1 출력, 못찾으면 0 출력
 *
 * 주의사항: maze 배열을 char로 했고, 이동할 수 있는 경우는 값이 '0','2','3'인 경우 가능하다는 점!!
 * 			
 */

public class SW_1227_미로2 {
	
	public static int SIZE=100;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1;t<=10;t++) {
			br.readLine(); //테스트케이스
			char[][] maze=new char[SIZE][SIZE]; //미로 배열 생성
			boolean[][] visited=new boolean[SIZE][SIZE]; //미로 방문 배열 생성
			
			//미로는 charArray
			for(int i=0;i<SIZE;i++) {
				maze[i]=br.readLine().toCharArray();
			}
			
			Pair start = new Pair(); //시작 좌표
			Pair end=new Pair(); //끝 좌표
			
			for(int i=0;i<SIZE;i++) {
				for(int j=0;j<SIZE;j++) {
					if(maze[i][j]=='2') { //시작좌표 찾음
						start.x=i;
						start.y=j;
					}else if(maze[i][j]=='3') { //끝좌표 찾음
						end.x=i;
						end.y=j;
					}
				}
			}
			
			System.out.print("#"+t+" "); 
			bfs(maze,visited,start,end); //bfs 호출

		}

	}
	
	//x,y좌표 담는 클래스
	static class Pair{
		int x;
		int y;
		
		public Pair() {
			
		}
		
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	//경로 여부 구하는 bfs
	public static void bfs(char[][] maze, boolean[][] visited,Pair start,Pair end) {
		
		Queue<Pair> q=new ArrayDeque<>(); //pair,즉 좌표 담는 queue 생성

		q.add(start); //시작점 q에 넣기
		visited[start.x][start.y]=true; //시작점 방문표시
		
		int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; //상우하좌 방향벡터
		
		while(!q.isEmpty()) { //아직 이동할 곳이 남아있을 때
			
			Pair p=q.poll(); //q의 맨앞 좌표 pop
			
			if(p.x==end.x && p.y==end.y) { //현재 좌표가 end 좌표와 같다면 탈출한것
				System.out.println(1); //경로 존재 
				return; //빠져나오기
			}
			
			for(int d=0;d<4;d++) { //상하좌우 4 방향을 모두 살핀다
				int dx=p.x+dir[d][0]; //이동한 x 좌표
				int dy=p.y+dir[d][1]; //이동한 y 좌표
				
				//이동한 좌표가 maze안에 존재하면서, 방문한적이 없으면서, 이동할 곳이 0(길)이나 3(도착지) 일경우
				if(dx>=0 && dx<SIZE && dy>=0 && dy<SIZE && !visited[dx][dy] && (maze[dx][dy]=='0' || maze[dx][dy]=='3')) {
					visited[dx][dy]=true; //이동해서 방문표시
					q.add(new Pair(dx,dy)); //q에 이동한 좌표 넣는다
				}
			}
			
		}
		
		System.out.println(0); //경로를 찾지 못했을 때
		return;

		
	}

}
