class Main {

	public static void main(String[] args) {	
        String str = "/to kse dfsadf";
		System.out.println(str.substring(str.indexOf(" "), str.indexOf(" ", str.indexOf(" ") + 1)));

		String str2 = "sdfasd";
		String[] sp = str2.split("/");
		System.out.println(sp[0]);
	}

}
