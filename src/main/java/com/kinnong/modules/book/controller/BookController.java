package com.kinnong.modules.book.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.utils.Query;
import com.kinnong.common.utils.R;
import com.kinnong.modules.book.entity.BookEntity;
import com.kinnong.modules.book.service.BookService;


/**
 * 图书
 *
 * @author ZJL
 * @email 1044869436@qq.com
 */
@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	private BookService bookService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<BookEntity> bookList = bookService.queryList(query);
		int total = bookService.queryTotal(query);
		
		return R.ok().put("rows", bookList).put("total", total);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	public R info(@PathVariable("id") Integer id){
		BookEntity book = bookService.queryObject(id);
		
		return R.ok().put("book", book);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	public R save(@RequestBody BookEntity book){
		book.setCreateTime(new Date());
		bookService.save(book);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	public R update(@RequestBody BookEntity book){
		bookService.update(book);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	public R delete(@RequestBody Integer[] ids){
		bookService.deleteBatch(ids);
		
		return R.ok();
	}

	/**
	 * 下架
	 * @param ids
	 * @return
	 */
    @RequestMapping("/lower")
    public R lower(@RequestBody Integer[] ids) {
    	bookService.updateStatus(0, ids);
    	return R.ok();
    }

	/**
	 * 上架
	 * @param ids
	 * @return
	 */
	@RequestMapping("/upper")
    public R upper(@RequestBody Integer[] ids) {
    	bookService.updateStatus(1, ids);
    	return R.ok();
    }

    //查询全部
	@RequestMapping("/getAll")
	public R getAll() {
		List<BookEntity> bookList = bookService.queryList(null);
		return R.ok().put("bookList", bookList);
	}
	
}
