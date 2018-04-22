package com.cognition.bl.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HelloWorld {

	@RequestMapping("/hello")
	public String hello(){
		
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
