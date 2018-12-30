package project.Server.rss;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import com.sun.syndication.feed.synd.*;
import com.sun.syndication.io.*;
import com.sun.syndication.io.XmlReader;

import org.w3c.dom.*;
import javax.xml.parsers.*;

public class RSSParser {
	public static boolean ReadRSSFeed(URL url) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		try{
			Document doc= dBuilder.parse(url.openConnection().getInputStream());
			NodeList items = doc.getDocumentElement().getElementsByTagName("channel");
			if(items.getLength() == 0)
				return false;
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
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
