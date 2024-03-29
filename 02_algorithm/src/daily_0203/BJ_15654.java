package daily_0203;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_15654_N과 M(5)
 * 난이도: S3
 * 수행결과: 맞았습니다!
 * 메모리: 31116 KB
 * 시간: 264 ms
 * 코드길이: 1541 B
 * 풀이 전략: <순열: 순서o, 중복x>
 * 			N과 M(1)과 같은 문제이다. 중복이 되지 않으므로 방문여부를 따져야하며, 
 * 			순서를 신경쓰므로 매 자릿수마다 num[0]~num[n-1] 탐색이 가능하다.
 * 			
 */

public class BJ_15654 {
	
	public static int[] arr; //하나의 순열을 담을 배열
	public static int[] num; //입력된 자연수를 담을 배열
	public static boolean[] visit; //해당 자연수를 방문했는지 확인하는 배열
	public static StringBuilder sb=new StringBuilder();
	
	
	/**
	 * 하나의 순열을 만드는 재귀함수
	 * @param n 자연수의 개수
	 * @param m 총 자릿수
	 * @param depth 현재 자릿수
	 */
	public static void dfs(int n,int m,int depth) {
		
		if(depth==m) { //자릿수를 모두 채웠다면
			for(int e:arr) { //그동안 채운 숫자들 형식에 맞춰 string에 추가
				sb.append(e).append(" ");
			}
			sb.append("\n");
			return; //빠져나온다
		}
		
		for(int i=0;i<n;i++) { //각 자릿수마다 0부터 끝까지 모든 index를 본다
			if(visit[i]==false) { //아직 해당 자연수가 사용되지 않았다면
				arr[depth]=num[i]; //해당 index의 자연수를 현재 자릿수에 넣고
				visit[i]=true; //해당 index의 자연수를 사용했음을 표시
				
				dfs(n,m,depth+1); //자릿수+1 하고 재귀함수 호출
				visit[i]=false; //다시 그 전 자릿수로 돌아오면 다시 사용해야하므로 방문 해제
			}
			
		}
		
		
	
	}
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken()); //자연수 갯수 입력받기
		int m=Integer.parseInt(st.nextToken()); //자릿수 입력받기
		
		num=new int[n]; //자연수 갯수만큼 생성
		visit=new boolean[n]; //자연수 갯수만큼 생성
		arr=new int[m]; //자릿수만큼 생성
		
		//입력받은 자연수를 num 배열에 넣는다
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			num[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(num); //사전 순으로 출력해야하므로 처음부터 자연수 배열을 정렬한다
		
		dfs(n,m,0); //재귀함수 호출. 처음 자릿수는 0이다
		
		System.out.println(sb); //형식에 맞춰 출력
		

	}

}

