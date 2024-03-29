package daily_0213;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_6808_규영이와 인영이의 카드게임
 * 난이도: D3
 * 수행결과: Pass
 * 메모리: 20708 KB
 * 시간: 3231 ms
 * 코드길이: 1445 B
 * 풀이 전략: <순열: 순서o, 중복 x>
 * 			문제만 잘 읽으면 dfs로 쉽게 풀 수 있는 문제.
 * 			그런데 nextPermutation으로 한번 더 풀어보자.
 */



public class SW_6808 {
	
	public static int[] arr;
	public static boolean[] visited;
	public static int win;
	public static int lose;
	public static int[] c1;
	public static int[] c2;
	
	//int배열에 특정 int값이 있는지 확인하는 메서드
	public static boolean contains(int[] arr, int key) {
		for(int e:arr) {
			if(e==key) {
				return true;
			}
		}
		return false;
	}
	
	//순열을 만들고 승패를 비교하는 메서드
	public static void dfs(int n,int depth) {
		
		if(depth==n) { //모든 자릿수를 살펴봤다면
			int sum=0;
			//9라운드를 거치며 점수 계산
			for(int j=0;j<9;j++) {
				if(c1[j]>arr[j]) sum+=c1[j]+arr[j];
				else if(c1[j]<arr[j]) sum-=c1[j]+arr[j];
			}
			
			//나온 점수로 누가 이겼는지 판단
			if(sum>0) win+=1;
			else if(sum<0) lose+=1;
			return;
		}
		
		for(int i=0;i<9;i++){
			if(visited[i]) continue; //중복불가. 방문을 했으면 다음 숫자
			visited[i]=true; //방문 확인
			arr[depth]=c2[i]; //인영이 카드로 순열 만든다. 
			dfs(n,depth+1); //재귀함수 호출
			visited[i]=false; //방문 햊[
		}
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc=Integer.parseInt(br.readLine()); //테스트케이스 입력받기
		
		for(int t=1;t<=tc;t++) { //테스트케이스만큼 반복
			
			c1=new int[9]; //규영이 카드
			c2=new int[9]; //인영이 카드
			arr=new int[9]; //만들어진 인영이 카드의 순열 배열
			visited=new boolean[9]; //인영이 카드의 방문 배열
			win=0; 
			lose=0;
			
			st=new StringTokenizer(br.readLine());
			for(int i=0;i<9;i++) {
				c1[i]=Integer.parseInt(st.nextToken()); //규영이 카드 순서 입력받기
			}
			
			//인영이 카드 종류 배열에 넣기
			int r=0;
			for(int i=1;i<=18;i++) {
				if(!contains(c1,i)) {
					c2[r++]=i;
				}
			}
			
			//인영이 카드로 순열 만들기
			dfs(9,0);
			System.out.println("#"+t+" "+win+" "+lose);
			
		}

	}


}
