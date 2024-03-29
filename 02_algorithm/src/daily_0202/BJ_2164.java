package daily_0202;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_2164_카드2
 * 난이도: S4
 * 수행결과: 맞았습니다!
 * 메모리: 31708 KB
 * 시간: 132 ms
 * 코드길이: 1082 B
 * 풀이 전략: queue를 사용해서 앞에서 빼고 뒤에서 넣는다
 * 주의사항: 처음에 NullPointer 에러가 났다. n=1일때를 고려하지 못했다.카드 수 먼저 확인 후 -> 반복을 해야한다
 */


public class BJ_2164 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 
		int n=Integer.parseInt(br.readLine()); //자연수 n
		Queue<Integer> q=new ArrayDeque<Integer>(); //카드 담을 queue 생성
		
		//1~n까지 queue에 넣기
		for(int i=1;i<=n;i++) {
			q.offer(i);
		}

		
		int result; //마지막 남은 카드의 수
		
		while(true) {
			//****사이즈 확인 전에 poll을 하면 n=1일때 오류가 발생한다****//
			if(q.size()==1) { //제거 했을 때 남은 카드가 한장이라면
				result=q.poll(); //result에 카드 넣고
				break; //반복 중단
			}
			q.poll(); //맨 위의 카드 제거
			int a=q.poll(); //맨 위의 카드 제거 및 return
			q.offer(a); //맨 위의 카드 뒤에 넣기
			
		}
		
		//형식에 맞춰 출력
		System.out.println(result);
		
		
	}

}
