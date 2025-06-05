package com.kinnong.modules.share.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kinnong.modules.share.dao.ShareConfigDao;
import com.kinnong.modules.share.entity.ShareConfigEntity;
import com.kinnong.modules.share.service.ShareConfigService;

@Service("shareConfigService")
public class ShareConfigServiceImpl implements ShareConfigService {
    
    @Autowired
    private ShareConfigDao shareConfigDao;
    
    @Override
    public ShareConfigEntity queryObject(Integer id) {
        return shareConfigDao.queryObject(id);
    }
    
    @Override
    public List<ShareConfigEntity> queryList(Map<String, Object> map) {
        return shareConfigDao.queryList(map);
    }
    
    @Override
    public int queryTotal(Map<String, Object> map) {
        return shareConfigDao.queryTotal(map);
    }
    
    @Override
    public void save(ShareConfigEntity shareConfig) {
        shareConfig.setCreateTime(new Date());
        shareConfig.setUpdateTime(new Date());
        if (shareConfig.getEnable() == null) {
            shareConfig.setEnable(1);
        }
        if (shareConfig.getConfigType() == null) {
            shareConfig.setConfigType("string");
        }
        shareConfigDao.save(shareConfig);
    }
    
    @Override
    public void update(ShareConfigEntity shareConfig) {
        shareConfig.setUpdateTime(new Date());
        shareConfigDao.update(shareConfig);
    }
    
    @Override
    public void delete(Integer id) {
        shareConfigDao.delete(id);
    }
    
    @Override
    public void deleteBatch(Integer[] ids) {
        shareConfigDao.deleteBatch(ids);
    }
    
    @Override
    public void updateEnable(Integer enable, Integer[] ids) {
        shareConfigDao.updateEnable(enable, ids);
    }
    
    @Override
    public ShareConfigEntity queryByConfigKey(String configKey) {
        return shareConfigDao.queryByConfigKey(configKey);
    }
    
    @Override
    public boolean updateValueByKey(String configKey, String configValue) {
        int result = shareConfigDao.updateValueByKey(configKey, configValue);
        return result > 0;
    }
    
    @Override
    public Map<String, String> getAllEnabledConfigs() {
        Map<String, Object> params = new HashMap<>();
        params.put("enable", 1);
        List<ShareConfigEntity> configList = shareConfigDao.queryList(params);
        
        Map<String, String> configMap = new HashMap<>();
        for (ShareConfigEntity config : configList) {
            configMap.put(config.getConfigKey(), config.getConfigValue());
        }
        return configMap;
    }
} 