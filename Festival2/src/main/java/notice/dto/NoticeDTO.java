package notice.dto;

import java.sql.Date;

public class NoticeDTO {
	private int n_id, n_readcnt, no;
	private String n_title, n_writer, n_content, u_email;
	private Date n_writedate;
	private String f_path, f_name;
	
	public String getU_email() {
		return u_email;
	}
	public void setU_email(String u_email) {
		this.u_email = u_email;
	}
	public int getN_id() {
		return n_id;
	}
	public void setN_id(int n_id) {
		this.n_id = n_id;
	}
	public int getN_readcnt() {
		return n_readcnt;
	}
	public void setN_readcnt(int n_readcnt) {
		this.n_readcnt = n_readcnt;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_writer() {
		return n_writer;
	}
	public void setN_writer(String n_writer) {
		this.n_writer = n_writer;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public Date getN_writedate() {
		return n_writedate;
	}
	public void setN_writedate(Date n_writedate) {
		this.n_writedate = n_writedate;
	}
	public String getF_path() {
		return f_path;
	}
	public void setF_path(String f_path) {
		this.f_path = f_path;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	
	
	
	
}

