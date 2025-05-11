package com.kinnong.modules.member.api;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.utils.R;
import com.kinnong.modules.member.entity.DepositRefundEntity;
import com.kinnong.modules.member.entity.MemberEntity;
import com.kinnong.modules.member.service.DepositRefundService;
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
	
	@Autowired
	private DepositRefundService depositRefundService;
	
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
	 * @param deposit 用户输入的押金金额
	 * @return
	 */
	@RequestMapping("/addDeposit")
	public R addDeposit(@RequestAttribute("userId") Long userId, BigDecimal deposit) {
		// 参数校验
		if (deposit == null || deposit.compareTo(BigDecimal.ZERO) <= 0) {
			return R.error("押金金额必须大于0");
		}
		
		// 获取用户当前信息
		MemberEntity currentMember = memberService.queryObject(userId);
		BigDecimal currentDeposit = currentMember.getDeposit() == null ? BigDecimal.ZERO : currentMember.getDeposit();
		
		// 更新押金（累加）
		MemberEntity member = new MemberEntity();
		member.setId(userId);
		member.setDeposit(currentDeposit.add(deposit));
		memberService.update(member);
		
		return R.ok().put("message", "押金充值成功");
	}
	
	/**
	 * 退押金
	 * @param userId
	 * @param deposit
	 * @return
	 */
	@RequestMapping("/delDeposit")
	public R delDeposit(@RequestAttribute("userId") Long userId) {
		// 查询用户信息
		MemberEntity member = memberService.queryObject(userId);
		if (member == null || member.getDeposit() == null || member.getDeposit().compareTo(new BigDecimal(0)) <= 0) {
			return R.error("您没有可退的押金");
		}
		
		// 检查是否有未完成的申请
		DepositRefundEntity pending = depositRefundService.queryPendingByMemberId(userId);
		if (pending != null) {
			return R.error("您已有一笔退押金申请正在处理中");
		}
		
		// 创建新的退押金申请
		DepositRefundEntity refund = new DepositRefundEntity();
		refund.setMemberId(userId);
		refund.setAmount(member.getDeposit());
		refund.setStatus(0); // 待审核
		refund.setApplyTime(new Date());
		depositRefundService.save(refund);
		
		return R.ok().put("message", "退押金申请已提交，请等待审核");
	}
}
