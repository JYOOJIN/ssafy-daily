package daily_0215;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_1697_숨바꼭질
 * 난이도: S1
 * 수행결과: 맞았습니다!!
 * 메모리: 13840 KB
 * 시간: 116 ms
 * 코드길이: 1274 B
 * 풀이 전략: <bfs>: 재귀 쓰면 안된다. queue 사용.
 * 			index 범위를 벗어나지 않으면서 방문하지 않았다면 방문하고, 시간을 +1 한다.
 * 			
 */


public class BJ_1697_숨바꼭질 {
	
	public static int[] visited; 
	public static int SIZE=100001; // 0<=N,k<=100000
	
	/**
	 * 최단 시간 구하는 bfs
	 * @param x 출발 index
	 * @param k 도착 index
	 */
	public static void bfs(int x,int k) {
		
		Queue<Integer> q=new ArrayDeque<>(); //queue 생성
		Arrays.fill(visited, -1); //방문 안했을 때를 -1 로 가정
		
		q.add(x); //제일 첫 노드 q에 삽입
		int time=0; 
		visited[x]=time; //처음 시작 지점은 이동을 안했으므로 시간이 0
		
		while(!q.isEmpty()) { //queue가 비어있지 않을 동안 반복
			 
			int tmp=q.poll(); //q의 제일 첫 원소 pop
			
			if(tmp==k) { //동생 위치에 도착했다면
				System.out.println(visited[tmp]); //해당 위치의 time 출력
				return;
			}
			
			//방문하지 않은 경우만 간다
			if(tmp>0 && visited[tmp-1]<0) { //범위 충족 & tmp-1 을 방문하지 않았다면
				visited[tmp-1]=visited[tmp]+1; //tmp까지의 시간에 +1을 한다
				q.add(tmp-1); //q에 현재 index를 넣는다
			}
			if(tmp<SIZE-1 && visited[tmp+1]<0) { //범위 충족 & tmp+1 을 방문하지 않았다면
				visited[tmp+1]=visited[tmp]+1; //tmp까지의 시간에 +1을 한다
				q.add(tmp+1); //q에 현재 index를 넣는다
			}
			if(2*tmp<SIZE && visited[2*tmp]<0) { //범위 충족 & tmp*2 을 방문하지 않았다면
				visited[2*tmp]=visited[tmp]+1; //tmp까지의 시간에 +1을 한다
				q.add(2*tmp); //q에 현재 index를 넣는다
			}
		}
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken()); //내 위치 index
		int k=Integer.parseInt(st.nextToken()); //동생 위치 index
		
		visited=new int[SIZE]; //visit 배열 생성
		
		bfs(n,k); //bfs 시작

	}

}
