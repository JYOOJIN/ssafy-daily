package daily_0202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: SW_1225_암호생성기
 * 난이도: D3
 * 수행결과: pass
 * 메모리: 20942 KB
 * 시간: 118 ms
 * 코드길이: 1078 B
 * 풀이 전략: queue를 이용해 앞에서 빼고 계산 후 뒤로 넣는것을 그 숫자가 0이하가 될때까지 반복한다
 * 
 * 주의사항: 문제 조건 잘보기!!! 한 사이클 = 5번 도는 것, 0이하가 되면 0을 넣는다
 * 
 */

public class SW_1225 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); //입력 담을 buffer생성
		StringTokenizer st;
		
		for(int t=1;t<=10;t++) { //testcase 만큼 반복
			Queue<Integer> q=new ArrayDeque<>(); //암호 담을 queue 생성 
			int n=Integer.parseInt(br.readLine()); //testcase 번호
			st=new StringTokenizer(br.readLine());
			
			//암호 값 queue에 넣기
			for(int i=0;i<8;i++) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			int k=1; //감소시킬 값
			
			while(true) {
				int f=q.poll(); //맨 앞자리를 return하고 삭제한다
				f=f-k; //빼낸 값을 k만큼 감소시킨다
				if(f<=0) { //감소시킨 값이 0이하라면
					q.offer(0); //맨 뒤에 0을 넣고
					break; //암호를 도출한다
				}
				q.offer(f); //감소시킨 값을 뒤에 넣는다
				k++; //감소시킬 값을 증가시킨다
				if(k==6) k=1; //감소시킬 값은 1~5를 반복하므로 6을 넘어가면 다시 1로 돌아간다
			}
			
			//형식에 맞춰 출력
			System.out.print("#"+t+" ");
			while(!q.isEmpty()) {
				System.out.print(q.poll()+" ");
			}
			
		}
	}
}
