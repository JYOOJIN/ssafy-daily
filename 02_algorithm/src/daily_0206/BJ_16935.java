package daily_0206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_16935_배열 돌리기 3
 * 난이도: G5
 * 수행결과: 맞았습니다!!
 * 메모리: 63332 KB
 * 시간: 660 ms
 * 코드길이: 3758 B
 * 풀이 전략: 상황별로 배열을 돌렸을 때 index가 어떻게 변화하는지 따져본다
 * 
 * 주의사항: 90도로 돌릴때, nxm 배열은 mxn 배열이 된다. 즉, 행열의 크기가 바뀜에 주의해야한다
 * 			
 */

public class BJ_16935 {
	
	public static int[][] num;
	
	//상하반전
	//현재 값은 tmp에 임시저장 해두고, 현재 위치에 상하반전된 값을 넣은후 상하반전한 위치에 tmp값을 넣는다
	//상하반전 시 x값이 대칭, y값은 고정
	public static void rotate1(int x, int y) {
		for(int i=0;i<x/2;i++) {
			for(int j=0;j<y;j++) {
				int tmp=num[i][j];
				num[i][j]=num[x-1-i][j];
				num[x-1-i][j]=tmp;
			}
		}
	}
	
	//좌우반전
	//현재 값은 tmp에 임시저장 해두고, 현재 위치에 좌우반전된 값을 넣은후 좌우반전한 위치에 tmp값을 넣는다
	//좌우반전 시 x값은 고정, y값이 대칭
	public static void rotate2(int x, int y) {
		for(int i=0;i<x;i++) {
			for(int j=0;j<y/2;j++) {
				int tmp=num[i][j];
				num[i][j]=num[i][y-1-j];
				num[i][y-1-j]=tmp;
			}
		}
	}
	
	//오른쪽 90도 회전
	//왼쪽부터 아래에서 위로 세로로 읽으면 그게 오른쪽 90도 회전값
	//행열 크기 바뀜에 주의!!!!!
	public static void rotate3(int x,int y) {
		int[][] copy=new int[y][x]; //오른쪽으로 회전하면 행,열 크기가 바뀌므로 행열이 배열을 담을 배열 생성
		
		//num값을 아래부터 위로 세로로 읽어서 copy에 위부터 아래로 세로로 넣는다
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				copy[j][i]=num[x-1-i][j];
			}
		}
		
		//명령을 여러번 수행하는 경우도 존재하므로, 모두 같은 num 배열에서 해야한다
		//행열 크기가 바뀐 num 배열 생성 후 copy배열을 복사한다
		num=new int[y][x];
		for(int i=0;i<y;i++) {
			for(int j=0;j<x;j++) {
				num[i][j]=copy[i][j];
			}
		}
	}
	
	//왼쪽 90도 회전
	//위부터 오른쪽에서 왼쪽으로 가로로 읽으면 그게 왼쪽 90도 회전값
	//행열 크기 바뀜에 주의!!!!!
	public static void rotate4(int x,int y) {
		int[][] copy=new int[y][x]; //왼쪽으로 회전하면 행,열 크기가 바뀌므로 행열이 배열을 담을 배열 생성
		
		//num값을 오른쪽부터 왼쪽으로 가로로 읽어서 copy에 위부터 아래로 세로로 넣는다
		for(int i=0;i<x;i++) {
			for(int j=0;j<y;j++) {
				copy[j][i]=num[i][y-1-j];
			}
		}
		
		//명령을 여러번 수행하는 경우도 존재하므로, 모두 같은 num 배열에서 해야한다
		//행열 크기가 바뀐 num 배열 생성 후 copy배열을 복사한다
		num=new int[y][x];
		for(int i=0;i<y;i++) {
			for(int j=0;j<x;j++) {
				num[i][j]=copy[i][j];
			}
		}
	}
	
	//배열을 4등분하고 시계방향 회전
	public static void rotate5(int x,int y) {
		
		//배열을 1/4등분해서 각각 넣을 배열 생성
		int[][] a=new int[x/2][y/2];
		int[][] b=new int[x/2][y/2];
		int[][] c=new int[x/2][y/2];
		int[][] d=new int[x/2][y/2];
		
		//배열은 4등분해서 각각 부분배열에 넣는다
		for(int i=0;i<x/2;i++) {
			for(int j=0;j<y/2;j++) {
				a[i][j]=num[i][j];
				b[i][j]=num[i][j+y/2];
				c[i][j]=num[i+x/2][j+y/2];
				d[i][j]=num[i+x/2][j];
			}
		}
		
		//회전 이후 부분배열을 위치에 맞게 넣는다
		for(int i=0;i<x/2;i++) {
			for(int j=0;j<y/2;j++) {
				num[i][j]=d[i][j];
				num[i][j+y/2]=a[i][j];
				num[i+x/2][j+y/2]=b[i][j];
				num[i+x/2][j]=c[i][j];
			}
		}	
	}
	
	//배열을 4등분하고 반시계방향 회전
	public static void rotate6(int x,int y) {
		
		//배열을 1/4등분해서 각각 넣을 배열 생성
		int[][] a=new int[x/2][y/2];
		int[][] b=new int[x/2][y/2];
		int[][] c=new int[x/2][y/2];
		int[][] d=new int[x/2][y/2];
		
		//배열은 4등분해서 각각 부분배열에 넣는다
		for(int i=0;i<x/2;i++) {
			for(int j=0;j<y/2;j++) {
				a[i][j]=num[i][j];
				b[i][j]=num[i][j+y/2];
				c[i][j]=num[i+x/2][j+y/2];
				d[i][j]=num[i+x/2][j];
			}
		}
		
		//회전 이후 부분배열을 위치에 맞게 넣는다
		for(int i=0;i<x/2;i++) {
			for(int j=0;j<y/2;j++) {
				num[i][j]=b[i][j];
				num[i][j+y/2]=c[i][j];
				num[i+x/2][j+y/2]=d[i][j];
				num[i+x/2][j]=a[i][j];
			}
		}
		
		
	}
	
	

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken()); //행 크기
		int m=Integer.parseInt(st.nextToken()); //열 크기
		int r=Integer.parseInt(st.nextToken()); //명령어의 개수
		
		num=new int[n][m]; //배열 생성
		
		//배열 값 입력
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				num[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		//명령어의 개수만큼 반복
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<r;i++) {
			int k= Integer.parseInt(st.nextToken());
			
			//행열 크기가 바뀔 수 있으므로 할때마다 num의 행열 크기를 parameter로 넘겨준다
			switch(k) {
				case 1: //상하반전
					rotate1(num.length,num[0].length);
					break;
				case 2: //좌우반전
					rotate2(num.length,num[0].length);
					break;
				case 3: //오른쪽 90도 회전
					rotate3(num.length,num[0].length);
					break;
				case 4: //왼쪽 90도 회전
					rotate4(num.length,num[0].length);
					break;
				case 5: //1/4하고 시계방향 회전
					rotate5(num.length,num[0].length);
					break;
				case 6: //1/4하고 반시계방향 회전
					rotate6(num.length,num[0].length);
					break;
				default:
					break;
			}
		}
		
		//배열의 행열 크기가 변할 수 있으므로 for-each 사용해서 출력
		for(int[] a:num) {
			for(int b:a) {
				System.out.print(b+" ");
			}
			System.out.println();
		}

	}

}

