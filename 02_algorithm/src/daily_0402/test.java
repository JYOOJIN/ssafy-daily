package daily_0402;

import java.util.Arrays;

public class test {
	
	public static int[] feat;
	public static int[][] feature;
	public static int index;
	public static void main(String[] args) {
		feat=new int[3];
		feature=new int[8][3];
		index=0;
		feature(3, 0);
		
		for(int i=0;i<8;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(feature[i][j]);
			}
			System.out.println();
		}
	}
	
	//자릿수에 맞춰 ab 구하는 함수(부분집합)
	public static void feature(int n,int depth) {
		
		if(depth==n) {
			for(int i=0;i<n;i++) {
				feature[index][i]=feat[i];
			}
			index++;
			return;
		}
		
		feat[depth]=0;
		feature(n,depth+1);
		feat[depth]=1;
		feature(n,depth+1);

	}

}
