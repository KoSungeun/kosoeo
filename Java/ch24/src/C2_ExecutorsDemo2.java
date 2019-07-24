import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class C2_ExecutorsDemo2 {

	public static void main(String[] args) {
		Runnable task1 = () -> {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
			}
			String name = Thread.currentThread().getName();
			System.out.println(name + ": " + (5 + 7));
		};
		Runnable task2 = () -> {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}
			String name = Thread.currentThread().getName();
			System.out.println(name + ": " + (7 - 5));
		};
		
		ExecutorService exr = Executors.newFixedThreadPool(2);
		exr.submit(task1);
		exr.submit(task2);
		exr.submit(() -> {
			String name = Thread.currentThread().getName();
			System.out.println(name + ": " + (5 * 7));
		});
		
		exr.shutdown();
	}

}
