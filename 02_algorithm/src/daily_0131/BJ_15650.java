package daily_0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_15650_N과 M(2)
 * 난이도: S3
 * 수행결과: 맞았습니다!
 * 메모리: 11536 KB
 * 시간: 80 ms
 * 코드길이: 1288 B
 * 풀이 전략: <조합: 순서x, 중복x>
 * 			재귀를 이해해야 한다. 다음 자릿수를 갈 때마다 그 전에 들어간 숫자보다 큰 수부터 for문을 돌린다.
 * 			어차피 start지점을 주기 때문에 visit 확인할 필요가 x.
 * 			모든 자릿수를 채웠으면 return
 */

public class BJ_15650 {

	private static int[] arr; //각 조합 자릿수별로 숫자 담는 배열
	private static StringBuilder result=new StringBuilder(); //결과담을 stringBuilder. appned 기능 이용하기 위해서
	
	/**
	 * 탐색을 해서 가능합 조합 result에 저장하는 재귀함수
	 * @param n 자연수의 개수
	 * @param m 자릿수
	 * @param depth 현재 자릿수
	 * @param start 자릿수에 넣을 수 있는 숫자 시작 지점
	 */
	public static void dfs(int n,int m,int depth,int start) {
		
		// 하나의 조합 완성했을 경우
		if(depth==m) { //자릿수를 끝까지 채웠다면, 즉 m개를 모두 뽑았을 경우
			for(int e:arr) { //for-each로 arr에 들어있는 요소 꺼내기
				result.append(e).append(" "); 
			}
			result.append("\n");
			return; //result에 형식에 맞게 넣고 return
		}
		
		//현재 자릿수에 집어넣는다
		for(int i=start;i<=n;i++) {
			//현재 자릿수에 숫자 넣기
			arr[depth]=i;
			//재귀함수 호출
			//자릿수+1, 중복이 허용되지 않으므로 시작 위치가 현재 들어간 숫자보다 +1 되어야한다
			dfs(n,m,depth+1,i+1);
		}
		
		
		
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); //입력값 담을 buffer생성
		StringTokenizer st=new StringTokenizer(br.readLine()); //StringTokenizer 생성
		int n=Integer.parseInt(st.nextToken()); //n 입력받기
		int m=Integer.parseInt(st.nextToken()); //m 입력받기
		
		arr=new int[m]; //자릿수 만큼 배열 생성하기
		
		//재귀함수 호출
		//현재는 첫째자리 수고 숫자 시작 위치는 1이다
		dfs(n,m,0,1); 
		
		//형식에 맞춰 출력
		System.out.println(result);
		
	}

}
