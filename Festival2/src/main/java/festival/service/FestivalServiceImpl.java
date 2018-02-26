package festival.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import festival.dao.FestivalDAOImpl;
import festival.dto.FestivalPageDTO;

public class FestivalServiceImpl implements FestivalService {
	
	@Autowired FestivalDAOImpl dao;	

	@Override
	public FestivalPageDTO getFestivalInfo(int offset, int pageNum, FestivalPageDTO page, int season) {
		return dao.getFestivalInfo(offset, pageNum, page, season);
	}
	@Override
	public HashMap<String, Object> getFestivalDetailInfo(String contentid, int offset, int pageNum) {
		return dao.getFestivalDetailInfo(contentid, offset, pageNum);
	}

}
