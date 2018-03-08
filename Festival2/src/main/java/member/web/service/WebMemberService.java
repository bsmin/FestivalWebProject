package member.web.service;

import member.web.dto.WebMemberDTO;

public interface WebMemberService {
	boolean select( String email );
	boolean insert( WebMemberDTO dto );
	boolean update( WebMemberDTO dto );
	boolean delete( String email );
	WebMemberDTO select( String email, String pwd );
//	String select( String email, String pwd );
}
