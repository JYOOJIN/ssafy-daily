package daily_0216;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_6987_월드컵
 * 난이도: 
 * 수행결과: 맞았습니다!!
 * 메모리:  KB
 * 시간:  ms
 * 코드길이:  B
 * 풀이 전략: 
 * 
 */


public class BJ_6987_월드컵 {
	
	public static StringBuilder sb=new StringBuilder();

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int[][] country=new int[6][3]; //각 나라별로 승,무,패
		
		
		for(int t=0;t<4;t++) {
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<6;i++) {
				country[i][0]=Integer.parseInt(st.nextToken()); //승
				country[i][1]=Integer.parseInt(st.nextToken()); //무
				country[i][2]=Integer.parseInt(st.nextToken()); //패
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}
		
		
		
	}

}
