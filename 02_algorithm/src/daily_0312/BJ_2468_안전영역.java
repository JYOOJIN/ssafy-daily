package daily_0312;

import java.io.*;
import java.util.*;

public class BJ_2468_안전영역 {
	
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int maxCnt;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		maxCnt=0; //최대 안전 구역의 갯수
		
		int max=0; //현재 지역의 최대 높이
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++) {
				map[i][j]=Integer.parseInt(br.readLine());
		
			}
		}
		
		
		
		
		
	}
}
