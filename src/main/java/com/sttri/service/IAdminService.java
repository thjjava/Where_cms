package com.sttri.service;

import java.util.List;

import com.sttri.bean.PageUtil;
import com.sttri.entity.Admin;
import com.sttri.entity.AdminCriteria;

public interface IAdminService {

	/**
	 * 根据主键ID查询管理员
	 * @param id
	 * @return
	 */
	public Admin queryAdminById(Integer id);
	
	/**
	 * 根据账号查询管理员
	 * @param id
	 * @return
	 */
	public Admin queryAdminByAccount(String account);
	
	/**
	 * 查询所有管理员
	 * @param id
	 * @return
	 */
	public List<Admin> findAll();
	
	/**
	 * 动态添加条件查询管理员
	 * @param id
	 * @return
	 */
	public List<Admin> selectAll(AdminCriteria criteria);
	
	/**
	 * 添加管理员
	 * @param id
	 * @return
	 */
	public int save(Admin admin);
	
	/**
	 * 更新管理员
	 * @param id
	 * @return
	 */
	public int update(Admin admin);
	
	/**
	 * 删除管理员
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 按分页查询管理员
	 * @param id
	 * @return
	 */
	public PageUtil<Admin> getAdminByPage(int limitSize, int start,String account, int level, String startTime, String endTime);
}
