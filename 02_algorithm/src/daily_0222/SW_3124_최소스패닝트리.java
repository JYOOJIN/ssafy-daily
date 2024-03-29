package daily_0222;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_3124_최소 스패닝 트리
 * 난이도: D4
 * 수행결과: Pass
 * 메모리: 121928 KB
 * 시간: 1922 ms
 * 코드길이: 1915 B
 * 풀이 전략: 최소 신장 트리(MST) 
 * 			- 간선 중심: 크루스칼 알고리즘 -> 여기서 이거 사용!! 	
 * 			- 정점 중심: 프림 알고리즘
 * 크루스칼 알고리즘:
 * - make, union, find 생성
 * - edge 정보 담는 클래스 생성. 이때 comparable 재정의.
 * - edgeList 생성.
 * - V-1개의 간선 선택
 * 
 * 주의사항: 애초에 더하면서 구한 weight의 값이 무조건 최소이다. 따로 min과 비교해서 구할 필요 없다.
 */

public class SW_3124_최소스패닝트리 {
	
	public static Edge[] edgelist;
	public static int[]	parent;
	public static int v,e;
	
	//간선의 정보를 담는 class
	//comparable 재정의를 통해 가중치 기준으로 오름차순 정렬하도록 한다
	static class Edge implements Comparable<Edge>{
		int to,from;
		long weight;

		public Edge(int to, int from, long weight) {
			super();
			this.to = to;
			this.from = from;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return (int) (this.weight-o.weight); //가중치 기준으로 오름차순
		}

	}
	
	public static void make() {
		parent=new int[v+1]; //1부터v까지
		for(int i=1;i<=v;i++) {
			parent[i]=i; //초기 부모는 자기 자신
		}
	}
	
	//찾기
	public static int find(int a) {
		
		if(a==parent[a]) return a;
		
		return parent[a]=find(parent[a]); //경로 압축

	}
	
	//합하기
	public static boolean union(int a,int b) {
		int rootA=find(a);
		int rootB=find(b);
		
		if(rootA==rootB) return false; //같은 루트를 가지고 있다면 이미 같은 트리이다
		
		parent[rootB]=rootA;
		return true;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc=Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			st=new StringTokenizer(br.readLine());
			v=Integer.parseInt(st.nextToken());
			e=Integer.parseInt(st.nextToken());
			
			edgelist=new Edge[e]; //간선의 개수만큼 배열 생성
			
			//간선 정보 입력받기
			for(int i=0;i<e;i++) {
				st=new StringTokenizer(br.readLine());
				int from=Integer.parseInt(st.nextToken());
				int to=Integer.parseInt(st.nextToken());
				long weight=(long)Integer.parseInt(st.nextToken());
				
				edgelist[i]=new Edge(from,to,weight);
				
			}
		
			
			Arrays.sort(edgelist);
			
			//1.집합 초기화
			make();
			
			//2.정렬된 간선 하나씩 탐색하면서 트리 확장
			int cnt=1; //정점의 개수
			long sum=0; //weight의 합
			
			for(int i=0;i<e;i++) {
				Edge e=edgelist[i];
				if(union(e.from,e.to)) { //사이클이 아니라면
					cnt++; //정점의 수+1
					sum+=e.weight; //가중치 더하기
					if(cnt==v) break; //모든 정점을 돌았다면 break
				}else {
					continue; //사이클이 존재하면 다음으로 가중치가 작은 간선을 찾는다
				}
			}
			
			System.out.println("#"+t+" "+sum);
	
		}
		

	}

}
