package daily_0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_1158_요세푸스 문제
 * 난이도: S4
 * 수행결과: 맞았습니다!
 * 메모리: 292144 KB
 * 시간: 492 ms
 * 코드길이: 1268 B
 * 풀이 전략: 대박 queue 쓰는 풀이 너무 신기하다. 원래는 linkedlist를 이용해서 index를 요리저리해서 구하려고 했는데
 * 			너무 복잡했고 실수의 위험도 컸다. queue를 이렇게 사용하는 거구나. 이렇게 간단할 수가.
 * 			k=3이라면 1,2는 pop하고 다시 넣고 3일때 pop해서 제거. 이렇게 하면 쉽게 구할 수 있다. 
 */

public class BJ_1158 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		
		int n=Integer.parseInt(st.nextToken()); //n명의 사람
		int k=Integer.parseInt(st.nextToken()); //양의 점수 k
		
		Queue<Integer> q=new ArrayDeque<Integer>(); //Queue 선언
		for(int i=0;i<n;i++) { //n까지의 자연수를 queue에 넣기
			q.offer(i+1);
		}
		
		while(true) {
			if(q.size()==1) { //마지막 하나 남았다면
				sb.append(q.poll()); //마지막으로 stringbuilder에 넣는다
				break; //반복문을 빠져나간다
			}
			
			for(int i=0;i<k-1;i++) { //k번째 사람이 될때까지 앞에서 뺐다가 뒤로 넣는것을 반복
				int tmp=q.poll(); //앞에것을 빼내서 저장
				q.offer(tmp); //앞에서 뺀것을 다시 뒤에 삽입
			}
			sb.append(q.poll()).append(", "); //k번째 사람

		}
		System.out.println("<"+sb+">"); //형식에 맞춰 출력
		
	}
}




