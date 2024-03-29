package daily_0220;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_1260_DFS와 BFS
 * 난이도: S2
 * 수행결과: 맞았습니다!
 * 메모리: 21272 KB
 * 시간: 252 ms
 * 코드길이: 1562 B
 * 풀이 전략: 그래프 표현: 인접 행렬 & DFS와 BFS 구현
 * 			DFS - 재귀, BFS - Queue
 */

public class BJ_1260_DFS와BFS {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken()); //정점의 개수
		int m=Integer.parseInt(st.nextToken()); //간선의 개수
		int v=Integer.parseInt(st.nextToken()); //정점의 번호
		
		int[][] arr=new int[n+1][n+1]; //인접행렬 생성. index는 1부터 N까지
		
		//인접행렬 생성
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			
			arr[from][to]=arr[to][from]=1; //양방향
		}
		
		//visited의 경우 새로 생성해서 바로 parameter로 전달했다
		
		dfs(n,arr,new boolean[n+1],v); 
		System.out.println();
		bfs(n,arr,new boolean[n+1],v);
		
		
		

	}
	
	/**
	 * 깊이 우선 탐색
	 * @param n 정점의 개수
	 * @param arr 인접행렬
	 * @param visited 방문 체크 배열
	 * @param current 현재 정점
	 */
	public static void dfs(int n,int[][] arr,boolean[] visited,int current) {

		visited[current]=true; //현재 노드 방문 확인
		System.out.print(current+" "); //탐색 출력
		
		for(int i=1;i<=n;i++) { 
			if(!visited[i] && arr[current][i]==1) { //방문하지 않았고 연결되어 있다면
				dfs(n,arr,visited,i); //그 길로 쭉 간다
			}
		}	
	}
	
	/**
	 * 
	 * @param n 정점의 개수
	 * @param arr 인접행렬
	 * @param visited 방문 체크 배열
	 * @param v 시작 정점
	 */
	public static void bfs(int n,int[][] arr,boolean[] visited,int v) {
		Queue<Integer> q=new ArrayDeque<>(); //q 생성
		
		q.add(v); //시작노드 q에 넣기
		visited[v]=true; //시작노드 방문 확인
		
		while(!q.isEmpty()) { //q가 비어있지 않는동안 반복
			
			int tmp=q.poll(); 
			System.out.print(tmp+" "); //탐색 출력
			
			for(int i=1;i<=n;i++) {
				if(!visited[i] && arr[tmp][i]==1) { //방문하지 않았고 연결되어 있다면
					q.add(i); //q에 넣고
					visited[i]=true; //방문확인
				}
			}
			
		}

	}

}
