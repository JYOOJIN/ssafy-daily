package daily_0226;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class kruskal {
	
	public static class Edge implements Comparable<Edge>{
		
		int from,to,weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
			
	}
	
	public static int[] parent;
	public static List<Edge> edgelist;
	public static int n;
	
	public static void make(int n) {
		
		parent=new int[n];
		for(int i=0;i<n;i++) {
			parent[i]=i;
		}
		
	}
	
	public static int find(int a) {
		
		if(a==parent[a]) return a;
		
		return parent[a]=find(parent[a]);
	}
	
	public static boolean union(int a,int b) {
		int rootA=find(a);
		int rootB=find(b);
		
		if(rootA==rootB) return false;
		
		parent[rootB]=rootA;
		return true;
	}
	
	
	public static void main(String[] args) {
		
		edgelist=new ArrayList<>();
		//간선 정보 입력받기
		
		
		//1)가중치 기준으로 정렬
		Collections.sort(edgelist);
		
		//2)서로소 집합 생성
		make(n);
		
		//3)낮은것부터 고르면서 트리 확장
		int edge=0; //고른 간선의 개수
		for(int i=0;i<edgelist.size();i++) {
			
			Edge tmp=edgelist.get(i);
			
			if(union(tmp.from,tmp.to)) {
				
			}else {
				continue;
			}
			
		}
		
		
		
		
		
		
		
		

	}

}
