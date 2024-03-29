package daily_0329;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_2573_빙산
 * 난이도: G4
 * 수행결과: 맞았습니다!!
 * 메모리: 162216 KB
 * 시간: 604 ms
 * 코드길이: 2434 B
 * 풀이 전략: bfs - 구역 개수 세기
 * 			1) 빙산 녹는 함수
 * 			2) 빙산의 개수 세는 함수 - bfs
 * 			이렇게 2개의 함수로 나누어서 계속 탐색하면 된다.개념 자체는 어렵지 않지만 잘 조합하는 문제.
 * 			bfs로 구역 개수 세기는 자주 나오니까 눈감고도 풀 수 있어야한다!
 * 
 * 주의 사항: 처음에 "다 녹을때까지 분리되지 않으면 0 출력"의 조건을 잊고 있었다. 문제 똑바로 읽고 미리 틀 짜놓기.
 * 
 *  */


public class BJ_2573_빙산 {
	
	public static int r,c;
	public static int[][] map;
	public static boolean[][] visited;
	public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		map=new int[r][c];
		
		for(int i=0;i<r;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<c;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		int ice=1; //빙산의 개수
		int time=1; //시간
		
		while(true) { 
			
			ice=melt(); //1년이 지나 녹은 후 빙산의 개수
			
			if(ice>1) { //2개 이상이면
				System.out.println(time); //시간 출력
				return;
			}
			
			int flag=0; //빙산 존재 유무 flag
			for(int i=0;i<r;i++) {
				for(int j=0;j<c;j++) {
					if(map[i][j]>0) flag++;
				}
			}
			
			if(flag==0) { //다 녹았다면 빙산이 존재하지 않으므로
				System.out.println(0); //0출력
				return;
			}
			
			time++; //시간 1년 지남
		}
	
	}
	
	//1년이 지나 빙산 녹는 함수
	public static int melt() {
		
		//기존 map 배열과 녹은 melted 배열을 구분해야 한다.
		//기존 map 배열을 기준으로 동서남북에서 바다와 맞닿아있는 갯수를 구하기 때문.
		//다 구한 후 기존 map 배열을 melted 배열로 update
		
		int[][] melted=new int[r][c];
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(map[i][j]>0) {
					int melting=0;
					for(int d=0;d<4;d++) {
						int dx=i+dir[d][0];
						int dy=j+dir[d][1];
						if(dx<0 || dx>=r || dy<0 || dy>=c) continue;
						if(map[dx][dy]==0) melting++;
					}
					melted[i][j]=map[i][j]-melting; //녹은 후의 빙산 높이 저장
					if(melted[i][j]<0) melted[i][j]=0; //다 녹았다면 0으로 초기화
				}
				
	
			}
		}
	 
		int ice=0;
		visited=new boolean[r][c];
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(melted[i][j]>0 && !visited[i][j]) { //빙산이 존재하면서 방문하지 않았다면
					ice++; //구역 개수+1
					bfs(melted,visited,i,j); //1년이 지나 녹은 후 map으로 bfs 통해 구역 찾기
				}
			}
		}
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				//System.out.print(melted[i][j]);
				map[i][j]=melted[i][j]; //기존 map 배열 update
			}
			//System.out.println();
		}

		return ice; //최종 구역 개수
		
	}

	
	//빙산 갯수 세는 함수
	public static void bfs(int[][] map,boolean[][] visited,int startx,int starty) {
		Queue<int[]> q=new ArrayDeque<>();
		
		q.add(new int[] {startx,starty});
		visited[startx][starty]=true;
		
		while(!q.isEmpty()) {
			int[] tmp=q.poll();
			for(int d=0;d<4;d++) {
				int dx=tmp[0]+dir[d][0];
				int dy=tmp[1]+dir[d][1];
				if(dx<0 || dx>=r || dy<0 || dy>=c) continue;
				if(map[dx][dy]>0 && !visited[dx][dy]) { //빙산이 존재하면서 방문하지 않았다면
					q.add(new int[] {dx,dy});
					visited[dx][dy]=true;
				}
			}
			
		}
	}
	
	

}
