package com.sttri.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sttri.bean.PageUtil;
import com.sttri.dao.RoleMenusMapper;
import com.sttri.entity.RoleMenus;
import com.sttri.entity.RoleMenusCriteria;
import com.sttri.entity.RoleMenusCriteria.Criteria;
import com.sttri.service.IRoleMenusService;

@Service
public class RoleMenusServiceImpl implements IRoleMenusService {
	@Autowired
	private RoleMenusMapper mapper;
	
	@Override
	public RoleMenus queryRoleMenusById(Integer id) {
		// TODO Auto-generated method stub
		RoleMenusCriteria RoleMenusCriteria = new RoleMenusCriteria();
		Criteria criteria = RoleMenusCriteria.createCriteria();
		criteria.andIdEqualTo(id);
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<RoleMenus> findAll() {
		// TODO Auto-generated method stub
		RoleMenusCriteria RoleMenusCriteria = new RoleMenusCriteria();
		RoleMenusCriteria.createCriteria();
		return mapper.selectByExample(RoleMenusCriteria);
	}

	@Override
	public List<RoleMenus> selectAll(RoleMenusCriteria criteria) {
		// TODO Auto-generated method stub
		return mapper.selectByExample(criteria);
	}
	
	@Override
	public List<RoleMenus> selectAll(Integer aid) {
		// TODO Auto-generated method stub
		RoleMenusCriteria criteria = new RoleMenusCriteria();
		criteria.createCriteria().andAidEqualTo(aid);
		return mapper.selectByExample(criteria);
	}

	@Override
	public int save(RoleMenus roleMenus) {
		// TODO Auto-generated method stub
		return mapper.insert(roleMenus);
	}

	@Override
	public int update(RoleMenus roleMenus) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(roleMenus);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public PageUtil<RoleMenus> getRoleMenusByPage(int limitSize, int start, String startTime, String endTime) {
		// TODO Auto-generated method stub
		RoleMenusCriteria example = new RoleMenusCriteria();
		example.setLimitSize(limitSize);
		example.setLimitStart(start);
		Criteria criteria = example.createCriteria();
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
		PageUtil<RoleMenus> pageUtil = new PageUtil<RoleMenus>(limitSize, totalCount, start);
		List<RoleMenus> list = new ArrayList<RoleMenus>();
		if (totalCount > 0) {
			example.setOrderByClause("id desc");
			example.setLimitStart((start - 1) * limitSize);
			example.setLimitSize(limitSize);
			list = mapper.selectByExample(example);
			for (RoleMenus log : list) {
				log.setAddtime(log.getAddtime());
			}
		}
		pageUtil.setDatas(list);
		return pageUtil;
	}

	@Override
	public List<RoleMenus> queryMenus(Integer aid) {
		// TODO Auto-generated method stub
		return mapper.queryMenus(aid);
	}

}
