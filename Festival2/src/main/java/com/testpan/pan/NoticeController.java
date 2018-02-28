package com.testpan.pan;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import common.service.CommonService;
import notice.dto.NoticeDTO;
import notice.dto.NoticePage;
import notice.service.NoticeService;

@Controller
@SessionAttributes("category") //어느페이지를 클릭했는디 보여주기위한 속성추가
public class NoticeController {
	@Autowired  private NoticeService notice;
	//공지사항 페이지 넘어가기
	@RequestMapping("/notice")
	public String notice(){
		return "notice/list";
	}
	
	//공지글 삭제처리 요청
	@RequestMapping("/delete.no")
	public String delete(@RequestParam int id){
		notice.delete(id);
		return "redirect:list.no";
	}
	
	//공지글 내용 변경처리 요청
	@RequestMapping("/update.no")
	public String update(NoticeDTO dto, @RequestParam MultipartFile file, HttpSession session){
		dto.setF_path("");
		dto.setF_name("");
		if(file.getSize()>0){
			dto.setF_name(file.getOriginalFilename());
			dto.setF_path(common.upload(file, "notice", session));
			
		}
		notice.update(dto);
		return "redirect:detail.no?id="+dto.getN_id();
	}
	
	//공지글 내용 변경화면 요청
	@RequestMapping("/modify.no")
	public String modify(Model model, @RequestParam int id){
		model.addAttribute("dto", notice.select(id));
		model.addAttribute("page", page);
		return "notice/modify";
	}
	
	//파일 다운로드처리 요청
	@RequestMapping("/download.no")
	@ResponseBody //특정화면으로 연결해 응답하지 않고 파일만 다운로드하는 것으로
	//이 처리 자체가 응답임.
	public File download(@RequestParam String filepath, HttpSession sesstion, 
								@RequestParam String filename, HttpServletResponse response){
		return common.download(filepath, filename, sesstion, response);
		
	}
	
	//공지글 상세내용 화면 요청
	@RequestMapping("/detail.no")
	public String detail(@RequestParam int id, Model model){
		notice.read(id);
		model.addAttribute("detail", notice.select(id));
		model.addAttribute("page", page);
		return "notice/detail";
	}
	
	@Autowired private CommonService common;
	
	//신규공지글 저장처리 요청
	@RequestMapping("/insert.no")
	public String insert(NoticeDTO dto, @RequestParam MultipartFile file , HttpSession session){
		dto.setF_path("");
		dto.setF_name("");
		if(file.getSize() > 0 ){
			dto.setF_path(common.upload(file, "notice", session));
			dto.setF_name(file.getOriginalFilename());
			
		}
		notice.insert(dto);
		return "redirect:list.no";
	}
	
	//공지글 글쓰기 화면 요청
	@RequestMapping("/new.no")
	public String list(){
		return "notice/new";
	}
	
	@Autowired   private NoticePage page;
	
	//공지글 목록 화면 요청
	@RequestMapping("/list.no")
	//파라미터가 꼭 있어야 하는 것은 아니라고 지정 → @RequestParam(required=false)
	//기본값을 지정 → @RequestParam(defaultValue="")
	public String list(Model model, @RequestParam(defaultValue="1") int curPage,
									@RequestParam(required=false) String search,
									@RequestParam(defaultValue="") String keyword){
		
		model.addAttribute("category", "no");
		page.setCurPage(curPage);
		page.setSearch(keyword.isEmpty() ? "" : search);
		page.setKeyword(keyword);
		
		if(keyword.isEmpty()){
		// 검색어가 없으면 전체목록조회이고(List<NoticeDTO> list = notice.list();
		
		// 검색어가 있으면 검색어 목록조회
//		List<NoticeDTO> list = notice.list();
//			model.addAttribute("list", list);
			model.addAttribute("page", notice.list(page));
		}else{
			//검색어가 있으면 검색어 조회
//		 	HashMap<String, String> map = new HashMap<String, String>();
//		 	map.put("search", search);
//		 	map.put("keyword","%"+keyword+"%");
//		 	model.addAttribute("list", notice.list(map));
//		 	model.addAttribute("search",search);
//		 	model.addAttribute("search",keyword);
			page.setKeyword("%"+keyword+"%");
			page = notice.list(page);

			page.setKeyword(keyword);
			model.addAttribute("page", page);
		}
		
		return "notice/list";
	}
}
