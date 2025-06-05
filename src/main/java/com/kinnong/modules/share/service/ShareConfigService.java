package com.kinnong.modules.share.service;

import java.util.List;
import java.util.Map;

import com.kinnong.modules.share.entity.ShareConfigEntity;

/**
 * 分享配置
 *
 * @author KINNONG
 */
public interface ShareConfigService {
    
    ShareConfigEntity queryObject(Integer id);
    
    List<ShareConfigEntity> queryList(Map<String, Object> map);
    
    int queryTotal(Map<String, Object> map);
    
    void save(ShareConfigEntity shareConfig);
    
    void update(ShareConfigEntity shareConfig);
    
    void delete(Integer id);
    
    void deleteBatch(Integer[] ids);
    
    void updateEnable(Integer enable, Integer[] ids);
    
    /**
     * 根据配置键查询配置
     * @param configKey 配置键
     * @return ShareConfigEntity
     */
    ShareConfigEntity queryByConfigKey(String configKey);
    
    /**
     * 根据配置键更新配置值
     * @param configKey 配置键
     * @param configValue 配置值
     * @return boolean
     */
    boolean updateValueByKey(String configKey, String configValue);
    
    /**
     * 获取所有启用的配置（用于小程序端获取）
     * @return Map<String, String> key-value形式的配置
     */
    Map<String, String> getAllEnabledConfigs();
} 