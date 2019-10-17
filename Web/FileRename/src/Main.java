import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File filepath = new File("C:\\Users\\kosmo_14\\Downloads\\mp3player\\mp3");

		for (File file : filepath.listFiles()) {
			file.renameTo(new File("a" + file.getName()));
		}

	}

}
