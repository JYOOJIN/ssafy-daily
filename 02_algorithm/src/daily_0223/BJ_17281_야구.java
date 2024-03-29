package daily_0223;

import java.io.*;
import java.util.*;

public class BJ_17281_야구 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine()); //이닝의 수
		
		int[][] player=new int[n][9];
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<9;j++) {
				player[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		
		
		

	}

}
