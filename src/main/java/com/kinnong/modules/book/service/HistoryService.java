package com.kinnong.modules.book.service;

import com.kinnong.modules.book.entity.HistoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 浏览记录
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public interface HistoryService {
	
	HistoryEntity queryObject(Integer id);
	
	List<HistoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(HistoryEntity history);
	
	void update(HistoryEntity history);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
