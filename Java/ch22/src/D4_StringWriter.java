import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class D4_StringWriter {

	public static void main(String[] args) {
		String ks = "공부에 있어서 돈이 꼭 필요한 것은 아니다.";
		String es = "Life is long if you know how to use it.";
		
		try(BufferedWriter bw = 
				new BufferedWriter(new FileWriter("String.txt"))) {
			bw.write(ks, 0, ks.length());
			bw.newLine();	// 줄 바꿈 문자를 삽입(운영체제 별 줄 바꿈의 표시 방법이 다름)
			bw.write(es, 0, es.length());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
