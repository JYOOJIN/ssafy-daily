package daily_0220;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_4963_섬의 개수
 * 난이도: S2
 * 수행결과: 맞았습니다!!
 * 메모리: 13328 KB
 * 시간: 120 ms
 * 코드길이: 1581 B
 * 풀이 전략: <dfs> 재귀사용 
 * 
 * 주의사항: dfs, bfs 둘다 사용할 수 있는 문제이다. 
 */

public class BJ_4963_섬의개수 {
	
	
	public static int w,h,island; //배열크기, 섬의 개수
	public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1},{-1,-1},{-1,1},{1,1},{1,-1}}; //8방 방향벡터

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while(true) {
			st=new StringTokenizer(br.readLine());
			w=Integer.parseInt(st.nextToken());
			h=Integer.parseInt(st.nextToken());
			
			if(w==0 && h==0) break; //0 0 입력하면 끝
			 
			island=0; //테스트케이스 마다 초기화
			
			int[][] map=new int[h][w]; //지도 생성
			boolean[][] visited=new boolean[h][w]; //방문 배열 생성
			
			//지도에 값 입력
			for(int i=0;i<h;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<w;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			//섬마다 떨어져 있기 때문에 배열 전체 탐색이 필요하다
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++) {
					//방문하지 않았으면서 땅이라면
					if(!visited[i][j] && map[i][j]==1) {
						dfs(map,visited,i,j); //dfs 호출하여 섬 구하기
						island+=1; //섬 개수 +1
					}
				}
			}
			System.out.println(island);
			
		}

	}
	
	
	public static void dfs(int[][] map, boolean[][] visited,int x,int y) {
		
		visited[x][y]=true; //현재 땅 방문 체크
		
		for(int d=0;d<8;d++) { //8방 탐색
			int dx=x+dir[d][0];
			int dy=y+dir[d][1];
			
			//배열 범위 안에 있으면서 방문하지 않았고, 땅이라면
			if(dx>=0 && dx<h && dy>=0 && dy<w && !visited[dx][dy] && map[dx][dy]==1) {
				dfs(map,visited,dx,dy); //그 곳으로 이동
			}
			
		}
		
		return; //더이상 갈곳이 없으면 return
	
	}

}
