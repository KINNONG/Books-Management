package com.kinnong.modules.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kinnong.modules.book.entity.BookEntity;
import com.kinnong.modules.sys.dao.BaseDao;

/**
 * 图书
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
@Mapper
public interface BookDao extends BaseDao<BookEntity> {

	void updateStatus(@Param("status")Integer status, @Param("ids")Integer[] ids);
	
	void delStock(@Param("id")Integer id, @Param("stock")int stock);

	void addStock(@Param("id")Integer id, @Param("stock")int stock);

	List<BookEntity> queryByIds(Integer[] ids);
}
