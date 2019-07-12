
public class QuizAZ {

	public static void main(String[] args) {
		for(int a = 0; a < 10; a++) {
			for(int z = 0; z < 10; z++) {
				if(a != z) {
					if((a*10 + z) + (z*10 + a) == 99) {
						System.out.printf("A:%d Z:%d\n",a,z);
					}
				}
			}
		}
	}

}
