package com.testpan.pan;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import comment.dto.CommentDTO;
import comment.service.CommentService;
import member.dto.MemberDTO;

@Controller
public class BoardCommentController {
	@Autowired @Qualifier("board") private CommentService service;
	
	@ResponseBody @RequestMapping("/board/comments/delete/{id}")
	public void delete(@PathVariable int id){
		service.delete(id);
	}

	@RequestMapping( value="/board/comments/update", produces="application/text; charset=utf-8")
	@ResponseBody
	public String update(@RequestBody CommentDTO dto){
		return service.update(dto) ? "성공" : "실패";
	}

	@RequestMapping("/board/comments/{pid}") //PathVariable
	public String list(Model model, @PathVariable int pid){
		model.addAttribute("list", service.list(pid));
		model.addAttribute("enter1", "\n");
		model.addAttribute("enter2", "\r\n");
		return "board/comments/list";
	}

	@RequestMapping("/board/comments/insert")
	@ResponseBody
	public String insert(HttpSession session, @RequestParam int pid, @RequestParam String content){
		CommentDTO dto = new CommentDTO();
		dto.setPid(pid);
		dto.setContent(content);
		MemberDTO member = (MemberDTO)session.getAttribute("login_info");
		dto.setUserid(member.getU_email());
		return service.insert(dto) ? "success" : "fail";
		
	}

}
