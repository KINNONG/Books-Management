package com.kinnong.modules.share.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 分享配置
 *
 * @author KINNONG
 */
public class ShareConfigEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    // 主键ID
    private Integer id;
    // 配置键
    private String configKey;
    // 配置名称
    private String configName;
    // 配置值
    private String configValue;
    // 配置类型：string, json
    private String configType;
    // 描述
    private String description;
    // 是否启用，0：禁用，1：启用
    private Integer enable;
    // 创建时间
    private Date createTime;
    // 更新时间
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigType() {
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
} 