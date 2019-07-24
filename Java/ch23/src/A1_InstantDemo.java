import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

public class A1_InstantDemo {

	public static void main(String[] args) {
		Instant start = Instant.now();
		System.out.println("시삭: " + start.getEpochSecond());
		
		int sum = 0;
//		sum = IntStream.range(0, 1000000000).parallel().sum();
//		
//		for(int i = 0; i < 1000000000; i++) {
//			sum += i;
//		}
		System.out.println("Time files like an arrow." + sum);
		
		Instant end = Instant.now();
		System.out.println("끝: " + end.getEpochSecond());
		
		Duration between = Duration.between(start, end);
		System.out.println("밀리 초 단위 차: " + between.toMillis());
	}

}
