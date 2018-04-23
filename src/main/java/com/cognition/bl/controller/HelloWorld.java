package com.cognition.bl.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cognition.bl.domain.SalesOrder;

@RestController
public class HelloWorld {
	
//	@Autowired
//	EmpRepository empRepository;

	@RequestMapping("/hello")
	public String hello(){
//		System.out.println(empRepository);
//		List<SalesOrder> list = empRepository.queryName("test01");
		return "hello world";
	}
	
	@RequestMapping("/hello2")
	public Map<String, String> hello1( String name){
		Map<String, String> map = new HashMap<>();
		map.put("hello", " world "+name);
		return map;
	}
	
	@RequestMapping("/update")
	public Map<String, String> update( String name){
		Map<String, String> map = new HashMap<>();
		String names =null;
		names.toString();
		map.put("hello", " world "+name);
		return map;
	}
	
	
	@ExceptionHandler(value=java.lang.ArithmeticException.class)
	public ModelAndView handlerArithexception(Exception e){
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception",e.toString());
		mv.setViewName("error1");
		return mv;
		
	}
	
	
	@ExceptionHandler(value=java.lang.NullPointerException.class)
	public ModelAndView handlerNulloint(Exception e){
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception",e.toString());
		mv.setViewName("error2");
		return mv;
		
	}
	
	
}
