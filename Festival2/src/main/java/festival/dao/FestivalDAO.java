package festival.dao;

import java.util.HashMap;

import festival.dto.FestivalDTO;
import festival.dto.FestivalPageDTO;

public interface FestivalDAO {
	FestivalPageDTO getFestivalInfo(int offset, int pageNum, FestivalPageDTO page, int season);
	HashMap<String, Object> getFestivalDetailInfo(String contentid, int offset, int pageNum);
}
