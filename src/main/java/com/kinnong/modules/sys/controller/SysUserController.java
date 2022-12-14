package com.kinnong.modules.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.kinnong.common.utils.MD5Util;
import com.kinnong.common.utils.Query;
import com.kinnong.common.utils.R;
import com.kinnong.modules.sys.entity.SysUserEntity;
import com.kinnong.modules.sys.service.SysUserService;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author lzl
 * @email 2803180149@qq.com
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		
		//查询列表数据
		Query query = new Query(params);
		List<SysUserEntity> userList = sysUserService.queryList(query);
		int total = sysUserService.queryTotal(query);
		
		return R.ok().put("rows", userList).put("total", total);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/getInfo")
	public R getInfo(@RequestAttribute Long userId){
		return R.ok().put("user", sysUserService.queryObject(userId));
	}
	
	/**
	 * 修改登录用户密码
	 */
	@RequestMapping("/password")
	public R password(String password, String newPassword, @RequestAttribute Long userId){
		
		SysUserEntity user = sysUserService.queryObject(userId);
		//sha256加密
		password = MD5Util.MD5Encode(password);
		//sha256加密
		newPassword = MD5Util.MD5Encode(newPassword);
				
		//更新密码
		int count = sysUserService.updatePassword(userId, password, newPassword);
		if(count == 0){
			return R.error("原密码不正确");
		}
		
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		SysUserEntity user = sysUserService.queryObject(id);
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@RequestMapping("/save")
	public R save(@RequestBody SysUserEntity user){
		sysUserService.save(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@RequestMapping("/update")
	public R update(@RequestBody SysUserEntity user){
		sysUserService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Long[] ids){
		sysUserService.deleteBatch(ids);
		return R.ok();
	}
}
