package daily_0402;

import java.io.*;
import java.util.*;

public class SW_2112_보호필름 {
	
	public static int d,w,k,index;
	public static int[][] film;
	public static boolean isChecked;
	public static int[] arr,feat;
	public static int[][] feature;

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
		int tc=Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			d=Integer.parseInt(st.nextToken());
			w=Integer.parseInt(st.nextToken());
			k=Integer.parseInt(st.nextToken());
			isChecked=false;
			film=new int[d][w];
			
			
			for(int i=0;i<d;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<w;j++) {
					film[i][j]=Integer.parseInt(st.nextToken()); //0이면 A, 1이면 B
				}
			}
			
			if(k==1) {
				System.out.println("#"+t+" "+0);
				continue;
			}
			if(check(film)) {
				System.out.println("#"+t+" "+0);
				continue;
			}
			
			int n=1;
			while(true) {
				arr=new int[n];
				
				
				combination(0, 0, n);
				if(isChecked) break;
				n++;
			}
			System.out.println("#"+t+" "+n);
			
		}	
	}
	
	public static void combination(int depth, int start,int n) {
		if(isChecked) return;
		
		if(depth==n) {
			//하나의 arr을 구했다면->약품 투입할 줄 조합
			feature=new int[(int) Math.pow(2, n)][n];
			feat=new int[n];
			index=0;
			ab(n,0);
			if(injection(arr,n,feature)) {
				isChecked=true;
				return;
			};
			
			return;
		}
		
		for(int i=start;i<d;i++) {
			arr[depth]=i;
			combination(depth+1, start+1, n);
		}

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
	
	/**
	 * 
	 * @param n 자릿수
	 * @return
	 */
	public static void ab(int n,int depth){
		
		if(depth==n) {
			for(int i=0;i<n;i++) {
				feature[index][i]=feat[i];
			}
			index++;
			return;
		}
		
		feat[depth]=0;
		ab(n,depth+1);
		feat[depth]=1;
		ab(n,depth+1);
		
	}
	
	/**
	 * 
	 * @param arr 줄 조합 담은 배열
	 * @param n 줄 자릿수
	 * @return
	 */
	public static boolean injection(int[] arr,int n,int[][] feature) {
		
		int size=(int) Math.pow(2, n);
		
		for(int i=0;i<size;i++) {
			int[][] copy=copy();
			for(int j=0;j<n;j++) {
				for(int k=0;k<w;k++) {
					copy[arr[j]][k]=feature[i][j];
				}
			}
			if(check(copy)) return true;
			
		}
		
		return false;
	}
	

	//합격여부 함수
	public static boolean check(int[][] arr) {
		for(int i=0;i<w;i++) {
			int prev=arr[0][i];
			int cnt=1;
			for(int j=1;j<d;j++) {
				int now=arr[j][i];
				if(prev==now) {
					cnt++;
				}else {
					cnt=1;
					prev=arr[j][i];
				}
				if(cnt==k) break;
				
			}
			if(cnt!=k) return false;

		}
		
		return true;	
	}
	
	

}
