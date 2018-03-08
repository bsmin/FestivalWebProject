package member.web.dao;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import member.web.dto.WebMemberDTO;

public class WebMemberDAOImpl implements WebMemberDAO {
	@Autowired private SqlSession mybatis;
	
	@Override
	public boolean select(String email) {
		// id�� ������ 0�̸� �������� �����Ƿ� ��밡��true
		return (Integer)mybatis.selectOne(
							"member.web.mapper.email_check", email)==0 ? true : false;
	}

	@Override
	public boolean insert(WebMemberDTO dto) {
		return mybatis.insert("member.web.mapper.insert", dto)>0 ? true : false;
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
	public WebMemberDTO select(String email, String pwd) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		map.put("pwd", pwd);
		return mybatis.selectOne("member.web.mapper.login", map);
	}

}
