package daily_0410;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_7465_창용마을무리의개수
 * 난이도: D4
 * 수행결과: Pass
 * 메모리: 32484 KB
 * 시간: 132 ms
 * 코드길이: 1340 B
 * 풀이 전략: 인접리스트 사용해서 dfs 돌리기. bfs로 구역 돌리는거 말고 dfs도 쓸줄 알아야한다. 
 *   
 *  */

public class SW_7465_창용마을무리의개수 {
	
	static class Node{
		int now,next;

		public Node(int now, int next) {
			super();
			this.now = now;
			this.next = next;
		}
		
	}
	
	static int n,m;
	static ArrayList<Integer>[] node;
	static boolean[] visited;

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			n=Integer.parseInt(st.nextToken());
			m=Integer.parseInt(st.nextToken());
			
			node=new ArrayList[n];
			visited=new boolean[n];
			
			int answer=0;
			
			for(int i=0;i<n;i++) {
				node[i]=new ArrayList<>(); //초기화!!!!!!!!
			}
			
			for(int i=0;i<m;i++) { //m개의 관계
				st=new StringTokenizer(br.readLine());
				int a=Integer.parseInt(st.nextToken())-1;
				int b=Integer.parseInt(st.nextToken())-1;
				
				node[a].add(b);
				node[b].add(a);
				
			}
			
			for(int i=0;i<n;i++) {
				if(visited[i]) continue; //방문했으면 pass
				dfs(i);
				answer++;
			}
			
			
			System.out.println("#"+t+" "+answer);
		}
	}
	
	public static void dfs(int start) {
		
		for(int next:node[start]) {
			if(visited[next]) continue;
			visited[next]=true;
			dfs(next);
		}
		
		
	}

}
