package daily_0423;

import java.io.*;
import java.util.*;

public class BJ_1644_소수의연속합 {

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		if(n==1) {
			System.out.println(0);
			return;
		}
		
		
		//에라토스테네스의 체로 n까지의 소수 구하기
		int[] num=new int[n+1];
		
		for(int i=1;i<=n;i++) {
			num[i]=i;
		}
		
		for(int i=2;i<=n;i++) {
			if(num[i]==0) continue;
			
			for(int j=2*i;j<=n;j+=i) {
				num[j]=0;
			}
		}
		
		List<Integer> list=new ArrayList<>(); //n까지의 소수 넣을 list
		for(int i=2;i<=n;i++) {
			if(num[i]==0) continue;
			list.add(i);
		}
	
		
		
		int cnt=0; //소수의 합
		for(int i=0;i<list.size()-1;i++) {
			int start=i; //시작값은 변하지 않는다
			int end=i+1; //end가 한칸씩 뒤로 이동한다.
			int sum=list.get(start);
			
			while(end<n) {
				
				if(sum==n) {
					cnt++;
					break;
				}
				if(sum>n) {
					break;
				}
				
				sum+=list.get(end++);
				
			}
			
		}
		
		
		if(list.get(list.size()-1)==n) cnt++;
		
		System.out.println(cnt);
		
		
		
	}

}