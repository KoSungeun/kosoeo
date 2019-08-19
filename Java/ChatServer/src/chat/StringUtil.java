package chat;

public class StringUtil {
	public String rSplit(String request, int index) {
		String[] result = request.split("/");
		return result[index];
	}

}
