package com.testpan.pan;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import common.dto.PageDTO;
import festival.dto.FestivalPageDTO;
import festival.service.FestivalService;

@Controller
@SessionAttributes("festival_info")
public class SeasonController {

	@Autowired private FestivalService service;
	@Autowired private FestivalPageDTO page;
	
	@RequestMapping("/season")
	public String info(HttpSession session, Model model,@RequestParam(defaultValue="1") int curPage ,@RequestParam(defaultValue="0") int season){
		FestivalPageDTO dto = service.getFestivalInfo(6, curPage, page, season);
		session.setAttribute("festival_info", dto);
		model.addAttribute("dto", dto);
		model.addAttribute("season", season);
		return "season";
	}
	
}
