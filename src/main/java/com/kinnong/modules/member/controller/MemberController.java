package com.kinnong.modules.member.controller;

import java.util.HashMap;
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
	
	/**
	 * 收集用户信息接口
	 * 专门用于收集用户的完整信息，包括支付信息
	 */
	@RequestMapping("/collectInfo")
	public R collectUserInfo(@RequestBody MemberEntity member){
		try {
			// 验证必要字段
			if (member.getRealName() == null || member.getRealName().trim().isEmpty()) {
				return R.error("真实姓名不能为空");
			}
			if (member.getPaymentType() == null || member.getPaymentType().trim().isEmpty()) {
				return R.error("支付方式不能为空");
			}
			
			// 根据支付方式验证对应的账户信息
			switch (member.getPaymentType()) {
				case "wechat":
					if (member.getWechatAccount() == null || member.getWechatAccount().trim().isEmpty()) {
						return R.error("微信账户不能为空");
					}
					break;
				case "alipay":
					if (member.getAlipayAccount() == null || member.getAlipayAccount().trim().isEmpty()) {
						return R.error("支付宝账户不能为空");
					}
					break;
				case "bank":
					if (member.getBankCardNumber() == null || member.getBankCardNumber().trim().isEmpty()) {
						return R.error("银行卡号不能为空");
					}
					if (member.getBankName() == null || member.getBankName().trim().isEmpty()) {
						return R.error("银行名称不能为空");
					}
					if (member.getCardholderName() == null || member.getCardholderName().trim().isEmpty()) {
						return R.error("持卡人姓名不能为空");
					}
					break;
				default:
					return R.error("不支持的支付方式");
			}
			
			if (member.getId() == null) {
				// 新增用户
				memberService.save(member);
			} else {
				// 更新用户
				memberService.update(member);
			}
			
			return R.ok("用户信息收集成功");
		} catch (Exception e) {
			return R.error("操作失败：" + e.getMessage());
		}
	}
	
	/**
	 * 查询用户支付信息
	 */
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
}
