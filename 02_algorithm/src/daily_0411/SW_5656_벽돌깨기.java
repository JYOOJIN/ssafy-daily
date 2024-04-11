package daily_0411;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_5656_벽돌깨기
 * 난이도: 모의 SW 역량 테스트
 * 수행결과: Pass
 * 메모리: 67412 KB
 * 시간: 203 ms
 * 코드길이: 3489 B
 * 풀이 전략: 1) 깰 벽돌 고르기 -> dfs로 완전탐색 및 백트래킹
 * 				- dfs 돌다가 벽돌이 다 사라지면 그만 돌아도 되므로 빠져나온다. 전역으로 flag 선언해도 됨. -> 이게 편하므로 이걸로 하겠다. 
 * 			2) 연쇄 제거하기 -> dfs
 * 			3) 중력 작용 -> list 이용
 *   
 *  */

public class SW_5656_벽돌깨기 {
	
	//좌표 저장하는 클래스
	static class Point{
		int x,y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

	static boolean flag; //백트래킹을 위한 flag
	static int n,w,h,min; //n개의 구슬을 모두 떨어트린 후 최대한 많은 벽돌을 깨서 최대한 적은 벽돌이 남도록 한다 
	static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; //방향벡터 (상 우 하 좌)
	static List<Point> list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb=new StringBuilder();
		
		int tc=Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken()); //구슬의 개수
			w=Integer.parseInt(st.nextToken()); //너비
			h=Integer.parseInt(st.nextToken()); //높이
			min=Integer.MAX_VALUE; //남는 벽돌 개수
			
			int[][] map=new int[h][w];
			
			for(int i=0;i<h;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<w;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			//입력받기 완
			 
			dfs(0,map); //dfs 실행. 완탐+백트래킹
			sb.append("#"+t+" " +min+"\n"); 
		}
		
		bw.write(sb.toString()); //bw는 string만 가능.
		bw.flush();
		bw.close();
		
		
	}
	
	
	//주의!!!!
	public static void dfs(int depth,int[][] arr) {
	
		if(flag) return; //더이상 깰 벽돌 안남아 있다면 바로 빠져나간다
		
		int remain=remainBlock(arr);
		
		if(remain==0) { //남은 벽돌이 없다면 빠져나가기
			min=0;
			flag=true; 
			return;
			//return true; //남은 벽돌 없다면 이게 최소이므로 더이상 dfs 돌 필요가 없다.빠져나가는 flag
		}
		
		if(depth==n) { //모든 구슬을 사용햇을 때
			
			min=Math.min(remain, min); //최솟값 비교 및 갱신
			return; //빠져나가기 
			//return false;
		}
		
		
		
		for(int i=0;i<w;i++) { //열마다 돌면서 가장 윗 벽돌 찾기
			
			int index=0;
			while(index<h && arr[index][i]==0) ++index; //0이 아닌, 때릴 수 있는 벽돌 찾을 때까지 반복
			
			if(index==h) continue; //현재 깨트릴 벽돌 없다. 다음 열로 넘어간다.
			
			int[][] copy=copy(arr); //단계마다 원본 배열 저장해놓는다
				
			attack(copy,new Point(index,i)); //연쇄 공격
			down(copy); //중력 작용
			
			//if(dfs(depth+1,copy)) return true;
			dfs(depth+1,copy);
		}
		
		//return false;
		
	}
	
	//남은 block 반환
	//남은 block이 0이라면  min이 무조건 0이므로 끝낼 수 있는 조건이 된다.
	public static int remainBlock(int[][] map) {
		int cnt=0;
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				if(map[i][j]>0) ++cnt;
			}
		}
		return cnt;
	}
	
	
	//연쇄 공격
	public static void attack(int[][] arr,Point start) {
		
		int num=arr[start.x][start.y]; //현재 위치의 숫자
		arr[start.x][start.y]=0; //현재 위치 깨기
		
		if(num==1) return; //1이면 자기자신만 깨지므로 dfs 끝
		
		for(int d=0;d<4;d++) { //4방향 살피면서
			for(int c=1;c<num;c++) { //num-1 만큼 뻗어나간다
				int dx=start.x+dir[d][0]*c;
				int dy=start.y+dir[d][1]*c;
				if(dx<0 || dx>=h || dy<0 || dy>=w) break; //배열 벗어나면 다음 방향으로 이동한다
				if(arr[dx][dy]>0) attack(arr,new Point(dx,dy)); //벽돌이 있다면 거기로 이동해서 다시 dfs 작용
			}
		}	
		
	}
	
	
	//떨어트리기
	//stack 사용해도, list 사용해도 가능
	public static void down(int[][] arr) {
				
		for(int i=0;i<w;i++) {
			List<Integer> downElement=new ArrayList<>();
			for(int j=h-1;j>=0;--j) {
				if(arr[j][i]>0) {
					downElement.add(arr[j][i]);
					arr[j][i]=0;
				}
				
			}
			
			int index=h-1;
			for(int j=0;j<downElement.size();j++) {
				arr[index-j][i]=downElement.get(j);
			}
			
		}
		
		
	}
	
	
	//배열 깊은 복사
	//int[][]를 반환해서 보내주는게 편하다
	public static int[][] copy(int[][] arr){
		int[][] copy=new int[h][w];
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				copy[i][j]=arr[i][j];
			}
		}
		
		return copy;
	}
			
	
	//디버깅용 출력
	public static void print(int[][] arr) {
		for(int i=0;i<h;i++) {
			for(int j=0;j<w;j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------");
	}
	
	
	

}
