package com.kinnong.modules.wechat.utils;

import java.util.HashMap;
import java.util.Map;

public class CacheUtils {
	static Map<String, String> map = new HashMap<String, String>();
	
	public static void put(String key, String val) {
		map.put(key, val);
	}
	
}
