package daily_0228;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_1767_프로세서연결하기
 * 난이도: SW Test 샘플문제
 * 수행결과: Pass
 * 메모리: 23942 KB
 * 시간: 139 ms
 * 코드길이: 2436 B
 * 풀이 전략: 완전 탐색. 각 코어는 4가지방향+연결x -> 총 5가지의 시도가 가능. 각 시도를 했을 때 연결가능하면 연결하고 다음 코어로 넘어감/연결 못하면 다음 방향 서치/모든 방향 안되면 연결x로 넘어간다
 * 			처음엔 코어의 순열을 구해서 각 코어의 최소 전선을 연결하는 식으로 했는데, 코어의 순서를 따질 수 없을 뿐더러 그리디로 최소 길이를 구하는것이 최종적을 최소 길이가 아니다.
 * 			완전 탐색을 해야하는데, 반복문은 너무 크고 얼마나 돌지를 모른다. 그래서 dfs를 통해 완전탐색을 진행한다.
 * 			연결이 가능한지 확인하는 search 메서드, 전선을 연결/해제하는 setLink 메서드 생성
 * 			4방탐색+연결x 의 경우를 따지는 dfs 생성. 기저조건: 모든 core를 다 확인했을 경우!!
 * 
 * 주의사항: 테스트케이스마다 변수 초기화 필수!!!!!!!
 */

public class SW_1767_프로세서연결하기 {

	public static int n,maxCore,minLen; //크기, 최대코어, 최소전선길이
	public static int[][] map; 
	public static ArrayList<int[]> list; //코어담을 리스트
	public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; //방향벡터
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc=Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			n=Integer.parseInt(br.readLine());
			map=new int[n][n];
			list=new ArrayList<>();
			//값 초기화 매우 중요!!!!!!!!!!
			maxCore=0;
			minLen=Integer.MAX_VALUE;
			
			//값 입력받기
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]==1 && i>0 && i<n-1 && j>0 && j<n-1) { //벽에붙언 코어가 아니라면 리스트에 넣는다
						list.add(new int[] {i,j});
					}
				}
			}
			
			dfs(0,0,0);
			System.out.println("#"+t+" "+minLen);
		}
		
		
		
	}
	
	//index:코어list의 index, cCnt:지금까지 처리한 코어의 개수, lCnt: 지금까지의 전선의 길이
	//여기서 index는 우리가 흔히 말하는 depth인 것이다. 
	static void dfs(int index,int cCnt,int lCnt) { 
		
		if(cCnt+(list.size()-index)<maxCore) return; //현재 처리한 코어와 남은 코어를 더해도 maxCore 보다 작다면 더 볼필요없이 return. 가지치기
		
		if(index==list.size()) { //모든 코어를 탐색했을 때. 이 코어들은 4방+연결x, 총 5가지 선택지 중 하나씩을 선택했을 것이다. 
			
			if(cCnt>maxCore) { //연결한 코어가 maxCore보다 크다면
				maxCore=cCnt; //maxCore 갱싱
				minLen=lCnt; //minLen 갱신
			}else if(cCnt==maxCore) { //연결한 코어가 maxCore와 같다면
				if(minLen>lCnt) minLen=lCnt; //minLen 비교해서 갱신
			}

			return;
		}
		
		//Ararylist 안에 1차원 배열
		int x=list.get(index)[0];  
		int y=list.get(index)[1];
		
		//4방 탐색 후 연결
		for(int d=0;d<4;d++) {
			if(search(x,y,d)) { //전원까지 갈수있다면
				int len=setLink(x,y,d,2); //전선연결
				dfs(index+1,cCnt+1,lCnt+len); //다음 코어 부르면서 dfs 호출, 처리한 코어+1, 구한길이+len
				setLink(x,y,d,0); //전선해제
			}
		}
		
		//연결 x
		dfs(index+1,cCnt,lCnt);
		
		
	}
	
	//현재코어(x,y)에서 d방향으로 s세팅
	static int setLink(int x,int y,int d,int s) {
		
		int len=0;
		int dx=x;
		int dy=y;
		
		while(true) {
			//현재 코어에서 해당 방향으로 이동한다
			dx+=dir[d][0];
			dy+=dir[d][1];
			
			if(dx<0 || dx>=n || dy<0 || dy>=n) break; //범위를 벗어나면
			
			map[dx][dy]=s; //전선:2, 빈칸:0 으로 설정
			len++; //전선의 길이 
			
		}
		
		return len;
		
	}
	
	//현재코어(x,y)에서 d방향으로 전원 연결가능한지 여부
	static boolean search(int x,int y,int d) {
		
		int dx=x;
		int dy=y;
		
		while(true) {
			dx+=dir[d][0];
			dy+=dir[d][1];
			
			if(dx<0 || dx>=n || dy<0 || dy>=n) return true; //방해물없이 전원까지 연결 가능
			if(map[dx][dy]>0) return false; //전선이나 코어 만나면 false
			
		}
		
		
	}

}
