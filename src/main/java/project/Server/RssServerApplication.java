package project.Server;

import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.Server.rss.*;

@SpringBootApplication
public class RssServerApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RssServerApplication.class, args);
		URL url = new URL("https://www.filmweb.pl/feed/news/latest");
		URL testUrl = new URL("http://www.touhou.pl");
		
		RSSParser.ReadRSSFeed(url);
	}

}

