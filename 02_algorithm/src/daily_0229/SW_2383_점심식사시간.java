package daily_0229;

import java.awt.desktop.AboutEvent;
import java.io.*;
import java.util.*;

public class SW_2383_점심식사시간 {
	
	public static int[][] map,s;
	public static boolean[] visited;
	public static ArrayList<int[]> list; //사람 좌표
	public static ArrayList<int[]> stair; //각 사람마다 계단 번호, 계단 길이
	public static int time,move,min;

	public static void main(String[] args) throws IOException{
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int tc=Integer.parseInt(br.readLine());
		for(int t=1;t<=tc;t++) {
			int n=Integer.parseInt(br.readLine()); //방 한변의 길이
			map=new int[n][n];
			
			s=new int[2][2];
			list=new ArrayList<>();
			stair=new ArrayList<>();
			time=0;
			move=0;
			min=Integer.MAX_VALUE;
			
			int sCnt=0;
			for(int i=0;i<n;i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
					if(map[i][j]==1) list.add(new int[] {i,j});
					else if(map[i][j]>1) {
						s[sCnt][0]=i;
						s[sCnt][1]=j;
						sCnt++;
					}
					
				}
			}
			visited=new boolean[list.size()];
			
			dfs(0);
			
			System.out.println("#"+t+" "+min);
			//System.out.println("#"+t+" "+time);
			
		}
	}
	
	static void dfs(int index) {
		
		if(index==list.size()) { //하나의 조합 완성
			stair.clear();
			
			for(int i=0;i<list.size();i++) {
				int dis=0; 
				if(visited[i]) {//현재 사람이 1번계단 골랐다면
					dis=Math.abs(s[0][0]-list.get(i)[0])+Math.abs(s[0][1]-list.get(i)[1]);
					stair.add(new int[] {dis+1,map[s[0][0]][s[0][1]],1}); //계단까의 거리, 계단 높이, 계단 번호
					
				}else { //현재 사람이 2번계단 골랐다면
					dis=Math.abs(s[1][0]-list.get(i)[0])+Math.abs(s[1][1]-list.get(i)[1]);
					stair.add(new int[] {dis+1,map[s[1][0]][s[1][1]],2}); //계단까의 거리, 계단 높이, 계단 번호
				}
			}
			time=0;
			printlist(stair);
			
			//bfs();
			go();
			System.out.println("시간: "+time);
			System.out.println("=========");
			if(time<min) min=time;
			
			
			return;
		}
		
		visited[index]=true;
		dfs(index+1);
		visited[index]=false;
		dfs(index+1);
	
	}
	
	
	static void go() { //계단 이동+계단 내려가기
		
		Queue<int[]> q=new ArrayDeque<>();
		Queue<Integer> s1q=new ArrayDeque<>();
		Queue<Integer> s2q=new ArrayDeque<>();
		Queue<Integer> tmp1=new ArrayDeque<>();
		Queue<Integer> tmp2=new ArrayDeque<>();
		
		for(int[] e:stair) {
			q.add(e);
		}
		
		while(!q.isEmpty() || !s1q.isEmpty() || !s2q.isEmpty()){
			
			time++;
			System.out.println(time+"분 후");
			for(int[] e:q) {
				int[] curr=q.poll();
				curr[0]--; //계단까지의 거리
				q.add(curr);
			}
			
			for(int[] e:q) {
				int[] curr=q.poll();
				if(curr[0]==0) {
					if(curr[2]==1) tmp1.add(curr[1]);
					else tmp2.add(curr[1]);
				}else q.add(curr);
			}
			
			for(int[] e:q) {
				System.out.println(Arrays.toString(e));
			}
			System.out.println("==========");
			
			while(s1q.size()!=3) {
				if(tmp1.isEmpty()) break;
				s1q.add(tmp1.poll());
			}
			
			while(s2q.size()!=3) {
				if(tmp2.isEmpty()) break;
				s2q.add(tmp2.poll());
			}
			
			for(int i=0;i<s1q.size();i++) {
				int r=s1q.poll();
				r--;
				if(r>0) s1q.add(r);
			}
			
			for(int i=0;i<s2q.size();i++) {
				int r=s2q.poll();
				r--;
				if(r>0) s2q.add(r);
			}
			
			System.out.print("1번계단: ");
			printq(s1q);
			
			System.out.print("2번계단: ");
			printq(s2q);
		}
	
	}
	

	static void bfs() {
		
		Queue<int[]> q=new ArrayDeque<>();
		Queue<Integer> s1q=new ArrayDeque<>();
		Queue<Integer> s2q=new ArrayDeque<>();
		Queue<Integer> tmp1=new ArrayDeque<>();
		Queue<Integer> tmp2=new ArrayDeque<>();
		
		for(int[] e:stair) {
			q.add(e);
		}
		
		while(!q.isEmpty() || !s1q.isEmpty() || !s2q.isEmpty()) {
			time++;
			
			//System.out.println(time+"초");
			for(int[] e:q) {
				int[] cur=q.poll();
				cur[0]--;
				if(cur[0]!=0) q.add(cur);
				else {
					if(cur[2]==1) {
						tmp1.add(cur[1]);
						//System.out.println("1번계단");
						//if(s1q.size()!=3) s1q.add(cur[1]);
						//else tmp1.add(cur[1]);
						
					}else if(cur[2]==2) {
						tmp2.add(cur[1]);
						//System.out.println("2번계단");
						//if(s2q.size()!=3) s2q.add(cur[1]);
						//else tmp.add(cur[1]);
					}
					
				}
			}
			
			while(s1q.size()!=3) {
				if(tmp1.isEmpty()) break;
				s1q.add(tmp1.poll());
			}
			
			while(s2q.size()!=3) {
				if(tmp2.isEmpty()) break;
				s2q.add(tmp2.poll());
				
			}
		
			for(int i=0;i<s1q.size();i++) {
				//System.out.println("계단1출발");
				int r=s1q.poll();
				r--;
				if(r>0) s1q.add(r);
			}
			
			for(int i=0;i<s2q.size();i++) {
				//System.out.println("계단2출발");
				int r=s2q.poll();
				r--;
				if(r>0) s2q.add(r);
			}
			
			//printq(s1q);
			//printq(s2q);

		}
	}
	
	
	
	static void printlist(List<int[]> list) {
		for(int[] e:list) {
			System.out.println(Arrays.toString(e));
		}
	}
	
	static void printq(Queue<Integer> q) {
		for(int e:q) {
			System.out.print(e+" ");
		}
		System.out.println();
	}

}
