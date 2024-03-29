package daily_0226;

public class np {
	
	public static int[] arr;
	
	public static void swap(int i,int j) {
		int tmp=arr[i];
		arr[i]=arr[j];
		arr[j]=tmp;
	}
	
	//매개변수로 들어온 arr의 다음 순열을 찾아준다
	public static boolean nextPermutation(int[] arr) {
		
		int i=arr.length-1;
		int j=arr.length-1;
		int k=arr.length-1;
		
		
		//1)절벽찾기
		while(i>0 && arr[i-1]>arr[i]) i--;
		if(i==0) return false;
		
		//2)swap
		while(arr[i-1]>arr[j]) j--;
		swap(i-1,j);
		
		//3)오름차순
		while(i<k) {
			swap(i,k);
			i++;
			k--;
		}
		
		return true;
	}

	public static void main(String[] args) {


	}

}
