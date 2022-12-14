package com.kinnong.modules.book.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.utils.R;
import com.kinnong.modules.book.entity.ShelfEntity;
import com.kinnong.modules.book.service.ShelfService;

/**
 * 书架接口
 * @author lzl
 *
 */
@RestController
@RequestMapping("/api/shelf")
public class ApiShelfController {
	
	@Autowired
	private ShelfService shelfService;
	
	/**
	 * 保存
	 * @return
	 */
    @PostMapping("save")
    public R save(@RequestBody ShelfEntity shelf, @RequestAttribute("userId") Long userId){
    	shelf.setMemberId(userId);
    	shelfService.save(shelf);
        return R.ok();
    }
    
    /**
     * 列表
     * @param userId
     * @return
     */
    @GetMapping("list")
    public R list(@RequestAttribute("userId") Long userId){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("memberId", userId);
    	List<ShelfEntity> shelfList = shelfService.queryList(params);
        return R.ok().put("shelfList", shelfList);
    }
    
    /**
     * 数量
     * @param userId
     * @return
     */
    @GetMapping("total")
    public R total(@RequestAttribute("userId") Long userId){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("memberId", userId);
    	Integer total = shelfService.queryTotal(params);
        return R.ok().put("total", total);
    }
    
    /**
     * 删除
     * @param id
     * @return
     */
    @GetMapping("del")
    public R del(Integer id) {
    	shelfService.delete(id);
    	return R.ok();
    }
    
    /**
     * 更新
     * @param shelf
     * @param userId
     * @return
     */
    @PostMapping("update")
    public R update(@RequestBody ShelfEntity shelf, @RequestAttribute("userId") Long userId) {
    	shelfService.update(shelf);
    	return R.ok();
    }
}
