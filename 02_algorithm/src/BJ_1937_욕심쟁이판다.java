import java.io.*;
import java.util.*;


public class BJ_1937_욕심쟁이판다 {

	static int n;
	static int[][] map,move;
	static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		move=new int[n][n];
		//각 칸마다 이동할 수 있는 칸의 수를 저장해두고
		//dfs로 이동하면서 더 이상 이동할 수 없을 때까지 가면서 최댓값을 구한다
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				int cnt=0;
				for(int d=0;d<4;d++) {
					int dx=i+dir[d][0];
					int dy=j+dir[d][1];
					//배열 범위를 벗어나거나 이동하는 곳에 대나무가 같거나 적다면
					if(dx<0 || dx>=n || dy<0 || dy>=n || map[dx][dy]<=map[i][j]) continue;
					cnt++;
				}
				move[i][j]=cnt;
			}
		}
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(move[i][j]);
			}
			System.out.println();
		}
		
		
	}
	
	
	

}
