package daily_0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_11659_구간 합 구하기 4
 * 난이도: S3
 * 수행결과: 맞았습니다!
 * 메모리: 75296 KB
 * 시간: 2072 ms
 * 코드길이: 1776 B
 * 풀이 전략: n,m의 범위가 10만이므로 최악의 경우 10만*10만 100억초로 시간초과가 난다
 * 			그러므로 <누적합>을 사용해야 한다. 재귀를 통해 누적합을 생성하고 
 * 			(end누적합) - (start누적합)으로 결과를 구한다.
 * 주의 사항: 배열은 0~n-1의 index, 여기에서는 1~n번째. 이럴때에는 배열을 n+1생성하고 맞춰서 하는게 편하다.
 * 			그리고  result 구할 때 end와 start 할 위치 조심하기
 */

public class BJ_11659 {
	
	private static int[] num; //초기 숫자 담는 배열
	private static int[] cnum; //누적합 담을 배열
	
	/**
	 * 누적합 구하기 위한 재귀함수
	 * @param n
	 * @param depth
	 */
	public static void sum(int n,int depth) {
		
		   if(depth>n) { //누적합 구한 숫자가 배열의 index를 넘어가면  return
			   return;
		   }
		   
		   //누적합을 구한다
		   //depth=1부터 들어오기 때문에 가능
		   cnum[depth]=cnum[depth-1]+num[depth];
		   
		   
		   //재귀함수 호출. 누적합 구한 횟수+1 한다
		   sum(n,depth+1);
		   
	}
 
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); //입력값 받을 buffer생성
		StringTokenizer st; //tokenize 선언
		
		st=new StringTokenizer(br.readLine()); //token담은 st생성
		int n=Integer.parseInt(st.nextToken()); //수 n 입력받기
		int m=Integer.parseInt(st.nextToken()); //합 구하는 횟수 m 입력받기
		
		num=new int[n+1]; //n+1만큼 배열생성. index=n을 사용하기 위해서
		cnum=new int[n+1]; //num과 같이 n+1만큼 배열생성
		 
		st=new StringTokenizer(br.readLine()); //한줄 읽기
		//배열 값 넣기
		for(int i=1;i<=n;i++) {
			//재귀용 코드
			num[i]=Integer.parseInt(st.nextToken());
			//누적합 바로 구하기
			//cnum[i]=cnum[i-1]+Integer.parseInt(st.nextToken());
		}
		
		//누적합 구하기 위한 재귀함수 호출
		//index=1부터 시작한다
		sum(n,1);
		
		
		//구해야 하는 횟수만큼 반복
		for(int t=0;t<m;t++) {
			st=new StringTokenizer(br.readLine()); //한줄 읽어서 token을 st에 저장
			
			int start=Integer.parseInt(st.nextToken()); //구해야 하는 합 시작 위치
			int end=Integer.parseInt(st.nextToken()); //구해야 하는 합 끝 위치
			
			int result=cnum[end]-cnum[start-1]; //끝위치-시작위치 해서 그 사이 누적합 구한다
			
			System.out.println(result); //형식에 맞춰 출력

		}
		
		
		

	}

}
