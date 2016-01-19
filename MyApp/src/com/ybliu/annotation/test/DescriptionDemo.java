package com.ybliu.annotation.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*
   ElemenetType.CONSTRUCTOR ������������
   ElemenetType.FIELD ������������ enum ʵ������
   ElemenetType.LOCAL_VARIABLE �ֲ�����������
   ElemenetType.METHOD ����������
   ElemenetType.PACKAGE ��������
   ElemenetType.PARAMETER ����������
   ElemenetType.TYPE �࣬�ӿڣ�����ע�����ͣ���enum������
   
   RetentionPolicy.SOURCE ע�⽫��������������
   RetentionPolicy.CLASS ע����class�ļ��п��ã����ᱻVM������
   RetentionPolicy.RUNTIME VM����������Ҳ����ע�ͣ���˿���ͨ��������ƶ�ȡע�����Ϣ��
 */
@Target({ElementType.METHOD,ElementType.TYPE})//������
@Retention(RetentionPolicy.RUNTIME)//��������(���л���)
@Inherited//��������̳и����е�ע��
@Documented//����ע������� javadoc ��
public @interface DescriptionDemo {
	
	String name() default "ybliu";
	
	int age() default 18;
}
