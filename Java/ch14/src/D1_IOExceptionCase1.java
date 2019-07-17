import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class D1_IOExceptionCase1 {

	public static void main(String[] args) {
		Path file = Paths.get("D:\\kosoeo\\java\\ch14\\src\\Simple.txt");
		BufferedWriter writer = null;

//		writer = Files.newBufferedWriter(file);
//
//		writer.write('A');
//		writer.write('Z');
//
//		if (writer != null)
//			writer.close();

	}

}
