package comment.dao;

import java.util.List;

import comment.dto.CommentDTO;

public interface CommentDAO {
	boolean insert(CommentDTO dto);
	boolean update(CommentDTO dto);
	boolean delete(int id);
	List<CommentDTO> list(int pid);
	CommentDTO select(int id);
}
