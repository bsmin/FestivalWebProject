package board.service;

import java.util.List;

import board.dto.BoardDTO;




public interface BoardService {
	boolean insert(BoardDTO dto);
	boolean update(BoardDTO dto);
	boolean delete(int b_id);
	List<BoardDTO> list();
	BoardDTO select(int b_id);
	void read(int b_id);
}
