package com.kinnong.modules.wechat.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.kinnong.common.annotation.AuthIgnore;
import com.kinnong.web.utils.IPUtils;
import com.kinnong.common.utils.R;
import com.kinnong.modules.member.entity.MemberEntity;
import com.kinnong.modules.member.service.MemberService;
import com.kinnong.modules.order.entity.OrderEntity;
import com.kinnong.modules.order.service.OrderService;


/**
 * @author lizhengle
 */
@RestController
@RequestMapping("/api/wechat/pay")
public class WxPayController{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "wxPayService")
    private WxPayService payService;
    
    @Autowired
    private MemberService memberService;
    
    @Autowired
    private OrderService orderService;

    /**
     * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
     * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
     * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
     *
     * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
     */
    @PostMapping("/unifiedOrder")
    public R unifiedOrder(@RequestAttribute Long userId, @RequestBody OrderEntity order, HttpServletRequest req) throws WxPayException {
    	MemberEntity user = memberService.queryObject(userId);
    	WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
    	request.setOutTradeNo(order.getOrderNumber());
    	request.setBody("测试商品");
    	request.setNotifyUrl(payService.getConfig().getNotifyUrl());
    	request.setTotalFee(order.getTotalAmount().multiply(new BigDecimal(100)).intValue());
    	request.setSpbillCreateIp(IPUtils.getIpAddr(req));
    	request.setTradeType("JSAPI");
    	WxPayUnifiedOrderResult result = this.payService.unifiedOrder(request);
    	
    	String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = String.valueOf(System.currentTimeMillis());
    			
    	//signKey 商户平台设置的密钥key
    	//签名字段：appId，timeStamp，nonceStr，package，signType
    			
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("appId", payService.getConfig().getAppId());
    	params.put("timeStamp", timeStamp);
    	params.put("nonceStr", nonceStr);
    	params.put("package", "prepay_id=" + result.getPrepayId());
    	params.put("signType", "MD5");
    	
    	String sign = SignUtils.createSign(params, "MD5", payService.getConfig().getMchKey(), new String[0]);
    	
    	params.put("paySign", sign);
    	
    	return R.ok().put("data", params);
    }



    /**
     * 此方法需要改造，根据实际需要返回com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse对象
     */
    @AuthIgnore
    @PostMapping("/parseOrderNotifyResult")
    public String parseOrderNotifyResult(@RequestBody String xmlData) throws WxPayException {
    	WxPayOrderNotifyResult wxPayOrderNotifyResult = this.payService.parseOrderNotifyResult(xmlData);
    	
    	if("SUCCESS".equals(wxPayOrderNotifyResult.getReturnCode())){
    		OrderEntity order = new OrderEntity();
    		order.setOrderNumber(wxPayOrderNotifyResult.getOutTradeNo());
    		order.setOrderStatus(2);
    		orderService.updateByOrderNumber(order);
    		return WxPayNotifyResponse.success("OK");
    	}else {
    		logger.error(wxPayOrderNotifyResult.getReturnMsg());
    		return WxPayNotifyResponse.success("OK");
    	}
    	
    }

}