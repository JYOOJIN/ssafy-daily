package daily_0228;

import java.io.*;
import java.util.*;

public class SW_1767_프로세서연결하기_live {
	
	static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; //4방 방향 벡터
	static int n,max,totalCnt,min,map[][]; //멕시노스크기, 최대 코어수, 비가장자리코어수, 최소전선길이합, 멕시노스셀정보
	static ArrayList<int[]> list; //비가장자리 코어 리스트

	public static void main(String[] args) throws IOException{
		 
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
			
		int tc=Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			n=Integer.parseInt(br.readLine()); //입력받기
			
			//초기화
			map=new int[n][n];
			list=new ArrayList<>();
			max=0;
			totalCnt=0;
			min=Integer.MAX_VALUE;
			
			//멕시노스 셀 정보 입력받기 및 비가장자리 코어 리스트 생성
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					//비가장자리코어 리스트에 담기
					if(i >0 && i<n-1 && j>0 && j<n-1 && map[i][j]==1) {
						list.add(new int[] {i,j}); //list에 1차원 배열 넣기 주의
						totalCnt++; //비가장자리코어 개수 +1
					}
				}
			}
			
			dfs(0,0,0);
			System.out.println("#"+tc+" "+min);
	
		}

	}
	
	static void dfs(int index,int cCnt, int lCnt) { //현재 코어로 전선처리 시도, cCnt:처리한 코어개수, lCnt: 전선길이의합
		
		if(cCnt+(totalCnt-index)<max) return; //처리한코어와 남은 코어를 더해도 최대코어 보다 작다면 처리하지 않는다. -> 가지치기
		
		if(index==totalCnt) { //모든 코어를 보는 한가지 경우의 수가 끝났다면 최대코어, 최소길이 비교
			if(max<cCnt) {
				max=cCnt;
				min=lCnt;
			}else if(max==cCnt) {
				if(min>lCnt) min=lCnt;
			}
			
			return;
		}
		
		
		int[] cur=list.get(index);
		int x=cur[0];
		int y=cur[1];
		
		//4방으로 시도
		for(int d=0;d<4;d++) {
			if(search(x,y,d)) { //그 방향으로 비어있다면
				int len=setStatus(x,y,d,2); //전선 놓기
				dfs(index+1,cCnt+1,lCnt+len); //다음 코어 가기
				setStatus(x,y,d,0); //전선 지우기. 마지막 코어까지 갔다가 나올때 연결했던 전선 없애주어야 한다.
			}
		}
			
		//전선 놓지 않기
		dfs(index+1,cCnt,lCnt); //코어개수, 전선길이 변화 X
		
		
		
	}
	
	static boolean search(int x,int y,int d) { //코어(x,y) 위치에서 d방향으로 전선 놓기 가능한지 체크
		
		int dx=x;
		int dy=y;
		
		while(true) {
			
			dx+=dir[d][0];
			dy+=dir[d][1];
			
			if(dx<0 || dx>=n || dy<0 || dy >=n) return true; //끝까지 가면 전선을 놓을 수 있다
			if(map[dx][dy]>0) return false;
		}
		
		
	}
	
	static int setStatus(int x,int y,int d,int s) { //코어 (x,y)위치에서 d방향으로 s(2:전선,0:빈칸)로 상태를 set
		
		int dx=x;
		int dy=y;
		int cnt=0;
		
		while(true) {
			
			dx+=dir[d][0];
			dy+=dir[d][1];
			
			if(dx<0 || dx>=n || dy<0 || dy >=n) break; //경계를 벗어나면 끝난다
			
			map[dx][dy]=s;
			
			cnt++; //처리한 빈칸의 개수
			
		}
		
		return cnt;
		
	}
	
	

}
