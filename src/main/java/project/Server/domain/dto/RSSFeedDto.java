package project.Server.domain.dto;

/**
 * DTO containing information about RSS feed item
 * @author Matt
 */
public class RSSFeedDto {
	private String title;
	private String description;
	private String publicationDate;
	private String image;
	private String url;
	
	public RSSFeedDto(String title, String description, String publicationDate, String image, String url) {
		this.title = title;
		this.description = description;
		this.publicationDate = publicationDate;
		this.image = image;
		this.url = url;
	}
	
	public String getTitle() { return title; }
	public  String getDescription() { return description; }
	public String getPublicationDate() { return publicationDate; }
	public String getImage() { return image; }
	public String getUrl() { return url; }
}
