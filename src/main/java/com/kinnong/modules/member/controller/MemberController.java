package com.kinnong.modules.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.utils.Query;
import com.kinnong.common.utils.R;
import com.kinnong.modules.member.entity.MemberEntity;
import com.kinnong.modules.member.service.MemberService;


/**
 * 会员
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
@RestController
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<MemberEntity> memberList = memberService.queryList(query);
		int total = memberService.queryTotal(query);
		return R.ok().put("rows", memberList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.queryObject(id);
		return R.ok().put("member", member);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody MemberEntity member){
		memberService.save(member);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody MemberEntity member){
		memberService.update(member);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		memberService.deleteBatch(ids);
		return R.ok();
	}
	
}
