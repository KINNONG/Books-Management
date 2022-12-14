package com.kinnong.modules.order.api;

import java.math.BigDecimal;
import java.util.Date;
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

import com.kinnong.common.utils.DateUtils;
import com.kinnong.common.utils.R;
import com.kinnong.modules.book.entity.BookEntity;
import com.kinnong.modules.book.service.BookService;
import com.kinnong.modules.member.entity.MemberEntity;
import com.kinnong.modules.member.service.MemberService;
import com.kinnong.modules.order.entity.OrderBookEntity;
import com.kinnong.modules.order.entity.OrderEntity;
import com.kinnong.modules.order.service.OrderService;

/**
 * 借阅接口
 * @author 2803180149
 *
 */
@RestController
@RequestMapping("/api/order")
public class ApiOrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private MemberService memberService;
	
    @GetMapping("list")
    public R getOrderList(@RequestAttribute Long userId, Integer status){
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("orderStatus", status);
    	params.put("memberId", userId);
    	List<OrderEntity> orderList = orderService.queryList(params);
        return R.ok().put("orderList", orderList);
    }
    
    @PostMapping("create")
    public R createOrder(@RequestAttribute("userId") Long userId, @RequestBody OrderEntity order){
    	
    	MemberEntity member = memberService.queryObject(userId);
    	if(member.getDeposit().compareTo(new BigDecimal(0)) < 1) {
    		return R.error("您还未交押金");
    	}
    	
    	List<OrderBookEntity> orderBookList = order.getOrderBookList();
    	for (OrderBookEntity orderBook : orderBookList) {
    		BookEntity book = bookService.queryObject(orderBook.getBookId());
    		if(book.getStock() < 1) {
    			return R.error(book.getBookName() + " 库存不足");
    		}
		}
    	
    	order.setMemberId(userId);
    	order.setCreateTime(new Date());
    	order.setOrderStatus(1);
    	order.setStartDate(new Date());
    	order.setEndDate(DateUtils.add(new Date(), 14));
    	orderService.createOrder(order);
    	return R.ok();
    }
    
    @GetMapping("detail")
    public R detail(Integer id) {
    	OrderEntity order = orderService.queryObject(id);
    	return R.ok().put("order", order);
    }
    
    @GetMapping("cancel")
    public R cancel(Integer id) {
    	OrderEntity order = new OrderEntity();
    	order.setOrderStatus(0);
    	order.setId(id);
    	orderService.update(order);
    	return R.ok();
    }
    
}
