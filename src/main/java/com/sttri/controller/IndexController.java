package com.sttri.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sttri.entity.Admin;
import com.sttri.entity.AdminCriteria;
import com.sttri.entity.RoleMenus;
import com.sttri.entity.AdminCriteria.Criteria;
import com.sttri.entity.Menus;
import com.sttri.service.IAdminService;
import com.sttri.service.IMenusService;
import com.sttri.service.IRoleMenusService;
import com.sttri.utils.CookiesUtil;

@Controller
public class IndexController {
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IMenusService menusService;
	@Autowired
	private IRoleMenusService roleMenusService;

	@RequestMapping("/")
	public String index(ModelMap map,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String error){
		System.out.println("login.error:"+error);
		Cookie[] cookies= request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				cookie.setValue("");
				response.addCookie(cookie);
			}
		}
		map.addAttribute("error", error);
		return "/login";
	}
	
	@RequestMapping("/login")
	public String login(ModelMap map,HttpServletRequest request,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		try {
			if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
				map.addAttribute("error", "账号和密码不能为空!");
				return "redirect:/";
			}
			AdminCriteria adminCriteria = new AdminCriteria();
			Criteria criteria = adminCriteria.createCriteria();
			criteria.andAccountEqualTo(account);
			criteria.andPasswordEqualTo(password);
			List<Admin> aList = this.adminService.selectAll(adminCriteria);
			if (aList != null && aList.size() > 0) {
				CookiesUtil.setString("Account", account, request, response);
				map.addAttribute("Account", account);
				HttpSession session = request.getSession();
				session.setAttribute("user", aList.get(0));
//				session.setMaxInactiveInterval(30);//30s
				return "/index"; 
			}else {
				map.addAttribute("error", "请输入正确的账号或密码!");
				return "redirect:/"; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/"; 
	}
	
	@RequestMapping("/logout")
	public String logout(ModelMap map,HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String error){
		System.out.println("logout");
		Cookie[] cookies= request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			cookie.setValue("");
			response.addCookie(cookie);
		}
		map.addAttribute("error", error);
		return "/login";
	}
	
	/**
	 * 获取当前登录用户的菜单
	 * @param map
	 * @param user
	 * @param request
	 * @param response
	 */
	@RequestMapping("/getMenu")
	public void getMenu(ModelMap map,@RequestParam String user,HttpServletRequest request,HttpServletResponse response){
		int aid =0;
		try {
			PrintWriter pw = response.getWriter();
			if (!StringUtils.isEmpty(user)) {
				AdminCriteria criteria = new AdminCriteria();
				criteria.createCriteria().andAccountEqualTo(user);
				List<Admin> list = this.adminService.selectAll(criteria);
				if (list != null && list.size()>0) {
					aid = list.get(0).getId();
				}
			}
			JSONArray fArray = new JSONArray();
			List<RoleMenus> rList = this.roleMenusService.queryMenus(aid);
			if (rList != null && rList.size() > 0) {
				for (int i = 0; i < rList.size(); i++) {//循环遍历该aid下分配的菜单
					List<Menus> fList = rList.get(i).getMenus();//获取某条记录下的菜单信息，fList.size()=1
					int fid = fList.get(0).getId();
					int fpid = fList.get(0).getPid();
					JSONObject fObject = new JSONObject();
					if (fpid == 0) {
						fObject.put("id", fid);
						fObject.put("text", fList.get(0).getName());
					}else {
						continue;
					}
					JSONArray cArray = new JSONArray();
					for (int j = 0; j < rList.size(); j++) {
						List<Menus> cList = rList.get(j).getMenus();
						int cid = cList.get(0).getId();
						int cpid = cList.get(0).getPid();
						if (cpid != 0 && fid == cpid) {
							JSONObject cObject = new JSONObject();
							cObject.put("id", cid);
							cObject.put("text", cList.get(0).getName());
							cObject.put("url", cList.get(0).getUrl());
							cArray.add(cObject);
						}
					}
					if (cArray.size() > 0) {
						if (i ==0) {
							fObject.put("state", "open");
						}else {
							fObject.put("state", "closed");
						}
						fObject.put("children", cArray);
					}
					fArray.add(fObject);
				}
			}
			JSONObject object= new JSONObject();
			object.put("id", "1");
			object.put("text", "功能菜单");
			object.put("children", fArray);
			JSONArray array = new JSONArray();
			array.add(object);
			System.out.println(array.toJSONString());
			pw.print(array.toJSONString());
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
