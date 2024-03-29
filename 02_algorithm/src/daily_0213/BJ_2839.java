package daily_0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 정유진
 * 문제: BJ_2839_설탕 배달
 * 난이도: S4
 * 수행결과: 맞았습니다!!
 * 메모리: 11532 KB
 * 시간: 80 ms
 * 코드길이: 492 B
 * 풀이 전략: <그리디 알고리즘>
 * 			어떤 상황에서 최소 봉지를 가질 수 있는지 생각해봐야한다.
 * 			1) 5로 나눠지면 그것이 가장 최소봉지
 * 			2) 5로 나눠지지 않는다면 3kg 봉지 한개 +1 하고 다시 5로 나눠본다. 최대한 많은 5를 가져가기 위해.
 * 
 */

public class BJ_2839 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine()); //설탕 무게
		
		int cnt=0; //봉지의 개수
		
		while(true) {
			if(n%5==0) { //5로 나눠떨어진다면
				cnt+=n/5;
				System.out.println(cnt); //5로 나눈 값이 최소 봉지
				break;
			}else if(n<0) { //5로 나눠 떨어지지 않아서 3kg를 들었는데 이게 0보다 작다면
				System.out.println(-1); //불가능
				break;
			}
			n-=3; //5로 나눠떨어지지 않으니 3kg 봉지 하나 들고 다시 5로 나눠보자.
			cnt++; //3kg 봉지 한개 +1
		}
		

	}

}
