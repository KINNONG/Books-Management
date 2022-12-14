package com.kinnong.modules.book.dao;

import org.apache.ibatis.annotations.Mapper;

import com.kinnong.modules.book.entity.CategoryEntity;
import com.kinnong.modules.sys.dao.BaseDao;

/**
 * 分类
 * 
 * @author lzl
 * @email 2803180149@qq.com
 */
@Mapper
public interface CategoryDao extends BaseDao<CategoryEntity> {

	
}
