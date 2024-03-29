package daily_0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: SW_1861_정사각형 방
 * 난이도: D4
 * 수행결과: Pass
 * 메모리: 31432 KB
 * 시간: 251 ms
 * 코드길이: 2039 B
 * 풀이 전략: 각 좌표에서 갈 수 있는 방의 최댓값 -> dfs/bfs 둘다 가능. 여기서는 dfs로 풀었다.
 * 			현재 좌표에서 재귀를 돌리면서 범위 안에서 갈 수 있는 방을 모두 이동한다. 즉, 상하좌우로 이동하며 재귀를 호출한다.
 * 주의사항: max를 갱신할때, 원래 그 max값을 가진 값들을 저장했던 list도 초기화를 해주어야 한다.
 * 		  max가 4일때 list={4,87,224,..} 이었다면, max가 5가 되면 list를 초기화하고 새로 값을 넣기 시작한다.
 * 		  이걸 캐치를 못해서 계속 틀렸다!!!!문제 이해를 제일 먼저 해야한다!!!!
 */

public class SW_1861 {
	
	
	public static int[][] num;
	public static int n;
	public static int max=Integer.MIN_VALUE; //최대 방문 방 개수
	public static List<Integer> list; //최대로 방을 방문한 원래 좌표의 원소 값 저장하는 리스트
	
	
	/**
	 * 상하좌우 움직이면서 최종 움직인 방 개수와 그 원소값을 저장하는 재귀함수
	 * @param i 현재 x 좌표
	 * @param j 현재 y 좌표
	 * @param cnt 이동한 방의 개수
	 * @param current 현재 좌표의 원소값
	 */
	public static void move(int i,int j,int cnt,int current) {
		int x=i; //현재 x 좌표
		int y=j; //현재 y 좌표
		
		//이동하려는 위치가 범위안에 있고, 원소의 값이 1차이라면
		//그곳으로 이동해서 재귀함수를 호출한다
		if(y+1>=0 && y+1<n && num[x][y+1]==num[x][y]+1) { //오른쪽
			move(x,y+1,cnt+1,current);
		}else if(y-1>=0 && y-1<n && num[x][y-1]==num[x][y]+1) { //왼쪽
			move(x,y-1,cnt+1,current);
		}else if(x-1>=0 && x-1<n && num[x-1][y]==num[x][y]+1) { //위쪽
			move(x-1,y,cnt+1,current);
		}else if(x+1>=0 && x+1<n && num[x+1][y]==num[x][y]+1) { //아래쪽
			move(x+1,y,cnt+1,current);
		}else {
			//상하좌우 모두 갈 수 없다면 이동을 멈춘다
			if(max==cnt) { //현재 이동한 방 개수가 max와 같다면
				list.add(current); //list에 넣어준다. 이것을 정렬했을 때 최솟값을 출력해야 하기 때문.
			}else if(max<cnt) { //현재 이동한 방 개수가 max보다 커서, 갱신이 필요하다면
				list.clear(); //원래 max를 가졌던 원소의 모음인 list를 초기화한다.
				list.add(current); //초기화한 list에 현재 좌표의 원소를 넣는다
				max=cnt; //max를 업데이트한다.
			}
		}
		return; //빠져나온다
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc=Integer.parseInt(br.readLine()); //테스트케이스 입력받기
		
		for(int t=1;t<=tc;t++) { //테스트케이스 만큼 반복
			n=Integer.parseInt(br.readLine()); //배열 크기
			num=new int[n][n]; //배열 생성
			
			//배열 값 입력받기
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					num[i][j]=Integer.parseInt(st.nextToken());
				}
			} 
			
			
			//최대로 방을 방문한 원래 좌표의 원소 값 저장하는 리스트 생성
			list=new ArrayList<>();
	
			max=0; //각 테스트케이스마다 max 초기화 필요
			
			//각 좌표를 돌면서
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					int cnt=1; //돈 방의 개수. 처음 나 자신도 포함하므로 초기값은 1이다
					move(i,j,cnt,num[i][j]); //재귀함수 호출
				}
			}
		
			
			Collections.sort(list); //list를 정렬
			System.out.println("#"+t+" "+list.get(0)+" "+max); //처음에 출발해야하는 방 번호, 이동한 최대 방 개수 출력
			
		}

	}

}
