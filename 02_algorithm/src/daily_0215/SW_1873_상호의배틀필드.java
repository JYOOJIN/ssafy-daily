package daily_0215;

import java.io.*;
import java.util.*;
 
/**
 * @author 정유진
 * 문제: SW_1873_상호의 배틀필드
 * 난이도: D3
 * 수행결과: Pass
 * 메모리: 35120 KB
 * 시간: 225 ms
 * 코드길이: 5141 B
 * 풀이 전략: 조건을 확인하고 범위 안에 있다면 조건대로 행한다.
 * 			여기서 주의해야할 조건은, <벽에 닿으면 포탄은 무조건 사라진다><방향 명령이 내리면 이동하지 못해도 바라보는 방향은 바꾼다>
 * 			그리고 범위를 잘 확인할것!!!
 * 
 */

public class SW_1873_상호의배틀필드 {
       
 
    public static void main(String[] args) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc=Integer.parseInt(br.readLine()); //테스트케이스
        
        for(int t=1;t<=tc;t++) { //테스트케이스 만큼 반복
        	
            st=new StringTokenizer(br.readLine());
            int h=Integer.parseInt(st.nextToken()); //높이
            int w=Integer.parseInt(st.nextToken()); //넓이
             
            //초기 게임 맵 입력
            char[][] map=new char[h][w];
            for(int i=0;i<h;i++) {
                String s=br.readLine();
                map[i]=s.toCharArray();
                
            }
             
            int n=Integer.parseInt(br.readLine()); //명령어의 개수
             
            //명령어 배열에 입력
            char[] demand=new char[n];
            String s=br.readLine();
            demand=s.toCharArray();
 
             
            //초기 x,y좌표. 즉 처음 전차의 위치를 구한다. 전차는 1개
            int x=0;
            int y=0;
             
            for(int i=0;i<h;i++) {
                for(int j=0;j<w;j++) {
                    if(map[i][j]=='<' || map[i][j]=='>'||map[i][j]=='v'||map[i][j]=='^') {
                        x=i;
                        y=j;
                        break;
                    }
                }
            }

            
            for(int cnt=0;cnt<n;cnt++) { //명령어 수만큼 반복

                char c=demand[cnt]; //명령어 저장
                 
                if(c=='U') { //Up
                    if(x-1>=0 && map[x-1][y]=='.') { //맵 범위 안에 있으면서 이동하려는 위치가 평지일때
                        map[x][y]='.'; //현재 위치 평지로 바꾼다
                        map[x-1][y]='^'; //이동 위치에 전차 이동
                        x=x-1; //좌표 업데이트
                    }else {
                    	map[x][y]='^'; //이동하지 못해도 방향은 바꾼다
                    }
                }else if(c=='D') { //Down
                    if(x+1<h && map[x+1][y]=='.') { //맵 범위 안에 있으면서 이동하려는 위치가 평지일때
                        map[x][y]='.'; //현재 위치 평지로 바꾼다
                        map[x+1][y]='v'; //이동 위치에 전차 이동
                        x=x+1; //좌표 업데이트
                    }else {
                    	map[x][y]='v'; //이동하지 못해도 방향은 바꾼다
                    }
                }else if(c=='L') { //Left
                    if(y-1>=0 && map[x][y-1]=='.') { //맵 범위 안에 있으면서 이동하려는 위치가 평지일때
                        
                        map[x][y]='.'; //현재 위치 평지로 바꾼다
                        map[x][y-1]='<'; //이동 위치에 전차 이동
                        y=y-1; //좌표 업데이트
                    }else {
                    	map[x][y]='<'; //이동하지 못해도 방향은 바꾼다
                    }
                     
                }else if(c=='R') { //Right
                    if(y<w-1 && map[x][y+1]=='.') { //맵 범위 안에 있으면서 이동하려는 위치가 평지일때
                        map[x][y]='.'; //현재 위치 평지로 바꾼다
                        map[x][y+1]='>'; //이동 위치에 전차 이동
                        y=y+1; //좌표 업데이트
                    }else {
                    	map[x][y]='>'; //이동하지 못해도 방향은 바꾼다
                    }
                }else if(c=='S') { //Shoot
                	//포탄은 벽돌 맞으면 제거하고 사라지고, 강철 맞으면 그냥 사라진다
                	
                    if(map[x][y]=='<') { //왼쪽으로 쏜다면
                        for(int j=y;j>=0;j--) { //현재위치에서 왼쪽으로 가면서 탐색
                            if(map[x][j]=='*') { //벽돌이라면
                            	map[x][j]='.'; //부순다
                            	break; //포탄은 벽에 맞으면 사라진다
                            }else if(map[x][j]=='#') { //강철이라면
                            	break; //포탄은 벽에 맞으면 사라진다
                            }
                        }
                    }else if(map[x][y]=='>') { //오른쪽으로 쏜다면
                        for(int j=y;j<w;j++) { //현재 위치에서 오른쪽으로 가면서 탐색
                            if(map[x][j]=='*') { //벽돌이라면
                            	map[x][j]='.'; //부순다
                            	break; //포탄은 벽에 맞으면 사라진다 
                            }else if(map[x][j]=='#') { //강철이라면
                            	break; //포탄은 벽에 맞으면 사라진다
                            }
                        }
                    }else if(map[x][y]=='^') { //위로 쏜다면
                        for(int i=x;i>=0;i--) { //현재 위치에서 위로 가면서 탐색
                            if(map[i][y]=='*') { //벽돌이라면
                            	map[i][y]='.'; //부순다
                            	break; //포탄은 벽에 맞으면 사라진다
                            }else if(map[i][y]=='#') { //강철이라면
                            	break;  //포탄은 벽에 맞으면 사라진다
                            }
                        }
                    }else if(map[x][y]=='v') { //아래로 쏜다면
                        for(int i=x;i<h;i++) { //현재 위치에서 아래로 가면서 탐색
                            if(map[i][y]=='*') { //벽돌이라면
                            	map[i][y]='.'; //부순다
                            	break; //포탄은 벽에 맞으면 사라진다
                            }else if(map[i][y]=='#') { //강철이라면
                            	break; //포탄은 벽에 맞으면 사라진다
                            }
                        }
                    }
                     
                     
                }   
                
//				단계마다 확인해보기              
//	            System.out.println(cnt+1+"번째: "+demand[cnt]);
//	            for(int i=0;i<h;i++) {
//	            	for(int j=0;j<w;j++) {
//	            		System.out.print(map[i][j]+" ");
//	            	}
//	            	System.out.println();
//	            }
//	            System.out.println();
                 
            }

            //형식에 맞춰 출력
            System.out.print("#"+t+" ");
            for(int i=0;i<h;i++) {
                for(int j=0;j<w;j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }

             
        }
    }
 
}
