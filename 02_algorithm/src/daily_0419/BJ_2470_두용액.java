package daily_0419;

import java.io.*;
import java.util.*;

public class BJ_2470_두용액 {

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n=Integer.parseInt(br.readLine());
		long[] map=new long[n];
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			map[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(map);
		
		int start=0;
		int end=n-1;
		long result=map[start]+map[end]; 
		long min=Math.abs(result);
		
		while(true) {
			
			if(start==end) {
				if(Math.abs(map[start]+map[end+1])>Math.abs(map[start-1]+map[end])){
					start-=1;
					break;
				}else {
					end+=1;
					break;
				}
			}
			
			if(result>0) { //0보다 작으면 end를 움직인다
				end-=1;
				result=map[start]+map[end];
				if(Math.abs(result)<min) min=Math.abs(result);
				else {
					end+=1;
					break;
				}
					
			}else if(result<0) { //0보다 크면 start를 움직인다
				start+=1;
				result=map[start]+map[end];
				if(Math.abs(result)<min) min=Math.abs(result);
				else break;
			}else { 
				start-=1;
				break;
			}
			
			
		}
		System.out.println(map[start]+" "+map[end]);
		return;
		
	}

}
