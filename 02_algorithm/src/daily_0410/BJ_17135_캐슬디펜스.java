package daily_0410;

import java.io.*;
import java.util.*;

/**
 * @author 정유진
 * 문제: BJ_17135_캐슬디펜스
 * 난이도: G3
 * 수행결과: 맞았습니다!!
 * 메모리: 41536 KB
 * 시간: 880 ms
 * 코드길이: 1024 B
 * 풀이 전략: pq를 이용해서 dis로 정렬해서 제거할 적을 구한다
 * 
 * 주의사항: 어디서 list와 pq를 초기화해주어야 할지에 주의!!!!! 그리고 while(!pq.isempty()) 조건으로 돌면서 그 안에서 pq.add 하면 무한반복
 *   
 *  */

public class BJ_17135_캐슬디펜스 {
	
	static class Point implements Comparable<Point>{
		int x,y,dis;
		boolean isLive;
		
		public Point(int x, int y, int dis, boolean isLive) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.isLive = isLive;
		}

		@Override
		public int compareTo(Point o) {
			if(this.dis==o.dis) {
				return this.y-o.y; //거리 같으면 가장 왼쪽에 있는 적을 공격
				
			}else {
				return this.dis-o.dis; //거리 오름차순
			}
		}
		
		
	}
	
	static int n,m,d;
	static int total, max; //각 궁수 조합마다의 제거한 적의 값과 그중 최댓값
	static int[] arr; //궁수 조합 index 들어갈 배열
	static int[][] map;
	static PriorityQueue<Point> pq;

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		
		map=new int[n+1][m];
		arr=new int[3];
		pq=new PriorityQueue<>();
		max=0;
		
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		
		combination(0, 0);
	
		System.out.println(max);
		
	}
	
	public static void attack() {
		
		//모두다 죽거나 성에 도착했다면 끝
		while(!pq.isEmpty()) {
			List<Point> list=new ArrayList<>();
			Point tmp=null;
			for(int i=0;i<3;i++) {//궁수 3명이 거리 가장 가까운 애 찾는다
				list.clear();
				int startx=n;
				int starty=arr[i];
				
				while(!pq.isEmpty()) {
					tmp=pq.poll();
					int dis=Math.abs(startx-tmp.x)+Math.abs(starty-tmp.y);
					if(dis<=d) tmp.dis=dis;
					else tmp.dis=Integer.MAX_VALUE;
					
					list.add(tmp);
				}
				for(int j=0;j<list.size();j++) {
					pq.add(list.get(j));
				}
				//현재 pq 가장 앞에는 제거할 궁수가 들어있다
				tmp=pq.poll();
				if(tmp.dis==Integer.MAX_VALUE) {
					pq.add(tmp);
					continue;
				}
				
				
				tmp.isLive=false; //제거하려고 찜콩
				pq.add(tmp);				
			}
			
			
			
			list=new ArrayList<>(); //살아남은 적들이 들어감
			while(!pq.isEmpty()) {
				tmp=pq.poll();
				if(!tmp.isLive) {
					total++;
					continue;
				}
				tmp.x+=1;
				if(tmp.x==n) continue;
				list.add(tmp);
				
			}
			
			
			
			for(int i=0;i<list.size();i++) {
				pq.add(list.get(i));
			}
			
		}
		
		return;
		
	}
	
	public static void combination(int depth,int start) {
		
		if(depth==3) {
			total=0;
		
			//arr에 궁수의 위치 들어있음
			//이 궁수 위치로 게임이 끝날때까지 반복
			
			//각 attack 시작마다 적 좌표 넘겨주어야 한다
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					if(map[i][j]==1) pq.add(new Point(i,j,0,true));
				}
			}
			
			attack();
			if(total>max) max=total;
			
			return;
		}
		
		for(int i=start;i<m;i++) {
			arr[depth]=i;
			combination(depth+1, i+1);
		}
		
		
	}

}
