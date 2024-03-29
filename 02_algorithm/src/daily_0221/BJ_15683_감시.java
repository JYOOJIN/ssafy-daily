package daily_0221;

import java.io.*;
import java.util.*;

public class BJ_15683_감시 {
	
	public static int[][] room;
	public static int n,m;
	
	public static int[][] dir1= {{-1,0,},{0,1},{1,0},{0,-1}};
	public static int[][] dir2= {{-1,0,1,0},{0,1,0,-1}};
	
	
	
	//사각지대의 개수
	public static int blind() {
		int cnt=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(room[n][m]==0) cnt+=1; 
			}
		}
		
		return cnt;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		room=new int[n][m];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				room[i][j]=Integer.parseInt(st.nextToken());
			}
		}
	}

}
