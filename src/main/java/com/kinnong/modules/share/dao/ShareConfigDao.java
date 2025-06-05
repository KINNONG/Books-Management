package com.kinnong.modules.share.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kinnong.modules.share.entity.ShareConfigEntity;
import com.kinnong.modules.sys.dao.BaseDao;

/**
 * 分享配置
 *
 * @author KINNONG
 */
@Mapper
public interface ShareConfigDao extends BaseDao<ShareConfigEntity> {

    /**
     * 根据配置键查询配置
     * @param configKey 配置键
     * @return ShareConfigEntity
     */
    ShareConfigEntity queryByConfigKey(@Param("configKey") String configKey);

    /**
     * 根据配置键更新配置值
     * @param configKey 配置键
     * @param configValue 配置值
     * @return int
     */
    int updateValueByKey(@Param("configKey") String configKey, @Param("configValue") String configValue);

    /**
     * 批量启用/禁用配置
     * @param enable 启用状态
     * @param ids 配置ID数组
     */
    void updateEnable(@Param("enable") Integer enable, @Param("ids") Integer[] ids);
} 