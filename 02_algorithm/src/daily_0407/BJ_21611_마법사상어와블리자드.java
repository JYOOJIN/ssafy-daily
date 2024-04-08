package daily_0407;

import java.io.*;
import java.util.*;

public class BJ_21611_마법사상어와블리자드 {
	
	static int n,m,d,s;
	static Point[][] map;
	static Point shark;
	static int[][] blizard;
	static int[][] dir= {{0,0},{-1,0},{1,0},{0,-1},{0,1}}; //1:상 2:하 3:좌 4:우
	static int[][] snail= {{0,-1},{1,0},{0,1},{-1,0}}; //좌 하 우 상
	static int m1,m2,m3; //각각 폭발한 1번,2번,3번 구슬 개수
	
	static class Point{
		
		int x,y; //좌표
		int num; //구슬 번호
		
		public Point(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}
	
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken()); //맵 크기
		m=Integer.parseInt(st.nextToken()); //블리자드 시전
		
		map=new Point[n][n]; //0부터 n-1 까지
		blizard=new int[m][2];  //m 개의 공격(d:방향, s: 거리)
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=new Point(i,j,Integer.parseInt(st.nextToken()));
				
			}
		}
		
		
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			blizard[i][0]=Integer.parseInt(st.nextToken()); //방향
			blizard[i][1]=Integer.parseInt(st.nextToken()); //거리
		}
		
		shark=new Point((n+1)/2-1, (n+1)/2-1,4); //상어 좌표는 고정. 구슬이 이동하지 못하도록 최대 구슬 숫자보다 높게 설정.
		System.out.println("shark:"+shark.x+","+shark.y);
		map[shark.x][shark.y].num=4;
		
		//-----------------------------입력 끝
		
		
		for(int i=0;i<m;i++) { //블리자드 m 번 시전
			
			//-----구슬 파괴 시작
			int dx=shark.x;
			int dy=shark.y;
			int d=blizard[i][0]; //방향
			
			for(int s=0;s<blizard[i][1];s++) {
				dx+=dir[d][0]; //x 좌표 이동
				dy+=dir[d][1]; //y 좌표 이동
				
				map[dx][dy].num=0; //구슬 제거
			}
			//-----구슬 파괴 끝
			print(map);
			//-----빈칸으로 이동 시작(안에서부터 빙글빙글)
			int x=shark.x;
			int y=shark.y;
			int now=0;
			d=0;
			
			for(int round=0;round<n-1;round++) {
				System.out.println("방향:"+d);
				for(int j=0;j<round+1;j++) { //좌, 우
					now=map[x][y].num;
					dx=x+snail[d][0];
					dy=y+snail[d][1];
					System.out.println("dx,dy: "+dx+","+dy);
					if(now==0) {
						map[x][y].num=map[dx][dy].num;
						map[dx][dy].num=0;
						print(map);
					}
					x=dx;
					y=dy;
				}
				d=(d+1)%4;
				System.out.println("방향:"+d);
				for(int j=0;j<round+1;j++) { //하, 상
					now=map[x][y].num;
					dx=x+snail[d][0];
					dy=y+snail[d][1];
					System.out.println("dx,dy: "+dx+","+dy);
					if(now==0) {
						map[x][y].num=map[dx][dy].num;
						map[dx][dy].num=0;
						print(map);
					}
					x=dx;
					y=dy;
				}
				d=(d+1)%4;
			}
			
			//마지막 줄
			for(int round=0;round<n-1;round++) {
				now=map[x][y].num;
				dx+=snail[d][0];
				dy+=snail[d][1];
				if(now==0 && map[dx][dy].num==0) break; 
				System.out.println("dx,dy: "+dx+","+dy);
				if(now==0) {
					map[x][y].num=map[dx][dy].num;
					map[dx][dy].num=0;
				}
			}
			
			print(map);
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
	}
	
	
	public static void print(Point[][] arr) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(arr[i][j].num);
			}
			System.out.println();
		}
		System.out.println("-----------");
		
		/*
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print("("+arr[i][j].x+","+arr[i][j].y+")");
			}
			System.out.println();
		}
		*/
	}

}
