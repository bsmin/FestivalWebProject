package member.dao;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import member.dto.MemberDTO;

public class MemberDAOImpl implements MemberDAO {

	@Autowired	private SqlSession mybatis;
	
	//회원가입 요청처리
	@Override
	public boolean insert(MemberDTO dto) {
		// TODO Auto-generated method stub
		return mybatis.insert("member.mapper.insert", dto) > 0 ? true : false;
	}
	
	@Override
	public boolean update(MemberDTO dto) {
		// TODO Auto-generated method stub
		return mybatis.update("member.mapper.update", dto) > 0 ? true : false;
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
		return mybatis.selectOne("member.mapper.searchmember", u_email);
	}
	
	//이메일 유효성검사
	@Override
	public String emailcheck(String u_email) {
		String check = mybatis.selectOne("member.mapper.emailcheck", u_email);
		return check;
	}
	
	//이메일 인증번호 발송
	@Autowired JavaMailSenderImpl javaMailSenderImpl;
	
	@Override
	public boolean emailsend(String subject, String text, String from, String to, String filePath) {
		  // javax.mail.internet.MimeMessage
	       
		MimeMessage message = javaMailSenderImpl.createMimeMessage();
		try {
	    // org.springframework.mail.javamail.MimeMessageHelper
	    MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
	    helper.setSubject(subject);
	    helper.setText(text, true);
	    helper.setFrom(from);
	    helper.setTo(to);
	    // 첨부 파일 처리
	    	if (filePath != null) {
	    		File file = new File(filePath);
	    		if (file.exists()) {
	    			helper.addAttachment(file.getName(), new File(filePath));
	    		}
	    	}
	    javaMailSenderImpl.send(message);
	    return true;
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	   return false;
	}
	
	//로그인요청처리
	@Override
	public MemberDTO select(String u_email, String u_pwd) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("u_email", u_email);
		map.put("u_pwd", u_pwd);
		return mybatis.selectOne("member.mapper.login", map);
	}

	@Override
	public boolean pwdUpdate(String u_email, String pwdran) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("u_email", u_email);
		map.put("u_pwd", pwdran);
		return mybatis.update("member.mapper.pwdupdate", map) > 0 ? true : false;
	}

	@Override
	public boolean updatepwd(MemberDTO dto) {
		return mybatis.update("member.mapper.pwdupdate", dto) > 0 ? true : false;
	}

}
