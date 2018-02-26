package files.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import files.dto.FilesDTO;

public class FilesDAOImpl implements FilesDAO {
	
	@Autowired	private SqlSession mybatis;
	
	@Override
	public boolean insert(FilesDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(FilesDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean boardDel(String b_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean profileDel(String u_email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FilesDTO boardselect(String b_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilesDTO profileselect(String u_email) {
		// TODO Auto-generated method stub
		return null;
	}

}
