package com.kinnong.modules.wechat.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.kinnong.common.utils.R;
import com.kinnong.modules.member.entity.MemberEntity;
import com.kinnong.modules.member.service.MemberService;
import com.kinnong.web.utils.IPUtils;


/**
 * @author ZJL
 * @email 1044869436@qq.com
 */
@RestController
@RequestMapping("/api/pay/wechat")
public class WechatPayController{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Value("${wechat.pay.notifyUrl}")
    private String notifyUrl;
    
    @Autowired
    private WxPayService wxPayService;
    
    @Autowired
    private MemberService memberService;
    
    
    /**
     * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
     * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
     * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
     *
     * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
     */
    @GetMapping("/unifiedOrder")
    public R unifiedOrder(@RequestAttribute("userId") Long userId, String orderNum, BigDecimal totalAmount, String goodsName, HttpServletRequest req) throws WxPayException {
    	WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
    	request.setOutTradeNo(orderNum);
    	MemberEntity member = memberService.queryObject(userId);
    	request.setBody(goodsName);
    	request.setNotifyUrl(notifyUrl);
    	request.setTotalFee(totalAmount.multiply(new BigDecimal(100)).intValue());
    	request.setSpbillCreateIp(IPUtils.getIpAddr(req));
    	request.setTradeType("JSAPI");
    	WxPayUnifiedOrderResult result = wxPayService.unifiedOrder(request);
    	
    	String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String nonceStr = String.valueOf(System.currentTimeMillis());
    			
    	//signKey 商户平台设置的密钥key
    	//签名字段：appId，timeStamp，nonceStr，package，signType
    			
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("appId", wxPayService.getConfig().getAppId());
    	params.put("timeStamp", timeStamp);
    	params.put("nonceStr", nonceStr);
    	params.put("package", "prepay_id=" + result.getPrepayId());
    	params.put("signType", "MD5");
    	
    	String sign = SignUtils.createSign(params, "MD5", wxPayService.getConfig().getMchKey(), new String[0]);
    	
    	params.put("paySign", sign);
    	
    	return R.ok().put("data", params);
    }


    /**
     * 此方法需要改造，根据实际需要返回com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse对象
     */
    @PostMapping("/notify")
    public String notify(@RequestBody String xmlData) throws Exception {
    	logger.info("--微信支付回调--");
    	logger.info(xmlData);
    	WxPayOrderNotifyResult wxPayOrderNotifyResult = WxPayOrderNotifyResult.fromXML(xmlData);
    	String orderNum = wxPayOrderNotifyResult.getOutTradeNo();
    	
    	if("SUCCESS".equals(wxPayOrderNotifyResult.getReturnCode())){
    		
    		//更新订单状态
    		
    		return WxPayNotifyResponse.success("OK");
    	}else {
    		logger.error(wxPayOrderNotifyResult.getReturnMsg());
    		return WxPayNotifyResponse.success("OK");
    	}
    }
    
    
    /**
     * <pre>
     * 微信支付-申请退款
     * 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
     * 接口链接：https://api.mch.weixin.qq.com/secapi/pay/refund
     * </pre>
     *
     * @param request 请求对象
     * @return 退款操作结果
     */
    @PostMapping("/refund")
    public WxPayRefundResult refund(@RequestBody WxPayRefundRequest request, Long storeId) throws WxPayException {
        return wxPayService.refund(request);
    }

}