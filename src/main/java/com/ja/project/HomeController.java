package com.ja.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@RequestMapping(value = "/busan", method = RequestMethod.GET)
	public String busan(Locale locale, Model model) {
		return "busan";
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test(Locale locale, Model model) {
	}
	
	@RequestMapping(value = "/busan_search", method = RequestMethod.GET)
	public String busan_search(@RequestParam("serverGroup")int serverGroup, @RequestParam("manager")String manager, Model model, HttpServletRequest request) throws IOException {
		Date s = new Date();
		Basic bs = new Basic(serverGroup);
		Map<String, Object> result = bs.busanNews();
		Date e = new Date();
		Long interval = e.getTime() - s.getTime();
		System.out.println(interval/1000);
		
		
		model.addAttribute("manager", manager);
		model.addAttribute("result", result);
		return "searchForBusan";
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public void search(@RequestParam("serverGroup")int serverGroup, @RequestParam("manager")String manager, Model model, HttpServletRequest request) throws IOException {
		System.out.println("HomeController의 search메소드");
		Date s = new Date();
		Basic bs = new Basic(serverGroup);
		Map<String, Object> result = bs.basicClickDom();
		Date e = new Date();
		Long interval = e.getTime() - s.getTime();
		System.out.println(interval/1000);
		
		
		model.addAttribute("manager", manager);
		model.addAttribute("result", result);
		
	}
	
	@RequestMapping(value = "/search_v1", method = RequestMethod.GET)
	public void search_v1(@RequestParam("serverGroup")int serverGroup, Model model, HttpServletRequest request) throws IOException {
		System.out.println("HomeController의 search메소드");
		Date s = new Date();
		Basic bs = new Basic(serverGroup);
		Map<String, Object> result = bs.basicClickDom();
//		Map<String, Object> result = bs.requestTEST("B");
		Date e = new Date();
		Long interval = e.getTime() - s.getTime();
		System.out.println(interval/1000);
		
		model.addAttribute("result", result);
		
		System.out.println("reqeust.getContextPath()" + request.getContextPath());
		System.out.println("reqeust.getRequestURI()" + request.getRequestURI());
		System.out.println("reqeust.getRequestURL()" + request.getRequestURL());
	}
	
	@RequestMapping(value = "/search_v3", method = RequestMethod.GET)
	public void search_v3(@RequestParam("serverGroup")int serverGroup, Model model, HttpServletRequest request) throws IOException {
		System.out.println("HomeController의 search메소드");
		Date s = new Date();
		Basic bs = new Basic(serverGroup);
		Map<String, Object> result = bs.basicClickDom();
//		Map<String, Object> result = bs.requestTEST("B");
		Date e = new Date();
		Long interval = e.getTime() - s.getTime();
		System.out.println(interval/1000);
		
		model.addAttribute("result", result);
	}
	
	@RequestMapping(value = "/search_v4", method = RequestMethod.GET)
	public void search_v4(@RequestParam("serverGroup")int serverGroup, @RequestParam("manager")String manager, Model model, HttpServletRequest request) throws IOException {
		System.out.println("HomeController의 search메소드");
		Date s = new Date();
		Basic bs = new Basic(serverGroup);
		Map<String, Object> result = bs.basicClickDom();
//		Map<String, Object> result = bs.requestTEST("B");
		Date e = new Date();
		Long interval = e.getTime() - s.getTime();
		System.out.println(interval/1000);
		
		
		model.addAttribute("manager", manager);
		model.addAttribute("result", result);
		
	}
}
