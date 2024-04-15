package sw역량테스트_2차;

import java.io.*;
import java.util.*;

public class Solution2 {

	//좌표 클래스
	static class Point{
	    int x,y;

	    public Point(int x, int y) {
	        super();
	        this.x = x;
	        this.y = y;
	    }
	    
	}

	static int w,h,min; //도로로부터 가장 멀리 떨어진 집의 최소거리
	static int[][] map; //지도
	//방향은 총 4가지이다. 
	static int[][] dir1= {{-1,0},{-1,1},{0,1},{1,1}};
	static int[][] dir2={{1,0},{1,-1},{0,-1},{-1,-1}};
	static List<Point> road; //도로 좌표 저장하는 리스트
	static int[][][] visited; //어느 좌표에서 해당 방향으로 도로를 놓은 적이 있는지 여부
	static List<Point> home; //집 좌표 저장

	public static void main(String[] args) throws Exception{
	    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st;
	    
	    int tc=Integer.parseInt(br.readLine());
	    for(int t=1;t<=tc;t++) {
	        
	        st=new StringTokenizer(br.readLine());
	        w=Integer.parseInt(st.nextToken());
	        h=Integer.parseInt(st.nextToken());
	        min=Integer.MAX_VALUE;
	        
	        map=new int[h][w];
	        home=new ArrayList<>();
	        
	        for(int i=0;i<h;i++) {
	            st=new StringTokenizer(br.readLine());
	            for(int j=0;j<w;j++) {
	                map[i][j]=Integer.parseInt(st.nextToken());
	                if(map[i][j]==1) home.add(new Point(i,j)); //집 좌표 저장
	            }
	        }
	        
	        
	        visited=new int[h][w][4]; //좌표+방향 
	        boolean build=false; //아무 도로도 짓지 못했을 때를 확인하기 위한 flag
	        
	        for(int i=0;i<h;i++) {
	            for(int j=0;j<w;j++) {
	                for(int d=0;d<4;d++) { //4방향 확인한다
	                    if(map[i][j]==0 && check(i,j,d) && visited[i][j][d]!=1) { //현재 좌표 에서 d방향으로 도로를 놓을 수 있다면
	                        build=true; //도로를 적어도 하나는 놓았다
	                        visited[i][j][d]=1; //방문 확인
	                        int far=Integer.MIN_VALUE; //이 도로와 집 사이 거리 중 가장 긴 거리
	                        buildRoad(i, j, d); //현재 위치에서 도로를 놓는다.
	                        
	                        for(int k=0;k<home.size();k++) { //각 집에서 다리까지의 거리 구한다
	                            int mindis=Integer.MAX_VALUE; //집과 다리의 최소 거리
	                            for(Point p:road) {
	                                int dis=Math.abs(p.x-home.get(k).x)+Math.abs(p.y-home.get(k).y);
	                                mindis=Math.min(dis, mindis);
	                            }
	                            far=Math.max(far, mindis); //집과 다리의 최소 거리중 가장 먼 거리
	                        }
	                        
	                        min=Math.min(min, far); //집과 다리 가장 먼 거리 중 최솟값
	                    }
	                    
	                    
	                }
	            }
	        }    
	        
	        if(!build) System.out.println("#"+t+" "+-1); //도로를 하나도 놓지 못했다면 -1
	        else System.out.println("#"+t+" "+min);
	        
	    }
	    
	    
	}

	//해당 좌표에서 해당 방향으로 도로를 놓으며 road list에 좌표 저장
	//해당 좌표의 방향 방문 체크
	public static void buildRoad(int x,int y,int d) {
	    road=new ArrayList<>();
	    road.add(new Point(x,y));
	    int dx=x+dir1[d][0];
	    int dy=y+dir1[d][1];
	    
	    while(dx>=0 && dx<h && dy>=0 && dy<w) {
	        road.add(new Point(dx,dy));
	        visited[dx][dy][d]=1;
	        dx+=dir1[d][0];
	        dy+=dir1[d][1];
	        
	    }
	    
	    int dx2=x+dir2[d][0];
	    int dy2=y+dir2[d][1];
	    
	    while(dx2>=0 && dx2<h && dy2>=0 && dy2<w) {
	        road.add(new Point(dx2,dy2));
	        visited[dx2][dy2][d]=1;
	        dx2+=dir2[d][0];
	        dy2+=dir2[d][1];
	    }
	    
	    
	}

	//해당 방향으로 도로를 낼 수 있는지 확인
	public static boolean check(int x,int y,int d) { 

	    boolean flag=false;
	    
	    int dx=x+dir1[d][0];
	    int dy=y+dir1[d][1];
	    
	    while(dx>=0 && dx<h && dy>=0 && dy<w) { //배열 범위 안에 있을 동안 반복
	        flag=true; 
	        if(map[dx][dy]==1) return false; //집 있으면 못놓는다
	        dx+=dir1[d][0];
	        dy+=dir1[d][1];
	        
	    }
	    
	    int dx2=x+dir2[d][0];
	    int dy2=y+dir2[d][1];
	    while(dx2>=0 && dx2<h && dy2>=0 && dy2<w) { //배열 범위 안에 있을 동안 반복
	        flag=true;
	        if(map[dx2][dy2]==1) return false; //집 있으면 못놓는다
	        dx2+=dir2[d][0];
	        dy2+=dir2[d][1];
	    }
	    
	    if(flag) return true; //양쪽 중 아무 방향으로 도로가 놓일 수 있다면
	    else return false; 
	}

}
