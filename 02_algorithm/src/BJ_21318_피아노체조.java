import java.io.*;
import java.util.*;


public class BJ_21318_피아노체조 {

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine());
		long[] arr=new long[n+1]; //1부터 n까지
		
		st=new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		long[] mistake=new long[n+1];
		for(int i=1;i<=n;i++) {
			if(arr[i-1]>arr[i]) mistake[i]=mistake[i-1]+1;
			else mistake[i]=mistake[i-1];
		}
		
		int q=Integer.parseInt(br.readLine());
		for(int ques=0;ques<q;ques++) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());;
			int end=Integer.parseInt(st.nextToken());
			long cnt=mistake[end]-mistake[start];
			
			bw.append(cnt+"\n");
			
		}
		bw.flush();
		bw.close();
		
	}

}
