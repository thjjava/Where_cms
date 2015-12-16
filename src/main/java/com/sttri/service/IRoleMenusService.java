package com.sttri.service;

import java.util.List;

import com.sttri.bean.PageUtil;
import com.sttri.entity.RoleMenus;
import com.sttri.entity.RoleMenusCriteria;


public interface IRoleMenusService {

	/**
	 * 根据主键ID查询
	 * @param id
	 * @return
	 */
	public RoleMenus queryRoleMenusById(Integer id);
	
	/**
	 * 查询所有
	 * @param id
	 * @return
	 */
	public List<RoleMenus> findAll();
	
	/**
	 * 动态添加条件查询
	 * @param id
	 * @return
	 */
	public List<RoleMenus> selectAll(RoleMenusCriteria criteria);
	
	/**
	 * 根据aid查询
	 * @param id
	 * @return
	 */
	public List<RoleMenus> selectAll(Integer aId);
	
	/**
	 * 添加
	 * @param id
	 * @return
	 */
	public int save(RoleMenus roleMenus);
	
	/**
	 * 更新
	 * @param id
	 * @return
	 */
	public int update(RoleMenus roleMenus);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	/**
	 * 按分页查询
	 * @param id
	 * @return
	 */
	public PageUtil<RoleMenus> getRoleMenusByPage(int limitSize, int start, String startTime, String endTime);
	
	public List<RoleMenus> queryMenus(Integer aid);
}
