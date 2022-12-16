package com.kinnong.modules.book.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.utils.R;
import com.kinnong.modules.book.entity.HistoryEntity;
import com.kinnong.modules.book.service.HistoryService;

/**
 * 分类接口
 * @author ZJL
 * @email 1044869436@qq.com
 */
@RestController
@RequestMapping("/api/history")
public class ApiHistoryController {
	
	@Autowired
	private HistoryService historyService;
	
	/**
	 * 列表
	 * @return
	 */
    @PostMapping("save")
    public R save(@RequestBody HistoryEntity history, @RequestAttribute("userId") Long userId){
    	history.setCreateTime(new Date());
    	history.setMemberId(userId);
    	historyService.save(history);
        return R.ok();
    }
    
   
}
