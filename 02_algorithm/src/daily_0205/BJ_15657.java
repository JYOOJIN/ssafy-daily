package daily_0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_15657_N과 M(8)
 * 난이도: S3
 * 수행결과: 맞았습니다!
 * 메모리: 16168 KB
 * 시간: 116 ms
 * 코드길이: 1786 B
 * 풀이 전략: <중복조합: 순서x, 중복o>
 * 			재귀함수 내에서 start parameter 넘겨줄때 주의!!!
 * 			
 */

public class BJ_15657 {
	
	private static int arr[];
	private static int num[];
	private static StringBuilder sb=new StringBuilder();
	
	public static void dfs(int n,int m,int depth,int start) {
		if(depth==m) { //자릿수를 모두 채웠다면
			for(int e:arr) { //그동안 채운 숫자들 형식에 맞춰 string에 추가
				sb.append(e).append(" ");
			}
			sb.append("\n");
			return; //빠져나온다
		}
		
		for(int i=start;i<n;i++) { //시작지점부터 끝까지
			arr[depth]=num[i]; //현재 index의 num을 현재 자릿수에 넣는다
			dfs(n,m,depth+1,i); //중복 가능하므로, 현재 index의 num을 포함해서 그 이상을 탐색한다
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken()); //자연수 개수 입력받기
		int m=Integer.parseInt(st.nextToken()); //자릿수 입력받기
		
		num=new int[n]; //자연수 개수만큼 생성
		arr=new int[m]; //자릿수 개수만큼 생성
		
		//입력받은 자연수를 num 배열에 넣는다
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			num[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(num); //사전순으로 출력해야하므로 자연수 배열 자체를 처음부터 정렬한다
		
		dfs(n,m,0,0); //재귀함수 호출. 처음 자릿수는 0이고 처음 자연수 배열의 index도 0이다
		
		System.out.println(sb); //형식에 맞춰 출력
	}

}
