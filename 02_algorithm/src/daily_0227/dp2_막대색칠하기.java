package daily_0227;

import java.io.*;
import java.util.*;

public class dp2_막대색칠하기 {

	public static int[] memo; //메모이제이션 할 배열
	
	public static int fibo(int n) {
		
		if(n>2 && memo[n]==0) { //memo[1]과 memo[2]는 값 설정 완료 && 아직 값 설정하지 않았다면
			return fibo(n-2)+2*fibo(n-1); // 규칙
		}
		
		return memo[n]; //값 설정되었다면 그 값을 return
	}
	
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		memo=new int[n+1]; //1부터 n까지의 값
		
		memo[1]=2; //초기화
		memo[2]=5; //초기화
		
		System.out.println(fibo(n)); //호출
		
	
	}

}
