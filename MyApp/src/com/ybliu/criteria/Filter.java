package com.ybliu.criteria;

import java.util.HashMap;
import java.util.Map;

/** ������ѯ���� **/
public class Filter {
	
	public static final String GT="gt";// ���� > 
	
	// װ����ӳ��ֵ
	private Map<KV, String> map = new HashMap<KV, String>();

	/** ��������
     * @param key ��������
     * @param value ��Ӧ��ֵ
     * @param type ��ѯ��������Ĭ��Ϊ == ��
     */
	public void setValue(String key, String value,String type) {
		
	       map.put(new KV(key,value), type);
	        
	}
	
	  
	 public Map<KV, String> getMap() {
	        return map;
	 };

}
