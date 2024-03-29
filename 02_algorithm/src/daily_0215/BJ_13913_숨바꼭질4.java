package daily_0215;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_13913_숨바꼭질 4
 * 난이도: G4
 * 수행결과: 맞았습니다!!
 * 메모리: 27844 KB
 * 시간: 2888 ms
 * 코드길이: 2133 B
 * 풀이 전략: <bfs>: 재귀 쓰면 안된다. queue 사용.
 * 			현재 예시에 있는 입력은 2개의 경로가 나오는데, 여기서는 둘 중 무엇이 나와도 상관이 없다.
 * 			2개를 모두 구하기 위해서는 v의 값을 비교해서 정보가 겹칠수있고,
 * 			1개만 구할 때는 v의 true/false만 따져주면 된다.
 * 			<경로 구하기>: prev를 담는 Node 클래스를 생성해서 backtracking 한다.
 * 
 * 주의사항: 객체 배열을 생성한 후, 그 전체에 대해서 객체를 미리 생성해주어야한다!!!!
 * for문 돌면서 visited[i] = New Node(); 이렇게, 기본 생성자를 이용해서 생성해주어야한다!!!!
 */


public class BJ_13913_숨바꼭질4 {
	
	public static Node[] visited; //Node 배열
	public static int SIZE=100001; //0<= n,k <= 100000
	public static StringBuilder sb=new StringBuilder(); //경로 저장할 문자열
	
	/**
	 * Node 정보 담은 클래스
	 */
	public static class Node{
		int prev; //현재 노드의 이전 노드
		int time; //현재 노드의 시간
		boolean v; //현재 노드의 방문 여부
		
		//기본 생성자
		public Node() {
			
		}
		
		//생성자
		public Node(int prev, int time,boolean v) {
			super();
			this.prev = prev;
			this.time = time;
			this.v=v;
		}
		
	}

	/**
	 * Node의 prev 이용해서 현재 경로를 추적하는 메서드
	 * @param node
	 */
	public static void backTracking(Node node) {
		
		
		while(true) {
			if(node.prev==-1) break; //첫번째 Node의 prev는 -1이다
			sb.insert(0, node.prev+" "); //sb의 제일 처음에 그 전 경로를 삽입한다
			node=visited[node.prev]; //이전 노드를 현재 노드로 업데이트한다
		}
	}
	
	public static void bfs(int n,int k) {
		
		Queue<Integer> q=new ArrayDeque<>(); //index 담을 queue 생성

		
		q.add(n); //q에는 index 들만 저장한다.
		visited[n]=new Node(-1,0,true); //시작점의 prev는 -1. 방문했으므로 true처리.
		
		while(!q.isEmpty()) { //queue가 비어있지 않는 동안 반복
			
			int tmp=q.poll(); //제일 첫번째 값 pop
			
			if(tmp==k) { //현재 index가 동생의 위치라면 return
				
				System.out.println(visited[tmp].time); //현재 동생 위치를 오기까지의 시간 출력
				backTracking(visited[tmp]); //현재 동생 위치부터 경로 역추적
				System.out.println(sb.append(tmp)); //형식에 맞게 출력
				return;
			}
			
			if(tmp*2<SIZE && !visited[tmp*2].v ) { //index가 범위안에 있으면서 방문하지 해당 노드 방문하지 않았을때
				
				visited[tmp*2].v=true; //방문처리
				visited[tmp*2].time=visited[tmp].time+1; //그 전 Node의 시간보다 +1
				visited[tmp*2].prev=tmp; //prev 저장
				q.add(tmp*2); //q에 현재 index 집어넣기
				
			}
			
			if(tmp>0 && !visited[tmp-1].v ){ //index가 범위안에 있으면서 방문하지 해당 노드 방문하지 않았을때
				visited[tmp-1].v=true; //방문처리
				visited[tmp-1].time=visited[tmp].time+1; //그 전 Node의 시간보다 +1
				visited[tmp-1].prev=tmp; //prev 저장
				q.add(tmp-1); //q에 현재 index 집어넣기
				
			}
			
			if(tmp<SIZE-1 && !visited[tmp+1].v) { //index가 범위안에 있으면서 방문하지 해당 노드 방문하지 않았을때
				visited[tmp+1].v=true; //방문처리
				visited[tmp+1].time=visited[tmp].time+1; //그 전 Node의 시간보다 +1
				visited[tmp+1].prev=tmp; //prev 저장
				q.add(tmp+1); //q에 현재 index 집어넣기
				
			}

		}

		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		
		visited=new Node[SIZE]; //객체 배열 생성
		
		//각 배열의 index 마다 객체 생성
		for(int i=0;i<SIZE;i++) {
			visited[i]=new Node();
		}
		
		//bfs 시작
		bfs(n,k);
		
	}
	
	

}
