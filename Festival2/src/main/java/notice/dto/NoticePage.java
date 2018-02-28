package notice.dto;

import java.util.List;

import common.dto.PageDTO;

public class NoticePage extends PageDTO {
	private List<NoticeDTO> list;

	public List<NoticeDTO> getList() {
		return list;
	}

	public void setList(List<NoticeDTO> list) {
		this.list = list;
	}
	
	
}
