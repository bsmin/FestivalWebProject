package festival.service;

import java.util.ArrayList;
import java.util.HashMap;

import festival.dto.FestivalDTO;
import festival.dto.FestivalPageDTO;

public interface FestivalService {
	FestivalPageDTO getFestivalInfo(int offset, int pageNum, FestivalPageDTO page, int season);

	HashMap<String, Object> getFestivalDetailInfo(String contentid, int offset, int pageNum);
}
