package com.kinnong.modules.member.api;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.annotation.AuthIgnore;
import com.kinnong.common.utils.Query;
import com.kinnong.common.utils.R;
import com.kinnong.modules.member.entity.DepositRefundEntity;
import com.kinnong.modules.member.entity.MemberEntity;
import com.kinnong.modules.member.service.DepositRefundService;
import com.kinnong.modules.member.service.MemberService;

/**
 * 小程序会员API
 *
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
	 * 收集用户信息接口
	 * 绑定当前登录用户的收款信息
	 */
	@RequestMapping("/collectInfo")
	public R collectUserInfo(@RequestAttribute("userId") Long userId, @RequestBody Map<String, Object> params){
		try {
			// 获取当前用户信息
			MemberEntity member = memberService.queryObject(userId);
			if (member == null) {
				return R.error("用户不存在");
			}
			
			// 验证必要字段
			String realName = (String) params.get("realName");
			String idCard = (String) params.get("idCard");
			String paymentType = (String) params.get("paymentType");
			
			if (realName == null || realName.trim().isEmpty()) {
				return R.error("真实姓名不能为空");
			}
			if (paymentType == null || paymentType.trim().isEmpty()) {
				return R.error("支付方式不能为空");
			}
			
			// 更新用户基本信息
			member.setRealName(realName.trim());
			member.setIdCard(idCard);
			member.setPaymentType(paymentType);
			
			// 根据支付方式验证和设置对应的账户信息
			switch (paymentType) {
				case "wechat":
					String wechatAccount = (String) params.get("wechatAccount");
					if (wechatAccount == null || wechatAccount.trim().isEmpty()) {
						return R.error("微信账户不能为空");
					}
					member.setWechatAccount(wechatAccount.trim());
					// 清空其他支付方式的信息
					member.setAlipayAccount(null);
					member.setBankCardNumber(null);
					member.setBankName(null);
					member.setCardholderName(null);
					break;
				case "alipay":
					String alipayAccount = (String) params.get("alipayAccount");
					if (alipayAccount == null || alipayAccount.trim().isEmpty()) {
						return R.error("支付宝账户不能为空");
					}
					member.setAlipayAccount(alipayAccount.trim());
					// 清空其他支付方式的信息
					member.setWechatAccount(null);
					member.setBankCardNumber(null);
					member.setBankName(null);
					member.setCardholderName(null);
					break;
				case "bank":
					String bankCardNumber = (String) params.get("bankCardNumber");
					String bankName = (String) params.get("bankName");
					String cardholderName = (String) params.get("cardholderName");
					
					if (bankCardNumber == null || bankCardNumber.trim().isEmpty()) {
						return R.error("银行卡号不能为空");
					}
					if (bankName == null || bankName.trim().isEmpty()) {
						return R.error("银行名称不能为空");
					}
					if (cardholderName == null || cardholderName.trim().isEmpty()) {
						return R.error("持卡人姓名不能为空");
					}
					
					member.setBankCardNumber(bankCardNumber.trim());
					member.setBankName(bankName.trim());
					member.setCardholderName(cardholderName.trim());
					// 清空其他支付方式的信息
					member.setWechatAccount(null);
					member.setAlipayAccount(null);
					break;
				default:
					return R.error("不支持的支付方式");
			}
			
			// 更新当前用户信息
			memberService.update(member);
			
			return R.ok("收款信息设置成功");
		} catch (Exception e) {
			return R.error("操作失败：" + e.getMessage());
		}
	}
	
	/**
	 * 查询用户支付信息
	 */
	@AuthIgnore
	@RequestMapping("/paymentInfo/{id}")
	public R getPaymentInfo(@PathVariable("id") Long id){
		MemberEntity member = memberService.queryObject(id);
		if (member == null) {
			return R.error("用户不存在");
		}
		
		Map<String, Object> paymentInfo = new HashMap<>();
		paymentInfo.put("id", member.getId());
		paymentInfo.put("realName", member.getRealName());
		paymentInfo.put("idCard", member.getIdCard());
		paymentInfo.put("paymentType", member.getPaymentType());
		paymentInfo.put("wechatAccount", member.getWechatAccount());
		paymentInfo.put("alipayAccount", member.getAlipayAccount());
		paymentInfo.put("bankCardNumber", member.getBankCardNumber());
		paymentInfo.put("bankName", member.getBankName());
		paymentInfo.put("cardholderName", member.getCardholderName());
		
		return R.ok().put("paymentInfo", paymentInfo);
	}
	
	/**
	 * 批量查询用户支付信息
	 */
	@AuthIgnore
	@RequestMapping("/paymentInfoList")
	public R getPaymentInfoList(@RequestParam Map<String, Object> params){
		Query query = new Query(params);
		List<MemberEntity> memberList = memberService.queryList(query);
		int total = memberService.queryTotal(query);
		
		// 只返回支付相关信息，不包含密码等敏感信息
		memberList.forEach(member -> {
			member.setPassword(null);
		});
		
		return R.ok().put("rows", memberList).put("total", total);
	}
	
	/**
	 * 小程序用户信息查询（当前登录用户）
	 */
	@RequestMapping("/getUserInfo")
	public R getUserInfo(@RequestAttribute("userId") Long userId){
		MemberEntity member = memberService.queryObject(userId);
		if (member != null) {
			// 不返回密码等敏感信息
			member.setPassword(null);
		}
		return R.ok().put("member", member);
	}
	
	/**
	 * 获取当前用户的收款信息
	 */
	@RequestMapping("/getMyPaymentInfo")
	public R getMyPaymentInfo(@RequestAttribute("userId") Long userId){
		MemberEntity member = memberService.queryObject(userId);
		if (member == null) {
			return R.error("用户不存在");
		}
		
		Map<String, Object> paymentInfo = new HashMap<>();
		paymentInfo.put("id", member.getId());
		paymentInfo.put("realName", member.getRealName());
		paymentInfo.put("idCard", member.getIdCard());
		paymentInfo.put("paymentType", member.getPaymentType());
		paymentInfo.put("wechatAccount", member.getWechatAccount());
		paymentInfo.put("alipayAccount", member.getAlipayAccount());
		paymentInfo.put("bankCardNumber", member.getBankCardNumber());
		paymentInfo.put("bankName", member.getBankName());
		paymentInfo.put("cardholderName", member.getCardholderName());
		
		return R.ok().put("paymentInfo", paymentInfo);
	}
	
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
