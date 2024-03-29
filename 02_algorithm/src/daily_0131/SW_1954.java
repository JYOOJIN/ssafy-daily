package daily_0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 정유진
 * 문제: SW_1954_달팽이 숫자
 * 난이도: D2
 * 수행결과: Pass
 * 메모리: 19168 KB
 * 시간: 118 ms
 * 코드길이: 1363 B
 * 풀이 전략: 방향벡터를 이용해서 설정한 순서대로 배열에 값을 저장한다
 * 			1) while 문을 이용해 푸는 방법
 * 			2) 재귀를 이용해 푸는 방법
 * 			이 경우에는 while 문이 더 이해하기 쉽고, 익숙하다. 
 */


public class SW_1954 {
	
	private static int[][] arr; //달팽이 배열 
	private static int[][] dir= {{0,1},{1,0},{0,-1},{-1,0}}; //우하좌상 방향 벡터 -> 달팽이이가 도는 방향 순서
	private static boolean[][] visit; //방문여부 저장하는 배열
	
	/**
	 * 달팽이 배열 만드는 함수
	 * @param n
	 * @param x
	 * @param y
	 */
	public static void Snail(int n,int x,int y) {
		int k=1; //초기값
		int d=0; //초기 방향값
		int dx,dy; //해당 방향으로 이동한 x좌표, y좌표
		
		//초기 설정
		arr[x][y]=k; 
		visit[x][y]=true;
		
		
		while(true) {
			//마지막 숫자까지 넣으면 break
			if(k==n*n) break;
			else {
				if(d%4==0) d=0; //방향이 "우하좌상"을 계속 반복하므로 index도 그 안에서 반복해야한다
				
				dx=x+dir[d][0]; //해당 방향으로 x좌표 이동
				dy=y+dir[d][1]; //해당 방향으로 y좌표 이동
				
				//이동한 좌표가 배열을 벗어나지 않으면서 방문한적이 없으면
				if(dx>=0 && dx<n && dy>=0 && dy<n && (visit[dx][dy]==false)) { 
					arr[dx][dy]=++k; //이동한 위치에 값 넣기
					visit[dx][dy]=true; //이동한 위치 방문 확인 
					x=dx; //현재 x위치 갱신
					y=dy; //현재 y위치 갱신
				}else {
					d++; //범위를 벗어나거나 방문한 노드라면, 방향을 바꿔야한다
				}
				
			}
		}
	}
	
	/**
	 * 달팽이 배열 만드는 재귀함수
	 * @param n
	 * @param x
	 * @param y
	 */
	public static void Snail2(int n,int x,int y,int k,int d) {
		//마지막 숫자까지 넣으면 return 
		if(k>n*n) return;
		//방향이 "우하좌상"을 계속 반복하므로 index도 그 안에서 반복해야한다
		if(d%4==0) d=0;
		
		
		int dx=x+dir[d][0]; //해당 방향으로 x좌표 이동
		int dy=y+dir[d][1]; //해당 방향으로 y좌표 이동
		
		//이동한 좌표가 배열을 벗어나지 않으면서 방문한적이 없으면
		if(dx>=0 && dx<n && dy>=0 && dy<n && (visit[dx][dy]==false)) {
			visit[dx][dy]=true; //이동한 위치 방문 확인 
			arr[dx][dy]=k; //이동한 위치에 값 넣기
			Snail2(n,dx,dy,k+1,d); //재귀함수 호출. 이동한 위치를 현재 위치로 넘겨주고, 들어갈 숫자도 +1한다. 방향은 바꾸지 x
		}else {
			Snail2(n,x,y,k,d+1); //재귀함수 호출. 방향을 바꾸어준다
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));//입력값 받을 buffer 생성
		int tc=Integer.parseInt(br.readLine()); //Stringtokenize 생성 및 testcase 입력받기
		
		//testcase만큼 반복하기
		for(int t=1;t<=tc;t++) {
			int n=Integer.parseInt(br.readLine()); //배열의 크기 입력받기
			
			//배열 생성 해줘야 함에 주의하자!!!!!!!!!!
			arr=new int[n][n]; //입력받은 크기만큼 배열 생성
			visit=new boolean[n][n]; //입력받은 크기만큼 방문 배열 생성
			
			//함수 호출
			Snail(n,0,0);
			
			//초기 설정 후 재귀함수 호출
			//arr[0][0]=1;
			//visit[0][0]=true;
			//Snail2(n,0,0,2,0);
			
			//형식에 맞춰 출력
			System.out.println("#"+t);
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					System.out.print(arr[i][j]+" ");
				}
				System.out.println();
			}
			
		}
		

	}

}
