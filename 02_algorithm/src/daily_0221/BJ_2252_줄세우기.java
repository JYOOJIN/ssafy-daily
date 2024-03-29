package daily_0221;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_2252_줄 세우기
 * 난이도: G3
 * 수행결과: 맞았습니다!!
 * 메모리: 48336 KB
 * 시간: 716 ms
 * 코드길이: 1966 B
 * 풀이 전략: <인접 리스트> 이용!! 인접행렬 사용시 메모리 초과가 난다.인접행렬 사용시 대부분 메모리 초과가 나므로 인접 리스트로 정착해야겠다.
 * 			<위상 정렬> 이용!! bfs,즉 Queue를 이용하자. dfs를 사용해 stack으로도 가능하지만, bfs만 알아도 될것같다.
 * 			진입차수가 0이면 q에 넣고, q에서 빼낸 노드의 간선을 없애는 것을 반복하면 된다. 
 * 
 * 주의사항:
 * V가 정점의 수, E가 간선의 수 일때
 * - 인접 리스트 O(V+E)
 * - 인접 행렬 O(V^2) 
 * 
 * 
 * @href https://sunrise-min.tistory.com/entry/%EC%9C%84%EC%83%81-%EC%A0%95%EB%A0%ACTopological-sort-BFSDFS
 */

public class BJ_2252_줄세우기 {

	public static int n,m; 
	public static int[] Indegree; //진입차수 배열
	public static ArrayList<Integer>[] list; //인접 리스트

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken()); //학생 수
		m=Integer.parseInt(st.nextToken()); //키를 비교한 회수
		
		//학생 번호는 1부터 n까지
		list=new ArrayList[n+1];
		Indegree=new int[n+1]; 
		
		//인접 리스트 생성
		for(int i=1;i<=n;i++) {
			list[i]=new ArrayList<Integer>(); //list 배열 안에 list 넣기
		}
		
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			
			list[from].add(to); //유향그래프
			Indegree[to]+=1; //진입차수 +1
		}
		
		bfs();
	
	}
	
	public static void bfs() {
		
		Queue<Integer> q=new ArrayDeque<>();
		
		//처음에 돌면서 진입차수가 0인 것을 모두 q에 넣는다.
		for(int i=1;i<=n;i++) {
			if(Indegree[i]==0) { //진입차수가 0이면 
				q.add(i); //q에 넣는다.
			}
		}
		
		
		while(!q.isEmpty()) { //더이상 탐색할 노드가 없을 때 중단
			
			//tmp와 인접한 간선 없애고, 그 노드의 진입차수가 0이면 q에 넣는다
			int tmp=q.poll(); 
			System.out.print(tmp+" "); //탐색한 노드 출력
			
			ArrayList<Integer> t= list[tmp]; //현재 tmp와 인접한 노드를 담은 리스트 t
			
			if(t==null) continue; //인접한 노드가 없다면 넘기고 q에서 다음 노드 꺼낸다
			
			for(int e:t) { //인접한 노드를 담은 리스트에서 노드 하나씩 탐색한다.
				
				Indegree[e]-=1; //인접한 노드 진입차수-1
				
				//간선 없앨때마다 그때그때 진입차수가 0이 되는지 확인하므로 따로 방문 처리 할 필요가 없다. 
				if(Indegree[e]==0) { //인접한 노드 진입차수가 0이 되었다면
					q.add(e); //q에 넣는다.
				}
				
			}
			
		}
		
	}
	
	

}
