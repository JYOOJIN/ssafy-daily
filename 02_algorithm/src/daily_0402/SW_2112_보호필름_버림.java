package daily_0402;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_2112_보호필름_버림 {
	
	public static int d,w,k,min,index;
	public static int[][] film,feature;
	public static int[] arr; //구한 조합 담을 배열
	public static int[] ab;
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
			index=0;
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
					ab=new int[cnt];
					index=0;
					int size=(int)Math.pow(2, cnt);
					feature=new int[size][cnt];
					int[][] copy=copy();
					feature(cnt,0);
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
			if(injection(arr)) {
				ischecked=true;
			}
			return;
		}
		
		for(int i=start;i<d;i++) {
			arr[depth]=i;
			dfs(i+1,depth+1,n); 
		}

	}
	
	//자릿수에 맞춰 ab 구하는 함수(부분집합)
	public static void feature(int n,int depth) {
		
		if(depth==n) {
			for(int i=0;i<n;i++) {
				feature[index][i]=ab[i];
			}
			index++;
			return;
		}
		
		ab[depth]=0;
		feature(n,depth+1);
		ab[depth]=1;
		feature(n,depth+1);

	}
	
	//원하는 행에 약품 주입하는 함수
	//전체 배열, 약 넣을 줄의 조합을 담은 배열
	public static boolean injection(int[] index) {
		int[][] arr=new int[d][w];
		//약품 주입
		for(int i=0;i<(int)Math.pow(2, index.length);i++) {
			arr=copy();
			for(int j=0;j<index.length;j++) {
				for(int k=0;k<w;k++) {
					arr[index[j]][k]=feature[i][j];
				}
			}
			return check(arr);
		}
		
		return false;

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
