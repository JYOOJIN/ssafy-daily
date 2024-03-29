package daily_0208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author 정유진
 * 문제: BJ_17406_배열 돌리기 4
 * 수행결과: 맞았습니다!
 * 메모리: 25156 KB
 * 시간: 336 ms
 * 코드길이: 3457 B
 * 풀이 전략: 1. 범위를 정해서 rotate
 *         2. 명령의 조합을 구해야한다
 *         3. 깊은 복사를 통해서 배열을 넘겨주어야한다
 */


public class BJ_17406 {
	
    static int N, M, K;
    static int[][] demand; //회전연산을 담는 배열
    static int[][] num; //초기 배열
    static int min = Integer.MAX_VALUE; //배열의 값
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        num = new int[N][M]; //초기배열 생성

        //초기배열 값 넣기
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                num[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //각 회전연산마다 r,c,s 3개의 변수 존재
        demand = new int[K][3];

        for(int k = 0; k<K; k++) {
            st = new StringTokenizer(br.readLine());
            demand[k][0] = Integer.parseInt(st.nextToken()) - 1;
            demand[k][1] = Integer.parseInt(st.nextToken()) - 1;
            demand[k][2] = Integer.parseInt(st.nextToken());
        }

        //회전연산을 담은 배열로 순열을 구함
        permutation(0, new int[K], new boolean[K]);

        System.out.printf("%d", min);
    }

    //순열을 구하는 재귀함수
    //구한 순열로 배열을 돌린다 -> 이때, 배열을 받을 때마다 깊은 복사 후 사용해야한다 -> 원본을 건드리면 안된다!!!
    public static void permutation(int cnt, int[] arr, boolean[] visited) {
        if(cnt == K) { //모든 자릿수를 채웠다면
            rotate(arr); //배열 회전
            return;
        }
        
        for(int i=0; i<K; i++) {
            if(visited[i]) continue; //방문 했다면 pass
            visited[i] = true;
            arr[cnt] = i;
            permutation(cnt+1, arr, visited); //재귀함수 호출
            visited[i] = false;
        }
    }

    public static void rotate(int[] arr) {
        int[][] tmp = copynum(); //깊은 복사를 통해 원본 배열 건드리지 x

        for(int k=0; k<K; k++) {
            int r = demand[arr[k]][0];
            int c = demand[arr[k]][1];
            int S = demand[arr[k]][2];

            for(int s=1; s<=S; s++) {
                //위
                int upTmp = tmp[r-s][c+s];
                for(int y = c+s; y > c-s; y--) {
                    tmp[r-s][y] = tmp[r-s][y-1];
                }
                //오른쪽
                int rightTmp = tmp[r+s][c+s];
                for(int x = r+s; x > r-s; x--) {
                    tmp[x][c+s] = tmp[x-1][c+s];
                }
                tmp[r-s+1][c+s] = upTmp;
                //아래
                int leftTmp = tmp[r+s][c-s];
                for(int y = c-s; y < c+s; y++) {
                    tmp[r+s][y] = tmp[r+s][y+1];
                }
                tmp[r+s][c+s-1] = rightTmp;
                //왼쪽
                for(int x = r-s; x < r+s; x++) {
                    tmp[x][c-s] = tmp[x+1][c-s];
                }
                tmp[r+s-1][c-s] = leftTmp;
            }
        }

        getAnswer(tmp); //돌린 배열의 값, 즉 최솟값을 찾는다
    }

    //배열 깊은 복사
    public static int[][] copynum() {
        int[][] tmp = new int[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                tmp[i][j] = num[i][j];
            }
        }

        return tmp;
    }

    //각 배열의 값을 구하는 함수
    public static void getAnswer(int[][] tmp) {
        for(int i=0; i<N; i++) {
            int sum = 0;
            for(int j=0; j<M; j++) {
                sum += tmp[i][j];
            }
            if(min > sum) min = sum;
        }
    }
}

