package daily_0228;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_1010_다리놓기
 * 난이도: S5
 * 수행결과: 맞았습니다!!
 * 메모리: 1312 KB
 * 시간: 128 ms
 * 코드길이: 1068 B
 * 풀이 전략: m개의 다리 중 순서없이 n개를 구하는 것 -> 조합!!!
 * 			그러나 재귀로 풀면 시간초과가 난다. mCk를 구하는 방법 -> dp로 이항계수 구하기
 * 			mCk = m-1Ck-1 + m-1Ck
 * 
 */

public class BJ_1010_다리놓기 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=0;t<tc;t++) {
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken());
			int m=Integer.parseInt(st.nextToken());
			
			//m개중 n개를 뽑는 조합
			int[][] b=new int[m+1][n+1];
			
			for(int i=0;i<=m;i++) {
				for(int j=0,end=Math.min(i, n);j<=end;++j) {
					if(j==0 || j==i) b[i][j]=1; //mC0 과 mCm은 1이다. 
					else b[i][j]=b[i-1][j-1]+b[i-1][j]; //그 외는 점화식으로 구한다.
				}
					
			}
			
			System.out.println(b[m][n]); //mCn의 결과

		}

	}

}
