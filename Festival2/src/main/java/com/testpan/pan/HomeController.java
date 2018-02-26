package com.testpan.pan;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import festival.dto.FestivalPageDTO;
import festival.service.FestivalService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired private FestivalService service;
	@Autowired private FestivalPageDTO page;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = { "/", "/index"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		model.addAttribute("dto",service.getFestivalInfo(8, 1, page, 0));
		return "home";
	}
	
}
