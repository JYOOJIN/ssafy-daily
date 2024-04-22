import java.io.*;
import java.util.*;

public class BJ_2470 {
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		
		int[] arr=new int[n];
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		int start=0;
		int end=n-1;
		int min=Integer.MAX_VALUE;
		
		Arrays.sort(arr);
		
		while(start<end) {
			int result=arr[start]+arr[end];
			
			if(min>Math.abs(result)) {
				min=Math.abs(result);
				
				if(result==0) break; 
			}
			
			if(result<0) start++;
			else end--;
		}
		
		System.out.println(arr[start]+" "+arr[end]);
 	}
}
