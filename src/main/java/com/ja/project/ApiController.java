package com.ja.project;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
	@RequestMapping(value = "/api/ncp", method = RequestMethod.GET)
	public Map<String, Object> request(Model model, HttpServletRequest request) throws IOException {
		System.out.println("@RestController의 request메소드");
		Date s = new Date();
		String n = request.getParameter("n");
		Basic bs = new Basic(Integer.parseInt(n));
		  
		Map<String, Object> result = bs.basicClickDom();
		Date e = new Date();
		Long interval = e.getTime() - s.getTime();
		System.out.println(interval/1000);
		    
		return result;
//		return bs.requestTEST("B");
		
	}
	
}
