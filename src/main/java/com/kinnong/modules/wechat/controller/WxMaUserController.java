package com.kinnong.modules.wechat.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.kinnong.modules.wechat.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;

import com.kinnong.common.annotation.AuthIgnore;
import com.kinnong.web.utils.JwtUtils;
import com.kinnong.common.utils.R;
import com.kinnong.modules.member.entity.MemberAuthEntity;
import com.kinnong.modules.member.entity.MemberEntity;
import com.kinnong.modules.member.service.MemberAuthService;
import com.kinnong.modules.member.service.MemberService;

/**
 * 微信小程序用户接口
 */
@RestController
@RequestMapping("/api/wechat")
public class WxMaUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxMaService wxService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberAuthService memberAuthService;

    @Autowired
    private JwtUtils jwtUtils;

    private JsonUtils jsonUtils;

    /**
     * 登陆接口
     */
    @AuthIgnore
    @GetMapping("login")
    public R login(String avatarUrl, String nickname, String gender, String code) {
        if (StringUtils.isBlank(code)) {
            return R.error("empty jscode");
        }

        try {
            WxMaJscode2SessionResult session = this.wxService.getUserService().getSessionInfo(code);
            this.logger.info(session.getSessionKey());
            System.out.println(session.getSessionKey());
            this.logger.info(session.getOpenid());

            //查询用户信息
            MemberAuthEntity ma = memberAuthService.queryByOpenid(session.getOpenid());
            if (ma != null) {
                MemberEntity member = memberService.queryObject(ma.getMemberId());
                System.out.println(member.getId()+"dwdwdwd");
                //生成token
                String token = jwtUtils.generateToken(member.getId());
                Map<String, Object> tokenMap = new HashMap<String, Object>();
                tokenMap.put("token", token);
                tokenMap.put("userInfo", member);
                System.out.println(tokenMap);
                return R.ok(tokenMap);

            }else {
                //用户不存在，创建用户信息
//                String sessionKey = session.getSessionKey();
                //注册
                register(avatarUrl,nickname,gender,session.getOpenid());
                MemberAuthEntity maValue = memberAuthService.queryByOpenid(session.getOpenid());
                System.out.println(maValue);
                MemberEntity member = memberService.queryObject(maValue.getMemberId());
                System.out.println(member.getId()+"dwdwdwd");
                //生成token
                String token = jwtUtils.generateToken(member.getId());
                Map<String, Object> tokenMap = new HashMap<String, Object>();
                tokenMap.put("token", token);
                tokenMap.put("userInfo", member);
                System.out.println(tokenMap);
                return R.ok(tokenMap);
            }

        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
            return R.error();
        }
    }

    /**
     * 用户注册
     */
    @AuthIgnore
    @GetMapping("register")
    public R register(String avatarUrl, String nickname, String gender,String openid) {
        try {
//            String openid = this.wxService.getUserService().getSessionInfo(code).getOpenid();
            //查询用户信息
//            MemberAuthEntity ma = memberAuthService.queryByOpenid(openid);
//            if (ma != null) { //判断是否注册
//                return R.ok();
//            }
            //注册
            MemberEntity member = new MemberEntity();
            member.setAvatarUrl(avatarUrl);
            member.setNickname(filterUtf8mb4(nickname));
            member.setGender(gender);
            member.setCreateTime(new Date());

            MemberAuthEntity memberAuth = new MemberAuthEntity();
            memberAuth.setAuthType("wechat");
            memberAuth.setOpenid(openid);
            memberAuth.setCreateTime(new Date());

            memberAuthService.register(member, memberAuth);
            return R.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return R.error();
        }
    }

    public static String filterUtf8mb4(String str) {
        final int LAST_BMP = 0xFFFF;
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            int codePoint = str.codePointAt(i);
            if (codePoint < LAST_BMP) {
                sb.appendCodePoint(codePoint);
            } else {
                i++;
            }
        }
        return sb.toString();
    }

}
