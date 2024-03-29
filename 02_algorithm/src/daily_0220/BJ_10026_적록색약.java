package daily_0220;

import java.io.*;

/**
 * @author 정유진
 * 문제: BJ_10026_적록색약
 * 난이도: G5
 * 수행결과: 맞았습니다!!
 * 메모리: 13472 KB
 * 시간: 92 ms
 * 코드길이: 2112 B
 * 풀이 전략: 적록색약 일때와 아닐때의 DFS를 따로 구했다. BFS로도 풀수는 있는 문제.
 * 			DFS가 이해는 조금 더 어렵지만 훨씬 간결하다.
 * 			같은 색이면 방문하고(->재귀호출) 아니라면 더이상 방문하지 않는다 (-> 재귀호출 x)
 * 
 */

public class BJ_10026_적록색약 {

	public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; //상하좌우 방향 벡터
	
	//x,y 좌표 담을 클래스
	static class Pair{
		
		int x;
		int y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine()); //배열의 크기 
		char[][] color=new char[n][n]; 
		
		//RGB 입력받기
		for(int i=0;i<n;i++) {
			color[i]=br.readLine().toCharArray();
		}
		
		
		int part1=0; //비적록색약
		int part2=0; //적록색약
		
		boolean[][] visited=new boolean[n][n]; //비적록색약 방문 배열
		boolean[][] visited2=new boolean[n][n]; //적록색약 방문 배열
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				if(!visited[i][j]) { //방문을 하지 않았다면
					RGB(color,visited,new Pair(i,j)); //비적록색약 DFS 호출
					part1+=1; //구역+1
				}
				if(!visited2[i][j]) { //방문을 하지 않았다면
					RB(color,visited2,new Pair(i,j)); //적록색약 DFS 호출
					part2+=1; //구역+1
				}
			}
		}
		
		System.out.println(part1+" "+part2);
	}
	
	//적록색약 X
	public static void RGB(char[][] color,boolean[][] visited,Pair current) {
		
		char c=color[current.x][current.y]; //현재 구역의 색
		
		int n=color[0].length;
		int x=current.x;
		int y=current.y;
		
		visited[x][y]=true; //현재 구역 방문 체크
		
		for(int d=0;d<4;d++) { //상하좌우 탐색
			int dx=x+dir[d][0];
			int dy=y+dir[d][1];
			
			//이동한 곳의 좌표가 배열 범위 안에 있으면서 방문한적이 없고 현재 구역과 같은 색깔일 때 이동
			if(dx>=0 && dx<n && dy>=0 && dy<n && !visited[dx][dy] && color[dx][dy]==c) {
				RGB(color,visited,new Pair(dx,dy)); //재귀함수 호출
			}
			
		}
		
	}
	
	//적록색약
	public static void RB(char[][] color,boolean[][] visited,Pair current) {
		
		char c=color[current.x][current.y]; //현재 구역의 색
		
		int n=color[0].length;
		int x=current.x;
		int y=current.y;
		
		visited[x][y]=true; //현재 구역 방문 체크
		
		for(int d=0;d<4;d++) { //상하좌우 탐색
			int dx=x+dir[d][0];
			int dy=y+dir[d][1];
			
			if(dx>=0 && dx<n && dy>=0 && dy<n && !visited[dx][dy]) { //이동한 좌표가 배열범위 안에 있으면서 방문한적이 없을 때
				if(c=='R' || c=='G') { //R과 G 일때
					if(color[dx][dy]=='R' || color[dx][dy]=='G') { //R과 G는 같은 색으로 본다
						RB(color,visited,new Pair(dx,dy)); //RB 재귀호출
					}
				}else { //B일때
					if(color[dx][dy]==c) { //이동할 구역의 색이 B면 이동한다
						RB(color,visited,new Pair(dx,dy)); //RB 재귀호출
					}
				}
			}
			
		}
	}

}
