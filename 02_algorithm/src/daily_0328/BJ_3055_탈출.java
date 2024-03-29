package daily_0328;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_3055_탈출
 * 난이도: G4
 * 수행결과: 맞았습니다!!
 * 메모리: 11768 KB
 * 시간: 80 ms
 * 코드길이: 1822 B
 * 풀이 전략: 다중 bfs - 토마토와 비슷한 문제. bfs를 동시다발적으로 여러 좌표에서 시작하는 문제.
 * 			이럴 때는 queue에 넣는 순서만 조정해주면 된다. 동시이긴 하지만, 물을 먼저 채워주면
 * 			고슴도치는 이동할 수 없다. 
 * 
 *  */

public class BJ_3055_탈출 {
	
	public static int r,c; //행열 크기
	public static char[][] map; //지도 배열
	public static int[][] visited; //이동 시간 배열
	public static Queue<int[]> q=new ArrayDeque<>(); //bfs에서 사용할 queue
	public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; //방향 벡터
	

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		
		map=new char[r][c];
		visited=new int[r][c];
		int[] dest=new int[2]; //목표지점(비버) 좌표
		int[] start=new int[2]; //시작지점(고슴도치) 좌표
		
		//map 입력받기
		for(int i=0;i<r;i++) {
			String s=br.readLine();
			map[i]=s.toCharArray();
			for(int j=0;j<c;j++) {
				if(map[i][j]=='D') {
					dest[0]=i;
					dest[1]=j;
				}else if(map[i][j]=='*') {
					q.add(new int[] {i,j}); //물이 먼저 이동해야 하므로 물 먼저 q에 넣기
				}else if(map[i][j]=='S') {
					start[0]=i;
					start[1]=j;
				}
			}
		}
		
		q.add(new int[] {start[0],start[1]}); //고슴도치 좌표 마지막에 넣기
		
		bfs(dest[0],dest[1]); //bfs 호출
		
	}
	
	public static void bfs(int destx,int desty) {
		while(!q.isEmpty()) {
			int[] tmp=q.poll();
			int x=tmp[0];
			int y=tmp[1];
			
			if(x==destx && y==desty) { //도착지점에 도착했다면
				System.out.println(visited[x][y]); //이동한 시간 출력
				return;
			}
			
			for(int d=0;d<4;d++) {
				int dx=x+dir[d][0];
				int dy=y+dir[d][1];
				
				if(dx<0 || dx>=r || dy<0 || dy>=c) continue; //배열 범위 밖이라면 패스
				
				if(map[x][y]=='*') { //현재 위치가 물이라면
					if(map[dx][dy]=='.') { //비어있는 곳만 이동 가능
						map[dx][dy]='*'; //물로 채우기
						q.add(new int[] {dx,dy}); //이동
						
					}
				}else if(map[x][y]=='S') { //현재 위치가 고슴도치라면
					if(map[dx][dy]=='.' || map[dx][dy]=='D') { //비어있는 곳, 비버 집으로 이동 가능
						map[dx][dy]='S'; //고슴도치 이동하기
						q.add(new int[] {dx,dy}); //이동
						visited[dx][dy]=visited[x][y]+1; //이동 시간 표시
					}
				}
				
			}
			
		}
		
		System.out.println("KAKTUS"); //도착지점에 도착하지 못했을 때
		return;
	}
	

}
