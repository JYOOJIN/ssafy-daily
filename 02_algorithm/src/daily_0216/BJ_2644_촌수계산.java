package daily_0216;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_2644_촌수 계산
 * 난이도: S2
 * 수행결과: 맞았습니다!!
 * 메모리: 11636 KB
 * 시간: 76 ms
 * 코드길이: 1461 B
 * 풀이 전략: <bfs>: 재귀 쓰면 안된다. queue 사용. 
 * 			그래프 표현: 인접 행렬 사용. 숨바꼭질 문제에서 time 구하는거랑 똑같은 문제.
 * 			촌수 = 간선의 개수이다. 
 * 			visit를 boolean으로 하지 않고 방문했던 곳에 현재 start에서 출발한 촌수를 저장한다. 
 * 			
 * 주의사항: 여기서는 index가 1부터 시작하므로 n+1 크기의 배열을 생성해야한다.	
 */

public class BJ_2644_촌수계산 {
	

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine()); //전체 사람의 수
		int[][] family=new int[n+1][n+1]; //index 1부터 n까지
		int[] visited=new int[n+1]; //index 1부터 n까지
		
		st=new StringTokenizer(br.readLine());
		int a=Integer.parseInt(st.nextToken()); //촌수계산
		int b=Integer.parseInt(st.nextToken()); //촌수계산
		
		//그래프 - 인접행렬
		int m=Integer.parseInt(br.readLine()); //관계의 수
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			
			family[x][y]=family[y][x]=1; //무향 그래프
		}
		
		bfs(n,family,visited,a,b); //bfs 호출

	}
	
	public static void bfs(int n,int[][] family,int[] visited,int start,int end) {
		
		Queue<Integer> q=new ArrayDeque<>(); //사람번호 담을 queue 생성
		Arrays.fill(visited, -1); //visit을 -1로 초기화. -1이면 방문하지 않았음
		q.add(start); //현재 사람 q에 넣기
		visited[start]=0; //현재 사람에서 출발하므로 촌수는 0이다
		
		while(!q.isEmpty()) { //q가 비어있지 않는 동안 -> 이동할 곳이 남아있는 동안 반복
			int tmp=q.poll(); //q의 제일 첫번째 사람 pop
			
			if(tmp==end) { //그 사람의 번호가 내가 찾는 사람의 번호였다면
				System.out.println(visited[tmp]); //그 사람까지 가는데 필요한 간선의 개수=촌수 출력
				return;
			}
			
			for(int i=1;i<=n;i++) { //start를 from으로 하고 to를 반복한다
				
				if(visited[i]<0 && family[tmp][i]==1) { //방문 X, 현재 노드와 이어져 있다면
					visited[i]=visited[tmp]+1; //이동한 노드에 이동했을 때의 촌수 넣기
					q.add(i); //q에 이동한 사람 번호 넣기
				}
			}
		}
		System.out.println(-1); //찾고자 하는 사람을 못찾았으면 -1 출력
		return;
		
		
	}

}
