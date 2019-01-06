package project.Server.rest;

import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.Server.domain.dto.RSSFeedDto;
import project.Server.rss.RSSParser;

/**
 * API Controller responsible for handling requests for RSS feeds.
 * @author Matt
 */
@Controller
public class RSSApiController  {
	private static final Logger LOGGER = LoggerFactory.getLogger(RSSApiController.class);
	private static final String URL_FILMWEB = "https://www.filmweb.pl/feed/news/latest";
	
	/**
	 * Function responsible for handling GET request.
	 * @return ResponseEntity containing HTTP response to the request.
	 * Returns status 200 and JSON with RSS feed item list if processing request was successful.
	 * Returns status 404 otherwise.
	 * @throws IOException - probable cause of exception is malformed URL
	 */
	@RequestMapping(value = "/rssFILMWEB", method = RequestMethod.GET)
	public ResponseEntity<List <RSSFeedDto > > rssRead() throws IOException {
		LOGGER.info("### Downloading RSS feed.");
		
		List<RSSFeedDto> data = RSSParser.ReadRSSFeed(new URL(URL_FILMWEB));
		if(data == null)
			LOGGER.info("### Reading RSS feed failed.");
		
		return (data == null)
			?  new ResponseEntity<>(data, HttpStatus.NOT_FOUND)
			:  new ResponseEntity<>(data, HttpStatus.OK);
	}
	
}
