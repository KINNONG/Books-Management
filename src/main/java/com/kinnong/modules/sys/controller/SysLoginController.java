package com.kinnong.modules.sys.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.kinnong.common.annotation.AuthIgnore;
import com.kinnong.web.utils.JwtUtils;
import com.kinnong.common.utils.MD5Util;
import com.kinnong.common.utils.R;
import com.kinnong.web.utils.ShiroUtils;
import com.kinnong.modules.sys.entity.SysUserEntity;
import com.kinnong.modules.sys.service.SysUserService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * 登录相关
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
@RestController
public class SysLoginController {
	
	@Autowired
	private Producer producer;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
    private JwtUtils jwtUtils;

	@AuthIgnore
	@RequestMapping("captcha.jpg")
	public void captcha(HttpServletResponse response)throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

		//生成文字验证码
		String text = producer.createText();
		//生成图片验证码
		BufferedImage image = producer.createImage(text);
		//保存到shiro session
		ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@AuthIgnore
	@RequestMapping(value = "/sys/login", method = RequestMethod.POST)
	public Map<String, Object> login(String username, String password, String captcha)throws Exception {
		/*String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equalsIgnoreCase(kaptcha)){
			return R.error("验证码不正确");
		}*/

		//用户信息
		SysUserEntity user = sysUserService.queryByUserName(username);

		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(MD5Util.MD5Encode(password))) {
			return R.error("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.error("账号已被锁定,请联系管理员");
		}

		//生成token
		String token = jwtUtils.generateToken(user.getId());
		R r = R.ok().put("token", token).put("user", user);
		return r;
	}
	
}
