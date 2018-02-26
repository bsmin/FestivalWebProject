package com.testpan.pan;


import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import files.dto.FilesDTO;
import files.service.FilesService;
import member.dto.MemberDTO;
import member.service.MemberService;

@Controller
public class MemberController {
	// 주입: inject AotoWired

	@Autowired private MemberService members;
	@Autowired private FilesService files;
	
	
	
	// 이메일 유효성 검사
	@RequestMapping("/checkemail.mem")
	public String emailcheck(@RequestParam String u_email, Model model) {
		String checkEmail;
		checkEmail = members.emailcheck(u_email);
		if(checkEmail == null || checkEmail.equals("")) {
			checkEmail = "check";
		}else{
			checkEmail = "using";
		}
		Gson gson = new Gson();
		JsonObject object = new JsonObject();
		object.addProperty("checkEmail", checkEmail);
		String jsonData = gson.toJson(object);
		model.addAttribute("checkEmail", jsonData);
		System.out.println(jsonData);
		return "mperson/insert";
	}
	
	//이메일 인증 및 이메일 찾기 요청
	@RequestMapping(value = "/auth.mem")
    public String sendMailAuth(@RequestParam String u_email, Model model) {
        int ran = new Random().nextInt(100000) + 10000; // 10000 ~ 99999
        String joinCode = String.valueOf(ran);
        String subject = "회원가입 인증 코드 발급 안내 입니다.";
        StringBuilder sb = new StringBuilder();
        sb.append("귀하의 인증 코드는 " + joinCode + " 입니다.");
        members.emailsend(subject, sb.toString(), "newbizone@gmail.com", u_email, null);
        Gson gson = new Gson();
		JsonObject object = new JsonObject();
		object.addProperty("auth", joinCode);
		String jsonData = gson.toJson(object);
		model.addAttribute("code", jsonData);
		System.out.println(jsonData);
		return "mperson/insert";  
    }



	//신규고객등록처리요청
	@RequestMapping("/join.mem")
	public String insert(MemberDTO dto, Model model) {
		Boolean joinck;
		joinck = members.insert(dto);
		if(joinck == true) {
			System.out.println(dto.getU_email());
			MemberDTO memberDTO = members.select(dto.getU_email());
			System.out.println(memberDTO.toString());
			Gson gson = new Gson();
			String jsonData = gson.toJson(memberDTO);
			model.addAttribute("joinD", jsonData);
			
		}else {
			model.addAttribute("joinD", "fial");
		}
		return "mperson/insert";
	}
	
	
	//로그인처리 요청
		@RequestMapping("/login.mem")	
		public String login(Model model, @RequestParam String u_email, @RequestParam String u_pwd){
			try {
				MemberDTO memberDTO = members.select(u_email, u_pwd);
				System.out.println(memberDTO.toString());
				Gson gson = new Gson();
				String jsonData = gson.toJson(memberDTO);
				model.addAttribute("joinL", jsonData);
				return "mperson/insert";
			}catch (Exception e) {
				MemberDTO memberDTO = new MemberDTO();
				System.out.println(memberDTO);
				Gson gson = new Gson();
				String jsonData = gson.toJson(memberDTO);
				model.addAttribute("joinL", jsonData);
				return "mperson/insert";	
			}
			
		}
	
		
		//임시비밀번호발송 이메일 인증
		@RequestMapping("/pwdauth.mem")
	    public String sendPwdAuth(@RequestParam String u_email, Model model) {
	        String pwdran = String.valueOf(new Random().nextInt(100000) + 10000); // 10000 ~ 99999
	        Boolean pwdup = members.pwdUpdate(u_email, pwdran);
	        if(pwdup == true) {
	        String subject = "임시 비밀번호 발급 안내 입니다.";
	        StringBuilder sb = new StringBuilder();
	        sb.append("귀하의 임시비밀번호는 " + pwdran + " 입니다.");
	        members.emailsend(subject, sb.toString(), "newbizone@gmail.com", u_email, null);
	        }else {
	        	pwdran = "fail";
	        }
	        Gson gson = new Gson();
			JsonObject object = new JsonObject();
			object.addProperty("pwdauth", pwdran);
			String jsonData = gson.toJson(object);
			model.addAttribute("pwdA", jsonData);
			System.out.println(jsonData);
			return "mperson/insert";  
	    }
		//회원 정보 수정 요청
		@RequestMapping("/memberupdate.mem")
		public String memberupdate (MemberDTO dto, Model model){
			Boolean updateck;
		
			updateck = members.update(dto);
			
			if(updateck == true) {
				System.out.println(dto.getU_email());
				MemberDTO memberDTO = members.select(dto.getU_email());
				System.out.println(memberDTO.toString());
				Gson gson = new Gson();
				String jsonData = gson.toJson(memberDTO);
				model.addAttribute("updateD", jsonData);
				
			}else {
				model.addAttribute("updateD", "fial");
			}
			return "mperson/insert";
		}
		//회원 비밀번호 수정요청
		@RequestMapping("/updatepwd.mem")
		public String updatepwd (MemberDTO dto, Model model){
			Boolean updatepwdck;
		
			updatepwdck = members.updatepwd(dto);
			
			if(updatepwdck == true) {
				System.out.println(dto.getU_email());
				MemberDTO memberDTO = members.select(dto.getU_email());
				System.out.println(memberDTO.toString());
				Gson gson = new Gson();
				String jsonData = gson.toJson(memberDTO);
				model.addAttribute("updatepwdD", jsonData);
				
			}else {
				model.addAttribute("updatepwdD", "fial");
			}
			return "mperson/insert";
		}
		
		@RequestMapping("/update.mem")
		public String update(FilesDTO dto, @RequestParam MultipartFile file, HttpSession session){
			dto.setF_path("");
			dto.setF_name("");
			if( file.getSize()>0 ){
				dto.setF_name(file.getOriginalFilename());
				dto.setF_path(files.upload(file, "notice", session));
			}
			files.update(dto);
			return "mperson/insert";
		}
}