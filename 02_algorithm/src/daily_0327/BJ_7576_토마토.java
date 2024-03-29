package daily_0327;

import java.util.*;
import java.io.*;

/**

@author 정유진
문제: BJ_7576_토마토
난이도: G5
수행결과: 맞았습니다!!
메모리:105344 KB
시간: 560 ms
코드길이: 2364 B
풀이 전략: 동시에 전파이므로 bfs -> bfs의 depth는 visited+1을 통해 구한다
		이 문제에서는 tom=0이라면 방문 하지 않았으면서 익지 않았으므로 따로 visited를 구현하지는 않았다.
		하지만 <<bfs의 depth는 visited+1 임을 잊지 않기!!!!>>
		
		이 문제의 핵심은 익은 토마토가 2군데 이상 있다면 어떻게 처리해야하지? 이다.
		하지만 queue에 한번에 넣어주고 bfs를 시작하면 자동으로 각 위치에서 bfs를 시작하는 것처럼 작동한다.
		<<토마토가 1인 경우 바로 queue에 넣어주고 하나씩 빼면서 bfs를 하면 각 위치에서 동시에 시작하는 것과 같다.>>
		
*/
public class BJ_7576_토마토 {

    public static int n,m;
    public static int[][] tom;
    public static int[][] visited;
    public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};
    public static Queue<int[]> q=new ArrayDeque<>();

	public static void main(String[] args) throws IOException{
	    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st=new StringTokenizer(br.readLine());
	    
	    m=Integer.parseInt(st.nextToken());
	    n=Integer.parseInt(st.nextToken());
	    
	    tom=new int[n][m];
	    visited=new int[n][m];
	    
	    
	    int flag=1;
	    
	    for(int i=0;i<n;i++) {
	        st=new StringTokenizer(br.readLine());
	        for(int j=0;j<m;j++) {
	            tom[i][j]=Integer.parseInt(st.nextToken());
	            flag*=tom[i][j];
	            if(tom[i][j]==1) q.add(new int[] {i,j});
	        }
	    }
	    
	    //처음부터 모든 토마토가 익어있는 경우
	    if(flag!=0) { 
	        System.out.println(0);
	    }else {
	    	System.out.println(bfs());
	    }

	}
	
	public static boolean check() {
	    
	    for(int i=0;i<n;i++) {
	        for(int j=0;j<m;j++) {
	           if(tom[i][j]==0) return false;
	        }
	    }
	    
	    return true;
	  
	}
	
	public static int bfs() {
	    
	    while(!q.isEmpty()) {
	        
	        int[] tmp=q.poll();
	        
	        for(int d=0;d<4;d++) {
	            int dx=tmp[0]+dir[d][0];
	            int dy=tmp[1]+dir[d][1];
	            
	            if(dx<0 || dx>=n || dy<0 || dy>=m) continue;
	            
	            if(tom[dx][dy]==0)  { //익지 않은 토마토라면(==아직 방문하지 않음)
	            	q.add(new int[] {dx,dy});
	                tom[dx][dy]=tom[tmp[0]][tmp[1]]+1;
	            }
	            
	        }
	
	    }
	   
	    
	    //탐색이 끝난 후
	    if(!check()) {
	    	return -1;
	    }else {
	    	int max=0;
	    	for(int i=0;i<n;i++) {
	    		for(int j=0;j<m;j++) {
	    			if(max<tom[i][j]) max=tom[i][j];
	    		}
	    	}
	    	return max-1;
	    }
	    
	    
	    
	    
	    
	}
}
