package daily_0203;

/**
 * @author 정유진
 * 문제: BJ_15654_N과 M(6)
 * 난이도: S3
 * 수행결과: 맞았습니다!
 * 메모리: 11564 KB
 * 시간: 80 ms
 * 코드길이: 1204 B
 * 풀이 전략: <조합: 순서x, 중복x>
 * 			N과 M(2)와 같은 문제이다. 순서를 신경쓰지 않으므로 순서가 달라도 같은 값이라고 생각한다.
 * 			그러므로 자신 다음 수부터 탐색하면 되고, 중복이 안되므로 자기 자신은 포함하지 않는다.
 * 			
 */

import java.io.*;
import java.util.*;

public class BJ_15655 {

	public static int[] arr; //하나의 조합을 담을 배열
	public static int[] num; //입력된 자연수를 담을 배열
	public static StringBuilder sb=new StringBuilder();
	
	public static void dfs(int n,int m,int depth,int start) {
		if(depth==m) { //자릿수를 모두 채웠다면
			for(int e:arr) { //그동안 채운 숫자들 형식에 맞춰 string에 추가
				sb.append(e).append(" ");
			}
			sb.append("\n");
			return; //빠져나온다
		}
		
		for(int i=start;i<n;i++) { //start부터 시작
			arr[depth]=num[i]; //현재 자릿수에 현재 index의 자연수 넣는다
			dfs(n,m,depth+1,i+1); //자릿수+1,중복이 되지 않으므로 내 숫자 다음부터 탐색하므로 +1
		}
	}
	
	public static void main(String[] args) throws IOException{
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
