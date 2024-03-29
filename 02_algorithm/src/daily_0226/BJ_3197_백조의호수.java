package daily_0226;

import java.io.*;
import java.util.*;


public class BJ_3197_백조의호수 {
	
	public static class Pair{
		int x,y;

		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}
	
	public static char[][] land;
	public static char[][] copy;
	public static int r,c;
	public static int[][] dir={{-1,0},{0,1},{1,0},{0,-1}};
	public static boolean[][] visited;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		r=Integer.parseInt(st.nextToken());
		c=Integer.parseInt(st.nextToken());
		
		land=new char[r][c];
		
		
		for(int i=0;i<r;i++) {
			String s=br.readLine();
			land[i]=s.toCharArray();
		}
		
		Pair[] people=new Pair[2];
		int cnt=0;
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				if(land[i][j]=='L') {
					people[cnt]=new Pair(i,j);
					cnt++;
				}
			}
		}
		
		int result=0;
		while(true) {
			
			
			if(meet(new boolean[r][c],people[0],people[1])) {
				System.out.println(result);
				break;
			}
			
			visited=new boolean[r][c];
			copy=copy(land);
			
			for(int i=0;i<r;i++) {
				for(int j=0;j<c;j++) {
					if(!visited[i][j] && (land[i][j]=='.'||land[i][j]=='L')) {
						for(int d=0;d<4;d++) {
							int dx=i+dir[d][0];
							int dy=j+dir[d][1];
							
							if(dx>=0 & dx<r && dy>=0 && dy<c && !visited[dx][dy] && land[dx][dy]=='X') {
								copy[dx][dy]='.';
							}

						}
					}
				}
			}

			land=copy(copy);
			result++;
			
		}
		
		
		
	}
	
	public static char[][] copy(char[][] arr) {
		char[][] copy=new char[r][c];
		
		for(int i=0;i<r;i++) {
			for(int j=0;j<c;j++) {
				copy[i][j]=arr[i][j];
			}
		}
		
		return copy;
		
	}
	
	public static boolean meet(boolean[][] visited,Pair start,Pair end) {
		
		Queue<Pair> q=new ArrayDeque<>();
		visited[start.x][start.y]=true;
		q.add(new Pair(start.x,start.y));
		
		
		while(!q.isEmpty()) {
			
			Pair tmp=q.poll();
			
			if(tmp.x==end.x && tmp.y==end.y) return true;
			
			
			for(int d=0;d<4;d++) {
				int dx=tmp.x+dir[d][0];
				int dy=tmp.y+dir[d][1];
				
				if(dx>=0 & dx<r && dy>=0 && dy<c && !visited[dx][dy] && (land[dx][dy]=='.'||land[dx][dy]=='L')) {
					visited[dx][dy]=true;
					q.add(new Pair(dx,dy));
				}

			}
			
		}
		
		return false;
	}
	
	

	
}
