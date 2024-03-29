package daily_0227;

import java.io.*;
import java.util.*;

public class dp1_아파트색칠하기 {
	
	public static int[] memo; //메모이제이션할 배열
	
	public static int fibo(int n) {
		
		if(n>2 && memo[n]==0) { //memo[1]과 memo[2]는 값이 들어가 있다 && 아직 값이 안들어가있다면
			return memo[n]=fibo(n-1)+fibo(n-2); //규칙
		}
		
		return memo[n]; //값이 들어가있다면
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		
		memo=new int[n+1]; //1부터 n까지의 값
		memo[1]=2; //초기화
		memo[2]=3; //초기화
		
		System.out.println(fibo(n)); //호출
		
		
		
	}
}
