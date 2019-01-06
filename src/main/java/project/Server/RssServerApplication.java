package project.Server;

import java.net.URL;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import project.Server.rss.*;

/**
 * Application class for the server
 * @author Matt
 */
@SpringBootApplication
public class RssServerApplication {

	/**
	 * Main function of the application
	 * @param args - Arguments passed through console.
	 */
	public static void main(String[] args) {
		SpringApplication.run(RssServerApplication.class, args);
	}

}

