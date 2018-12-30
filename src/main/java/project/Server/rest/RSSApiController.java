package project.Server.rest;

import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import project.Server.domain.dto.RSSFeedDto;
import project.Server.rss.RSSParser;

@Controller
@RequestMapping(value = "/api")
public class RSSApiController  {
	private static final Logger LOGGER = LoggerFactory.getLogger(RSSApiController.class);
	private static final String URL_FILMWEB = "https://www.filmweb.pl/feed/news/latest";
	
	@RequestMapping(value = "/rssFILMWEB", method = RequestMethod.GET)
	public ResponseEntity<List <RSSFeedDto > > rssRead() throws Exception {
		LOGGER.info("### Downloading RSS feed.");
		
		List<RSSFeedDto> data = RSSParser.ReadRSSFeed(new URL(URL_FILMWEB));
		if(data == null)
			LOGGER.info("### Reading RSS feed failed.");
		
		return (data == null)
			?  new ResponseEntity<>(data, HttpStatus.NOT_FOUND)
			:  new ResponseEntity<>(data, HttpStatus.OK);
	}
	
}
