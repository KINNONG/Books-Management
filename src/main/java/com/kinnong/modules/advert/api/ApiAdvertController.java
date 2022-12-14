package com.kinnong.modules.advert.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kinnong.common.annotation.AuthIgnore;
import com.kinnong.common.utils.R;
import com.kinnong.modules.advert.entity.AdvertEntity;
import com.kinnong.modules.advert.service.AdvertService;


@RestController
@RequestMapping("/api/advert/")
public class ApiAdvertController {
	
	@Autowired
	private AdvertService advertService;
	
	@AuthIgnore
    @GetMapping("list")
    public R list(){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("enable", 1);
    	params.put("sidx", "sort");
    	params.put("order", "ASC");
    	List<AdvertEntity> advertList = advertService.queryList(params);
        return R.ok().put("advertList", advertList);
    }
}
