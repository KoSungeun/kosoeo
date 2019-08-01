
public class Main {

	public static void main(String[] args) throws InterruptedException {
		try {
			ConnectionPool.getInstance("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger", 5, 20);
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		
		for(int i = 0; i<100; i++) {
			TestThread test = new TestThread(i);
			test.start();
//			Thread.sleep(10);
		}
	}

}
