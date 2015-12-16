package com.sttri.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sttri.bean.PageUtil;
import com.sttri.dao.AdminMapper;
import com.sttri.entity.Admin;
import com.sttri.entity.AdminCriteria;
import com.sttri.entity.AdminCriteria.Criteria;
import com.sttri.service.IAdminService;

@Service
public class AdminServiceImpl implements IAdminService {
	@Autowired
	private AdminMapper mapper;
	
	@Override
	public Admin queryAdminById(Integer id) {
		// TODO Auto-generated method stub
		AdminCriteria adminCriteria = new AdminCriteria();
		Criteria criteria = adminCriteria.createCriteria();
		criteria.andIdEqualTo(id);
		return mapper.selectByPrimaryKey(id);
	}
	
	@Override
	public Admin queryAdminByAccount(String account) {
		// TODO Auto-generated method stub
		AdminCriteria adminCriteria = new AdminCriteria();
		Criteria criteria = adminCriteria.createCriteria();
		criteria.andAccountEqualTo(account);
		List<Admin> aList = mapper.selectByExample(adminCriteria);
		if (aList != null && aList.size() > 0) {
			return aList.get(0);
		}else {
			return null;
		}
	}

	@Override
	public List<Admin> findAll() {
		// TODO Auto-generated method stub
		AdminCriteria adminCriteria = new AdminCriteria();
		adminCriteria.createCriteria();
		return mapper.selectByExample(adminCriteria);
	}

	@Override
	public List<Admin> selectAll(AdminCriteria criteria) {
		// TODO Auto-generated method stub
		return mapper.selectByExample(criteria);
	}

	@Override
	public int save(Admin admin) {
		// TODO Auto-generated method stub
		return mapper.insert(admin);
	}

	@Override
	public int update(Admin admin) {
		// TODO Auto-generated method stub
		return mapper.updateByPrimaryKey(admin);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return mapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public PageUtil<Admin> getAdminByPage(int limitSize, int start,String account,
			int level, String startTime, String endTime) {
		// TODO Auto-generated method stub
		AdminCriteria example = new AdminCriteria();
		example.setLimitSize(limitSize);
		example.setLimitStart(start);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotEmpty(account)) {
			criteria.andAccountEqualTo(account);
		}
		if (level != -1 && level != 0) {
			criteria.andLevelEqualTo(level);
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
		PageUtil<Admin> pageUtil = new PageUtil<Admin>(limitSize, totalCount, start);
		List<Admin> list = new ArrayList<Admin>();
		if (totalCount > 0) {
			example.setOrderByClause("id desc");
			example.setLimitStart((start - 1) * limitSize);
			example.setLimitSize(limitSize);
			list = mapper.selectByExample(example);
			for (Admin log : list) {
				log.setAddtime(log.getAddtime());
			}
		}
		pageUtil.setDatas(list);
		return pageUtil;
	}

}
