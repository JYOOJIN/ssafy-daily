package daily_0222;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_17471_게리맨더링
 * 난이도: G4
 * 수행결과: 맞았습니다!
 * 메모리: 18228 KB
 * 시간: 136 ms
 * 코드길이: 2906 B
 * 풀이시간: 1시간 50분
 * 풀이 전략: 조합으로 선거구를 나눈 후, 각 조합마다 연결되어 있는지 확인한다.
 * 			모든 조합이 연결되어 있다면, 인구수 차를 계산해서 최소 인구수 차를 구한다.
 * 주의사항:
 * 1. 연결되어있는지 확인하는 dfs에서, 어떤 조건에서 true인지, false가 나오는지, 기저조건 주의. 
 * 빠져나오면서 boolean이 변하지 않도록 주의.
 * 
 * 2. 조합을 구했는데 사실은 같은 조합이 2번 반복된다. 이걸 해결할 방법이 있을지?
 * 3. bfs로 푸는게 더 쉬웠을것같다. 인접한지 여부를 찾는게 오래걸렸는데 bfs로 하면 q에서 출력한 결과물 수==m 이면 되는거잖아!!!
 */


public class BJ_17471_게리맨더링 {
	
	public static int[] city; //인구수
	public static boolean[] visited; //방문 확인 배열
	public static int[] arr; //조합된 구역 번호를 담는 배열
	public static List<Integer>[] list; //인접 리스트
	public static int n; //구역의 개수
	public static int min=Integer.MAX_VALUE; //인구 차이의 최솟값
	public static int find; //탐색한 구역의 수
	
	//연결되어있는지 확인하는 dfs
	public static boolean linked(int[] a,int current,int m) {

		visited[current]=true; //현재 정점 방문 체크
		for(int e:a) { //조합에 있는 정점이
			for(int s:list[current]) { 
				if(s==e && !visited[e]) { //현재 정점과 연결되어있고, 방문한적이 없다면
					find++; //구역 방문+1
					linked(a,e,m); //재귀호출
				}
			}
		}
		
		if(find==m) { //선거구에 있는 지역 수만큼 구역을 방문했다면, 모든 구역은 이어져있다
			return true;
		}
		
		return false;
	}
	
	//조합 찾는 dfs
	public static void dfs(int n,int m,int depth,int start) {
		
		if(depth==m) { //조합 하나 완성했을 때
			
			int[] arr2=new int[n-m]; //조합의 나머지 구역을 담은 배열
			
			int cnt=0;
			for(int k=1;k<=n;k++) {
				if(!Arrays.toString(arr).contains(k+"")) {
					arr2[cnt]=k;
					cnt++;
				}
			}
			
			
			visited=new boolean[n+1];
			boolean bool1,bool2 = false;
			
			find=1;
			bool1=linked(arr,arr[0],m); //조합에 포함된 정점 연결되어있는지 확인

			find=1;
			bool2=linked(arr2,arr2[0],n-m); //조합에 미포함된 정점 연결되어있는지 확인

			if(bool1 && bool2) { //연결되어 있다면
				int p1=0; //내가 속한 선거구 인원수
				int p2=0; //반대 선거구 인원수
				
				for(int x=1;x<=n;x++) {
					if(Arrays.toString(arr).contains(x+"")) {
						p1+=city[x]; 
					}else {
						p2+=city[x];
					}
				}
				
				int sub=Math.abs(p1-p2);
				if(min>sub) min=sub; //최솟값 찾기
			}
			return;
		}
		
		//조합 구하기(중복x,순서x)
		for(int i=start;i<=n;i++) {
			arr[depth]=i;
			dfs(n,m,depth+1,i+1);
		}
		
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n=Integer.parseInt(br.readLine());
		city=new int[n+1]; //1~N번 구역
		
		st=new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			city[i]=Integer.parseInt(st.nextToken()); //각 구역의 인구수
		}
		
		list=new ArrayList[n+1]; //연결리스트 생성
		
		//연결리스트 초기화
		for(int i=1;i<=n;i++) {
			list[i]=new ArrayList<>();
		}
		
		//연결리스트에 값 입력받기
		for(int i=1;i<=n;i++) {
			st=new StringTokenizer(br.readLine());
			int cnt=Integer.parseInt(st.nextToken());
			for(int j=0;j<cnt;j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		
		//n개를 2구역을 쪼개는 경우는 총 n/2개이다.
		for(int i=1;i<=n/2;i++) {
			arr=new int[i];
			dfs(n,i,0,1); //조합을 구하고, 거기에서 연결을 확인하고, 연결이 되어있다면 인구수 차를 구한다
		}
		
		if(min==Integer.MAX_VALUE) System.out.println(-1); //연결이 되어있지 않아 min 업데이트가 안되있다면 두 선거구로 나눌 수 없다
		else System.out.println(min); //두 선거구의 인구 차이의 최솟값
	}

}
