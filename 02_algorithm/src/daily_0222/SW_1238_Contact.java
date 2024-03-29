package daily_0222;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_1238_Contact
 * 난이도: D4
 * 수행결과: Pass
 * 메모리: 20760 KB
 * 시간: 118 ms
 * 코드길이: 1433 B
 * 풀이 전략: bfs를 진행하면서 level, 즉 depth를 구할 수 있어야 한다. 
 * 			visit를 boolean이 아닌, level을 저장하는 int 배열로 생성한다.
 * 			방문할때, 그 전 노드의 level+1 로 level을 점점 증가시키는 것이다.
 * 			그리고 최종적으로 가장 높은 level을 가진 정점 중 가장 큰 수가 최댓값이다.
 * 	
 * 			그래프의 표현방식: 인접 행렬. 처음엔 리스트로 진행하였으나 index로 접근이 편하고 싶어서 행렬로 변경하였다.
 *  
 *  */



public class SW_1238_Contact {
	
	public static int[][] arr; //인접 행렬
	public static int[] visited; //방문 & 레벨 체크 배열
	public static int SIZE=100; //가능한 번호 수
	public static int max; //최댓값

	
	//너비 우선 탐색
	public static void bfs(int start) {
		Queue<Integer> q=new ArrayDeque<>();
		
		int depth=1;
		visited[start]=depth; //처음 시작하는 곳을 level1로 설정
		q.add(start);
		
		while(!q.isEmpty()) {
			
			int tmp=q.poll();
			
			for(int i=1;i<=SIZE;i++) {
				if(visited[i]==0 && arr[tmp][i]==1) { //방문한적 없으면서 인접하다면
					q.add(i); //q에 넣고
					visited[i]=visited[tmp]+1; //방문처리를 해주면서, 지금까지의 depth에 +1
					
					//System.out.println(i+"의 depth: "+visited[i]);
				}
				
			}	
			
			depth=Math.max(depth, visited[tmp]); //가장 큰 depth를 구한다
		}
		
		for(int i=SIZE;i>0;i--) { //가장 큰 인덱스를 구하므로 뒤에서 시작한다.
			if(visited[i]==depth) { //가장 큰 depth를 가지면서 번호가 가장 큰 인덱스를 구한다.
				max=i;
				break;
			}
		}
		
		

	}
	

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t=1;t<=10;t++) {
			
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int start=Integer.parseInt(st.nextToken()); //시작 정점
			
			max=0;
			arr=new int[SIZE+1][SIZE+1]; //1부터 100
			visited=new int[SIZE+1]; //1부터 100
			
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n/2;i++) { 
				int from=Integer.parseInt(st.nextToken()); //시작정점
				int to=Integer.parseInt(st.nextToken()); //도착정점
				arr[from][to]=1; //유향 그래프
				
			}
			
			bfs(start); //너비우선탐색 시작
			
			System.out.println("#"+t+" "+max); //형식에 맞춰 출력
			
			
		}

	}

}
