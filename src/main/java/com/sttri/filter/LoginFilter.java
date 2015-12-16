package com.sttri.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sttri.entity.Admin;

public class LoginFilter  implements Filter {
	
	// 日志记录器
	public final Logger log = Logger.getLogger(this.getClass());
	
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		 	HttpServletRequest request=(HttpServletRequest)arg0;   
		   HttpServletResponse response  =(HttpServletResponse) arg1;    
		   Admin admin = (Admin)request.getSession().getAttribute("user");
		   String url=request.getRequestURI();   
		   if(admin == null) {    
	            //判断获取的路径不为空且不是访问登录页面或执行登录操作时跳转   
	            if(url!=null && !url.equals("") && ( url.indexOf("Login")<0 && url.indexOf("login")<0 ) ) {   
	                response.sendRedirect(request.getContextPath() + "/login.html");   
	                return ;   
	            }              
	        }   
		   chain.doFilter(arg0, arg1);   
          return;
	}
	
	public void destroy() {
		//TODO
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
	
}