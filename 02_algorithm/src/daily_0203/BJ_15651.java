package daily_0203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_15651_N과 M(3)
 * 수행결과: 맞았습니다!
 * 메모리: 112212 KB
 * 시간: 368 ms
 * 코드길이: 1382 B
 * 풀이 전략: <중복순열: 순서o, 중복o>
 * 			재귀를 이해해야한다. 재귀를 돌때마다, 즉 각 자릿수에 갈때마다 for문으로 모든 숫자를 확인한다. 
 * 			깊이가 m과 같아지면 길이를 넘은 것이기에 return, 빠져나오고 for문 다시 돌면서 가능한거 찾는다.
 * 			중복이 가능하기 때문에, visit를 확인하지 않아도 되서 더 쉽다.
 * 주의사항: 재귀함수에서 depth+1 대신 depth++를 했더니 stackOverflow 발생!!!!!!!!!
 */


public class BJ_15651 {
	
	private static StringBuilder sb=new StringBuilder();
	private static int[] arr; //각 조합별로 숫자 담을 배열
	
	/**
	 * 각 순열을 구해주는 재귀 함수
	 * @param n 자연수 범위
	 * @param m 총 자릿수
	 * @param depth 현재 자릿수를 나타낸다
	 */
	public static void dfs(int n,int m,int depth) {
		if(depth==m) { //자릿수를 다 채웠다면
			for(int a:arr) { //그동안 채운 숫자들 형식에 맞춰 string에 추가
				sb.append(a).append(" ");
			}
			sb.append("\n");
			return; //하나의 순열 완성하면 빠져나가기
		}
		
		for(int i=0;i<n;i++) { //n까지의 자연수이므로 n번 반복
			
			arr[depth]=i+1; //현재 depth,즉 자리수에 값 넣는다
			dfs(n,m,depth+1); //재귀함수 호출. 다음 자릿수로 넘어간다
			
		}
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken()); //자연수 범위
		int m=Integer.parseInt(st.nextToken()); //각 순열의 자릿수
		 
		arr=new int[m]; //자릿수만큼 생성
		
		dfs(n,m,0); //재귀함수 호출. 처음에는 0번째 자리부터 시작한다
		
		System.out.println(sb); //형식에 맞춰 출력
		
		
	}
	
}

