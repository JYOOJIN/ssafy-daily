package daily_0202;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @author 정유진
 * 문제: SW_1218_괄호 짝짓기
 * 난이도: D4
 * 수행결과: pass
 * 메모리: 18416 KB
 * 시간: 102 ms
 * 코드길이: 1394 B
 * 풀이 전략: Stack을 이용. 여는 괄호는 stack에 넣고 닫는 괄호면 stack을 pop해서 짝이 맞는다면 유효, 맞지 않는다면 유효하지 않다고 판단
 * 
 */

public class SW_1218 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); //입력 담을 buffer 생성
		char[] arr; //괄호 담을 배열 선언
		Stack<Character> bracket=new Stack<>(); //괄호 담을 stack 생성
		
		for(int t=1;t<=10;t++) { //testcase 10개
			int n=Integer.parseInt(br.readLine()); //testcase의 길이
			arr=new char[n]; //괄호 담을 배열 생성
			String s=br.readLine(); 
			arr=s.toCharArray(); //배열안에 char 형태로 넣기
			int flag=0; //유효함을 판단
			
			for(int i=0;i<n;i++) { //괄호의 길이, 즉 개수만큼 반복
				
				//여는 괄호라면 stack에 넣는다
				if(arr[i]=='(' || arr[i]=='{' || arr[i]=='[' || arr[i]=='<') {
					bracket.push(arr[i]);
				}else { //닫는 괄호라면 stack을 pop하고 짝이 맞는지 비교한다
					if(arr[i]==')') {
						if(bracket.pop()=='(') continue;
						else {
							flag++;
							break;
						}
					}
					if(arr[i]=='}') {
						if(bracket.pop()=='{') continue;
						else{
							flag++;
							break;
						}
					}if(arr[i]==']') {
						if(bracket.pop()=='[') continue;
						else {
							flag++;
							break;
						}
					}
					if(arr[i]=='>') {
						if(bracket.pop()=='<') continue;
						else {
							flag++;
							break;
						}
					}
					
				}
			}
			if(flag>0) System.out.println("#"+t+" 0"); //유효하지 않다면 0출력
			else System.out.println("#"+t+" 1"); //유효하다면 1출력
			
			
		}
	}

}
