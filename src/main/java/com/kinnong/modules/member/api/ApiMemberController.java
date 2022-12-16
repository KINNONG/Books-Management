package com.kinnong.modules.member.api;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.utils.R;
import com.kinnong.modules.member.entity.MemberEntity;
import com.kinnong.modules.member.service.MemberService;

/**
 * 用户接口
 * @author ZJL
 * @email 1044869436@qq.com
 */
@RestController
@RequestMapping("/api/member")
public class ApiMemberController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * 用户详情
	 * @param userId
	 * @return
	 */
    @GetMapping("info")
    public R info(@RequestAttribute Long userId){
    	MemberEntity member = memberService.queryObject(userId);
        return R.ok().put("member", member);
    }
	
	/**
	 * 更新用户
	 * @param member
	 * @return
	 */
	@RequestMapping("/update")
	public R update(@RequestBody MemberEntity member){
		memberService.update(member);
		return R.ok();
	}
	
	/**
	 * 交押金
	 * @param userId
	 * @param deposit
	 * @return
	 */
	@RequestMapping("/addDeposit")
	public R addDeposit(@RequestAttribute("userId") Long userId, BigDecimal deposit) {
		MemberEntity member = new MemberEntity();
		member.setId(userId);
		member.setDeposit(new BigDecimal(100));
		memberService.update(member);
		return R.ok();
	}
	
	/**
	 * 退押金
	 * @param userId
	 * @param deposit
	 * @return
	 */
	@RequestMapping("/delDeposit")
	public R delDeposit(@RequestAttribute("userId") Long userId) {
		MemberEntity member = new MemberEntity();
		member.setId(userId);
		member.setDeposit(new BigDecimal(0));
		memberService.update(member);
		return R.ok();
	}
}
