package board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

public class BoardServiceImpl implements BoardService {

	@Autowired BoardDAO dao;	
	
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
	public boolean delete(int b_id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BoardDTO> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	@Override
	public BoardDTO select(int b_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void read(int b_id) {
		// TODO Auto-generated method stub

	}

}
