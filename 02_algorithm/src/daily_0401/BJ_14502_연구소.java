package daily_0401;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_14502_연구소
 * 난이도: G4
 * 수행결과: 맞았습니다!!
 * 메모리: 97984 KB
 * 시간: 448 ms
 * 코드길이: 2275 B
 * 풀이 전략:  1)조합을 이용해서 빈칸 중 벽을 세울 3개의 좌표의 조합을 구한다.
 * 			2)해당 좌표에 벽을 세운 후 bfs를 이용해 바이러스를 퍼트린다.
 * 			3)최종적으로 안전 영역의 크기를 구한 후 최댓값과 비교한다. 
 * 
 * 주의 사항: 새로 벽을 세우고 bfs를 돌릴 때마다 바이러스의 위치를 queue에 넣어주어야 한다.
 * 			처음에 map을 입력받을 때만 queue에 넣었더니 오류가 났다.
 * 			그리고 map,즉 원본은 유지한 채 copy본을 만들어 벽을 세워야 한다. 
 * 
 *  */

public class BJ_14502_연구소 {
	
	public static int n,m,max;
	public static int[][] map; //원본지도 입력받을 배열
	public static int[] arr=new int[3]; //조합 담을 배열
	public static List<int[]> list; //빈칸 위치 담을 리스트
	public static Queue<int[]> q; //바이러스 위치 담을 queue
	public static int[][] dir= {{-1,0},{0,1},{1,0},{0,-1}}; //방향 벡터
	
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		map=new int[n][m];
		list=new ArrayList<>();
		q=new ArrayDeque<>();
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++){
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==0) list.add(new int[] {i,j});
			}
		}
		
		combination(0, 0);
		
		System.out.println(max);
	
	}
	
	//조합 구하기(중복 x,순서 x)
	public static void combination(int depth,int start) {
		
		if(depth==3) { //3개의 조합을 구했다면 
			
			int[][] copy=new int[n][m];
			copy=copy(); //배열 깊은복사
			
			for(int i=0;i<3;i++) {
				copy[list.get(arr[i])[0]][list.get(arr[i])[1]]=1; //조합으로 구한 위치에 벽세우기
			}
			bfs(copy); //새롭게 벽을 세운 배열로 bfs 돌기

			return;
		}
		
		//조합 구하기
		for(int i=start;i<list.size();i++) {
			arr[depth]=i;
			combination(depth+1, i+1);
		}
		
	}
	
	//배열 복사하는 함수
	public static int[][] copy(){
		
		int[][] copy=new int[n][m];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				copy[i][j]=map[i][j];
			}
		}
		
		return copy;
	}
	
	//바이러스 퍼지는 bfs
	public static void bfs(int[][] copy) {
		
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(map[i][j]==2) q.add(new int[] {i,j}); //바이러스 위치 queue에 넣기. 여러개.
			}
		}
		
		while(!q.isEmpty()) {
			int[] tmp=q.poll();
			int x=tmp[0];
			int y=tmp[1];
			
			for(int d=0;d<4;d++) {
				int dx=x+dir[d][0];
				int dy=y+dir[d][1];
				
				if(dx<0 || dx>=n || dy<0 || dy>=m) continue; //배열 벗어나면 패스
				
				if(copy[dx][dy]==0) { //벽이라면
					q.add(new int[] {dx,dy}); //이동
					copy[dx][dy]=2; //번지기
				}
			}
		}
		
		int cnt=0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(copy[i][j]==0) cnt++; //빈칸 개수 구하기
			}
		}
	
		if(cnt>max) max=cnt; //최댓값과 비교
	}
	
	

}
