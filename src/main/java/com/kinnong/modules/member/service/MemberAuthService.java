package com.kinnong.modules.member.service;

import java.util.List;
import java.util.Map;

import com.kinnong.modules.member.entity.MemberAuthEntity;
import com.kinnong.modules.member.entity.MemberEntity;

/**
 * 授权
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public interface MemberAuthService {
	
	MemberAuthEntity queryObject(Integer id);
	
	List<MemberAuthEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MemberAuthEntity memberAuth);
	
	void update(MemberAuthEntity memberAuth);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);

	MemberAuthEntity queryByOpenid(String openid);

	void register(MemberEntity member, MemberAuthEntity memberAuth);

	MemberAuthEntity queryOne(Integer userId, String authType);
}
