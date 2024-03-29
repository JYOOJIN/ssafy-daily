package daily_0203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_15652_N과 M(4)
 * 난이도: S3
 * 수행결과: 맞았습니다!
 * 메모리: 13816 KB
 * 시간: 100 ms
 * 코드길이: 881 B
 * 풀이 전략: <중복조합: 순서x, 중복o>
 * 			중복을 허용하면서 올림차순 이라는 것은 순서는 안따지는 중복조합이다.
 * 			재귀 이용. 각 조합의 길이만큼의 배열에 for문을 돌며 숫자를 넣는데, 넣기 시작하는 숫자는
 * 			본인을 포함한 n까지의 숫자이다. 1부터 n까지 보는것이 아니다. 나와 같거나 나보다 큰 숫자만 본다.
 * 			
 */


public class BJ_15652 {
	
	private static int[] arr; //각 조합 담을 배열
	private static StringBuilder sb=new StringBuilder();
	
	/**
	 * 각 조합 구할 재귀함수
	 * @param n 자연수 범위
	 * @param m 총 자릿수
	 * @param depth 현재 자릿수
	 * @param start 시작해야하는 자연수
	 */
	public static void dfs(int n,int m,int depth, int start) {
		
		if(depth==m) { //자릿수를 모두 채웠다면
			for(int e:arr) { //그동안 채운 숫자들 형식에 맞춰 string에 추가
				sb.append(e).append(" ");
			}
			sb.append("\n");
			return; //빠져나온다
		}
		
		//start 부터 시작을 한다.
		for(int i=start;i<=n;i++) {
			arr[depth]=i; //현재 값 현재 자릿수에 넣기
			dfs(n,m,depth+1,i); //자릿수는 +1 되지만, 중복이 가능하므로(현재 자연수부터 시작 가능하므로) start parameter는 증가 x
		}
		
		
	}
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken()); //자연수 범위
		int m=Integer.parseInt(st.nextToken()); //각 순열의 자릿수
		 
		arr=new int[m]; //자릿수만큼 생성
		
		dfs(n,m,0,1); //재귀함수 호출. 현재 자릿수는 0, 자연수는 1부터 시작
		
		System.out.println(sb); //형식에 맞춰 출력
		

	}

}


