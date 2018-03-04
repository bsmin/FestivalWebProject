package member.dto;

public class MemberDTO {
	private int u_id, id; 
	private String u_email; 
	private String u_name, name; 
	private String u_pwd; 
	private String u_gender; 
	private int u_age; 
	private String u_phone; 
	private String u_addr; 
	private int admin;
	
	
	
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

	public int getAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public MemberDTO() {}

	public MemberDTO(int u_id, String u_email, String u_name, String u_pwd, String u_gender, int u_age, String u_phone,
			String u_addr) {
		super();
		this.u_id = u_id;
		this.u_email = u_email;
		this.u_name = u_name;
		this.u_pwd = u_pwd;
		this.u_gender = u_gender;
		this.u_age = u_age;
		this.u_phone = u_phone;
		this.u_addr = u_addr;
	}

	public int getU_id() {
		return u_id;
	}

	public void setU_id(int u_id) {
		this.u_id = u_id;
	}

	public String getU_email() {
		return u_email;
	}

	public void setU_email(String u_email) {
		this.u_email = u_email;
	}

	public String getU_name() {
		return u_name;
	}

	public void setU_name(String u_name) {
		this.u_name = u_name;
	}

	public String getU_pwd() {
		return u_pwd;
	}

	public void setU_pwd(String u_pwd) {
		this.u_pwd = u_pwd;
	}

	public String getU_gender() {
		return u_gender;
	}

	public void setU_gender(String u_gender) {
		this.u_gender = u_gender;
	}

	public int getU_age() {
		return u_age;
	}

	public void setU_age(int u_age) {
		this.u_age = u_age;
	}

	public String getU_phone() {
		return u_phone;
	}

	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}

	public String getU_addr() {
		return u_addr;
	}

	public void setU_addr(String u_addr) {
		this.u_addr = u_addr;
	}

	

}
