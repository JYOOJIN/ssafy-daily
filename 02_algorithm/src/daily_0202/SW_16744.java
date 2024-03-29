package daily_0202;

/**
 * @author 정유진
 * 문제: SW_16744_마이쭈시뮬레이션
 * 난이도: D8
 * 수행결과: pass
 * 메모리: 18332 KB
 * 시간: 105 ms
 * 코드길이: 935 B
 * 풀이 전략: queue 이용. 너무 복잡하게 생각하지 말고 각 번호의 사람이 마이쭈를 얼마나 가져가는지에 집중!!
 * 
 */

import java.util.*;
import java.io.*;


public class SW_16744 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
		int tc=Integer.parseInt(br.readLine()); //testcase 입력받기
		
		for(int t=1;t<=tc;t++) { //testcase만큼 반복
			Queue<Integer> q=new ArrayDeque<Integer>(); //줄을 의미하는 큐 생성
			int n=Integer.parseInt(br.readLine()); //마이쭈 개수
			int[] num=new int[100]; //사람 번호 당 받을 마이쭈 개수
			int i=1; //사람 번호
			
			//초기값 설정
			q.offer(i); //맨 처음 1번 사람이
			num[1]=1; //맨 처음 마이쭈 1개를 받았다
			
			while(true) {
				
				int tmp=q.poll(); //줄을 빠져나오고 tmp에 임시저장
				n-=num[tmp]; //tmp에 담긴 번호의 사람이 받은 마이쭈만큼 차감
				if(n<=0) { //남은 마이쭈가 0이나 그 이하라면 지금 나간 사람이 마지막 마이쭈를 받은 사람
					System.out.println("#"+t+" "+tmp); //형식에 맞춰 출력
					break; //반복문 나가기
				}
				q.offer(tmp); //현재 마이쭈 받고 나간 사람이 다시 들어온다
				num[tmp]++; //한번 더 올수록 받을 마이쭈의 개수가 1개씩 증가한다
				
				q.offer(++i); //현재 총 사람 번호 count
				num[i]++; //그 번호의 사람도 올때마다 마이쭈 1개씩 더 받는다
				
				
			}
			
		}
	}

}


