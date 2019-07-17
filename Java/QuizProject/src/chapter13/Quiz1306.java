package chapter13;

public class Quiz1306 {

	public static void main(String[] args) {
		int[][] num = new int[3][9];

		for(int i = 0; i < num.length; i++) {
			for(int j = 0; j < num[i].length; j++) {
				num[i][j] = (i + 2) * (j + 1);
			}
		}
		
		
		for(int i = 0; i < num.length; i++) {
			for(int j = 0; j < num[i].length; j++) {
				System.out.printf("%d * %d = %d\t", i + 2, j + 1, num[i][j]);
			}
			System.out.println();
		}
	}

}
