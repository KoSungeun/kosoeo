package chapter13;

public class Quiz1309 {

	public static void main(String[] args) {
		int[] arr = {210, 19, 72, 129, 34};
		
		int temp;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr.length - i -1; j++) {
				if(arr[j] > arr[j+1]) {
					temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
			}
		}
		for(int print : arr) {
			System.out.println(print);
		}
	}

}
