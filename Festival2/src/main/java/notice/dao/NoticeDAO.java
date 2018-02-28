package notice.dao;

import java.util.HashMap;
import java.util.List;

import notice.dto.NoticeDTO;
import notice.dto.NoticePage;

public interface NoticeDAO {
		boolean insert(NoticeDTO dto);
		boolean update(NoticeDTO dto);
		boolean delete(int id);
		List<NoticeDTO> list();
		NoticePage list(NoticePage page);
		List<NoticeDTO> list(HashMap<String, String> map);
		NoticeDTO select(int id);
		void read(int id);

}
