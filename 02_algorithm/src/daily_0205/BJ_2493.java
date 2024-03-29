package daily_0205;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_2493_탑
 * 난이도: G5
 * 수행결과: 맞았습니다!
 * 메모리: 105940 KB
 * 시간: 724 ms
 * 코드길이: 963 B
 * 풀이 전략:  스택이라는 자료구조를 어떻게 이용할 수 있는지의 대표적인 예시.
 * 			처음에는 for문으로 했다가 시간초과가 나서 재귀로 시도했지만 또 시간초과가 났다.
 * 			현재 타워 높이와 그 앞의 것을 모두 비교하는 것은 비효율적이다.
 * 			Stack을 이용해서, 조건에 맞는 타워의 index를 효율적으로 구할 수 있다.
 * 			Stack은 타워의 인덱스를 담았으며,
 * 			1) 스택이 비어있을 경우 -> 수신부가 존재 x
 * 			2) 스택에 값이 있을 경우 -> 내 높이보다 stack top index의 타워 높이가 높다면 그 index를 저장하고 현재 내높이 스택에 넣음
 * 			3) 					-> 내 높이보다 작으면 높은 거 나올때까지 pop 반복
 * 			
 */
public class BJ_2493 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb=new StringBuilder();
		int n=Integer.parseInt(br.readLine());
		
		Stack<Integer> s=new Stack<>(); //각 tower의 index 담은 stack
		int[] h=new int[n]; //각 tower의 높이가 담긴 배열
		
		//tower 높이 입력받기
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			h[i]=Integer.parseInt(st.nextToken());
		}
		
		//tower 개수만큼 반복
		for(int i=0;i<n;i++) {
			while(true) {
				if(s.empty()) { //stack이 비었다면, 수신지가 존재하지 x
					sb.append(0).append(" "); //결과에 0을 넣는다
					s.add(i); //나도 누군가의 수신부가 될 수 있기 때문에 stack에 넣는다
					break; //반복을 빠져나간다
				}
				
				//stack이 비지 않으면서
				if(h[i]<h[s.peek()]) { //현재 stack의 top에 있는 index의 tower 높이가 현재 내 높이보다 높다면-> 수신부 
					sb.append(s.peek()+1).append(" "); //현재 stack의 top에 있는 index를 결과에 넣는다 
					s.add(i); //나도 누군가의 수신부가 될수 있다. 내 수신부도 다시 다른 사람의 수신부가 될수있으므로 pop하지 않는다
					break;
				}else {
					s.pop(); //나보다 높이 작다면 높이 높은것 나오거나 스택이 빌때까지 pop
				}
			}
		}
		
		System.out.println(sb); //형식에 맞춰 출력

	}

}

