package member.web.service;

import org.springframework.beans.factory.annotation.Autowired;

import member.web.dao.WebMemberDAO;
import member.web.dto.WebMemberDTO;



public class WebMemberServiceImpl implements WebMemberService {
	@Autowired private WebMemberDAO dao;
	
	@Override
	public boolean select(String email) {
		return dao.select(email);
	}

	@Override
	public boolean insert(WebMemberDTO dto) {
		return dao.insert(dto);
	}

	@Override
	public boolean update(WebMemberDTO dto) {
		return false;
	}

	@Override
	public boolean delete(String email) {
		return false;
	}

	@Override
	public String select(String email, String pwd) {
		return dao.select(email, pwd);
	}

}
