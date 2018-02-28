package comment.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import comment.dto.CommentDTO;

public class CommentDAOImpl implements CommentDAO {
	@Autowired SqlSession mybatis;
	
	@Override
	public boolean insert(CommentDTO dto) {
		return mybatis.insert("board.mapper.comment_insert", dto)>0 ? true : false;
	}

	@Override
	public boolean update(CommentDTO dto) {
		return mybatis.update("board.mapper.comment_update", dto) > 0 ? true : false;
	}

	@Override
	public boolean delete(int id) {
		return mybatis.delete("board.mapper.comment_delete", id) > 0 ? true : false;
	}

	@Override
	public List<CommentDTO> list(int pid) {
		return mybatis.selectList("board.mapper.comment_list", pid);
	}

	@Override
	public CommentDTO select(int id) {
		return null;
	}

}
