package daily_0407;

import java.io.*;
import java.util.*;

public class BJ_21611_마법사상어와블리자드_2 {
	
	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	static int n,m;
	static int[][] map;
	static int[][] bomb;
	static int[][] dir= {{0,0},{-1,0},{1,0},{0,-1},{0,1}}; // 1:상 2:하 3:좌 4:우
	static int[][] snail={{0,-1},{1,0},{0,1},{-1,0}}; //좌 하 우 상
	static Point[] order;
	static int m1,m2,m3; //폭발한 1번, 2번, 3번 구슬의 개수

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		map=new int[n][n];
		order=new Point[n*n];
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		bomb=new int[m][2];
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			bomb[i][0]=Integer.parseInt(st.nextToken()); //방향
			bomb[i][1]=Integer.parseInt(st.nextToken()); //거리
		}
		
		map[n/2][n/2]=4; //상어 있는 위치 헷갈리니까 4로 표시해두기
		
		//입력받기 끝
		
		//블리자드 날리기
		
		snail(); //순서 정해두기
		for(int i=0;i<m;i++) { //m번 날린다
			
			//구슬파괴 시작
			int x=n/2;
			int y=n/2;
			int d=bomb[i][0];
			for(int s=0;s<bomb[i][1];s++) {
				x+=dir[d][0];
				y+=dir[d][1];
				
				map[x][y]=0;
				
			}
			
			//System.out.println("블리자드던진후");
			//print(map);
			//구슬 파괴 끝
			
			//빈칸 이동하기
			move();
			
			//구슬 폭발하기
			while(explode()) {
				//구슬 폭발 후 이동하기
				move();
			}
			
			//구슬 폭발 끝
			
			//구슬 변화 시작
			grouping();
			
			//System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~");
			
		}
		
		int answer=m1+2*m2+3*m3;
		System.out.println(answer);
	
	}
	
	public static void grouping() {
		
		int[][] copy=new int[n][n];
		int index=1;
		
		int flag=map[order[1].x][order[1].y];
		int cnt=1;
		
		for(int i=2;i<n*n;i++) {
			if(flag==0) break;
			if(index==n*n) break;
			if(flag==map[order[i].x][order[i].y]) {
				cnt++;
			}else {
				copy[order[index].x][order[index].y]=cnt;
				copy[order[index+1].x][order[index+1].y]=flag;
				
				index+=2;
				
				flag=map[order[i].x][order[i].y];
				cnt=1;
				
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				map[i][j]=copy[i][j];
			}
		}
		
		map[n/2][n/2]=4;
		
		//System.out.println("그룹핑 후");
		//print(map);
	}
	
	
	public static boolean explode() {
		
		boolean boom=false;
		int flag=map[order[1].x][order[1].y];
		int cnt=1;
		for(int i=2;i<n*n;i++) {
			if(flag==0) break;
			if(flag==map[order[i].x][order[i].y]) {
				cnt++;
			}else {
				if(cnt>=4) {
					boom=true;
					
					if(flag==1) m1+=cnt;
					else if(flag==2) m2+=cnt;
					else m3+=cnt;
					
					for(int j=i-cnt;j<i;j++) {
						map[order[j].x][order[j].y]=0;
					}
					flag=map[order[i+1].x][order[i+1].y];
					cnt=1;
				}else {
					flag=map[order[i].x][order[i].y];
					cnt=1;
				}
				
			}
		}
		
		//System.out.println("폭발후");
		//print(map);
		
		return boom;
		
		
		
	}
	
	//빈자리 보이면 땡겨오기
	public static void move() {
		
		for(int i=0;i<n*n-1;i++) {
			if(map[order[i].x][order[i].y]==0) { //현재 위치가 0이라면
				int j=i+1;
				while(true) {
					if((order[j].x==0 && order[j].y==0) || map[order[j].x][order[j].y]!=0) {
						map[order[i].x][order[i].y]=map[order[j].x][order[j].y];
						map[order[j].x][order[j].y]=0;
						break;	
					}
					
					j++;
				}
			}
		}
		
		//System.out.println("빈칸 이동 후");
		//print(map);
		
	}
	
	
	//달팽이 순서 order 배열에 저장해두기
	public static void snail() {
		
		int dx=n/2;
		int dy=n/2;
		int d=0;
		int index=0;
		order[index++]=new Point(dx,dy);
		
		for(int round=0;round<n-1;round++) {
			for(int j=0;j<round+1;j++) { //좌, 우
				dx+=snail[d][0];
				dy+=snail[d][1];
				order[index++]=new Point(dx,dy);
			}
			d=(d+1)%4;
			for(int j=0;j<round+1;j++) { //하, 상
				dx+=snail[d][0];
				dy+=snail[d][1];
				order[index++]=new Point(dx,dy);
			}
			d=(d+1)%4;
		}
		
		//마지막 줄
		for(int round=0;round<n-1;round++) {
			dx+=snail[d][0];
			dy+=snail[d][1];
			order[index++]=new Point(dx,dy);
			
		}
		
	}
	
	
	public static void print(int[][] arr) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println("------------");
		
		/*
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print("("+i+","+j+")");
			}
			System.out.println();
		}
		System.out.println("=============");
		*/
	}

}
