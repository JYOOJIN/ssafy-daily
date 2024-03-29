package daily_0305;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int[][] dir= {{1,0},{0,-1},{-1,0},{0,1}}; //하좌상우 방향벡터
		
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		
		int[][] map=new int[n][n];
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		//현재 방향(하)
		int d=0; 
		//현재 위치(0,0)
		int x=0;
		int y=0; 
		
		int result=map[x][y];
		
		//명령만큼 반복
		for(int i=0;i<k;i++) {
			String command=br.readLine();
			if(command.equals("F")) { //전진
				x+=dir[d][0];
				y+=dir[d][1];
				result+=map[x][y];
			}else if(command.equals("R")){ //90도 회전
				d=(d+1)%4; 
			}
			
		}
		
		System.out.println(result);
		
		
	}

}
