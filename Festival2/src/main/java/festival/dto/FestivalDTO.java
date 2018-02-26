package festival.dto;


public class FestivalDTO {
	private String contentId;
	private String addr1;
	private String image;
	private String startevent;
	private String endevent;
	private String mapx;
	private String mapy;
	private String title;
	private String term;
	private String readcount;
	public FestivalDTO() {}

    public FestivalDTO(String contentId, String startevent, String endevent, String title) {
        this.contentId = contentId;
        this.startevent = startevent;
        this.endevent = endevent;
        this.title = title;
    }
    
	public FestivalDTO(String contentId, String addr1, String image, String mapx, String mapy, String title,
			String term) {
		this.contentId = contentId;
		this.addr1 = addr1;
		this.image = image;
		this.mapx = mapx;
		this.mapy = mapy;
		this.title = title;
		this.term = term;
	}

	public String getcontentId() {
		return contentId;
	}
	public void setcontentId(String contentId) {
		this.contentId = contentId;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getStartevent() {
		return startevent;
	}
	public void setStartevent(String startevent) {
		this.startevent = startevent;
	}
	public String getEndevent() {
		return endevent;
	}
	public void setEndevent(String endevent) {
		this.endevent = endevent;
	}
	public String getMapx() {
		return mapx;
	}
	public void setMapx(String mapx) {
		this.mapx = mapx;
	}
	public String getMapy() {
		return mapy;
	}
	public void setMapy(String mapy) {
		this.mapy = mapy;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getReadcount() {
		return readcount;
	}

	public void setReadcount(String readcount) {
		this.readcount = readcount;
	}
	
	
}
