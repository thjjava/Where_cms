package com.sttri.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sttri.bean.PageUtil;
import com.sttri.dao.MenusMapper;
import com.sttri.entity.Menus;
import com.sttri.entity.MenusCriteria;
import com.sttri.entity.MenusCriteria.Criteria;
import com.sttri.service.IMenusService;

@Service
public class MenusServiceImpl implements IMenusService {
	@Autowired
	private MenusMapper mapper;
	
	@Override
	public Menus queryMenusById(Integer id) {
		// TODO Auto-generated method stub
		MenusCriteria menusCriteria = new MenusCriteria();
		Criteria criteria = menusCriteria.createCriteria();
		criteria.andIdEqualTo(id);
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public Menus queryMenusByAccount(String name) {
		// TODO Auto-generated method stub
		MenusCriteria menusCriteria = new MenusCriteria();
		Criteria criteria = menusCriteria.createCriteria();
		criteria.andNameEqualTo(name);
		List<Menus> aList = mapper.selectByExample(menusCriteria);
		if (aList != null && aList.size() > 0) {
			return aList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<Menus> findAll() {
		// TODO Auto-generated method stub
		MenusCriteria menusCriteria = new MenusCriteria();
		menusCriteria.createCriteria();
		return mapper.selectByExample(menusCriteria);
	}

	@Override
	public List<Menus> selectAll(MenusCriteria criteria) {
		// TODO Auto-generated method stub
		return mapper.selectByExample(criteria);
	}
	
	@Override
	public List<Menus> selectAll(Integer pid) {
		// TODO Auto-generated method stub
		MenusCriteria criteria = new MenusCriteria();
		criteria.createCriteria().andPidEqualTo(pid);
		return mapper.selectByExample(criteria);
	}

	@Override
	public int save(Menus menus) {
		// TODO Auto-generated method stub
		return mapper.insert(menus);
	}

	@Override
	public int update(Menus menus) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(menus);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public PageUtil<Menus> getMenusByPage(int limitSize, int start,String name, String startTime, String endTime) {
		// TODO Auto-generated method stub
		MenusCriteria example = new MenusCriteria();
		example.setLimitSize(limitSize);
		example.setLimitStart(start);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(name)) {
			criteria.andNameEqualTo(name);
		}
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (StringUtils.isNotEmpty(startTime)) {
			try {
				criteria.andAddtimeGreaterThan(sim.parse(startTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (StringUtils.isNotEmpty(endTime)) {
			try {
				criteria.andAddtimeLessThan(sim.parse(endTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		int totalCount = mapper.countByExample(example);
		PageUtil<Menus> pageUtil = new PageUtil<Menus>(limitSize, totalCount, start);
		List<Menus> list = new ArrayList<Menus>();
		if (totalCount > 0) {
			example.setOrderByClause("id desc");
			example.setLimitStart((start - 1) * limitSize);
			example.setLimitSize(limitSize);
			list = mapper.selectByExample(example);
			for (Menus log : list) {
				log.setAddtime(log.getAddtime());
			}
		}
		pageUtil.setDatas(list);
		return pageUtil;
	}

}
