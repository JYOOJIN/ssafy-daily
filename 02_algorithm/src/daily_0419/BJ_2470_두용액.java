package daily_0419;

import java.io.*;
import java.util.*;

public class BJ_2470_두용액 {

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine());
		int[] map=new int[n];
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			map[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(map);
		
		int start=0;
		int end=n-1;
		int min=Integer.MAX_VALUE;
		int[] result=new int[2];
		
		while(start<end) {
			
			int sum=map[start]+map[end];
			
			if(Math.abs(sum)<min) {
				min=Math.abs(sum);
				result[0]=map[start];
				result[1]=map[end];
				if(sum==0) break;
				
			}
			
			if(sum<0) start++;
			else end--;
		}
		
		
		
		System.out.println(result[0]+" "+result[1]);
		
	}

}