package com.kinnong.modules.sys.service;

import java.util.List;
import java.util.Map;

import com.kinnong.modules.sys.entity.SysUserEntity;


/**
 * 系统用户
 * 
 * @author lzl
 * @email 2803180149@qq.com
 */
public interface SysUserService {

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);
	
	/**
	 * 根据用户ID，查询用户
	 * @param id
	 * @return
	 */
	SysUserEntity queryObject(Long id);
	
	/**
	 * 查询用户列表
	 */
	List<SysUserEntity> queryList(Map<String, Object> map);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存用户
	 */
	void save(SysUserEntity user);
	
	/**
	 * 修改用户
	 */
	void update(SysUserEntity user);
	
	/**
	 * 删除用户
	 */
	void deleteBatch(Long[] ids);
	
	/**
	 * 修改密码
	 * @param id       用户ID
	 * @param password     原密码
	 * @param newPassword  新密码
	 */
	int updatePassword(Long id, String password, String newPassword);
}
