package daily_0329;

import java.io.*;
import java.util.*;

public class SW_5656_벽돌깨기 {
	
	public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; //위쪽 오른쪽 아래쪽 왼쪽
	public static int n,w,h;
	public static int[][] map;
	public static int min=Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc=Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken());
			w=Integer.parseInt(st.nextToken());
			h=Integer.parseInt(st.nextToken());
			
			map=new int[h][w];
			
			//map 입력받기
			for(int i=0;i<h;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<w;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			//원본 map은 고정으로 있어야한다
			int[][] copy=new int[h][w];
			copy=copy(map);
			
			dfs(0,copy);
			
			System.out.println(min);
		}
		
		
		
	}
	
	//구슬 떨어트리는 
	public static void dfs(int depth,int[][] map) {
		
		if(depth==n) { //구슬 다 사용했다면
			int countBlock=0;
			for(int i=0;i<h;i++) {
				for(int j=0;j<w;j++){
					if(map[i][j]>0) countBlock+=1;
				}
			}
			if(min>countBlock) min=countBlock;
			
			return;
		}
		
		int[][] block=choose(map);
		for(int i=0;i<w;i++) {
			int x=block[i][0];
			int y=block[i][1];
			
			map=crash(map,x,y);
			print(map);
			dfs(depth+1,map);
			
		}
		
		
	}
	
	//구슬이 때릴 위치 정하는 함수
	public static int[][] choose(int[][] map) {
		int[][] block=new int[w][2]; //x좌표, y좌표 저장하는 배열
		for(int j=0;j<w;j++) {
			for(int i=0;i<h;i++) {
				if(map[i][j]>0) {
					block[j][0]=i;
					block[j][1]=j;
					break;
				}
			}
		}
		
		return block; //구슬이 때릴 수 있는 좌표가 담긴 배열
	}
	
	//배열 출력 함수
	public static void print(int[][] arr) {
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println("=================");
	}
	
	//배열 복사 함수
	public static int[][] copy(int[][] arr){
		int[][] copy=new int[h][w];
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				copy[i][j]=arr[i][j];
			}
		}
		
		return copy;
		
	}
	
	//(x,y)에 구슬 떨어트린 후의 결과
	public static int[][] crash(int[][] map,int x,int y) {
		
		Queue<int[]> q=new ArrayDeque<>();
		
		q.add(new int[] {x,y});
		
		while(!q.isEmpty()) {
			
			int[] tmp=q.poll();
			int val=map[tmp[0]][tmp[1]];
			map[tmp[0]][tmp[1]]=0;
			
			for(int d=0;d<4;d++) {
				int dx=tmp[0];
				int dy=tmp[1];
				for(int k=1;k<val;k++) {
					dx+=dir[d][0];
					dy+=dir[d][1];
					if(dx<0 || dx>=h || dy<0 || dy>=w) continue;
					if(map[dx][dy]>0) {
						q.add(new int[] {dx,dy});
					}
					
				}
			}
		}
		
		//벽돌아래 빈공간 있을 경우 떨어진다
		return fall(map);
	}
	
	//빈 공간이 있을 경우 벽돌은 밑으로 떨어진다
	public static int[][] fall(int[][] map) {
		
		int[][] copy=new int[h][w];
		for(int j=0;j<w;j++) {
			Queue<Integer> q= new ArrayDeque<>();
			for(int i=h-1;i>=0;i--) {
				if(map[i][j]>0) q.add(map[i][j]);
			}
			int size=q.size();
			for(int i=h-1;i>h-1-size;i--) {
				copy[i][j]=q.poll();
			}
			
		}

		return copy;
			
	}

}
