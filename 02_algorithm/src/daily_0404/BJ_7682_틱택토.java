package daily_0404;

import java.io.*;
import java.util.*;

public class BJ_7682_틱택토 {

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			String s=br.readLine();
			if(s.equals("end")) return;
			char[][] map=new char[3][3];
			char[] c=s.toCharArray();
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					map[i][j]=c[3*i+j];
				}
			}
			
			for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			
		}
		
		
		
	}

}
