package daily_0223;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_15961_회전 초밥
 * 난이도: G4
 * 수행결과: 맞았습니다!
 * 메모리: 213752 KB
 * 시간: 616 ms
 * 코드길이: 1683 B
 * 풀이 전략: Sliding window를 사용하며 각 window에 대하여 set으로 중복을 제거하여 최대 초밥 가짓수를 구하려 하였다.
 * 			그런데 set을 만드는 과정에서 익숙치 않아서 뭔가 실수가 난것같다. 아니면 시간이 너무 초과되던가.
 * 			암튼 n과 k의 최댓값이 매우 크기 때문에 무조건 o(N)으로 해결을 해야한다. 이중 for문은 절대 쓰지 못한다.
 * 			중복 초밥을 나는 set으로 없애려 했는데, visited를 이용하는 것이 더 좋은 방법인 것같다.
 * 			1) 쿠폰 초밥은 무조건 포함. 초반에 visited 처리 해준다.
 * 			2) window를 만들어 방문한적이 없으면 count+1, 방문한적이 있으면 window에 추가+visit 값 증가
 * 			3) window를 이동시킬 때, 앞에 제거한 초밥의 visted를 -1 하는데, 
 * 			이때 visited가 0이 되면, 즉 방문한적이 없는 상태가 되면 현재 있는 window에 그 값이 없는 것이므로
 * 			count-1을 해주어야 한다. 그 전에서 visited가 1 이상이면 중복되어 들어간 것이기에, 현재 window에 존재하므로 
 * 			count를 건드릴 필요가 없다. 뒤에 추가되는 값에 대해서는 2)와 같은 방식을 거친다.
 * 			
 * =====> sliding window는 앞뒤로 값을 빼고 넣기에 Queue를 사용한다
 * 
 * 주의사항: 항상 문제에서 주어지는 변수의 범위에 주의. 중복 제거는 Set도 가능하지만, visit를 이용하는 방법도 알아두자.
 */



public class BJ_15961_회전초밥 {

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		int n=Integer.parseInt(st.nextToken()); //접시의 수
		int d=Integer.parseInt(st.nextToken()); //초밥의 가짓수
		int k=Integer.parseInt(st.nextToken()); //연속해서 먹는 접시의 수
		int c=Integer.parseInt(st.nextToken()); //쿠폰 번호
		
		int[] sushi=new int[n]; //회전벨트 위의 초밥
		Queue<Integer> window=new ArrayDeque<>(); //sliding window
		
		//회전벨트 위의 초밥 입력받기
		for(int i=0;i<n;i++) {
			sushi[i]=Integer.parseInt(br.readLine());
		}
		
		int[] visited=new int[d+1]; //먹은 적 있는 초밥 처리. 1부터 d까지.
		
		int max=0; //최대 초밥 가짓수
		int cnt=1; //쿠폰은 처음에 무조건 적용 
		visited[c]++; //쿠폰은 무조건 방문 되어있음
		
		//초기 window 설정
		for(int i=0;i<k;i++) {
			if(visited[sushi[i]]==0) { //방문한적이 없다면
				cnt++; //가짓수+1
			}
			window.add(sushi[i]); //window에 넣기
			visited[sushi[i]]++; //방문처리 해주기
		}

		
		max=Math.max(max, cnt); //최댓값 구하기
		
		for(int i=1;i<n;i++) {
			int s=window.poll(); //맨 앞 초밥 없애기
			visited[s]-=1; //없앤 초밥의 먹은 횟수 -1
			if(visited[s]==0) cnt-=1; //현재 window에 초밥 포함이 안되어 있으니 -1
			window.add(sushi[(i+k-1)%n]); //"회전"초밥이므로 %n 필요
			if(visited[sushi[(i+k-1)%n]]==0) {
				cnt+=1;
			}
			visited[sushi[(i+k-1)%n]]+=1;
			
			max=Math.max(max, cnt); //최대 초밥 가짓수
		}
		
		bw.write(max+""); //출력
		bw.flush();
		bw.close();


	}

}
