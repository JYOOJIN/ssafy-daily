package daily_0221;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: SW_3289_서로소집합
 * 난이도: D4
 * 수행결과: Pass
 * 메모리: 108968 KB
 * 시간: 414 ms
 * 코드길이: 2128 B
 * 풀이 전략: 서로소 집합 연산 - make,union,find
 * 			make: parent를 자기자신으로 초기화
 * 			find: <path compression>을 통해서 현재 노드에서 대표자를 찾으면서 만나는 모든 노드들이 루트를 가리키도록 한다.
 * 			union: 대표자가 같은, 즉 같은 집합일 경우 합치지 않고 다른 집합이면 합친다
 * 
 */

public class SW_3289_서로소집합 {
	
	public static int[] parent;
	
	//초기화 연산
	public static void makeSet(int n) {
		for(int i=1;i<=n;i++) { //1부터 n까지
			parent[i]=i; //처음에 부모는 자기자신이다
		}
	}
	
	//멤버 x가 포함된 집합의 대표자의 index를 반환
	//재귀로 대표자까지 한칸씩 타고 올라가면 무조건 시간초과가 난다.(o(n))
	//path compression -> 현재 노드의 대표자 찾습니다 -> 현재 노드의 부모의 대표자 찾습니다 -> 이렇게 거슬러 올라간다 -> 그러면 최종적으로 거쳐온 모든 노드들이 대표자를 찾게됨
 	public static int FindSet(int x) {
		if(x==parent[x]) return x;
		else {
			parent[x]=FindSet(parent[x]); //path 압축: find하면서 만나는 모든 노드들이 직접 루트(대표자)를 가리키도록 한다
			return parent[x];
		}
	}
	
 	//x,y를 포함하는 집합을 합친다
	public static void UnionSet(int x,int y) {
		int rootX=FindSet(x); //root를 찾는다
		int rootY=FindSet(y); //root를 찾는다
		
		if(rootX!=rootY) { //같은 집합일때 합치면 StackOverFlowError 난다
			parent[rootY]=rootX; //다른 집합이면 합친다
			
		}else return;

	}

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc=Integer.parseInt(br.readLine()); //테스트케이스 수
		
		for(int t=1;t<=tc;t++) {
			StringBuilder sb=new StringBuilder();
			st=new StringTokenizer(br.readLine());
			int n=Integer.parseInt(st.nextToken()); //n개의 집합
			int m=Integer.parseInt(st.nextToken()); //연산의 개수
			
			parent=new int[n+1]; //1부터 n까지인 부모 배열 생성
			makeSet(n); //초기화
			
			for(int d=0;d<m;d++) {
				st=new StringTokenizer(br.readLine());
				int op=Integer.parseInt(st.nextToken()); //연산
				int a=Integer.parseInt(st.nextToken());
				int b=Integer.parseInt(st.nextToken());

				if(op==0) { //Union
					UnionSet(a, b); //합친다
				}else if(op==1) { //Find
					if(FindSet(a)==FindSet(b)) sb.append(1); //같은 집합이면 1
					else sb.append(0); //다른 집합이면 0
				}
			}

			System.out.println("#"+t+" "+sb); //형식에 맞춰 출력
		}
		

	}
	
}
