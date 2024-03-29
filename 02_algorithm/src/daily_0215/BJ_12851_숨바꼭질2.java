package daily_0215;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_12581_숨바꼭질 2
 * 난이도: G4
 * 수행결과: 맞았습니다!!
 * 메모리: 26344 KB
 * 시간: 188 ms
 * 코드길이: 1515 B
 * 풀이 전략: <bfs>: 재귀 쓰면 안된다. queue 사용.
 * 			
 */

public class BJ_12851_숨바꼭질2 {
	
	public static int[] visited;
	public static int time;
	public static int cnt;
	public static int SIZE=100001;
	
	public static void bfs(int n,int k) {
		
		Queue<Integer> q=new ArrayDeque<>();
		Arrays.fill(visited, -1);
		
		q.add(n);
		time=0;
		cnt=0;
		visited[n]=time;
		
		while(!q.isEmpty()) {
			
			int tmp=q.poll();
			
			if(tmp==k) {
				time=visited[tmp];
				cnt+=1;
			}
			
			if(tmp>0 && (visited[tmp-1]<0 || visited[tmp-1]>=visited[tmp]+1)) {
				visited[tmp-1]=visited[tmp]+1;
				q.add(tmp-1);
			}
			
			if(tmp<SIZE-1 && (visited[tmp+1]<0 || visited[tmp+1]>=visited[tmp]+1)) {
				visited[tmp+1]=visited[tmp]+1;
				q.add(tmp+1);
			}
			
			if(tmp*2<SIZE && (visited[tmp*2]<0 || visited[tmp*2]>=visited[tmp]+1)) {
				visited[tmp*2]=visited[tmp]+1;
				q.add(tmp*2);
			}
			
		}
		
	}
	
	

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		
		visited=new int[SIZE];
		
		bfs(n,k);
		
		System.out.println(time);
		System.out.println(cnt);

	}

}
