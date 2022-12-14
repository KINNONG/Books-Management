package com.kinnong.modules.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kinnong.modules.book.entity.BookEntity;
import com.kinnong.modules.sys.dao.BaseDao;

/**
 * 图书
 * 
 * @author lzl
 * @email 2803180149@qq.com
 * @date 2017-07-14 13:43:12
 */
@Mapper
public interface BookDao extends BaseDao<BookEntity> {

	void updateStatus(@Param("status")Integer status, @Param("ids")Integer[] ids);
	
	void delStock(@Param("id")Integer id, @Param("stock")int stock);

	void addStock(@Param("id")Integer id, @Param("stock")int stock);

	List<BookEntity> queryByIds(Integer[] ids);
}
