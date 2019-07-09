
public class E3_ByTimesWhile {

	public static void main(String[] args) {
		int i = 2;	
		while(i < 10) {
			System.out.println("-------------------------");
			int j = 1;
			while(j < 10) {	
				System.out.printf("%d x %d = %d\n", i, j, i*j);
				j++;
			}
			i++;
		}
	}

}
