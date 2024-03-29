package daily_0305;

import java.io.*;
import java.util.*;

public class BJ_17135_캐슬디펜스 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int d=Integer.parseInt(st.nextToken());
		
		int[][] map=new int[n+1][m];
		for(int i=0;i<n+1;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[j][i]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		
	}

}
