package com.ybliu.criteria;

import java.util.HashMap;
import java.util.Map;

/** 条件查询对象 **/
public class Filter {
	
	public static final String GT="gt";// 大于 > 
	
	// 装条件映射值
	private Map<KV, String> map = new HashMap<KV, String>();

	/** 设置条件
     * @param key 属性名称
     * @param value 对应的值
     * @param type 查询的条件，默认为 == 。
     */
	public void setValue(String key, String value,String type) {
		
	       map.put(new KV(key,value), type);
	        
	}
	
	  
	 public Map<KV, String> getMap() {
	        return map;
	 };

}
