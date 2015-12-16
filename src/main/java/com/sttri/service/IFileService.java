package com.sttri.service;

import java.util.List;

import com.sttri.bean.PageUtil;
import com.sttri.entity.File;
import com.sttri.entity.FileCriteria;

public interface IFileService {

	/**
	 * 根据主键ID查询
	 * @param id
	 * @return
	 */
	public File queryFileById(Integer id);
	
	/**
	 * 根据账号查询
	 * @param id
	 * @return
	 */
	public File queryFileByName(String filenames);
	
	/**
	 * 查询所有
	 * @param id
	 * @return
	 */
	public List<File> findAll();
	
	/**
	 * 动态添加条件查询
	 * @param id
	 * @return
	 */
	public List<File> selectAll(FileCriteria criteria);
	
	/**
	 * 添加
	 * @param id
	 * @return
	 */
	public int save(File file);
	
	/**
	 * 更新
	 * @param id
	 * @return
	 */
	public int update(File file);
	
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
	public PageUtil<File> getFileByPage(int limitSize, int start,String filename, String startTime, String endTime);
}
