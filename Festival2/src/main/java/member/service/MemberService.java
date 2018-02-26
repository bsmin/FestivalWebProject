package member.service;

import java.util.List;
import member.dto.MemberDTO;



public interface MemberService {
	public boolean insert(MemberDTO dto);
	public boolean update(MemberDTO dto);
	public boolean delete(String u_email);
	public List<MemberDTO> list();
	public MemberDTO select(String u_email);
	public String emailcheck(String u_email);
	public boolean  emailsend(String subject, String text, String from, String to, String filePath);
	public MemberDTO select( String u_email, String u_pwd ); 
	public boolean pwdUpdate(String u_email, String pwdran);
	public boolean updatepwd(MemberDTO dto);

}
