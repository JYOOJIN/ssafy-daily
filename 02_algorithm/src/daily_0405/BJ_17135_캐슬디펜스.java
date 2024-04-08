package daily_0405;

import java.io.*;
import java.util.*;

import daily_0405.BJ_17135_캐슬디펜스_버림.Point;

public class BJ_17135_캐슬디펜스 {
	
	static int n,m,d,max;
	static int[][] map; //변하지 않을 값이기 때문에 전역 선언
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		
		map=new int[n+1][m]; //행은 0부터 n까지, 열은 0부터 m-1까지
		arr=new int[3]; //궁수 3명의 위치 담는 배열. 즉, y좌표(x좌표는 n고정)
		max=Integer.MIN_VALUE; //제거할 수 있는 적의 최대 수
		
		//입력받은 map은 변하지 않는다. 다 copy 만들어서 해야함.
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		
	
	}
	
	//공격해서 제거하는 함수
	public static void attack() {
		
	}
	
	
	public static void combination(int depth,int start) {
		
		if(depth==3) { //궁수는 3명
			
			int[][] copy=copy(); //새 배열 주기
			for(int i=0;i<3;i++) {
				int minLen=Integer.MAX_VALUE;
				Point minPoint=null; //제거할 적 들어감
				
				//현재 궁수가 제거할 적 하나 정하기
				for(int j=0;j<n;j++) {
					for(int k=0;k<m;k++) {
						if(copy[j][k]==1) {
							int dis=getDistance(new Point(j,k), new Point(n,arr[i]));
							if(dis<=d && dis<minLen) {
								minLen=dis;
								minPoint=new Point(j, k);
							}
						}
					}
				}
				
				
				
		
			}

			return;
		}
		
		for(int i=start;i<m;i++) {
			arr[depth]=i;
			combination(depth+1, start+1);
		}

	}
	
	public static void move(int[][] arr) {
		
	}
	

	//맨해튼 거리 구하는 함수
	public static int getDistance(Point start,Point end) {
		return Math.abs(start.x-end.x)+Math.abs(start.y-end.y);
	}
	
	
	//map을 복사해서 copy 배열 반환하는 함수
	public static int[][] copy(){
		
		int[][] copy=new int[n+1][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				copy[i][j]=map[i][j];
			}
		}
		
		return copy;
	}

	//디버깅용 배열 출력
	public static void print(int[][] arr) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
	}
	

	
	
	
}
