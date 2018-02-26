package festival.dto;

import java.util.ArrayList;

import common.dto.PageDTO;

public class FestivalPageDTO extends PageDTO{
	private ArrayList<FestivalDTO> listItem;
//	private int season;
//
//	
//	public int getSeason() {
//		return season;
//	}
//
//	public void setSeason(int season) {
//		this.season = season;
//	}

	public ArrayList<FestivalDTO> getListItem() {
		return listItem;
	}

	public void setListItem(ArrayList<FestivalDTO> listItem) {
		this.listItem = listItem;
	}
	
}
