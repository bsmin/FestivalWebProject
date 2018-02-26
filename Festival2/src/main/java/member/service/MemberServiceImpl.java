package member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import member.dao.MemberDAO;
import member.dto.MemberDTO;

public class MemberServiceImpl implements MemberService {
	
	@Autowired MemberDAO dao;
	
	//회원가입요청
	@Override
	public boolean insert(MemberDTO dto) {
		return dao.insert(dto);
	}

	@Override
	public boolean update(MemberDTO dto) {
		// TODO Auto-generated method stub
		return dao.update(dto);
	}

	@Override
	public boolean delete(String u_email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<MemberDTO> list() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberDTO select(String u_email) {
		// TODO Auto-generated method stub
		return dao.select(u_email);
	}

	//이메일 유효성 체크
	@Override
	public String emailcheck(String u_email) {
		String check = dao.emailcheck(u_email);
		return check;
	}
	
	//email 인증번호보내기
	@Override
	public boolean emailsend(String subject, String text, String from, String to, String filePath) {
		return dao.emailsend(subject, text, from, to, filePath);
	}
	
	//로그인 요청
	@Override
	public MemberDTO select(String u_email, String u_pwd) {
		return dao.select(u_email, u_pwd);
	}

	@Override
	public boolean pwdUpdate(String u_email, String pwdran) {
		return dao.pwdUpdate(u_email, pwdran);
	}

	@Override
	public boolean updatepwd(MemberDTO dto) {
		// TODO Auto-generated method stub
		return dao.updatepwd(dto);
	}

}
