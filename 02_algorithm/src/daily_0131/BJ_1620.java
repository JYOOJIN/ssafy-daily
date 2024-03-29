package daily_0131;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_1620_나는야 포켓몬 마스터 이다솜
 * 난이도: S4
 * 수행결과: 맞았습니다!
 * 메모리: 98584 KB
 * 시간: 724 ms
 * 코드길이: 1030 B
 * 풀이 전략: 처음에 하나의 hashMap으로 했을 때는 value에서 key를 찾는 과정때문에 시간초과 발생
 * 			key로는 value를 바로 찾을 수 있기 때문에, 번호가 key인 hashMap과 이름이 key인 hashMap을 만들었다
 * 			입력받은 값이 string인지 int인지 판별은 parseint를 통해 오류가 안나면 int, 오류가 나면 String
 */

public class BJ_1620 {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		StringBuilder sb=new StringBuilder();
		
		int n=Integer.parseInt(st.nextToken()); //포켓몬 수
		int m=Integer.parseInt(st.nextToken()); //찾는 횟수
		
		HashMap<Integer,String> keymap=new HashMap<>(n+1); //번호가 key인 HashMap
		HashMap<String,Integer> valuemap=new HashMap<>(n+1); //이름이 key인 HashMap
		
		for(int i=1;i<=n;i++) { //입력받은 값 각 map에 넣기
			String s=br.readLine();
			keymap.put(i, s);
			valuemap.put(s, i);
		}
		
		for(int i=0;i<m;i++) {
			String find=br.readLine();
			
			try {
				int num=Integer.parseInt(find); //입력받은 값이 번호인지, 이름인지 판별
				// 입력받은 값이 번호일대
				sb.append(keymap.get(num)).append("\n");
				
			}catch(Exception e) { 
				// 입력받은 값이 포켓몬 이름일때
				sb.append(valuemap.get(find)).append("\n");
			}
			
		}
		
		//출력
		System.out.println(sb);
		
	}

}
