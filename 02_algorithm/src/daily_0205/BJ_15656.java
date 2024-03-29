package daily_0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_15656_N과 M(7)
 * 난이도: S3
 * 수행결과: 맞았습니다!
 * 메모리: 220336 KB
 * 시간: 616 ms
 * 코드길이: 1373 B
 * 풀이 전략: <중복순열: 순서o, 중복o>
 * 			기존 순열에서 visit 따질 필요없어졌다.
 * 주의사항: 반드시 재귀함수에서는 return 필요!!!!
 */

public class BJ_15656 {
	
	private static int arr[];
	private static int num[];
	private static StringBuilder sb=new StringBuilder();
	
	public static void dfs(int n,int m,int depth) {
		if(depth==m) {
			for(int e:arr) {
				sb.append(e).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i=0;i<n;i++) {
			arr[depth]=num[i];
			dfs(n,m,depth+1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		
		num=new int[n];
		arr=new int[m];
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			num[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(num); //사전식 오름차순 이어야하기 때문에 미리 정렬
		
		dfs(n,m,0);
		System.out.println(sb);

	}

}
