package daily_0129;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * @author 정유진
 * D3_1289_원재의 메모리 복구하기
 */

public class SW_1289 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); //입력받을  buffer 생성
		int[] memory; //원래 메모리 담을 배열 선언 -> 크기가 가변적이므로 여기서 생성은 x
		int[] init; //초기화된 메모리 담을 배열 선언 -> 크기가 가변적이므로 여기서 생성은 x
		
		int test=Integer.parseInt(br.readLine()); //testcase 입력받기 -> string이므로 int로 형변환
		
		for(int t=1;t<=test;t++) { //testcase만큼 반복
			int change=0; //고쳐야 하는 횟수
			String s=br.readLine(); //입력받은 원래 메모리
			memory=new int[s.length()]; //입력받은 메모리의 길이만큼 원래 메모리 담을 배열 생성
			init=new int[s.length()]; //입력받은 메모리의 길이만큼 초기화된 메모리 담을 배열 생성
			
			for(int i=0;i<s.length();i++) { //입력받은 메모리의 길이만큼 반복
				memory[i]=s.charAt(i)-'0'; //string을 charArray로 만들고 '0'을 빼서 int 숫자로 배열에 넣기
			}
			
			for(int i=0;i<s.length();i++) {//입력받은 원래 메모리의 길이 만큼 반복
				if(memory[i]!=init[i]) { //해당 메모리와 초기화된 메모리의 bit가 다를 때
					change+=1; //고친 횟수 +1
					Arrays.fill(init, i,init.length,memory[i]); //초기화 메모리에서 해당 위치부터 끝까지 바꾼 숫자로 채워진다. 즉, 원래 메모리 숫자로 채워진다
				}else { //해당 메모리와 초기화된 메모리의 bit가 같을 때
					continue; //아무일도 일어나지 않고 다시 조건문으로 돌아간다
				}
			}
			
			System.out.println("#"+t+" "+change); //형식에 맞춰 고친 횟수 출력
			
			
			
		}
		
		
		
		

	}

}
