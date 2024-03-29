package daily_0130;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Integer.*; //Integer에 있는 모든 static 멤버 -> parseInt사용시 생략 가능
import static java.lang.Math.random; //Math random()
//import static java.lang.System.out;

/**
 * @author 정유진
 * 문제: BJ_15649_N과 M(1)
 * 수행결과: 맞았습니다!
 * 메모리: 20792 KB
 * 시간: 160 ms
 * 코드길이: 2150 B
 * 풀이 전략: <순열: 순서o, 중복X>
 * 			재귀를 이해해야한다. 재귀를 돌때마다, 즉 각 자릿수에 갈때마다 for문으로 모든 숫자를 확인한다. 
 * 			이때 방문 여부 확인을 위해 visit 배열 사용. 방문했으면 넘기고 방문 안했으면 그쪽으로 간다.
 * 			깊이가 m과 같아지면 길이를 넘은 것이기에 return, 빠져나오고 for문 다시 돌면서 가능한거 찾는다.
 */



public class BJ_15649 {
	
	private static int[] arr; 
	private static boolean[] visit; 
	private static StringBuilder result=new StringBuilder();
	
	
	public static void dfs(int n, int m, int depth) { //depth가 자릿수
		if(depth==m) { //자릿수가 끝까지 갔다면
			for(int val:arr) { //for-each
				result.append(val).append(' '); //출력할 result에 넣기
			}
			result.append("\n");
			return; //탈출
		}
		
		
		//여기서 i는 n이하의 숫자들. 0부터 n-1까지니까 +1해서 저장해두는거임
		for(int i=0;i<n;i++) { //각 자리마다 0부터 n-1까지
			if(!visit[i]) { //내가 지금 i 방문했는데 방문했다고 표시가 안되어있으면
				visit[i]=true; //i 방문했다고 표시. 그럼 이 숫자는 다음에 못쓰는거임. 그치 중복이 안되니까. 앞에서 쓴 숫자는 뒤에서 쓸수없으니까.
				arr[depth]=i+1; //해당 자릿수에 그 숫자 넣기
				//System.out.println("arr["+depth+"]: "+(i+1));
				dfs(n,m,depth+1); //그 다음 자릿수로 ㄱㄱ
				//System.out.println("숫자"+(i+1)+"해방!!");
				visit[i]=false; // 끝까지 갔으면 그 해당 숫자는 다시 해방.다음에 다시 써야함.
			}						
		}		
		
		
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); //입력값 받을 buffer 생성
		StringTokenizer st=new StringTokenizer(br.readLine()); //tokenize 선언
		
		//out.println("import static을 이용하면 클래스 이름 생략 가능");
		int n= parseInt(st.nextToken()); // 1부터 N까지 자연수
		int m= parseInt(st.nextToken()); // 중복 없이 M개
		
		visit=new boolean[n]; //자연수 수만큼 배열 생성
		arr=new int[m]; //자릿수만큼 생성
		
		dfs(n,m,0); //재귀 함수 호출
		System.out.println(result); //형식에 맞춰 출력

	}

}
