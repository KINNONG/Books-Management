package com.kinnong.modules.book.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.annotation.AuthIgnore;
import com.kinnong.common.utils.Query;
import com.kinnong.common.utils.R;
import com.kinnong.modules.book.entity.BookEntity;
import com.kinnong.modules.book.service.BookService;

/**
 * 图书接口
 * @author lzl
 *
 */
@RestController
@RequestMapping("/api/book")
public class ApiBookController {
	
	@Autowired
	private BookService bookService;
	
	/**
	 * 查询列表
	 * @param map
	 * @return
	 */
	@AuthIgnore
	@GetMapping("list")
    public R list(@RequestParam Map<String, Object> map){
		Query query = new Query(map);
    	List<BookEntity> bookList = bookService.queryList(query);
    	Integer total = bookService.queryTotal(map);
        return R.ok().put("bookList", bookList).put("total", total);
    }
    
	/**
	 * 详情
	 * @param id
	 * @return
	 */
	@AuthIgnore
    @GetMapping("detail")
    public R detail(int id){
    	BookEntity book = bookService.queryObject(id);
    	return R.ok().put("book", book);
    }
	
	/**
	 * 推荐图书
	 * @param userId
	 * @return
	 */
	@GetMapping("recommend")
    public R recommend(@RequestAttribute("userId") Long userId){
    	List<BookEntity> bookList = bookService.getLike(userId);
        return R.ok().put("bookList", bookList);
    }
    
}
