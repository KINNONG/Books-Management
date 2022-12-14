package com.kinnong.modules.member.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kinnong.modules.member.dao.MemberDao;
import com.kinnong.modules.member.entity.MemberEntity;
import com.kinnong.modules.member.service.MemberService;


@Service("MemberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDao memberDao;
	
	@Override
	public MemberEntity queryObject(Long id){
		return memberDao.queryObject(id);
	}
	
	@Override
	public List<MemberEntity> queryList(Map<String, Object> map){
		return memberDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return memberDao.queryTotal(map);
	}
	
	@Override
	public void save(MemberEntity member){
		memberDao.save(member);
	}
	
	@Override
	public void update(MemberEntity member){
		memberDao.update(member);
	}
	
	@Override
	public void delete(Long id){
		memberDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		memberDao.deleteBatch(ids);
	}

	@Override
	public MemberEntity queryByOpenid(String openid) {
		return memberDao.queryByOpenid(openid);
	}

	@Override
	public MemberEntity queryByLoginName(String loginName) {
		return memberDao.queryByLoginName(loginName);
	}
}
