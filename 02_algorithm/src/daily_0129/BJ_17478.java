package daily_0129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author 정유진
 * BJ_17478_재귀함수가 뭔가요?
 */

public class BJ_17478 {
	
	public static void underline(int n) { //underline을 찍는 함수
		for(int i=0;i<n;i++) { //받은 n만큼 반복한다
			System.out.print("____"); //underline출력
		}
	}
	
	
	public static void chat(int n,int i) { //재귀함수
		
		//재귀함수의 시작.
		underline(i); 
		System.out.println("\"재귀함수가 뭔가요?\""); 
		
		if(n==0) { //종료조건인 n=0을 달성했다면
			//각 underline을 달고 return 해서 재귀를 끝내고, 빠져나간다
			underline(i);
			System.out.println("\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			underline(i);
			System.out.println("라고 답변하였지.");
			
			return;
		}else { //아직 종료조건을 달성하지 못했다면
			
			//각 underline을 달고 형식에 맞게 출력한다
			underline(i);
			System.out.println("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
			underline(i);
			System.out.println("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
			underline(i);
			System.out.println("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
			
			//재귀함수 호출. n은 -1을 해서 반복 횟수를 줄이고, i는 +1을해서 반복횟수를 늘린다
			//재귀함수를 호출함으로써 여기서 잠깐 멈춰있다. 재귀를 끝내야 다음 step으로 넘어갈 수 있음
			chat(n-1,i+1);
		}
		
		//재귀함수를 빠져나올때 출려가는 부분
		underline(i);
		System.out.println("라고 답변하였지.");
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); //값을 입력받을 buffer생성
		int n=Integer.parseInt(br.readLine()); //재귀횟수 입력받음
		int i=0; //underline 반복 횟수 선언 및 초기화
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다."); //반복하지 않는 첫줄
		chat(n,i); //재귀함수 첫 호출

	}

}
