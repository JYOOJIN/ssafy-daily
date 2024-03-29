package daily_0227;

import java.io.*;
import java.util.*;


public class BJ_1753_최단경로 {

	public static int[][] map; //인접행렬
	public static List<Node>[] list;
	public static boolean[] visited; //방문 체크 배열
	public static int[] min; //최단 거리 배열
	public static int V;
	
	public static class Node{
		int to,weight;

		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}
	}
	
	public static void main(String[] args) throws IOException{
		 BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st=new StringTokenizer(br.readLine());
		 V=Integer.parseInt(st.nextToken());
		 int E=Integer.parseInt(st.nextToken());
		 
		 map=new int[V+1][V+1];
		 list=new ArrayList[V+1];
		 for(int i=1;i<=V;i++) {
			 list[i]=new ArrayList<Node>();
		 }
		 visited=new boolean[V+1];
		 min=new int[V+1];
		 
		 int K=Integer.parseInt(br.readLine());
		 for(int i=0;i<E;i++) {
			 st=new StringTokenizer(br.readLine());
			 int u=Integer.parseInt(st.nextToken());
			 int v=Integer.parseInt(st.nextToken());
			 int w=Integer.parseInt(st.nextToken());
			 
			 map[u][v]=w; //유향 가중치 그래프
			 list[u].add(new Node(v,w)); //유향 가중치 그래프
			 
		 }
		 
		 for(int i=1;i<=V;i++) {
			 dijkstra(K, i);
			 if(min[i]>=Integer.MAX_VALUE) System.out.println("INF");
			 else System.out.println(min[i]);
		 }
	}
	
	public static int minDis(int[] minArr) {
		
		int min=Integer.MAX_VALUE;
		int minIndex=0;
		for(int i=1;i<=V;i++) {
			if(min>minArr[i] && !visited[i]) {
				min=minArr[i];
				minIndex=i;
			}
		}
		
		return minIndex; //최소 거리를 가지는 index
		
	}
	
	public static void dijkstra(int start,int end) {
		Arrays.fill(min,Integer.MAX_VALUE);
		
		visited=new boolean[V+1];

		visited[start]=true;
		min[start]=0;
		
		while(start!=end) {
			
			boolean b=true;
			for(int i=1;i<=V;i++) {
				if(i==end) continue;
				b=b&&visited[i];
			}
			
			if(visited[end]||b) break;
			
//			for(int i=1;i<=V;i++) {
//				if(map[start][i]>0) {
//					min[i]=Math.min(map[start][i]+min[start], min[i]);
//				}
//			}
			
			if(list[start]==null) continue;
			
			for(int i=0;i<list[start].size();i++) {
				min[i]=Math.min(list[start].get(i).weight+min[start], min[i]);
			}
			
			int next=minDis(min);
			visited[next]=true;
			start=next;
		}
		
	}

}
