package daily_0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_11659_구간 합 구하기 5
 * 난이도: S1
 * 수행결과: 맞았습니다!
 * 메모리: 129560 KB
 * 시간: 1360 ms
 * 코드길이: 1973 B
 * 풀이 전략: <시간 초과 고려> => Stringbuilder 사용, 누적합 사용!!!
 * 			처음에 재귀로 풀었으나 시간초과. 최대한 시간 복잡도를 줄이기 위해 for문으로 행별로 누적합 구하기.
 * 주의 사항: 배열은 0~n-1의 index, 여기에서는 1~n번째. 이럴때에는 배열을 n+1생성하고 맞춰서 하는게 편하다.
 * 			StringBuilder를 사용해서 sysout을 마지막에 한번만 사용하도록 한다
 */

public class BJ_11660 {

	
	private static int[][] num; //초기 숫자 담는 배열
	private static int[][] cnum; //누적합 담을 배열
	
	
//	/**
//	 * 행마다 누적합 구하는 재귀함수
//	 * @param n
//	 * @param x
//	 * @param y
//	 */
//	public static void sum(int n,int x,int y) {
//		
//		if(y>n) { //누적합 구한 숫자가 배열의 열을 넘어가면  return
//			return;
//		}
//		//누적합을 구한다
// 		//depth=1부터 들어오기 때문에 가능
//		for(int i=1;i<=n;i++) {
//			cnum[i][y]=cnum[i][y-1]+num[i][y];
//			
//			//재귀함수 호출. 열을 증가시킨다.
//			sum(n,i,y+1);
//		}
//		
//		
//	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); //입력값 받을 buffer생성
		StringTokenizer st=new StringTokenizer(br.readLine()); //tokenize 선언
		int n=Integer.parseInt(st.nextToken());//수 n 입력받기
		int m=Integer.parseInt(st.nextToken());//합 구하는 횟수 m 입력받기
		
		
		num=new int[n+1][n+1]; //n+1만큼 이차원 배열생성. 
		cnum=new int[n+1][n+1]; //num과 같이 n+1만큼 배열생성
		
		//2차원 배열에 값 넣기
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine()); //st위치 주의!!!!
			for(int j=1;j<=n;j++) {
				num[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		//누적합 구하는 재귀함수 호출
		//sum(n,1,1);
		
		//누적합 구하기
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				cnum[i][j]=cnum[i][j-1]+num[i][j];
			}
		}
		
		//결과 저장하기 위한 StringBuilder 생성
		StringBuilder sb=new StringBuilder();

		//합을 구해야 하는 횟수만큼 반복
		for(int i=0;i<m;i++) {
			int result=0; //결과값 초기화
			st=new StringTokenizer(br.readLine()); //한줄 읽고 tokenizer
			//좌표 입력받기
			int x1=Integer.parseInt(st.nextToken());
			int y1=Integer.parseInt(st.nextToken());
			int x2=Integer.parseInt(st.nextToken());
			int y2=Integer.parseInt(st.nextToken());
			
			//누적합 이용해서 좌표 사이 값 더하기
			for(int j=x1;j<=x2;j++) {
				result+=cnum[j][y2]-cnum[j][y1-1];
			}
			
			//stringBuilder에 결과값 저장
			sb.append(result).append("\n");
			
		}
		
		//출력
		System.out.println(sb);
		
	
	}

}
