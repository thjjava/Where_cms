package com.sttri.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import com.sttri.entity.Admin;
import com.sttri.utils.CookiesUtil;

@Controller
public class BaseController {

	/**
	 * 从seesion或cookie中获取登录用户的账号
	 * @param request
	 * @param response
	 * @return
	 */
	public String getLoginAccount(HttpServletRequest request,HttpServletResponse response){
		//从seesion中获取对象，如果session存在，则根据seesion对象获取account，否则取cookie中保存的account
		HttpSession session = request.getSession();
		Admin oAdmin = (Admin)session.getAttribute("user");
		String account = CookiesUtil.getString("Account", null, request);
		if (oAdmin != null) {
			account = oAdmin.getAccount();
		}
		return account;
	}
	
}
