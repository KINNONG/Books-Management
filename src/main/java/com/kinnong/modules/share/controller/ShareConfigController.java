package com.kinnong.modules.share.controller;

import java.util.Date;
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
import com.kinnong.modules.share.entity.ShareConfigEntity;
import com.kinnong.modules.share.service.ShareConfigService;

/**
 * 分享配置管理
 *
 * @author KINNONG
 */
@RestController
@RequestMapping("/shareconfig")
public class ShareConfigController {
    
    @Autowired
    private ShareConfigService shareConfigService;
    
    /**
     * 列表查询
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        try {
            // 查询列表数据
            Query query = new Query(params);
            
            List<ShareConfigEntity> configList = shareConfigService.queryList(query);
            int total = shareConfigService.queryTotal(query);
            
            return R.ok().put("rows", configList).put("total", total);
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("查询失败: " + e.getMessage());
        }
    }
    
    /**
     * 根据ID查询配置信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        ShareConfigEntity config = shareConfigService.queryObject(id);
        
        return R.ok().put("shareConfig", config);
    }
    
    /**
     * 保存配置
     */
    @RequestMapping("/save")
    public R save(@RequestBody ShareConfigEntity shareConfig) {
        shareConfig.setCreateTime(new Date());
        shareConfig.setUpdateTime(new Date());
        shareConfigService.save(shareConfig);
        
        return R.ok();
    }
    
    /**
     * 修改配置
     */
    @RequestMapping("/update")
    public R update(@RequestBody ShareConfigEntity shareConfig) {
        shareConfigService.update(shareConfig);
        
        return R.ok();
    }
    
    /**
     * 删除配置
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        shareConfigService.deleteBatch(ids);
        
        return R.ok();
    }
    
    /**
     * 启用配置
     */
    @RequestMapping("/enable")
    public R enable(@RequestBody Integer[] ids) {
        shareConfigService.updateEnable(1, ids);
        
        return R.ok();
    }
    
    /**
     * 禁用配置
     */
    @RequestMapping("/disable")
    public R disable(@RequestBody Integer[] ids) {
        shareConfigService.updateEnable(0, ids);
        
        return R.ok();
    }
    
    /**
     * 根据配置键查询单个配置
     */
    @RequestMapping("/getByKey/{configKey}")
    public R getByKey(@PathVariable("configKey") String configKey) {
        ShareConfigEntity config = shareConfigService.queryByConfigKey(configKey);
        
        return R.ok().put("shareConfig", config);
    }
    
    /**
     * 快速更新配置值
     */
    @RequestMapping("/updateValue")
    public R updateValue(@RequestParam("configKey") String configKey, 
                        @RequestParam("configValue") String configValue) {
        boolean success = shareConfigService.updateValueByKey(configKey, configValue);
        
        if (success) {
            return R.ok().put("message", "配置更新成功");
        } else {
            return R.error("配置更新失败，请检查配置键是否存在");
        }
    }
} 