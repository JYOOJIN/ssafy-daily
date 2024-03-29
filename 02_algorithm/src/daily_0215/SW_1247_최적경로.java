package daily_0215;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_1247_최적 경로
 * 난이도: D5
 * 수행결과: Pass
 * 메모리: 21052 KB
 * 시간: 1913 ms
 * 코드길이: 1833 B
 * 풀이 전략: <순열: 순서o, 중복 x>
 * 			고객들의 순서 순열을 구해서 각각 거리를 계산한 후 최소거리를 찾는 완전탐색 방법이다.
 * 주의사항: 전체 입력받은 배열의 인덱스와 고객 순열의 인덱스 값을 헷갈리지 말아야한다!!
 */

public class SW_1247_최적경로 {
	
	public static int[] arr; //고객 순열 담을 배열
	public static boolean[] visited; //고객 순열 생성 시 방문 여부 배열
	public static Pair[] pair; //회사,집,고객 좌표 담을 배열
	public static int min; //최소 거리
	
	//회사,집,고객들의 좌표를 담는 클래스
	static class Pair{
		int x;
		int y;
		
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
		
	}
	
	
	//고객들의 순열을 구하는 재귀함수
	public static void dfs(int n,int depth) {
		
		if(depth==n) { 
			int distance=0; 
			
			//회사 - 첫번째 고객사이의 거리
			distance+=Math.abs(pair[0].x-pair[arr[0]].x)+Math.abs(pair[0].y-pair[arr[0]].y);
			
			//고객들 사이의 거리
			for(int i=0;i<n-1;i++) {
				distance+=Math.abs(pair[arr[i]].x-pair[arr[i+1]].x)+Math.abs(pair[arr[i]].y-pair[arr[i+1]].y);
			}
			
			//마지막 고객 - 집 사이의 거리
			distance+=Math.abs(pair[arr[n-1]].x-pair[1].x)+Math.abs(pair[arr[n-1]].y-pair[1].y);
			
			//최소 거리 구하기
			if(min>distance) min=distance;
			
			return;
		}
		
		for(int i=0;i<n;i++) {
			if(visited[i]) continue;
			visited[i]=true;
			arr[depth]=i+2; //여기서 i+2는 pair 배열의 index이다. 회사,집을 제외해야하므로 +2 필요!! 
			dfs(n,depth+1); //재귀함수 호출
			visited[i]=false;
			
		}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc=Integer.parseInt(br.readLine()); //테스트케이스
		
		for(int t=1;t<=tc;t++) { 
			int n=Integer.parseInt(br.readLine()); //고객의 수
			
			arr=new int[n]; //고객 순열 담을 배열
			visited=new boolean[n]; //고객 순열 생성 시 방문 여부 배열
			pair=new Pair[n+2]; //회사,집,고객 좌표 담을 배열
			min=Integer.MAX_VALUE; //초기화
			
			//좌표값 입력받기
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n+2;i++) {
				int x=Integer.parseInt(st.nextToken());
				int y=Integer.parseInt(st.nextToken());
				pair[i]=new Pair(x,y);
			}
		
			//재귀함수 호출
			dfs(n,0);
			
			//형식에 맞춰 출력
			System.out.println("#"+t+" "+min);
			
			
			
			
			
		}

	}

}

