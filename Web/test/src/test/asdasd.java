package test;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class asdasd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		
		
		try {
			Connection conn = Jsoup.connect("https://book.naver.com/bestsell/bestseller_body.nhn?cp=yes24&cate=total&bestWeek=2019-9-5&indexCount=&type=list&page=1");
			Document dom = conn.get();
			System.out.println(dom.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
