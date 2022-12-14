package com.kinnong.modules.advert.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kinnong.modules.advert.entity.AdvertEntity;
import com.kinnong.modules.sys.dao.BaseDao;

/**
 * @author lzl
 * @email 2803180149@qq.com
 */
@Mapper
public interface AdvertDao extends BaseDao<AdvertEntity> {
	
}
