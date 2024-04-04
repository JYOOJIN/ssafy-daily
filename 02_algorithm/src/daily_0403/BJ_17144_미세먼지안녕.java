package daily_0403;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_17144_미세먼지안녕
 * 난이도: G4
 * 수행결과: 맞았습니다!!
 * 메모리: 117168 KB
 * 시간: 368 ms
 * 코드길이: 4197 B
 * 풀이 전략: 1) 확산 - 동시 bfs
 * 			  time을 기준으로, time 별로 해야하는 일이 있다면 현재 q.size를 구해서 그 size만큼만 q를 빼내고 time++한다.
 * 		   2) 공기청정기 - 배열 돌리기 
 * 			  돌리는 범위와 prev,next를 이해한다면 쉽게 돌릴 수 있다. 방향이 반복된다는 점에 주의.
 * 
 * 주의사항: 어디서 copyMap을 써야할지, 어디서 어떤 배열을 가져와야 할지가 매우 중요하다!!!!!
 * 		  (a맵 기준으로 b맵에다가 확산한다 -> b맵 기준으로 공기청정기 작동 -> b맵이 a맵이 된다)
 * 
 *  */


public class BJ_17144_미세먼지안녕 {
	
	//좌표와 미세먼지 양 담을 클래스
	static class Point{
		int x,y,dust;

		public Point(int x, int y, int dust) {
			super();
			this.x = x;
			this.y = y;
			this.dust = dust;
		}
		
	}
	
	
	static int r,c,t;
	static Point[] start=new Point[2]; //공기청정기 위치 담을 배열
	static Queue<Point> q; //bfs에서 사용할 queue
	static int[][] dir1= {{0,1},{-1,0},{0,-1},{1,0}}; //반시계 방향 벡터
	static int[][] dir2= {{0,1},{1,0},{0,-1},{-1,0}}; //시계 방향 벡터
	

	public static void main(String[] args) throws Exception{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		t=Integer.parseInt(st.nextToken());
		
		int[][] map=new int[r][c];
		q=new ArrayDeque<>();
		int index=0;
		
		for(int i=0;i<r;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<c;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==-1) start[index++]=new Point(i,j,-1); //시작점
				if(map[i][j]>0) q.add(new Point(i,j,map[i][j])); //미세먼지 존재하면 q에 넣기
			}
		}
		
		//t초 동안 (확산+공기청정기 가동)
		bfs(map);
		
		int ans=countDust(map); //남은 미세먼지 수 구하기
		System.out.println(ans); //출력
		
	}
	
	//배열에 남은 미세먼지 count
	static int countDust(int[][] arr) {
		int ans=0;
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(arr[i][j]!=-1) ans+=arr[i][j];
			}
		}
		
		return ans;
	}
	
	//디버깅 용 배열 출력 함수
	static void print(int[][] arr) {
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				System.out.print(arr[i][j]+"  ");
			}
			System.out.println();
		}
		System.out.println("--------------------");
	}
	
	//배열 깊은 복사
	static void copy(int[][] map,int[][] newMap) {
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				newMap[i][j]=map[i][j];
			}
		}
	}
	
	//공기청정기 가동
	static void move(int[][] map) {

		moveUp(map); //윗칸 반시계 방향 가동
		moveDown(map); //아랫칸 시계 방향 가동

	}

	//공기청정기 윗칸 가동
	private static void moveUp(int[][] map) {
		
		int x=start[0].x; //시작 x좌표
		int y=start[0].y; //시작 y좌표
		int prev=0;
		int d=0; //방향 변수
		boolean arrive=false; //한바퀴 돌았다는 체크용
		
		while(true) {
			if(arrive) break; //공기청정기 기준으로 한바퀴 돌았다면
			d=d%4; //방향벡터 설정
			
			while(true) { //배열 안에 있을동안 해당 방향으로 킵 고잉
				
				int dx=x+dir1[d][0];
				int dy=y+dir1[d][1];
				
				if(dx<0 || dx>start[0].x || dy<0 || dy>=c) break; //범위 벗어나면 방향 바꾼다
				
				int next=map[dx][dy];
				if(next==-1) { //공기 청정기 만나면 한바퀴 체크 해주고 빠져나감
					arrive=true;
					map[dx][dy]=-1;
					break;
				}
				map[dx][dy]=prev;
				prev=next;	
				x=dx;
				y=dy;
			}
			d++; //방향 바꿈
		}
	}
	
	//공기청정기 아랫칸 가동
	private static void moveDown(int[][] map) {
		int x=start[1].x;
		int y=start[1].y;
		int prev=0;
		int d=0;
		boolean arrive=false;
		
		while(true) {
			if(arrive) break;
			d=d%4;
			while(true) { //배열 안에 있을동안 해당 방향으로 킵 고잉
				
				int dx=x+dir2[d][0];
				int dy=y+dir2[d][1];
				if(dx<start[1].x || dx>=r || dy<0 || dy>=c) break;
				int next=map[dx][dy];
				if(next==-1) {
					arrive=true;
					map[dx][dy]=-1;
					break;
				}
				map[dx][dy]=prev;
				prev=next;	
				x=dx;
				y=dy;
			}
			d++;
		}
	}

	//확산
	//기준맵을 기준으로 새맵에 담아야 하는데...기준맵으로 쭉 하면 확산에 오차가 생김.음수값이 나오게됨.그러면 안됨.
	//map을 가지고 확산(newMap에 담기) -> newMap기준으로 공기청정기 작동 -> 옮겨진 newMap이 새로운 map이 되어 반복
	static void bfs(int[][] map) {
		int time=0;
		while(!q.isEmpty()) { //q가 비는건 빈칸없이 확산했을 때이다. 우리는 그전에 시간이 끝나면 나가야 한다.
			while(time!=t) { //시간이 다되면 빠져나간다.
				int[][] newMap=new int[r][c];
				copy(map,newMap); //newMap에다가 map을 복사함
				int size=q.size(); //1초 동안 돌 q의 size 
				for(int step=0;step<size;step++) { //1초 동안 확산
					Point tmp=q.poll();
					int plus=tmp.dust/5;
					
					for(int d=0;d<4;d++) {
						int dx=tmp.x+dir1[d][0];
						int dy=tmp.y+dir1[d][1];
						
						//배열을 벗어났거나, 공기청정기가 있으면 확산 x
						if(dx<0 || dx>=r || dy<0 || dy>=c || map[dx][dy]==-1) continue;
						
						newMap[dx][dy]+=plus;
						newMap[tmp.x][tmp.y]-=plus;
						
					}
		
				}
				//확산을 끝냄
				
				//현재 map에서 공기청정기 작동
				move(newMap);
				
				//map에다가 newMap을 복사함
				copy(newMap,map); 
				
				//미세먼지 존재하는 칸을 새롭게 q에 담는다
				q=new ArrayDeque<>();
				for(int i=0;i<r;i++) {
					for(int j=0;j<c;j++) {
						if(map[i][j]>0) q.add(new Point(i, j, map[i][j]));
					}
				}
				
				//시간 증가
				time++;
			}
			
			//시간 다되어 빠져나오면 끝난다
			return; 
			
		}
		
	}
	
	

}
