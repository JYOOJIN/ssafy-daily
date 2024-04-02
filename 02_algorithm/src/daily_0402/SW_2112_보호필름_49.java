package daily_0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_2112_보호필름_49 {
	
	public static int d,w,k,min;
	public static int[][] film;
	public static int[] arr; //구한 조합 담을 배열
	public static boolean ischecked;

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			
			st=new StringTokenizer(br.readLine());
			d=Integer.parseInt(st.nextToken()); //높이
			w=Integer.parseInt(st.nextToken()); //가로
			k=Integer.parseInt(st.nextToken()); //연속 k개
			min=Integer.MAX_VALUE;
			ischecked=false;
			film=new int[d][w]; //원본 유지해야한다. copy 만들어서 검사.
			for(int i=0;i<d;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<w;j++) {
					film[i][j]=Integer.parseInt(st.nextToken()); //0이면 A, 1이면 B
				}
			}
					
			if(check(film)) {
				System.out.println("#"+t+" "+0);
			}else {
				int cnt=1;
				//완전탐색
				while(true) {
					arr=new int[cnt];
					int[][] copy=copy();
					dfs(0,0,cnt);
					if(ischecked) {
						System.out.println("#"+t+" "+cnt);
						break;
					}
					cnt++;
				}
			}
			
			
		}
		
	}
	
	public static void print(int[][] arr) {
		for(int i=0;i<d;i++) {
			for(int j=0;j<w;j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println();
		}
		System.out.println("============");
	}
	
	public static int[][] copy() {
		int[][] copy=new int[d][w];
		for(int i=0;i<d;i++) {
			for(int j=0;j<w;j++) {
				copy[i][j]=film[i][j];
			}
		}
		return copy;
	}
	
	public static void dfs(int start,int depth, int n) {
		if(ischecked) return;
		int[][] copy;
		
		if(depth==n) {
			copy=copy();
			if(injection(copy, arr,0)) {
				ischecked=true;
				return;
			}
			copy=copy();
			if(injection(copy, arr,1)) {
				ischecked=true;
				return;
			}
			
			return;
		}
		
		for(int i=start;i<d;i++) {
			arr[depth]=i;
			dfs(i+1,depth+1,n); 
		}

	}
	
	//원하는 행에 약품 주입하는 함수
	public static boolean injection(int[][] arr, int[] index,int feature) {
		
		//약품 주입
		for(int i=0;i<index.length;i++) {
			for(int j=0;j<w;j++) {
				arr[index[i]][j]=feature;
			}
		}
		
		return check(arr);
	
	}
	
	//합격여부 함수
	public static boolean check(int[][] arr) {
		
		for(int i=0;i<w;i++) {
			int prev=arr[0][i];
			int cnt=1;
			for(int j=1;j<d;j++) {
				if(cnt==k) break;
				int now=arr[j][i];
				if(prev==now) {
					cnt++;
				}else {
					cnt=1;
					prev=arr[j][i];
				}
				
			}
			if(cnt!=k) return false;

		}
		
		return true;	
	}

}
