package daily_0207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author 정유진
 * 문제: BJ_11286_절댓값 힙
 * 난이도: S1
 * 수행결과: 맞았습니다!!
 * 메모리: 24496 KB
 * 시간: 292 ms
 * 코드길이: 1990 B
 * 풀이 전략: <priority queue, comparator 이용>
 * 			pq와 comparator를 이용해서 내가 원하는 기준으로 새로 정렬할 수 있다.
 * 			comparator 공부 많이 했다고 생각했는데 실제 문제에 적용해보니 꽤 헤맸다.
 * 			어디에서 쓰이는지, 어떻게 하는지 방법을 알았으니 앞으로도 잘 적용하기
 * 			
 */

public class BJ_11286 {
  
    
    static class AbsouluteComparator implements Comparator<Integer>{

    	//절댓값이 낮아야 우선순위가 높다
    	//절댓값이 같다면 부호를 비교한다

		@Override
		public int compare(Integer o1, Integer o2) {
			if(Math.abs(o1)==Math.abs(o2)) {
				return o1-o2;
			}else {
				return Math.abs(o1)-Math.abs(o2);
			}
		}
    	
    }
    

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		PriorityQueue<Integer> pq=new PriorityQueue<>(new AbsouluteComparator()); //새로 정의한 comparator를 담은 priorityqueue 생성
		
		int n=Integer.parseInt(br.readLine());
		
		for(int i=0;i<n;i++) {
			int x=Integer.parseInt(br.readLine()); //입력받은 값

			if(x==0) { //값이 0이라면
				if(pq.isEmpty()) { //비어있다면 결과에 0 넣기
					sb.append(0).append("\n");
				}else { //절댓값 가장 작은 값 출력&제거
					sb.append(pq.poll()).append("\n"); //pq의 맨 앞에 있는 값 결과에 넣기
				}
			}else { //값이 0이 아니라면
				
				pq.offer(x); //pq에 값 넣기
			}
		}
		System.out.println(sb); //형식에 맞춰 출력
	}

}

