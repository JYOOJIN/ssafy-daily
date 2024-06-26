import java.io.*;
import java.util.*;



public class BJ_2812_크게만들기 {
	
	
	//N개 중 K개의 조합을 구해서 비교하는 방식은 N과 K의 최댓값이 50000이므로 너무 크다
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		
		String[] s=new String[n];
		int[] num=new int[n];
		s=br.readLine().split("");
		
		for(int i=0;i<n;i++) {
			num[i]=Integer.parseInt(s[i]);
		}
		
		Stack<Integer> stack=new Stack<>();
		int cntPop=0; //pop한 것의 개수 세기. 이게 k와 같으면 더이상 pop 금지
		for(int i=0;i<n;i++) {
			
			if(stack.empty()) {
				stack.push(num[i]);
				continue;
			}
			
			while(!stack.empty() && stack.peek()<num[i] && cntPop!=k) {
				stack.pop();
				cntPop++;
				if(cntPop==k) break;
			}
			
			stack.push(num[i]);
			
			
		}
		
		while(cntPop!=k) {
			stack.pop();
			cntPop++;
		}
		
		
		for(int e:stack) {
			System.out.print(e);
		}
		
		
		
	}

}
