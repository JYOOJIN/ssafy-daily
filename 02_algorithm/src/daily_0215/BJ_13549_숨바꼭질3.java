package daily_0215;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_13549_숨바꼭질 3
 * 난이도: G5
 * 수행결과: 맞았습니다!!
 * 메모리: 16044 KB
 * 시간: 120 ms
 * 코드길이: 1403 B
 * 풀이 전략: <bfs>: 재귀 쓰면 안된다. queue 사용.
 * 			
 */

public class BJ_13549_숨바꼭질3 {
	
	public static int[] visited;
	public static int SIZE=100001;
	
	public static void bfs(int n,int k) {
		
		Queue<Integer> q=new ArrayDeque<>();
		Arrays.fill(visited, -1);

		q.add(n);
		visited[n]=0;
		
		while(!q.isEmpty()) {
			
			int tmp=q.poll();
			
			if(tmp==k) {
				System.out.println(visited[tmp]);
				return;
			}
			
			if(2*tmp<SIZE && (visited[2*tmp]<0 || visited[2*tmp]>=visited[tmp])) {
				visited[tmp*2]=visited[tmp];
				q.add(tmp*2);
			}
			if(tmp>0 && (visited[tmp-1] <0 || visited[tmp-1]>=visited[tmp]+1)) {
				visited[tmp-1]=visited[tmp]+1;
				q.add(tmp-1);
			}
			if(tmp<SIZE-1 && (visited[tmp+1] <0 || visited[tmp+1]>=visited[tmp]+1)) {
				visited[tmp+1]=visited[tmp]+1;
				q.add(tmp+1);
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
	}

}
