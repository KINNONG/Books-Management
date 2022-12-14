package com.kinnong.modules.book.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kinnong.modules.book.dao.BookDao;
import com.kinnong.modules.book.dao.HistoryDao;
import com.kinnong.modules.book.entity.BookEntity;
import com.kinnong.modules.book.service.BookService;
import com.kinnong.modules.member.dao.MemberDao;


@Service("bookService")
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private HistoryDao historyDao;
	
	@Override
	public BookEntity queryObject(Integer id){
		BookEntity book = bookDao.queryObject(id);
		return book;
	}
	
	@Override
	public List<BookEntity> queryList(Map<String, Object> map){
		List<BookEntity> bookList = bookDao.queryList(map);
		return bookList;
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return bookDao.queryTotal(map);
	}
	
	@Override
	public void save(BookEntity book){
		bookDao.save(book);
	}
	
	@Override
	public void update(BookEntity book){
		bookDao.update(book);
	}
	
	@Override
	public void delete(Integer id){
		bookDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		bookDao.deleteBatch(ids);
	}
	
	@Override
	public void updateStatus(Integer status, Integer[] ids) {
		bookDao.updateStatus(status, ids);
	}
	
	/**
	 * 可能喜欢
	 * */
	@Override
    public List<BookEntity> getLike(Long userId) {
		
        return new ArrayList<BookEntity>();
    }
}
