package com.kinnong.modules.book.service;

import com.kinnong.modules.book.entity.ShelfEntity;

import java.util.List;
import java.util.Map;

/**
 * 书架
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public interface ShelfService {
	
	ShelfEntity queryObject(Integer id);
	
	List<ShelfEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ShelfEntity shelf);
	
	void update(ShelfEntity shelf);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
