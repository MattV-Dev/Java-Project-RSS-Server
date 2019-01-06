package project.Server.rss;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import project.Server.domain.dto.RSSFeedDto;

import org.w3c.dom.*;
import javax.xml.parsers.*;

/**
 * A class responsible for parsing through RSS feed.
 * @author Matt
 */
public class RSSParser {
	/**
	 * Reads RSS feed from provided URL, returning processed list of items, packed into aan item list
	 * @param url - URL to the RSS feed.
	 * @return List of objects representing RSS feed items, of type RSSFeedDto. If reading the feed failed, returns null.
	 */
	public static List<RSSFeedDto> ReadRSSFeed(URL url){
		
		List<RSSFeedDto> output = new ArrayList<>();
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc= dBuilder.parse(url.openConnection().getInputStream());
			NodeList items = doc.getDocumentElement().getElementsByTagName("channel");
			if(items.getLength() == 0)
				return null;
			Element channel = (Element) items.item(0);
			items = channel.getElementsByTagName("item");
			for(int i = 0; i < items.getLength(); i++) {
				Node nItem = items.item(i);
				if(nItem.getNodeType() != Node.ELEMENT_NODE)
					continue;
				Element item = (Element) nItem;
				
				String title = item.getElementsByTagName("title").item(0).getTextContent();
				title = title == null ? "" : title;
				
				String description = item.getElementsByTagName("description").item(0).getTextContent();
				description = description == null ? "" : description;
				description = description.replaceAll("<[^>]*(>|$)", "");
				description = description.replaceAll("\\[[^\\]]*(\\]|$)", "");
				
				String date = item.getElementsByTagName("dc:date").item(0).getTextContent();
				date = date == null ? "" : date;
				date = date.replaceAll("T.*$", "");
				
				Element image = (Element) item.getElementsByTagName("media:content").item(0);
				String img = null;
				if(image != null)
					img = image.getAttribute("url");
				
				String link = item.getElementsByTagName("link").item(0).getTextContent();
				link = link == null ? "" : link;
				
				System.out.println("Title: " + title);
				System.out.println("Description: " + description);
				System.out.println("Date: " + date);
				System.out.println("img: " + img);
				System.out.println("Link: " + link);
				System.out.println();
				
				RSSFeedDto token = new RSSFeedDto(title, description, date, img, link);
				output.add(token);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return output;
	}
	
}
