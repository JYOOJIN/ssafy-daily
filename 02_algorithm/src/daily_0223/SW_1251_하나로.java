package daily_0223;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_1251_하나로
 * 난이도: D4
 * 수행결과: Pass
 * 메모리: 109204 KB
 * 시간: 723 ms
 * 코드길이: 2599 B
 * 풀이시간: 1시간
 * 풀이 전략: 최소 신장 트리(MST) 
 * 			- 간선 중심: 크루스칼 알고리즘 -> 여기서 이거 사용!! 	
 * 			- 정점 중심: 프림 알고리즘
 * =================================================
 * 크루스칼 알고리즘
 * 1) Edge 클래스 생성
 * 2) make,find,union 생성
 * 3) Edge 리스트 생성 후, 두 간선+가중치 값 넣기
 * 4) Edge 리스트 정렬
 * 5) Edge 리스트 처음부터 탐색하면서 해당 간선의 두 정점 union 가능하면 (구한 간선의 개수+1)(weight 정보 저장)
 * =================================================
 * 주의사항: 소수점까지 계산 위해서는 double 필요, 반올림은 Math.round()
 */

public class SW_1251_하나로 {
	
	public static List<Edge> edgelist; //간선 정보 저장
	public static int[] parent; //루트
	public static int n; //섬의 개수
	
	//간선 정보 저장 & 정렬 재정의 클래스
	static class Edge implements Comparable<Edge>{
		int from,to; 
		double weight;

		public Edge(int from, int to, double weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}
	
	}
	
	//서로소집합 생성
	public static void make() {
		for(int i=0;i<n;i++) {
			parent[i]=i;
		}
	}
	
	//집합의 루트 찾는 함수
	public static int find(int a) {
		if(a==parent[a]) return a;
		
		return parent[a]=find(parent[a]);
	}
	
	//집합끼리 합치는 함수
	public static boolean union(int a,int b) {
		
		int rootA=find(a);
		int rootB=find(b);
		
		if(rootA==rootB) return false; //이미 같은 집합이라면 합치지 않는다
		
		parent[rootB]=rootA;
		return true;
		
	}

	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int tc=Integer.parseInt(br.readLine()); //테스트케이스
		
		for(int t=1;t<=tc;t++) {
			n=Integer.parseInt(br.readLine()); //섬의개수
			
			long[][] land=new long[n][2]; //land의 x좌표,y좌표
			
			//x좌표 입력받기
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				land[i][0]=Integer.parseInt(st.nextToken());
			}
			//y좌표 입력받기
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<n;i++) {
				land[i][1]=Integer.parseInt(st.nextToken());
			}
			
			//세율 입력받기 -> 형식 주의!!!!!
			double e=Double.parseDouble(br.readLine());
			
			edgelist=new ArrayList<>(); //edge 담을 list 생성
			
			double[][] distance=new double[n][n]; //거리 정보 담을 인접행렬
			parent=new int[n]; //루트 집합 생성
			
			for(int i=0;i<n;i++) {
				for(int j=i+1;j<n;j++) { //자기 자신보다 번호가 큰 섬들과의 간선 찾기
					long x=Math.abs(land[i][0]-land[j][0]);
					long y=Math.abs(land[i][1]-land[j][1]);
					double dis=Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)); //두 섬 사이의 거리
					
					distance[i][j]=distance[j][i]=dis; //무향 그래프
					edgelist.add(new Edge(i,j,dis)) ; //edgelist에 간선 정보 넣는다
				}
			}
			
			//크루스칼 알고리즘
			
			//1. 간선 리스트 정렬
			Collections.sort(edgelist); //정렬
			
			//2. 서로소 집합 생성
			make();
			
			//3. union여부로 최소 간선 찾아서 트리 확장하기
			
			double[] min=new double[n-1]; //최소 거리 간선 배열
			int cnt=0; //간선의 개수

			while(true) {
				if(cnt==n-1) break; //MST의 간선 개수: n-1
				
				for(int i=0;i<edgelist.size();i++) { //정렬된 간선 리스트 처음부터 탐색
					Edge edge=edgelist.get(i);
					if(union(edge.from,edge.to)) { //현재 간선의 두 정점을 합칠 수 있다면
						min[cnt]=edge.weight; //고른 간선의 길이
						cnt++; //찾은 간선의 개수
						
					}else {
						continue; //사이클이 존재한다면 다음 간선 찾으러 간다
					}
				}
			}
			
			double m=0;
			for(int i=0;i<n-1;i++) {
				m+=Math.pow(min[i], 2);
			}
			
			double money=e*m; //환경 부담금
			long realmoney=Math.round(money); //환경 부담금 소수 첫째 자리에서 반올림 후 double에 담기
			 
			
			sb.append("#").append(t).append(" ").append(realmoney).append("\n"); //형식에 맞춰 sb에 append

		}
		System.out.println(sb); //출력

	}

}
