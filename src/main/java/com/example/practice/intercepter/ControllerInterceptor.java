package com.example.practice.intercepter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Slf4j
public class ControllerInterceptor implements HandlerInterceptor {
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String path = request.getServletPath();

		// logging
		log.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ controller interceptor preHandle START");
		log.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ path : {}", path);
		if (handler instanceof HandlerMethod) {
			log.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ handler : {}", (HandlerMethod) handler);
		}

		return true;
	}

    
}
