package com.kinnong.modules.member.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.annotation.AuthIgnore;
import com.kinnong.web.utils.JwtUtils;
import com.kinnong.common.utils.R;
import com.kinnong.modules.member.entity.MemberEntity;
import com.kinnong.modules.member.service.MemberService;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录相关
 * 
 * @author lzl
 * @email 2803180149@qq.com
 */
@RestController
@RequestMapping("/api")
public class ApiLoginController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
    private JwtUtils jwtUtils;
	
	@Value("${serverUrl}")
	private String serverUrl;

	/**
	 * 登录
	 */
	@AuthIgnore
	@RequestMapping(value = "/login")
	public Map<String, Object> login(String loginName, String password)throws Exception {

		//用户信息
		MemberEntity member = memberService.queryByLoginName(loginName);

		//账号不存在、密码错误
		if(member == null || !member.getPassword().equals(password)) {
			return R.error("账号或密码不正确");
		}

		//生成token
	    String token = jwtUtils.generateToken(member.getId());
	    Map<String, Object> map = new HashMap<String, Object>();
        map.put("token", token);
		map.put("member", member);
		return R.ok(map);
	}
	
	/**
	 * 注册
	 */
	@AuthIgnore
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Map<String, Object> register(@RequestBody MemberEntity member)throws IOException {
		MemberEntity m = memberService.queryByLoginName(member.getLoginName());
		if(m != null) {
			return R.error("账号已存在!");
		}
		if(member.getAvatarUrl() == null || "".equals(member.getAvatarUrl())) {
			member.setAvatarUrl(serverUrl + "/img/avatar.png");
		}
		member.setCreateTime(new Date());
		memberService.save(member);
		return R.ok();
	}
	
}
