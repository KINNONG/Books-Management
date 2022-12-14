package com.kinnong.modules.book.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.kinnong.modules.book.dao.ShelfDao;
import com.kinnong.modules.book.entity.ShelfEntity;
import com.kinnong.modules.book.service.ShelfService;



@Service("shelfService")
public class ShelfServiceImpl implements ShelfService {
	@Autowired
	private ShelfDao shelfDao;
	
	@Override
	public ShelfEntity queryObject(Integer id){
		return shelfDao.queryObject(id);
	}
	
	@Override
	public List<ShelfEntity> queryList(Map<String, Object> map){
		return shelfDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return shelfDao.queryTotal(map);
	}
	
	@Override
	public void save(ShelfEntity shelf){
		ShelfEntity c = shelfDao.query(shelf.getMemberId(), shelf.getBookId());
		if(c == null) {
			shelf.setNum(1);
			shelfDao.save(shelf);
		}
	}
	
	@Override
	public void update(ShelfEntity shelf){
		shelfDao.update(shelf);
	}
	
	@Override
	public void delete(Integer id){
		shelfDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		shelfDao.deleteBatch(ids);
	}
	
}
