package daily_0405;

import java.io.*;
import java.util.*;

public class BJ_17135_캐슬디펜스 {

	static int n,m,d;
	static int[] arr;
	static List<Point> enemy;

	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		
		int[][] map=new int[n+1][m]; //어차피 넘겨줘야 되기 때문에 전역하면 헷갈린다. 지역선언 하자.
		arr=new int[m]; 
		enemy=new ArrayList<>(); //적의 좌표 담는 리스트. 초반에 담고 제거되지 않는 한 아래로 한칸씩 내린다.
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) enemy.add(new Point(i,j)); //적의 좌표 저장
			}
		}
				
	}
	
	//거리 구하기(맨해튼 거리)
	public static int distance(Point start,Point end) {
		int distance=Math.abs(start.x-end.x)+Math.abs(start.y-end.y);
		return distance;
	}
	
	//배열 깊은 복사(newMap에 map 복사)
	public static void copy(int[][] map,int[][] newMap) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				newMap[i][j]=map[i][j];
			}
		}
	}
	

	//아래로 한칸이동
	public static void move() {
		for(int i=0;i<enemy.size();i++) {
			enemy.get(i).x+=1; //아래로 한칸 이동
			if(enemy.get(i).x==n) enemy.remove(i); //성이 있는 칸에 도착했다면 제외
		}
	}
	
	//공격
	public static void attack(int[] arr,int[][] map) {
		for(int i=0;i<3;i++) { //궁수의 수만큼 반복
			
			
			
			
			
			
			
			
			
		}
	}
	
	//궁수 위치 조합 구하기
	public static void combination(int depth,int start) {
		
		if(depth==3) {
			
			
			
			
		}
		
		for(int i=start;i<m;i++) {
			arr[depth]=i;
			combination(depth+1, i+1);
		}
	}

}
