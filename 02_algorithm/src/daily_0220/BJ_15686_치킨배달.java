package daily_0220;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_15686_치킨 배달
 * 난이도: G5
 * 수행결과: 맞았습니다!!
 * 메모리: 15880 KB
 * 시간: 216 ms
 * 코드길이: 1751 B
 * 풀이 전략: <순열: 순서 o, 중복 x>
 * 			C개의 치킨집에서 M개의 치킨집을 고르고, 그 치킨집이 있을 때 도시의 치킨거리를 구한후
 * 			조합 들 중 도시 치킨거리가 가장 작은 것을 출력한다
 *  
 *  */

public class BJ_15686_치킨배달 {
	
	public static List<Pair> home=new ArrayList<>(); //집 좌표 담을 리스트
	public static List<Pair> chicken=new ArrayList<>(); //치킨집 좌표 담을 리스트
	public static int[] arr; //조합 담을 배열
	public static int cityMin; //도시 최소 치킨거리
	
	//x,y좌표를 담을 클래스
	static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	
	/**
	 * 
	 * @param n 치킨집 총 개수
	 * @param m 남길 치킨집 개수
	 * @param depth 조합의 자릿수
	 * @param start 시작점
	 */
	public static void dfs(int n,int m,int depth,int start) {
		if(depth==m) { //m개의 치킨집을 모두 골랐을 때
			int citydis=0;
			for(Pair h:home) { //home마다 조합의 치킨집 사이의 거리를 구한후, 모두 더해 도시 치킨 거리를 구한다
				int min=Integer.MAX_VALUE;
				int dis=0;
				for(int e:arr) {
					dis=Math.abs(h.x-chicken.get(e).x) + Math.abs(h.y-chicken.get(e).y);
					if(min>dis) min=dis;
 				}
				citydis+=min;
			}
			
			
			if(cityMin>citydis) cityMin=citydis; //도시 치킨거리의 최소 값을 구한다
			return; //하나의 조합으로 도시 치킨거리를 구하면 빠져나온다
		}
		
		//조합구하기
		for(int i=start;i<n;i++) {
			arr[depth]=i; //현재 depth에 값 넣기
			dfs(n,m,depth+1,i+1); //dfs 호출. 자릿수와 시작지점에 각각 +1
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken()); //도시의 크기
		int m=Integer.parseInt(st.nextToken()); //존재해야 하는 치킨 집 수
	
		int[][] map=new int[n+1][n+1]; //도시의 배열
		arr=new int[m]; //치킨집 조합답을 배열 생성
		
		
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					home.add(new Pair(i,j)); //집 좌표 넣기
				}else if(map[i][j]==2) {
					chicken.add(new Pair(i,j)); //치킨집 좌표 넣기
				}
			}
		}
		
		cityMin=Integer.MAX_VALUE; //초기화
		
		dfs(chicken.size(),m,0,0); //도시 최소 치킨거리 구할 dfs 호출. 총 치킨집개수 C에서 m개의 치킨집을 고르는 조합.
		
		System.out.println(cityMin); //출력
		
		
		
		
		
	}

}

