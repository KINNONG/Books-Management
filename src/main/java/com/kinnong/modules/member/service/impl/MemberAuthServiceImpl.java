package com.kinnong.modules.member.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kinnong.modules.member.dao.MemberAuthDao;
import com.kinnong.modules.member.dao.MemberDao;
import com.kinnong.modules.member.entity.MemberAuthEntity;
import com.kinnong.modules.member.entity.MemberEntity;
import com.kinnong.modules.member.service.MemberAuthService;


@Service("memberAuthService")
public class MemberAuthServiceImpl implements MemberAuthService {
	@Autowired
	private MemberAuthDao memberAuthDao;
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public MemberAuthEntity queryObject(Integer id){
		return memberAuthDao.queryObject(id);
	}
	
	@Override
	public List<MemberAuthEntity> queryList(Map<String, Object> map){
		return memberAuthDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return memberAuthDao.queryTotal(map);
	}
	
	@Override
	public void save(MemberAuthEntity memberAuth){
		memberAuthDao.save(memberAuth);
	}
	
	@Override
	public void update(MemberAuthEntity memberAuth){
		memberAuthDao.update(memberAuth);
	}
	
	@Override
	public void delete(Integer id){
		memberAuthDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		memberAuthDao.deleteBatch(ids);
	}

	@Override
	public MemberAuthEntity queryByOpenid(String openid) {
		return memberAuthDao.queryByOpenid(openid);
	}

	@Override
	@Transactional
	public void register(MemberEntity member, MemberAuthEntity memberAuth) {
		memberDao.save(member);
		memberAuth.setMemberId(member.getId());
		memberAuthDao.save(memberAuth);
	}

	@Override
	public MemberAuthEntity queryOne(Integer userId, String authType) {
		return memberAuthDao.queryOne(userId, authType);
	}
	
}
