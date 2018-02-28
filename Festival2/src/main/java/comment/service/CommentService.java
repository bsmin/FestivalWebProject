package comment.service;

import java.util.List;

import comment.dto.CommentDTO;

public interface CommentService {
	boolean insert(CommentDTO dto);
	boolean update(CommentDTO dto);
	boolean delete(int id);
	List<CommentDTO> list(int pid);
	CommentDTO select(int id);
}
