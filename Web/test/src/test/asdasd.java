package test;

import java.io.IOException;
import java.util.function.Consumer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class asdasd {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Connection conn = Jsoup.connect("https://book.naver.com/bookdb/book_detail.nhn?bid=15469316")
		            .header("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,es;q=0.6") ;
			Document dom = conn.get();
<<<<<<< HEAD
			Elements bookInfo = dom.select("div.book_info");
			for(Element e : bookInfo.select("em")) {
				
			}
=======
>>>>>>> 592647392354bdb387cb522a1fb85e288d3aa4cd
			
			System.out.println(dom.select("#bookIntroContent").text());
			
	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
