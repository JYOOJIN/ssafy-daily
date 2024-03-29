package daily_0216;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_1868_파핑파핑 지뢰찾기
 * 난이도: D4
 * 수행결과: Pass
 * 메모리: 45992 KB
 * 시간: 245 ms
 * 코드길이: 2090 B
 * 풀이 전략: <bfs>로 0일때의 영역을 넓혀나가야하다.
 * 			생각보다는 쉬운 문제였는데 조건들을 잘 따져야한다.
 * 			먼저 배열을 돌며 '0'일때 bfs 이용해서 탐색하며 click++
 * 			두번째로 0을 클릭했을 때 열리지 않는 곳 탐색하며 click++
 * 
 * 			arr에 지뢰찾기 맵 정보를 저장하고 이 arr은 건드리지 않는다. 
 * 			실제로 방문 여부와 지뢰의 수를 담는 배열은 visited로 하였다.
 */

public class SW_1868_파핑파핑지뢰찾기 {
	
	public static int click,n; //클릭수, 배열 크기
	public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1},{-1,-1},{-1,1},{1,1},{1,-1}}; //8방 방향벡터
	
	//x,y좌표 담을 클래스
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
		
		int tc=Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			n=Integer.parseInt(br.readLine());
			
			char[][] arr=new char[n][n]; 
			int[][] visited=new int[n][n]; //지뢰의 수가 나타난다. 
			
			
			for(int i=0;i<n;i++) {
				arr[i]=br.readLine().toCharArray(); //지뢰찾기 맵을 입력받는다
				Arrays.fill(visited[i], -1); //방문 배열 초기화
			}
			
			click=0; //testcase마다 반드시 초기화!!!!!
	
			//1차 탐색
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					//방문하지 않고 지뢰가 아니면서, 주변에 지뢰가 없는 경우
					//여기서부터 퍼져나가기(bfs)시작해야한다
					if(visited[i][j]<0 && arr[i][j]=='.' && findBomb(arr, i, j)==0) {
						bfs(arr,visited,i,j);
					}
					
				}
			}
			
			//2차 탐색
			//0으로 열린 땅 제외하고 탐색
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(visited[i][j]<0 && arr[i][j]=='.') { //방문하지 않은 땅 방문
						visited[i][j]=findBomb(arr, i, j);
						click+=1;
					}
					
				}
			}
			
			//형식에 맞춰 출력
			System.out.print("#"+t+" ");
			System.out.println(click);
			
		}

	}
	
	//8방 탐색하여 주변 지뢰의 수 반환
	public static int findBomb(char[][] arr,int x,int y) {
		int bomb=0;
		for(int d=0;d<8;d++) {
			int dx=x+dir[d][0];
			int dy=y+dir[d][1];
			
			if(dx>=0 && dx<n && dy>=0 && dy<n && arr[dx][dy]=='*') {
				bomb+=1;
			}
		}
		
		return bomb;
		
	}
	
	//주변지뢰가 0인 곳에서 시작하여, 넓혀가다가 지뢰가 있는 땅을 만나면 그 방향으로는 더이상 넓히지 않음
	public static void bfs(char[][] arr, int[][] visited,int i,int j) {
		Queue<Pair> q=new ArrayDeque<>(); //queue 생성
		
		q.add(new Pair(i,j)); //현재 좌표 q에 add
		visited[i][j]=0; //시작지점은 지뢰가 0개이다
		
		while(!q.isEmpty()) {
			
			Pair tmp=q.poll(); //q에서 pop
			int bomb=findBomb(arr, tmp.x, tmp.y); //pop한 위치의 주변 지뢰 수
			if(bomb>0) continue; //주변에 지뢰가 있다면 더 탐색하지 않는다.
			
			//현재 위치, 즉 tmp 주변에 지뢰가 없을때 8방 탐색한다
			for(int d=0;d<8;d++) {
				
				int dx=tmp.x+dir[d][0];
				int dy=tmp.y+dir[d][1];
				
				//이동한 좌표가 범위안에 있으면서 방문하지 않았고 지뢰가 아닐때
				if(dx>=0 && dx<n && dy>=0 && dy<n && visited[dx][dy]<0 && arr[dx][dy]=='.') {
					q.add(new Pair(dx,dy)); //방문해서 q에 add
					visited[dx][dy]=findBomb(arr, dx, dy); //방문한 곳 지뢰 개수
				}
			}
			
			
		}
		click+=1; //한번의 bfs(탐색)을 마치면 click++

	}

}

