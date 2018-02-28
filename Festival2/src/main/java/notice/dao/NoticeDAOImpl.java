package notice.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import notice.dto.NoticeDTO;
import notice.dto.NoticePage;



public class NoticeDAOImpl implements NoticeDAO {
	@Autowired private SqlSession mybatis;

	@Override
	public boolean insert(NoticeDTO dto) {
		return mybatis.insert("notice.mapper.insert", dto)>0? true:false;
	}

	@Override
	public boolean update(NoticeDTO dto) {
		return mybatis.update("notice.mapper.update", dto) > 0? true:false;
	}

	@Override
	public boolean delete(int id) {
		return mybatis.delete("notice.mapper.delete", id) > 0 ? true:false;
	}

	@Override
	public List<NoticeDTO> list() {
		return mybatis.selectList("notice.mapper.list");
	}

	@Override
	public NoticeDTO select(int id) {
		return mybatis.selectOne("notice.mapper.select", id);
	}
	
	public void read(int id){
		mybatis.update("notice.mapper.read", id);
	}

	@Override
	public List<NoticeDTO> list(HashMap<String, String> map) {
		return mybatis.selectList("notice.mapper.list", map);
	}

	@Override
	public NoticePage list(NoticePage page) {
		page.setTotalList( (Integer)mybatis.selectOne("notice.mapper.total", page));
		List<NoticeDTO> list = mybatis.selectList("notice.mapper.list", page);
		page.setList(list);
		return page;
	}
}
