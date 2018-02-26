package com.testpan.pan;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import festival.dto.FestivalDTO;
import festival.service.FestivalService;

@Controller

public class DetailController {
	
	@Autowired private FestivalService service;
	
	@RequestMapping("detail.fa")
	public String detail(Model model, @RequestParam String contentId,@RequestParam String title,@RequestParam String image,@RequestParam String term,@RequestParam String addr,@RequestParam String mapx,@RequestParam String mapy){
		FestivalDTO dto1 = new FestivalDTO(contentId, addr, image, mapx, mapy, title, term);
		HashMap<String, Object> datas = service.getFestivalDetailInfo(contentId, 1, 1);
		model.addAttribute("dto1", dto1);
		model.addAttribute("img_list", datas.get("img_list") );
		model.addAttribute("dto2", datas.get("dto2") );
		model.addAttribute("enter1", "\r\n");
		model.addAttribute("enter2", "\n");
		model.addAttribute("enter3", "<br />");
		return "festival_detail";
	}
	
}
