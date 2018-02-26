package board.dto;

import java.sql.Date;

public class BoardDTO {
	private int b_id; 
	private int u_id; 
	private String b_writer; 
	private String b_title; 
	private String b_content; 
	private Date b_date; 
	private int b_readcnt;
	private int b_linkcnt;
	
	public BoardDTO() {}

	public BoardDTO(int b_id, int u_id, String b_writer, String b_title, String b_content, Date b_date, int b_readcnt,
			int b_linkcnt) {
		super();
		this.b_id = b_id;
		this.u_id = u_id;
		this.b_writer = b_writer;
		this.b_title = b_title;
		this.b_content = b_content;
		this.b_date = b_date;
		this.b_readcnt = b_readcnt;
		this.b_linkcnt = b_linkcnt;
	}

	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getB_writer() {
		return b_writer;
	}

	public void setB_writer(String b_writer) {
		this.b_writer = b_writer;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_content() {
		return b_content;
	}

	public void setB_content(String b_content) {
		this.b_content = b_content;
	}

	public Date getB_date() {
		return b_date;
	}

	public void setB_date(Date b_date) {
		this.b_date = b_date;
	}

	public int getB_readcnt() {
		return b_readcnt;
	}

	public void setB_readcnt(int b_readcnt) {
		this.b_readcnt = b_readcnt;
	}

	public int getB_linkcnt() {
		return b_linkcnt;
	}

	public void setB_linkcnt(int b_linkcnt) {
		this.b_linkcnt = b_linkcnt;
	}

}
