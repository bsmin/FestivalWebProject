package comment.dto;

import java.sql.Date;

//¡æ CommentDAO·Î
public class CommentDTO {
	private int id, pid;
	private String userid, content, name, str_writedate;
	private Date writedate;
	
	
	
	public String getStr_writedate() {
		return str_writedate;
	}
	public void setStr_writedate(String str_writedate) {
		this.str_writedate = str_writedate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	
	
	
}
