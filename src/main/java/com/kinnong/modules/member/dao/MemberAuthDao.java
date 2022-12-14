package com.kinnong.modules.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kinnong.modules.member.entity.MemberAuthEntity;
import com.kinnong.modules.sys.dao.BaseDao;

/**
 * 授权
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 */
@Mapper
public interface MemberAuthDao extends BaseDao<MemberAuthEntity> {

	MemberAuthEntity queryByOpenid(String openid);
	
	MemberAuthEntity queryOne(@Param("memberId")Integer memberId, @Param("authType")String authType);
	
}
