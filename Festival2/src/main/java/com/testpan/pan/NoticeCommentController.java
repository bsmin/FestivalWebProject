package com.testpan.pan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import comment.service.CommentService;

@Controller
public class NoticeCommentController {
	@Autowired @Qualifier("notice") private CommentService service;

}
