package com.sttri.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sttri.bean.PageUtil;
import com.sttri.dao.FileMapper;
import com.sttri.entity.File;
import com.sttri.entity.FileCriteria;
import com.sttri.entity.FileCriteria.Criteria;
import com.sttri.service.IFileService;

@Service
public class FileServiceImpl implements IFileService {
	@Autowired
	private FileMapper mapper;
	
	@Override
	public File queryFileById(Integer id) {
		// TODO Auto-generated method stub
		FileCriteria FileCriteria = new FileCriteria();
		Criteria criteria = FileCriteria.createCriteria();
		criteria.andIdEqualTo(id);
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public File queryFileByName(String name) {
		// TODO Auto-generated method stub
		FileCriteria FileCriteria = new FileCriteria();
		Criteria criteria = FileCriteria.createCriteria();
		criteria.andFilenameEqualTo(name);
		List<File> aList = mapper.selectByExample(FileCriteria);
		if (aList != null && aList.size() > 0) {
			return aList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<File> findAll() {
		// TODO Auto-generated method stub
		FileCriteria FileCriteria = new FileCriteria();
		FileCriteria.createCriteria();
		return mapper.selectByExample(FileCriteria);
	}

	@Override
	public List<File> selectAll(FileCriteria criteria) {
		// TODO Auto-generated method stub
		return mapper.selectByExample(criteria);
	}

	@Override
	public int save(File File) {
		// TODO Auto-generated method stub
		return mapper.insert(File);
	}

	@Override
	public int update(File File) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(File);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public PageUtil<File> getFileByPage(int limitSize, int start,String name,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		FileCriteria example = new FileCriteria();
		example.setLimitSize(limitSize);
		example.setLimitStart(start);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(name)) {
			criteria.andFilenameEqualTo(name);
		}
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.isNotEmpty(startTime)) {
			try {
				criteria.andUploadtimeGreaterThan(sim.parse(startTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(endTime)) {
			try {
				criteria.andUploadtimeLessThan(sim.parse(endTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int totalCount = mapper.countByExample(example);
		PageUtil<File> pageUtil = new PageUtil<File>(limitSize, totalCount, start);
		List<File> list = new ArrayList<File>();
		if (totalCount > 0) {
			example.setOrderByClause("id desc");
			example.setLimitStart((start - 1) * limitSize);
			example.setLimitSize(limitSize);
			list = mapper.selectByExample(example);
			for (File log : list) {
				log.setUploadtime(log.getUploadtime());
			}
		}
		pageUtil.setDatas(list);
		return pageUtil;
	}

}
