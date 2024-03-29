package daily_0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_16926_배열 돌리기 1
 * 난이도: S1
 * 수행결과: 맞았습니다!
 * 메모리: 44812 KB
 * 시간: 1224 ms
 * 코드길이: 2944 B
 * 풀이 전략: start하는 기준점을 (0,0) (1,1) ...등으로 잡고 풀어보려했고, 실제로 이게 맞았으나 돌리는 범위를 생각하기가 어려워서
 * 			각 좌표를 r번 돌린 최종 좌표를 반환하는 함수를 만들었다. 현재 좌표와 몇겹인지를 알려주는 depth를 받아서, 이동한 좌표를 반환한다.
 */

public class BJ_16926 {
	
	public static int n;
	public static int m;
	public static int r;
	
	/**
	 * 각 좌표의 rotate 한 값을 구하는 함수
	 * @param i 현재 x좌표
	 * @param j 현재 y좌표
	 * @param depth 몇겹인지
	 */
	public static int[] rotate(int i,int j,int depth) {
		
		int rr=r%(2*(n-1-2*depth)+2*(m-1-2*depth)); //한바퀴 기준으로 나눈다
		int x=i;
		int y=j;

		//System.out.println(rr+"번 회전");
		//System.out.println("원래 x: "+x+", 원래 y: "+y);
		while(rr>0) { //0이 되면 중단

			if(depth<=x && x<n-1-depth && y==depth) {
				x=x+1;
				rr-=1;
				//System.out.println("down "+x+" "+y);
			}else if(x==n-1-depth && depth<=y && y<m-1-depth) {
				y=y+1;
				rr-=1;
				//System.out.println("right "+x+" "+y);
			}else if(y==m-1-depth && depth<x && x<=n-1-depth) {
				x=x-1;
				rr-=1;
				//System.out.println("up "+x+" "+y);
			}else if(x==depth && depth<y && y<=m-1-depth) {
				y=y-1;
				rr-=1;
				//System.out.println("left "+x+" "+y);
			}
			
			
		}
		//반복 끝내면 현재 기준점의 좌표를 구할 수 있다
		//System.out.println("x: "+x+", y: "+y);
		int[] here= {x,y};
		return here;
		
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		r=Integer.parseInt(st.nextToken());
		
		int[][] num=new int[n][m];
		int[][] copy=new int[n][m];
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				num[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		int min;
		if(n>m) min=m;
		else min=n;
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				//가장 가까운 벽과 얼마나 떨어져있냐가 depth
				int[] distance= {i,j,n-1-i,m-1-j};
				int d=Integer.MAX_VALUE;
				for(int f:distance) {
					if(f<d) d=f;
				}
 			
				//System.out.println("현재 좌표: "+i+" "+j+", 깊이: "+d);
				
				int[] now=rotate(i,j,d);
				copy[now[0]][now[1]]=num[i][j];
				
			}
		}

		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				System.out.print(copy[i][j]+" ");
			}
			System.out.println();
		}
		
		

	}

}

