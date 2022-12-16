package com.kinnong.modules.advert.service;

import java.util.List;
import java.util.Map;

import com.kinnong.modules.advert.entity.AdvertEntity;


/**
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public interface AdvertService {
	
	AdvertEntity queryObject(Integer id);
	
	List<AdvertEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AdvertEntity advert);
	
	void update(AdvertEntity advert);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
