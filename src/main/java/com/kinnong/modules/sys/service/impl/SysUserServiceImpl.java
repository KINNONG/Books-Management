package com.kinnong.modules.sys.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kinnong.common.utils.MD5Util;
import com.kinnong.modules.sys.dao.SysUserDao;
import com.kinnong.modules.sys.entity.SysUserEntity;
import com.kinnong.modules.sys.service.SysUserService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 * 系统用户
 * 
 * @author lzl
 * @email 2803180149@qq.com
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {
	
	@Autowired
	private SysUserDao sysUserDao;

	@Override
	public SysUserEntity queryByUserName(String username) {
		return sysUserDao.queryByUserName(username);
	}
	
	@Override
	public SysUserEntity queryObject(Long id) {
		return sysUserDao.queryObject(id);
	}

	@Override
	public List<SysUserEntity> queryList(Map<String, Object> map){
		return sysUserDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysUserDao.queryTotal(map);
	}

	@Override
	@Transactional
	public void save(SysUserEntity user) {
		user.setCreateTime(new Date());
		user.setPassword(MD5Util.MD5Encode(user.getPassword()));
		sysUserDao.save(user);
		
	}

	@Override
	@Transactional
	public void update(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(MD5Util.MD5Encode(user.getPassword()));
		}
		sysUserDao.update(user);
		
	}

	@Override
	@Transactional
	public void deleteBatch(Long[] id) {
		sysUserDao.deleteBatch(id);
	}

	@Override
	public int updatePassword(Long id, String password, String newPassword) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("password", password);
		map.put("newPassword", newPassword);
		return sysUserDao.updatePassword(map);
	}
	
}
