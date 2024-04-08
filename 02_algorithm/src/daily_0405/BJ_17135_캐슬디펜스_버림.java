package daily_0405;

import java.io.*;
import java.util.*;

public class BJ_17135_캐슬디펜스_버림 {

	static int n,m,d,max,attacked;
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
		arr=new int[3]; 
		enemy=new ArrayList<>(); //적의 좌표 담는 리스트. 초반에 담고 제거되지 않는 한 아래로 한칸씩 내린다.
		max=Integer.MIN_VALUE;
		
		//입력받은 map은 변하지 않는다. 다 copy 만들어서 해야함.
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) enemy.add(new Point(i,j)); //적의 좌표 저장
			}
		}
		
		for(int i=0;i<enemy.size();i++) {
			System.out.println(enemy.get(i).x+","+enemy.get(i).y);
		}
		
		
		combination(map, 0, 0);
		
		System.out.println(max);
				
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
	
	
	public static void print(int[][] arr) {
		for(int i=0;i<n+1;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}

	//아래로 한칸이동
	public static void move() {
		System.out.println("size"+enemy.size());
		for(int i=enemy.size();i>=0;--i) {
			enemy.get(i).x+=1; //아래로 한칸 이동
			if(enemy.get(i).x==n) enemy.remove(i);
		}
		
		
		
	}
	
	
	//공격
	public static void attack(int[] arr,List<Point> enemy) {
		Set<Integer> removed=new HashSet<>(); //제거되는 적의 index 
		
		for(int i=0;i<3;i++) { //궁수의 수만큼 반복
			int minLen=Integer.MAX_VALUE;
			int minIndex=0; //제일 왼쪽
			
			for(int j=0;j<enemy.size();j++) { //존재하는 적만큼 반복
				int x= n;
				int y=arr[i];
				
				int dis=distance(new Point(x,y), enemy.get(j)); //현재 궁수와 적과의 거리
				if(dis<=d && minLen>dis) {
					minLen=dis;
					minIndex=j;
				}
				
			}
			
			removed.add(minIndex); //제거되는 적의 인덱스. 미리 제거하면 안된다. 
		}
		
		for(int e:removed) {
			System.out.println(e);
		}
		
		attacked=removed.size();
		
		
		
		
		//공격받은 적 제외된 상태
		
		for(int i=0;i<enemy.size();i++) {
			System.out.println(enemy.get(i).x+","+enemy.get(i).y);
		}
		
		
	}
	
	//궁수 위치 조합 구하기
	public static void combination(int[][] map,int depth,int start) {
		
		if(depth==3) {
			
			System.out.println(Arrays.toString(arr));
			
			int[][] copy=new int[n+1][m];
			List<Point> copylist=new ArrayList<>(enemy);
			copy(map,copy); //copy에 map 저장하기
			
			attacked=0;
			
			
			while(!enemy.isEmpty()) {
				//구합 조합으로 공격하기
				attack(arr,copylist);
				//이동
				move();
				
				
			}
			
			//max 비교
			if(attacked>max) max=attacked;
			return;
			
		}
		
		for(int i=start;i<m;i++) {
			arr[depth]=i;
			combination(map,depth+1, i+1);
		}
	}

}
