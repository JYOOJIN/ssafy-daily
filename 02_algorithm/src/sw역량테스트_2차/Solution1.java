package sw역량테스트_2차;

import java.io.*;
import java.util.*;

public class Solution1 {

	public static void main(String[] args) throws Exception{
	    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

	    StringTokenizer st;
	    
	    int tc=Integer.parseInt(br.readLine());
	    
	    for(int t=1;t<=tc;t++) {
	        int n=Integer.parseInt(br.readLine()); //나무의 개수
	        long day=1; //날짜
	        int max=0; //최대 높이
	        int[] tree=new int[n];
	        st=new StringTokenizer(br.readLine());
	        
	        //나무 입력받으면서 최대 높이 구한다
	        for(int i=0;i<n;i++) {
	            tree[i]=Integer.parseInt(st.nextToken());
	            if(max<tree[i]) {
	                max=tree[i];
	            }
	        }
	        
	        //최대 높이 나무와의 차이를 저장
	        List<Integer> diff=new ArrayList<Integer>();
	        for(int i=0;i<n;i++) {
	            if(tree[i]==max) continue;
	            diff.add(max-tree[i]);
	        }
	        
	        
	        long[] num=new long[2]; //1 저장, 2저장
	        
	        //2와 1이 얼마나 있는지 계산 후 저장
	        for(int i=0;i<diff.size();i++) {
	            
	            if(diff.get(i)%2==0) {
	                num[1]+=(diff.get(i)/2)*2; //근(2)
	            }else {
	                num[0]+=diff.get(i)%2; //나머지(1)
	                num[1]+=(diff.get(i)/2)*2; //근(2)
	            }
	            
	        }

	        
	        while(true) {
	            if(num[0]==0 && num[1]==0) break; //모든 나무의 높이가 같아짐
	            
	            if(day%2==1) { //홀수데이
	                if(num[0]>0) { //홀수가 남아있으면
	                    num[0]-=1; //물주고
	                    day+=1;    //날짜 하루 지나고
	                    continue;
	                }else { //홀수가 안남아있다면 짝수 처리하러 간다
	                    if(num[1]==2) { //짝수가 2라면
	                        day+=1; //하루 그냥 넘긴다
	                        continue;
	                    }else { //짝수가 2가 아니면
	                        num[1]-=1; //물주고
	                        day+=1;    //날짜 하루 지나고
	                        continue;
	                    }
	                }
	                
	                
	            }else {//짝수데이
	                
	                if(num[1]>0) { //짝수가 남아있으면
	                    num[1]-=2; //물주고
	                    day+=1;    //날짜 하루 지나고
	                    continue;
	                }else { //짝수 안남아있다면 홀수만 남아있는 것
	                    day+=1; //하루 그냥 넘긴다
	                    continue;
	                }
	                
	            }
	            
	            
	        }
	        
	        System.out.println("#"+t+" "+(day-1)); //최종 day에서 첫날은 1로 했으므로 -1 한 후 출력
	        
	    }
	    
	}

}
