package daily_0409;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_16236_아기상어
 * 난이도: G3
 * 수행결과: 맞았습니다!!
 * 메모리: 296332 KB
 * 시간: 880 ms
 * 코드길이: 3676 B
 * 풀이 전략: pq를 이용해서 size 별 정렬을 자동으로 해준다
 * 		   먹으러 가는 거리를 정하기 위해서 bfs를 이용해 거리를 구한다
 * 주의사항: 그런데 메모리랑 시간이 너무 걸린다. x,y,dis를 저장하는 클래스를 만들고 dis 기준으로(같다면 x,y좌표 따져서) pq로 자동정렬하면 더 빠르다.
 *   
 *  */

public class BJ_16236_아기상어_2 {
	
	static class Point implements Comparable<Point>{
		int x,y,size;
		boolean isLive;
		
		public Point(int x, int y, int size,boolean isLive) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.isLive = isLive;
		}

		@Override
		public int compareTo(Point o) {
			return this.size-o.size;
		}
		
		
	}

	static int n,total;
	static int[][] map;
	static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};
	static List<Point> fishList;
	static Point shark;
	static PriorityQueue<Point> pq;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n=Integer.parseInt(br.readLine());
		total=0; //최종 이동한 거리
		map=new int[n][n];
		pq=new PriorityQueue<>();
		
		//map 입력받으면서 물고기 좌표 pq에 넣음
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken()); //물고기 크기
				if(map[i][j]==9) shark=new Point(i,j,2,false); //상어 좌표,사이즈 저장
				else if(map[i][j]>0 && map[i][j]<9) pq.add(new Point(i,j,map[i][j],true)); 
			}
		}
		
		int eat=0;
		while(!pq.isEmpty()) {
			
			fishList=new ArrayList<>();
			//현재 상어보다 크기 작은 물고기 빼낸다
			while(!pq.isEmpty() && pq.peek().size<shark.size) {
				fishList.add(pq.poll());
			}
			
			if(fishList.isEmpty()) break;
			
			int minDis=Integer.MAX_VALUE;
			int minIndex=0;
			boolean flag=false;
			
			//가장 가까운 물고기를 찾는다
			//다 -1이면 갈수가 없으니까 끝내야되는데
			//다 -1인걸 어떻게 하지? 
			for(int i=0;i<fishList.size();i++) {
				int dis=bfs(shark,fishList.get(i));
				if(dis==-1) continue;
				flag=true; 
				if(dis<minDis) {
					minDis=dis;
					minIndex=i;
				}else if(dis==minDis) {
					if(fishList.get(i).x<fishList.get(minIndex).x) {
						minDis=dis;
						minIndex=i;
					}else if(fishList.get(i).x==fishList.get(minIndex).x) {
						if(fishList.get(i).y<fishList.get(minIndex).y) {
							minDis=dis;
							minIndex=i;
						}
					}
					
				}
				
			}
			
			if(!flag) break; //가는 방법이 없으면 더 먹을 수 없으므로 끝
			
			
			total+=minDis;
			
			//상어가 이동하려는 위치
			int dx=fishList.get(minIndex).x;
			int dy=fishList.get(minIndex).y;
			
			map[shark.x][shark.y]=0; //shark가 현재 자리 떠난다
			map[dx][dy]=9; //이동한 자리는 상어라는 표시의 9
			
			eat++;
			if(eat==shark.size) {
				shark.size+=1;
				eat=0;
			}
			
			shark=new Point(dx, dy, shark.size, true);
			
			//먹은 물고기 없앤다
			fishList.remove(minIndex);
			//나머지 물고기 다시 pq에 넣는다
			for(int i=0;i<fishList.size();i++) {
				pq.add(fishList.get(i));
			}
			
		}
		
		System.out.println(total);
		
		
	}
	
	public static int bfs(Point start,Point end) {
		
		int[][] visited=new int[n][n];
		Queue<Point> q=new ArrayDeque<>();
		
		//q 초기 설정
		//start는 언제나 shark
		q.add(start);
		visited[start.x][start.y]=1; //현재 위치 -> 1이므로 최종에서 1빼줘야함!!!

		while(!q.isEmpty()) {
			Point tmp=q.poll();
			
			if(tmp.x==end.x && tmp.y==end.y) {
				return visited[tmp.x][tmp.y]-1;
			}
			
			for(int d=0;d<4;d++) {
				int dx=tmp.x+dir[d][0];
				int dy=tmp.y+dir[d][1];
				
				if(dx<0 || dx>=n || dy<0 || dy>=n || map[dx][dy]>start.size || visited[dx][dy]>0) continue; 
			
				q.add(new Point(dx,dy,start.size,true));
				visited[dx][dy]=visited[tmp.x][tmp.y]+1;
			}
		}
		
		
		return -1; //길이 없다
		
		
	}

}







