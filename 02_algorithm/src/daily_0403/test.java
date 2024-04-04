package daily_0403;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class test {

	static int r,c,t;
	static int[][] dir1= {{0,1},{-1,0},{0,-1},{1,0}}; //반시계 방향 벡터
	static int[][] dir2= {{0,1},{1,0},{0,-1},{-1,0}}; //시계 방향 벡터
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		t=Integer.parseInt(st.nextToken());
		
		int[][] map=new int[r][c];
		int index=0;
		//(0,0)부터 (n-1,n-1)까지
		for(int i=0;i<r;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<c;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		move(map);
		print(map);
		
	}
	
	static void move(int[][] map) {
		moveUp(map);
		moveDown(map);
	}
	
	static void print(int[][] arr) {
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				System.out.print(arr[i][j]+"  ");
			}
			System.out.println();
		}
	}
	
	static void moveUp(int[][] map) {
		
		int x=2;
		int y=3;
		int prev=0;
		int d=0;
		boolean arrive=false;
		
		while(true) {
			if(arrive) break;
			d=d%4;
			while(true) { //배열 안에 있을동안 해당 방향으로 킵 고잉
				
				int dx=x+dir1[d][0];
				int dy=y+dir1[d][1];

				if(dx<0 || dx>2 || dy<0 || dy>=c) break;
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
		
		
		
		//print(map);

	}
	
	static void moveDown(int[][] map) {
		
		int x=3;
		int y=3;
		int prev=0;
		int d=0;
		boolean arrive=false;
		
		while(true) {
			if(arrive) break;
			d=d%4;
			while(true) { //배열 안에 있을동안 해당 방향으로 킵 고잉
				
				int dx=x+dir2[d][0];
				int dy=y+dir2[d][1];
				System.out.println(dx+","+dy);
				if(dx<3 || dx>=r || dy<0 || dy>=c) break;
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
		
		
		
		//print(map);

	}
	

}
