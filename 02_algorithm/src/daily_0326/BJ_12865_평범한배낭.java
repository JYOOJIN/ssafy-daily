package daily_0326;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_12865_평범한배낭
 * 난이도: G5
 * 수행결과: 맞았습니다!!
 * 메모리: 51560 KB
 * 시간: 172 ms
 * 코드길이: 1258 B
 * 풀이 전략: DP - Knapsack
 * 		대표적인 dynamic programming 문제. 하향식+메모제이션을 통해 풀이한다.
 * 		행을 물품의 번호, 열을 배낭이 담을 수 있는 무게로 한 이차원 배열을 채워나간다.
 * 		점화식만 알면 쉬운 문제. 꼭 이차원 배열을 직접 채워본다.
 * 		
 * -------------------------------------------------
 * 		if w_i<W:
 * 			if v_i+V[i-1,w-w_i] > V[i-1,w]:
 * 				V[i,w]=v_i+V[i-1,w-w_i]
 *			else:
 *				V[i,w]=V[i-1,w]
 * 			
 * 		else:
 * 			V[i,w]=V[i-1,w]
 * ---------------------------------------------------
 */

public class BJ_12865_평범한배낭 {
	
	static int n,k;
	static int[][] p;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken()); //물품의 수
		k=Integer.parseInt(st.nextToken()); //배낭 무게
		p=new int[n+1][2]; //물건의 무게와 가치. 1번 부터 시작
		
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			p[i][0]=Integer.parseInt(st.nextToken()); //물건 무게
			p[i][1]=Integer.parseInt(st.nextToken()); //물건 가치
		}
		
		int[][] bag=new int[n+1][k+1];
		
		for(int i=1;i<=n;i++) { //물건 숫자
			for(int j=1;j<=k;j++) { //배낭 무게
				if(p[i][0]<=j) {
					if(p[i][1]+bag[i-1][j-p[i][0]]>bag[i-1][j]) {
						bag[i][j]=p[i][1]+bag[i-1][j-p[i][0]];
					}else {
						bag[i][j]=bag[i-1][j];
					}
				}else {
					bag[i][j]=bag[i-1][j];
				}
			}
		}
		
		System.out.println(bag[n][k]);
	}


}
