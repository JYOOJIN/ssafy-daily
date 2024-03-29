package daily_0201;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: SW_2001_파리 퇴치
 * 난이도: D2
 * 수행결과: pass
 * 메모리: 19420 KB
 * 시간: 110 ms
 * 코드길이: 1271 B
 * 풀이 전략: 크기가 n인 이중배열의 각 좌표마다 m만큼의 이중배열 크기를 탐색하며 최대 파리의 개수를 구한다.
 * 주의사항: sliding Window로 풀어보기!!!!!
 */

public class SW_2001 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] arr; //파리를 담는 배열
		int testcase=Integer.parseInt(br.readLine()); //testcase의 수
	
		for(int t=1;t<=testcase;t++) { //testcase만큼 반복
			
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken()); //파리 담는 배열의 크기
			int m=Integer.parseInt(st.nextToken()); //파리채의 크기
			
			arr=new int[n][n]; //입력받은 크기 만큼 배열 생성
			
			//입력받은 값 배열에 넣기
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			int max=Integer.MIN_VALUE; //최대 파리의 개수
			int fly=0; //각 파리채마다 파리의 개수
			
			// (0,0) 에서 (n-m,n-m)까지
			for(int i=0;i<=n-m;i++) {
				for(int j=0;j<=n-m;j++) {
					fly=0;
					//각 좌표마다 크기가 m인 파리채에서 잡히는 파리의 수를 구한다
					for(int x=i;x<i+m;x++) {
						for(int y=j;y<j+m;y++) {
							fly+=arr[x][y];
						}
					}
					//현재 좌표의 파리의 개수가 max보다 크면 max를 update
					if(fly>max) max=fly;
				}
			}
			
			//형식에 맞춰 출력
			System.out.println("#"+t+" "+max);
		}
		
	}

}
