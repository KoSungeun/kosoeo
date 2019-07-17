import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class D2_IOExceptionCase2 {

	public static void main(String[] args) {
		Path file = Paths.get("D:\\kosoeo\\java\\ch14\\src\\Simple.txt");
		BufferedWriter writer = null;

		try {
			// 아래 문장에서 IOException 발생 가능
			writer = Files.newBufferedWriter(file);
			
			writer.write('A');		// IOException 발생 가능
			writer.write('Z');		// IOException 발생 가능

			if (writer != null)
				writer.close();		// IOException 발생 가능
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
