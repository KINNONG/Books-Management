package com.kinnong.modules.book.dao;

import com.kinnong.modules.book.entity.ShelfEntity;
import com.kinnong.modules.sys.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 书架
 * 
 * @author lizhengle
 * @email 2803180149@qq.com
 */
@Mapper
public interface ShelfDao extends BaseDao<ShelfEntity> {

	ShelfEntity queryByBookId(Integer bookId);

	void addNum(Integer bookId);

	void deleteByBookId(@Param("memberId")Long memberId, @Param("bookId")Integer bookId);

	ShelfEntity query(@Param("memberId")Long memberId, @Param("bookId")Integer bookId);
	
}
