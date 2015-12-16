package com.sttri.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sttri.bean.PageUtil;
import com.sttri.bean.QueryJSON;
import com.sttri.entity.Admin;
import com.sttri.entity.AdminCriteria;
import com.sttri.entity.Menus;
import com.sttri.entity.MenusCriteria;
import com.sttri.entity.RoleMenus;
import com.sttri.entity.RoleMenusCriteria;
import com.sttri.entity.RoleMenusCriteria.Criteria;
import com.sttri.service.IAdminService;
import com.sttri.service.IMenusService;
import com.sttri.service.IRoleMenusService;
import com.sttri.utils.JacksonUtil;

@Controller
@RequestMapping("/menus")
public class MenusController extends BaseController {
	@Autowired
	private IMenusService menusService;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private IRoleMenusService roleMenusService;
	
	@RequestMapping("/toMenus")
	public String toMenus(ModelMap map){
		MenusCriteria criteria = new MenusCriteria();
		criteria.createCriteria().andPidEqualTo(0);
		List<Menus> mList = this.menusService.selectAll(criteria);
		map.addAttribute("mList", mList);
		return "/menus/menus";
	}
	
	@RequestMapping("/list" )
	public String queryAdmin(ModelMap map,@RequestParam(required = false) String page,
			@RequestParam(required = false) String rows,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,HttpServletRequest request,HttpServletResponse response ) {

		int start = Integer.parseInt((page == "0" || page == null) ? "1":page);
		int limitSize = Integer.parseInt((rows == "0" || rows == null) ? "10":rows);
		map.addAttribute("start", start);
		map.addAttribute("limitSize", limitSize);
		map.addAttribute("startTime", startTime);
		map.addAttribute("endTime", endTime);
		try {
			PrintWriter pw = response.getWriter();
			PageUtil<Menus> pUtil = this.menusService.getMenusByPage(limitSize, start,name, startTime, endTime);
			if(pUtil != null && pUtil.getDatas().size() > 0){
				QueryJSON  qu = new QueryJSON();
				qu.setTotal(pUtil.getTotalCount());
				qu.setRows(pUtil.getDatas());
				System.out.println(JacksonUtil.objectToJson(qu));
				pw.print(JacksonUtil.objectToJson(qu));
			}else{
				String json = "{\"total\":1,\"rows\":[{\"name\":\"无记录数据\"}]}";
				pw.print(json);
			}
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param map
	 * @param admin
	 * @return 1-成功 2-账号已存在 ，其他-失败
	 */
	@RequestMapping("/save")
	public void save(ModelMap map,@ModelAttribute Menus menus,HttpServletRequest request,HttpServletResponse response){
		int result = -1;
		try {
			PrintWriter pw = response.getWriter();
			MenusCriteria menusCriteria = new MenusCriteria();
			com.sttri.entity.MenusCriteria.Criteria criteria = menusCriteria.createCriteria();
			criteria.andNameEqualTo(menus.getName());
			criteria.andPidEqualTo(menus.getPid());
			List<Menus> list = this.menusService.selectAll(menusCriteria);
			if (list != null && list.size() > 0 ) {
				result = 2;
			}else {
				result = this.menusService.save(menus);
			}
			pw.print(result);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param map
	 * @param admin
	 * @return 1-成功 2-账号不存在 ，其他-失败
	 */
	@RequestMapping("/update")
	public void update(ModelMap map,@ModelAttribute Menus menus,HttpServletRequest request,HttpServletResponse response){
		int result = 0;
		try {
			PrintWriter pw = response.getWriter();
			int id = menus.getId();
			Menus oMenus = this.menusService.queryMenusById(id);
			if (oMenus != null) {
				menus.setAddtime(oMenus.getAddtime());
				menus.setEdittime(new Date());
				result = this.menusService.update(menus);
			}else {
				result = 2;
			}
			pw.print(result);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据ID查询
	 * @param map
	 * @param id
	 * @return
	 */
	@RequestMapping("getbyid")
	public void getbyid(ModelMap map,@RequestParam String id,HttpServletRequest request,HttpServletResponse response){
		try {
			PrintWriter pw = response.getWriter();
			Menus menus = this.menusService.queryMenusById(Integer.parseInt(id));
			pw.print(JacksonUtil.objectToJson(menus));
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除账号
	 * @param map
	 * @param id
	 * @return 0-失败 1-成功 ，其他-失败
	 */
	@RequestMapping("/delete")
	public void delete(ModelMap map,@RequestParam(required=true) String id,HttpServletRequest request,HttpServletResponse response){
		int result = 0;
		try {
			result = this.menusService.delete(Integer.parseInt(id));
			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/toPermission")
	public String toPermission(ModelMap map,HttpServletRequest request,HttpServletResponse response){
		try {
			String account = getLoginAccount(request, response);
			map.addAttribute("Account", account);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "/menus/permission";
	}
	
	@RequestMapping("/getAdmin")
	public void getAdmin(ModelMap map,HttpServletRequest request,HttpServletResponse response){
		try {
			List<Admin> aList = this.adminService.findAll();
			map.addAttribute("aList", aList);
			JSONArray cArray = new JSONArray();
			if (aList != null && aList.size() > 0) {
				for (int i = 0; i < aList.size(); i++) {
					JSONObject object = new JSONObject();
					object.put("id", aList.get(i).getId());
					object.put("text", aList.get(i).getAccount());
					object.put("url", request.getContextPath()+"/menus/getMenu?user="+aList.get(i).getAccount());
					cArray.add(object);
				}
			}
			JSONObject object= new JSONObject();
			object.put("id", "1");
			object.put("text", "管理员列表");
			object.put("children", cArray);
			JSONArray array = new JSONArray();
			array.add(object);
			System.out.println(array.toJSONString());
			PrintWriter pw = response.getWriter();
			pw.print(array.toJSONString());
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取菜单
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
			List<Menus> mList = this.menusService.selectAll(0);//查询所有父节点的菜单
			if (mList != null && mList.size() >0 ) {
				for (int i = 0; i < mList.size(); i++) {
					int id = mList.get(i).getId();
					List<Menus> cList = this.menusService.selectAll(id);//查询父节点为id的所有菜单
					JSONObject fObject = new JSONObject();
					JSONArray cArray = new JSONArray();
					if (cList != null && cList.size() > 0) {
						for (int j = 0; j < cList.size(); j++) {
							JSONObject cObject = new JSONObject();
							cObject.put("id", cList.get(j).getId());
							cObject.put("text", cList.get(j).getName());
							cObject.put("url", cList.get(j).getUrl());
							cObject.put("aid", aid);
							if (isRoleMenued(aid,cList.get(j).getId())) {
								cObject.put("checked", true);
							}
							cArray.add(cObject);
						}
					}
					fObject.put("id", mList.get(i).getId());
					fObject.put("text", mList.get(i).getName());
					fObject.put("aid", aid);
					if (isRoleMenued(aid,mList.get(i).getId())) {
						fObject.put("checked", true);
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
			object.put("aid", aid);
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
	/**
	 * 验证该aid用户下是否分配了mid菜单
	 * @param aid
	 * @param mid
	 * @return
	 */
	public boolean isRoleMenued(int aid,int mid){
		boolean flag = false;
		try {
			RoleMenusCriteria roleMenusCriteria = new RoleMenusCriteria();
			Criteria criteria = roleMenusCriteria.createCriteria();
			criteria.andAidEqualTo(aid);
			criteria.andMidEqualTo(mid);
			List<RoleMenus> rList = this.roleMenusService.selectAll(roleMenusCriteria);
			if (rList != null && rList.size() >0) {
				flag = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return flag;
	}
	
	@RequestMapping("/saveMenus")
	public void saveMenus(ModelMap map,HttpServletRequest request,HttpServletResponse response) {
		int result = -1;
		try {
			PrintWriter pw = response.getWriter();
			int aid = Integer.parseInt(request.getParameter("aid"));
			String menus = request.getParameter("menus");
			if (!StringUtils.isEmpty(menus)) {
				String[] arry = menus.split(",");
				for (int i = 0; i < arry.length; i++) {
					int mid = Integer.parseInt(arry[i]);
					//未分配的则进行保存数据库
					if (!isRoleMenued(aid, mid)) {
						RoleMenus roleMenus = new RoleMenus();
						roleMenus.setAid(aid);
						roleMenus.setMid(mid);
						result = this.roleMenusService.save(roleMenus);
					}else {
						List<RoleMenus> list = this.roleMenusService.selectAll(aid);
						if (list != null && list.size() > 0) {
							for (int j = 0; j < list.size(); j++) {
								if (mid != list.get(j).getMid()) {
									result = this.roleMenusService.delete(list.get(j).getId());
								}
							}
						}else {
							result = 1;
						}
					}
				}
			}else {
				List<RoleMenus> list = this.roleMenusService.selectAll(aid);
				if (list != null && list.size() > 0) {
					for (int j = 0; j < list.size(); j++) {
						result = this.roleMenusService.delete(list.get(j).getId());
					}
				}else {
					result = 1;
				}
			}
			if (result == 1) {
				pw.print("SUCCESS");
			}else {
				pw.print("FALSE");
			}
			pw.flush();
			pw.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
