package com.testpan.pan;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import board.dto.BoardDTO;
import board.service.BoardService;
import notice.service.NoticeService;



@Controller
public class BoardController {
	//공지글 글쓰기 화면 요청
	@RequestMapping("/new.bo")
	public String list(){
		return "board/new";
	}
	@Autowired private BoardService boardservice;
	//문의게시판 페이지 넘어가기
	@RequestMapping("/board")
	public String board(){
		return "board/list";
	}
	//목록 조회 
	@RequestMapping("/select.an")
	public String list(Model model){
		List<BoardDTO> list = boardservice.list();
		System.out.println(list.toString());
		Gson gson = new Gson();
		String jsonData = gson.toJson(list);
		model.addAttribute("list", jsonData);
		return "/list";
	}
}
