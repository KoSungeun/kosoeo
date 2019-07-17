package chapter13;

public class Quiz1307 {

	public static void main(String[] args) {
		int[][] arr1 = new int[2][4];
		int[][] arr2 = new int[4][2];
		
		int num = 1;
		for(int i = 0; i < arr1.length; i++) {
			for(int j = 0; j < arr1[i].length; j++) {
				arr1[i][j] = num;
				num++;
			}
		}
		for(int i = 0; i < arr2.length; i++) {
			for(int j = 0; j < arr2[i].length; j++) {
				arr2[i][j] = arr1[j][i];
			}
		}
		
	}

}
