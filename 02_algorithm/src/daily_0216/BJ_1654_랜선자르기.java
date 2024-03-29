package daily_0216;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_1654_랜선 자르기
 * 난이도: S2
 * 수행결과: 맞았습니다!!
 * 메모리:  KB
 * 시간:  ms
 * 코드길이:  B
 * 풀이 전략: 
 * 
 */

public class BJ_1654_랜선자르기 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int k=Integer.parseInt(st.nextToken());
		int n=Integer.parseInt(st.nextToken());
		int[] len=new int[k];
		
		for(int i=0;i<k;i++) {
			len[i]=Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(len);
		
		long cnt=0;
		long start=1;
		long end=len[0];
		long mid=(start+end)/2;
		long max=Integer.MIN_VALUE;
		
		while(true) {
			cnt=0;
			for(int e:len) {
				cnt+=e/mid;
			}
			
			if(cnt>n) {
				start=mid;
			}else if(cnt<n) {
				end=mid;
			}else {
				if(max<mid) {
					max=mid;
					start++;
				}else {
					break;
				}
			}
			mid=(start+end)/2;
		}
		
		System.out.println(max);
		

	}

}
