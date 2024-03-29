package daily_0208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: SW_5215_햄버거 다이어트
 * 난이도: D3
 * 수행결과: Pass
 * 메모리: 20744 KB
 * 시간: 860 ms
 * 코드길이: 1682 B
 * 풀이 전략: <SubSet : 순서 x, 중복 x> 
 * 			칼로리를 넘지 않으면서 최대의 맛을 내는 부분조합을 찾는것.
 * 			재료를 몇개 써야하는지 제한이 없기 때문에 모든 부분조합을 찾으면서 각각 칼로리와 맛을 계산한다.
 */

public class SW_5215 {

	public static int cal;
	public static int taste;
	public static int max;
	public static int n;
	public static int l;
	public static int[][] food;
	public static boolean[] visited;
	
	/**
	 * subset을 구해서 해당 subset의 칼로리, 맛 점수를 계산하는 재귀함수
	 * @param num 원소 개수
	 * @param food 재료 정보 담은 배열
	 */
	public static void makeSubSet(int num,int[][] food) {
		if(num==n) { //원소 끝까지 다 확인했다면
			for(int i=0;i<n;i++) {
				if(visited[i]==true) {//해당 재료를 사용했다면
					taste+=food[i][0]; //맛 점수 더하기
					cal+=food[i][1]; //칼로리 더하기
				}
			}
			
			if(cal<=l) { //음식의 칼로리가 지정한 칼로리보다 낮다면
				if(max<taste) max=taste; //음식의 맛 점수가 max보다 높다면 그 점수가 max가 된다
			}
			cal=0; //초기화
			taste=0; //초기화
			return;
		}
		
		visited[num]=true; //해당 재료를 사용한다
		makeSubSet(num+1, food); //확인한 원소 수+1
		
		visited[num]=false; //해당 재료를 사용하지 않는다
		makeSubSet(num+1, food); //확인한 원소 수+1

	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc=Integer.parseInt(br.readLine()); //테스트케이스 
		
		for(int t=1;t<=tc;t++) { //테스트케이스 만큼 반복
			st=new StringTokenizer(br.readLine());
			n=Integer.parseInt(st.nextToken()); //재료 개수
			l=Integer.parseInt(st.nextToken()); //최대 칼로리
			  
			food=new int[n][2]; //n개의 재료에 대한 맛점수, 칼로리 항목이 있다
			visited=new boolean[n]; //사용여부를 확인한다
			
			//초기화
			max=0;
			cal=0;
			taste=0;
			
			//각 재료의 맛점수, 칼로리 입력받기
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				food[i][0]=Integer.parseInt(st.nextToken()); //맛 점수
				food[i][1]=Integer.parseInt(st.nextToken()); //칼로리
			}
			
			//재귀함수 호출
			makeSubSet(0,food); //처음에는 0개의 원소를 봤다
			System.out.println("#"+t+" "+max); //형식에 맞춰 출력
			
		}

	}

}
