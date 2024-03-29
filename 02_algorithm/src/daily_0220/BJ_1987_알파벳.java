package daily_0220;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_1987_알파벳
 * 난이도: G4
 * 수행결과: 맞았습니다!!
 * 메모리: 12368 KB
 * 시간: 868 ms
 * 코드길이: 1323 B
 * 풀이 전략: 지나온 경로를 구해야 하므로 <DFS>
 * 			지나온 알파벳을 visited로 체크하여 방문했던 알파벳이면 방문하지 않고, 아직 방문 안했으면 재귀호출 통해 방문한다.
 * 			방문할때 방문한칸+1해서 넘겨주고, 더이상 갈곳이 없으면 max와 cnt 비교한 후 return.
 * 
 * 주의사항: 방문한 후 빠져나올 때, visited를 초기화 해주어야 한다. 하나의 경로가 끝나는 지점에 주의하자. 
 * 		  class 가 메모리를 많이 소비한다. 또한 return을 통해서 탐색이 끝나면 빠져나오도록 하자. 
 * 		  최적화를 통해 메모리를 (293356 -> 12368)로, 시간을 (1680 -> 868) 로 줄였다.
 */

public class BJ_1987_알파벳 {
	
	public static int max,r,c; //최대의 칸수, 배열 크기
	public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; //상하좌우 방향 벡터

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		
		char[][] arr=new char[r][c]; //보드 생성
		boolean[] visited=new boolean[26]; //알파벳 방문 확인
		
		//보드에 알파벳 입력받기
		for(int i=0;i<r;i++) {
			arr[i]=br.readLine().toCharArray();
		}
		
		//시작 위치는 (0,0)이고 시작 칸도 지나는 칸에 포함하믈 1로 시작
		dfs(arr,visited,0,0,1);
		System.out.println(max);

	}
	
	/**
	 * 깊이 우선 탐색
	 * @param arr 보드
	 * @param visited
	 * @param x 현재 x좌표
	 * @param y 현재 y좌표
	 * @param cnt 현재 이동한 칸수
	 */
	public static void dfs(char[][] arr,boolean[] visited,int x,int y,int cnt) {

		visited[arr[x][y]-'A']=true; //현재 구역 알파벳 방문 체크. char -> int 로 만드는 방법.
		
		for(int d=0;d<4;d++) { //사방탐색
			int dx=x+dir[d][0]; 
			int dy=y+dir[d][1];
			
			//이동할 좌표가 배열안에 있으면서 아직 방문하지 않은 알파벳이라면
			if(dx>=0 && dx<r && dy>=0 && dy<c && !visited[arr[dx][dy]-'A']) {
				//****매우 중요한 파트****
				dfs(arr,visited,dx,dy,cnt+1); //그 방향으로 방문
				visited[arr[dx][dy]-'A']=false; //끝까지 방문하고 다시 빠져나올 때 빠져나온 구역의 알파벳은 다시 사용할 수 있다
			}
		}
		
		//****여기가 한 경로가 끝났을 때****
		
		if(cnt>max) max=cnt; //하나의 길을 끝까지 빠져나왔다면 cnt와 max를 비교
		
		return; //갈곳이 없으면 이 깊이는 끝난것이므로 return
		
	}

}
