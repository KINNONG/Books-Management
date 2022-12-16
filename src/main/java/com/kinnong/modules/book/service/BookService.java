package com.kinnong.modules.book.service;

import java.util.List;
import java.util.Map;

import com.kinnong.modules.book.entity.BookEntity;

/**
 * 图书
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
public interface BookService {
	
	BookEntity queryObject(Integer id);
	
	List<BookEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BookEntity book);
	
	void update(BookEntity book);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	void updateStatus(Integer status, Integer[] ids);
	
	List<BookEntity> getLike(Long userId);
}
