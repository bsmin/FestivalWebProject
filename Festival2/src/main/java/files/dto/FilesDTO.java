package files.dto;

public class FilesDTO {
	
	private int	f_id;
	private int b_id;
	private String u_email;
	private String f_path;
	private String f_name;
	
	public FilesDTO() {}

	public FilesDTO(int f_id, int b_id, String u_email, String f_path, String f_name) {
		super();
		this.f_id = f_id;
		this.b_id = b_id;
		this.u_email = u_email;
		this.f_path = f_path;
		this.f_name = f_name;
	}

	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public int getB_id() {
		return b_id;
	}

	public void setB_id(int b_id) {
		this.b_id = b_id;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
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
