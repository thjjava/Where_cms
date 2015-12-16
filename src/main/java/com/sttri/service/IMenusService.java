package com.sttri.service;

import java.util.List;

import com.sttri.bean.PageUtil;
import com.sttri.entity.Menus;
import com.sttri.entity.MenusCriteria;


public interface IMenusService {

	/**
	 * 根据主键ID查询
	 * @param id
	 * @return
	 */
	public Menus queryMenusById(Integer id);
	
	/**
	 * 根据账号查询
	 * @param id
	 * @return
	 */
	public Menus queryMenusByAccount(String account);
	
	/**
	 * 查询所有
	 * @param id
	 * @return
	 */
	public List<Menus> findAll();
	
	/**
	 * 动态添加条件查询
	 * @param id
	 * @return
	 */
	public List<Menus> selectAll(MenusCriteria criteria);
	
	/**
	 * 根据pid查询
	 * @param id
	 * @return
	 */
	public List<Menus> selectAll(Integer pid);
	
	/**
	 * 添加
	 * @param id
	 * @return
	 */
	public int save(Menus menus);
	
	/**
	 * 更新
	 * @param id
	 * @return
	 */
	public int update(Menus menus);
	
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
	public PageUtil<Menus> getMenusByPage(int limitSize, int start, String name, String startTime, String endTime);

}
