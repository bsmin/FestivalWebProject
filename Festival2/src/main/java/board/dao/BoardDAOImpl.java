package board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import board.dto.BoardDTO;

public class BoardDAOImpl implements BoardDAO {

	@Autowired private SqlSession mybatis;
	
	@Override
	public boolean insert(BoardDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(BoardDTO dto) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BoardDTO> list() {
		// TODO Auto-generated method stub
		return mybatis.selectList("board.mapper.boardlist");
	}

	@Override
	public BoardDTO select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void read(int id) {
		// TODO Auto-generated method stub

	}

}
