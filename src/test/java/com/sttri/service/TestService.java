//package com.sttri.service;
//
//import java.util.List;
//
//import org.apache.log4j.Logger;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.alibaba.fastjson.JSON;
//import com.sttri.entity.Admin;
//import com.sttri.entity.AdminCriteria;
//import com.sttri.entity.Menus;
//import com.sttri.entity.RoleMenus;
//import com.sttri.entity.RoleMenusCriteria;
//import com.sttri.entity.RoleMenusCriteria.Criteria;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring.xml",
//		"classpath:spring-mybatis.xml" })
//public class TestService {
//
//	@Autowired
//	private IAdminService adminService;
//	@Autowired
//	private IRoleMenusService roleMenusService;
//	
//	/*@Test
//	public void saveAdmin(){
//		Admin admin = new Admin();
//		admin.setAccount("admin1");
//		admin.setPassword("admin1");
//		admin.setAddtime(new Date());
//		admin.setCity("上海");
//		admin.setEmail("admin@admin.com");
//		admin.setLevel(0);
//		admin.setSex(0);
//		admin.setStatus(0);
//		admin.setSummary("超级管理员");
//		admin.setTelephone("12345678901");
//		int result = this.adminService.save(admin);
//		System.out.println("测试新增管理员结果:"+result);
//	}*/
//
//	/*@Test
//	public void queryAdminById(){
//		Admin admin = this.adminService.queryAdminById(1);
//		System.out.println(admin.getAccount());
//	}*/
//	
//	/*@Test
//	public void queryAdminAll(){
//		AdminCriteria criteria = new AdminCriteria();
//		criteria.createCriteria().andIdEqualTo(1);
//		List<Admin> list = this.adminService.selectAll(criteria);
//		System.out.println(list.size());
//	}*/
//	
////	@Test
////	public void deleteAdmin(){
////		int result = this.adminService.delete(2);
////		System.out.println(result);
////	}
//	
//	/*@Test
//	public void queryRoleMenus(){
//		List<RoleMenus> rList = this.roleMenusService.queryMenus(1);
//		System.out.println(JSON.toJSON(rList.toString()));
//		System.out.println(rList.size());
//		for (int i = 0; i < rList.size(); i++) {
//			List<Menus> mList = rList.get(i).getMenus();
//			System.out.println(mList.size());
//		}
//		List<Menus> mList = rList.get(0).getMenus();
//		System.out.println(JSON.toJSON(mList.toString()));
//		
//	}*/
//	
//	@Test
//	public void queryRoleMenusByMid(){
//		RoleMenusCriteria roleMenusCriteria = new RoleMenusCriteria();
//		Criteria criteria = roleMenusCriteria.createCriteria();
//		criteria.andAidEqualTo(1);
//		criteria.andMidEqualTo(1);
//		List<RoleMenus> rList = this.roleMenusService.selectAll(roleMenusCriteria);
//		System.out.println(JSON.toJSON(rList.toString()));
//	}
//}
