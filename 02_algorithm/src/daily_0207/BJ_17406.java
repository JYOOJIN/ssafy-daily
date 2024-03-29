package daily_0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_17406_배열 돌리기 4
 * 난이도: G4
 * 수행결과: 맞았습니다!!
 * 메모리:  KB
 * 시간:  ms
 * 코드길이:  B
 * 풀이 전략: 
 * 			
 */

public class BJ_17406 {

	public static int[][] num;
	public static int n;
	public static int m;
	
	  
	//배열num의 값 구해서 return
	public static int MinArr(int x,int y) {
		int min=Integer.MAX_VALUE;
		for(int i=1;i<=x;i++) {
			int cnt=0;
			for(int j=1;j<=y;j++) {
				cnt+=num[i][j];
			}
			if(cnt<min) min=cnt;
		}
		
		return min;
	}
	
	public static int[] rotate(int i,int j,int depth,int length) {
		
		int x=i;
		int y=j;
		
		if(depth<=x && x<n-1-depth && y==depth) {
			x=x+1;
			//System.out.println("down "+x+" "+y);
		}else if(x==n-1-depth && depth<=y && y<m-1-depth) {
			y=y+1;
			//System.out.println("rigt "+x+" "+y);
		}else if(y==m-1-depth && depth<x && x<=n-1-depth) {
			x=x-1;
			//System.out.println("up "+x+" "+y);
		}else if(x==depth && depth<y && y<=m-1-depth) {
			y=y-1;
			//System.out.println("left "+x+" "+y);
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
		int k=Integer.parseInt(st.nextToken());
		
		num=new int[n+1][m+1];
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=1;j<=m;j++) {
				num[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		for(int q=0;q<k;q++) {
			st=new StringTokenizer(br.readLine());
			int r=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			int s=Integer.parseInt(st.nextToken());
			
			for(int p=0;p<(2*s+1)/2;p++) { //rotate 껍질 갯수
				int rotate=8*s;
				while(rotate>0) {
					
				}
				
				
				
			}
			
			
		}
		
		
		
	}

}
