import java.io.*;
import java.util.*;

public class BJ_20922_겹치는건싫어 {

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		
		int[] arr=new int[n];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		int max=0; //최장 연속 부분 수열의 길이
		int[] num=new int[100001];
		int prevEnd=0;
		
		for(int i=0;i<n-1;i++) {
			
			for(int j=0;j<12;j++) {
				System.out.print(num[j]);
			}
			System.out.println();
			
			int start=i;
			int end=prevEnd;
			int len=0;
			
			if(i>0) {
				num[arr[i-1]]-=1;
			}
			num[arr[start]]+=1;
			if(num[arr[start]]>k) break;
			
			for(int j=0;j<12;j++) {
				System.out.print(num[j]);
			}
			System.out.println();
			
			
			while(end<n-1) {
				
				if(num[arr[end]]>k) {
					System.out.println(arr[start]+"에서 시작 | "+arr[end-1]+"에서 끝");
					num[arr[end]]-=1;
					prevEnd=end-1;
					if(max<end-start) max=end-start;
					
					break;
				}
				
				end++;
				num[arr[end]]+=1;
			}
			
			System.out.println(arr[start]+"에서 시작 | "+arr[end-1]+"에서 끝");
			System.out.println("len: "+(end-start));
			if(max<end-start) max=end-start;
			
			
		}
		
		System.out.println("max"+max);
		
		
		
	}
	
	

}
