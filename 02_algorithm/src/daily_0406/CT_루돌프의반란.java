package daily_0406;

import java.io.*;
import java.util.*;

public class CT_루돌프의반란 {
	
	static int n,m,p,c,d;
	static int[] r,score; //루돌프의 좌표, 산타의 점수
	static int[][] s; //산타의 좌표
	static int[][] map;
	static int[][] dir4= {{-1,0},{0,1},{1,0},{0,-1}}; //산타 방향 벡터
	static int[][] dir8= {{-1,0},{0,1},{1,0},{0,-1},{1,1},{-1,1},{1,-1},{-1,-1}}; //루돌프 방향 벡터
	

	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken()); //게임판 크기
		m=Integer.parseInt(st.nextToken()); //게임 턴 수
		p=Integer.parseInt(st.nextToken()); //산타의 수
		c=Integer.parseInt(st.nextToken()); //루돌프의 힘
		d=Integer.parseInt(st.nextToken()); //산타의 힘
		
		r=new int[2];
		score=new int[p];
		s=new int[n][3]; //0부터 p-1 번째까지
		map=new int[n+1][n+1]; //좌표 1부터 n까지
		
		st=new StringTokenizer(br.readLine());
		
		r[0]=Integer.parseInt(st.nextToken()); //루돌프의 x 좌표
		r[1]=Integer.parseInt(st.nextToken()); //루돌프의 y 좌표
		
		for(int i=0;i<p;i++) {
			st=new StringTokenizer(br.readLine());
			int num=Integer.parseInt(st.nextToken());
			s[num-1][0]=1; //산타 i의 탈락여부 -> 0이 되면 탈락
			s[num-1][1]=Integer.parseInt(st.nextToken()); //산타 i의 x 좌표
			s[num-1][2]=Integer.parseInt(st.nextToken()); //산타 i의 y 좌표
		}
		
		//---입력받기 완료
		
		for(int t=0;t<m;t++) { //게임턴수만큼 반복
			
			//산타가 모두 탈락했다면(0이라면) return
			int end=0;
			for(int i=0;i<p;i++) {
				end+=s[i][0];
			}
			if(end==0) return;
			
			//루돌프 이동
			
			//산타 이동
			
		}
		
		
	}
	
	
	public static void moveRudolph() {
		int minDis=Integer.MAX_VALUE;
		int minNum=0;
		for(int i=0;i<p;i++) {
			if(s[i][0]==0) continue; //탈락한 산타는 제외하기
			int dis=getDis(r[0],r[1] , s[i][1], s[i][2]);
			if(dis<minDis) {
				minDis=dis; 
				minNum=i; //가장 가까운 산타의 num
			}else if(dis==minDis) {
				if(s[i][1]>s[minNum][1]) { //r 좌표가 큰 산타
					minNum=i;
				}else if(s[i][1]==s[minNum][1]) { //r이 동일한 경우
					if(s[i][2]>s[minNum][1]) { //c 좌표가 큰 산타
						minNum=i;
					}
				}
			}
		}
		
		//---가장 가까운 산타의 번호를 구했다(minNum).루돌프와 산타의 거리도 구했다(minDis)
		int minD=0; //최소 방향
		for(int d=0;d<8;d++) {
			int dx=r[0]+dir8[d][0];
			int dy=r[1]+dir8[d][1];
			
			int newDis=getDis(s[minNum][1],s[minNum][2] , dx, dy);
			if(newDis<minDis) {
				minDis=newDis;
				minD=d;
			}
		}
		
		r[0]+=dir8[minD][0]; //루돌프 이동 
		r[1]+=dir8[minD][1]; //루돌프 이동
		
		for(int i=0;i<p;i++) {
			if(s[i][0]==0) continue; //탈락한 산타는 제외하기
			if(s[i][1]==r[0] && s[i][2]==r[1]) { //루돌프가 이동한 곳에 산타가 있었다면
				crash(0,minD,i); //충돌
			}
		}
		
		
	}
	
	//충돌
	public static void crash(int m,int d,int num) { //루돌프가 이동한거면 m=0, 산타가 이동한거면 m=1
		if(m==0) { //루돌프가 움직여서 충돌이 났다
			score[num]+=c; //산타 점수 득점
			//산타 이동 -> 이 산타 기절해야한다
			s[num][1]=c*dir8[d][0]; 
			s[num][2]=c*dir8[d][1]; 
			
			//이동한 자리에 다른 산타 있으면 상호작용
			
		}else {
			
			
			
		}
	}
	
	public static void interaction() {
		
	}
	
	//두 좌표 사이의 거리 구함
	public static int getDis(int sx,int sy,int ex,int ey) {
		return (int) (Math.pow((sx-ex), 2)+Math.pow((sy-ey), 2));
	}

}
