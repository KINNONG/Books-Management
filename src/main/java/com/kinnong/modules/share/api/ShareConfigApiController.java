package com.kinnong.modules.share.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.annotation.AuthIgnore;
import com.kinnong.common.utils.R;
import com.kinnong.modules.share.entity.ShareConfigEntity;
import com.kinnong.modules.share.service.ShareConfigService;

/**
 * 分享配置API - 供小程序调用
 *
 * @author KINNONG
 */
@RestController
@RequestMapping("/api/shareconfig")
public class ShareConfigApiController {
    
    @Autowired
    private ShareConfigService shareConfigService;
    
    /**
     * 获取所有启用的分享配置
     * 返回key-value格式，方便小程序直接使用
     */
    @AuthIgnore
    @RequestMapping("/getAllConfigs")
    public R getAllConfigs() {
        Map<String, String> configMap = shareConfigService.getAllEnabledConfigs();
        
        return R.ok().put("configs", configMap);
    }
    
    /**
     * 获取腾讯会议相关配置
     * 专门为腾讯会议功能提供的接口
     */
    @AuthIgnore
    @RequestMapping("/getTencentMeetingConfig")
    public R getTencentMeetingConfig() {
        ShareConfigEntity modalTitle = shareConfigService.queryByConfigKey("tencent_meeting_modal_title");
        ShareConfigEntity modalContent = shareConfigService.queryByConfigKey("tencent_meeting_modal_content");
        ShareConfigEntity confirmText = shareConfigService.queryByConfigKey("tencent_meeting_confirm_text");
        ShareConfigEntity downloadUrl = shareConfigService.queryByConfigKey("tencent_meeting_download_url");
        
        return R.ok()
                .put("modalTitle", modalTitle != null ? modalTitle.getConfigValue() : "复制成功")
                .put("modalContent", modalContent != null ? modalContent.getConfigValue() : "腾讯会议下载链接已复制到剪贴板，请在浏览器中打开下载")
                .put("confirmText", confirmText != null ? confirmText.getConfigValue() : "确定")
                .put("downloadUrl", downloadUrl != null ? downloadUrl.getConfigValue() : "https://meeting.tencent.com/download-center.html");
    }
    
    /**
     * 根据配置键获取单个配置值
     */
    @AuthIgnore
    @RequestMapping("/getConfig")
    public R getConfig(String configKey) {
        ShareConfigEntity config = shareConfigService.queryByConfigKey(configKey);
        
        if (config != null) {
            return R.ok().put("configValue", config.getConfigValue());
        } else {
            return R.error("配置不存在或已禁用");
        }
    }
} 