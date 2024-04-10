package daily_0409;

import java.io.*;
import java.util.*;

public class BJ_19236_청소년상어 {
	
	static class Fish implements Comparable<Fish>{
		int x,y,d,num; //좌표, 방향
		boolean isShark; //상어 유무
		boolean isLive; //생존 유무
		
		public Fish(int x,int y,int d, int num,boolean isShark,boolean isLive) {
			super();
			this.x=x;
			this.y=y;
			this.d = d;
			this.num=num;
			this.isShark = isShark;
			this.isLive=isLive;
		}

		@Override
		public int compareTo(Fish o) {
			return this.num-o.num; //오름차순
		}
		
	}
	
	
	static int[][] dir= {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}}; //상부터 반시계 방향(1~8)
	static Fish[][] map;
	static int sumFish=0; //상어가 먹을 수 있는 물고기 번호의 합의 최댓값
	static int N=4;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		map=new Fish[N][N];
		
		for(int i=0;i<4;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<4;j++) {
				int a=Integer.parseInt(st.nextToken()); //물고기의 번호
				int b=Integer.parseInt(st.nextToken()); //방향
				map[i][j]=new Fish(i,j,b-1,a,false,true); //좌표+방향+번호+상어유무+생존유무
			}
		}
				
		//상어가 (0,0)에 들어감
		map[0][0].isShark=true;

		//물고기 이동
		move();
		
		
	}
	
	public static void move() {
		
		//pq에 넣으면 num 오름차순으로 자동 정렬
		PriorityQueue<Fish> pq=new PriorityQueue<>();
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				pq.add(map[i][j]);
			}
		}
		
		while(!pq.isEmpty()) {
			Fish tmp=pq.poll();
			int x=tmp.x;
			int y=tmp.y;
			System.out.println(map[x][y].num+" 이동!!!");
			if(map[x][y].isShark) continue; //현재 위치가 상어면 pass
			
			for(int d=0;d<8;d++) { //반복만 하는 것
				int dx=x+dir[map[x][y].d][0];
				int dy=y+dir[map[x][y].d][1];
				
				//배열 벗어낫거나 상어라면 방향 바꿔준다
				if(dx<0 || dx>=N || dy<0 || dy>=N || map[dx][dy].isShark) {
					map[x][y].d=(map[x][y].d+1)%8;
					continue;
				}
				
				//이동하려는 좌표에 물고기가 존재한다면
				if(map[dx][dy].isLive) {
					int dNum=map[dx][dy].num;
					int dD=map[dx][dy].d;
					
					map[dx][dy].num=map[x][y].num;
					map[dx][dy].d=map[x][y].d;
					
					map[x][y].num=dNum;
					map[x][y].d=dD;
					
				}else { //물고기 없다면 이동만 하면 된다
					map[dx][dy].num=map[x][y].num;
					map[dx][dy].d=map[x][y].d;
					map[dx][dy].isLive=true;
				}
				
				
				for(int i=0;i<N;i++) {
					for(int j=0;j<N;j++) {
						System.out.print(map[i][j].num+","+map[i][j].d+"\t");
					}
					System.out.println();
				}
				System.out.println("----------------");
				break;
			}
			
			
		}
		
		System.out.println("최종");
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(map[i][j].num+","+map[i][j].d+"\t");
			}
			System.out.println();
		}
		
		
	}

}











