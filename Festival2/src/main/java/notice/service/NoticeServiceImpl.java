package notice.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import notice.dao.NoticeDAO;
import notice.dto.NoticeDTO;
import notice.dto.NoticePage;

public class NoticeServiceImpl implements NoticeService{
	@Autowired private NoticeDAO dao;
	
	@Override
	public boolean insert(NoticeDTO dto) {
		return dao.insert(dto);
	}

	@Override
	public boolean update(NoticeDTO dto) {
		return dao.update(dto);
	}

	@Override
	public boolean delete(int id) {
		return dao.delete(id);
	}

	@Override
	public List<NoticeDTO> list() {
		return dao.list();
	}

	@Override
	public NoticeDTO select(int id) {
		return dao.select(id);
	}

	@Override
	public void read(int id) {
		dao.read(id);
	}

	@Override
	public List<NoticeDTO> list(HashMap<String, String> map) {
		return dao.list(map);

	}

	@Override
	public NoticePage list(NoticePage page) {
		return dao.list(page);
	}
	
}
