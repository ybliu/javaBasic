package com.ybliu.aop;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ValidateFiled {
	
	/**
	 * ��������λ��
	 */
	public int index() default -1 ;
	
	/**
	 * ��������ǻ����������ͻ�String ���Ͳ���ָ���ò�������������Ƕ���Ҫ��֤��������ĳ�����ԣ����øò���ָ��������
	 */
	public String filedName() default "" ;
	
	/**
	 * ������֤
	 */
	public String regStr() default "";
	
	/**
	 * �Ƿ���Ϊ��  �� Ϊtrue��ʾ����Ϊ�� �� false��ʾ�ܹ�Ϊ��
	 */
	public boolean notNull() default false;
	
	/**
	 * �Ƿ���Ϊ��  �� Ϊtrue��ʾ����Ϊ�� �� false��ʾ�ܹ�Ϊ��
	 */
	public int maxLen() default -1 ;
	
	/**
	 * ��С���� �� �û���֤�ַ���
	 */
	public int minLen() default -1 ;
	
	/**
	 *���ֵ ��������֤������������
	 */
	public int maxVal() default -1 ;
	
	/**
	 *��Сֵ ��������֤��ֵ��������
	 */
	public int minVal() default -1 ;
	
}
