package com.example;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.domain.MyException;

@ControllerAdvice
public class MyControlerAdvice {

	@ExceptionHandler({ MyException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ModelAndView handleMyException(Exception e, WebRequest req) {
	    System.out.println("MyControllerAdvice#handleMyException");
	    ModelAndView mav = new ModelAndView();
	    mav.addObject("myerror", "MyControllerAdvice#handleMyException");
	    mav.setViewName("myview");
	    return mav;
	  }

}
