package daily_0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: SW_4012_요리사
 * 난이도: 모의 SW 역량테스트
 * 수행결과: Pass
 * 메모리: 21688 KB
 * 시간: 220 ms
 * 코드길이: 1596 B
 * 풀이 전략: n개의 재료중에서 n/2개를 고르는 조합이다. n/2개를 골라 음식 A를 하면, 나머지는 자연히 음식 B를 하므로, 음식 A의 조합만 구하면 된다. 
 */

public class SW_4012 {
	
	public static int[][] s; //시너지 담는 이중 배열
	public static boolean[] visited; //해당 재료를 골랐는지, 안골랐는지
	public static int[] arr; //하나의 조합을 담는 배열
	public static int min; //맛 차이의 최솟값
	
	//해당 조합의 재료로 만든 시너지 값을 계산후, min 값인지 계산
	public static void food(int n) {
		int total=0;
		
		//배열 전체를 돌기 때문에 재료가 3개더라도 (1,2) (1,3) (2,3) (2,1) (3,1) (3,2) 의 모든 경우가 커버된다
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				//재료는 중복해서 사용할 수 없으므로 i와 j가 같으면 안된다.
				if(i!=j) {
					if(visited[i] && visited[j]) { //두 재료를 모두 사용한다면 -> A음식
						total+=s[i][j]; //+시너지
					}else if(!visited[i]&&!visited[j]) { //두 재료를 모두 사용하지 않으면 -> B음식
						total-=s[i][j]; //-시너지
					}
				}
				
			}
		}
		
		//total은 (A음식 시너지 값-B음식 시너지값)이다.
		if(Math.abs(total)<min) min=Math.abs(total);
	}
	
	
	/**
	 * n개의 재료 중 n/2개의 재료 조합 뽑은 재귀함수
	 * @param n 총 재료의 개수
	 * @param depth 현재 고른 재료의 개수 
	 * @param start 다음 원소 시작하는 지점
	 */
	public static void cook(int n,int depth,int start) { 
		if(depth==n/2) { //총 원소 개수 n개 => 뽑는 재료 개수 n/2개
			food(n); //해당 재료 조합으로 시너지 구하기. 이 상태면 n/2개의 재료의 visit=true인 상태. 나머지는 false. 
			return; //빠져나오기
		}
		
		//조합을 구하는 방법!!!!!
		for(int i=start;i<n;i++) { 
			visited[i]=true; //해당 재료를 사용
			cook(n,depth+1,i+1); //i번째 재료를 사용하고, 다른 재료 고르러 가기
			visited[i]=false; //해당 재료 써서 고르고 return하면 이 재료 쓴 다른 조합도 구해야하므로 댜시 false
		}
		
		
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc=Integer.parseInt(br.readLine()); //테스트케이스 입력받기
		 
		for(int t=1;t<=tc;t++) { //테스트케이스 만큼 반복
			int n=Integer.parseInt(br.readLine()); //재료 개수
			min=Integer.MAX_VALUE; //min 초기화
			s=new int[n+1][n+1]; //index를 1~n까지 쓰므로 크기는 n+1
			arr=new int[n/2]; //n/2개의 재료를 고른다
			visited=new boolean[n+1]; //index를 1~n까지 쓰므로 크기는 n+1
			
			//입력받은 시너지 값 배열에 넣기
			for(int i=1;i<=n;i++) {
				
				st=new StringTokenizer(br.readLine());
				for(int j=1;j<=n;j++) {
					s[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			//재귀함수 호출. 
			//재료 개수 n개, 현재 고른 재료 수 0, index 시작은 1부터 
			cook(n,0,1);
			
			//형식에 맞춰 출력
			System.out.println("#"+t+" "+min);
			
			
			
		}
		
		
	}

}
