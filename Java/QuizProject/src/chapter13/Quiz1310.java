package chapter13;

public class Quiz1310 {

	public static void main(String[] args) {
		int[][] arr = new int [4][4];
		int num = 1;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				arr[i][j] = num;
				num++;
			}
		}
		print(arr);
		rotation(arr);
		print(arr);
		rotation(arr);
		print(arr);
		rotation(arr);
		print(arr);

		
	}
	public static void rotation(int[][] arr) {
		int[][] temp = new int [4][4];
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				temp[i][j] = arr[arr.length -1 - j][i];
			}
		}
		System.arraycopy(temp, 0, arr, 0, temp.length);
	}
	
	public static void print(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				System.out.printf("%d\t", arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

}
