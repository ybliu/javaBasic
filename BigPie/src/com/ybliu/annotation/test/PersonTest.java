package com.ybliu.annotation.test;

import java.lang.reflect.Method;


public class PersonTest {

	@SuppressWarnings("unchecked")
	public static void main(String args[])
	{
		
		try {
			Class clazz= Class.forName("com.ybliu.annotation.test.Person");
			boolean isAnnotation=clazz.isAnnotationPresent(DescriptionDemo.class);
			//1、获取类的注解
			if(isAnnotation)
			{
				DescriptionDemo ps=(DescriptionDemo)clazz.getAnnotation(DescriptionDemo.class);
				System.out.println(ps.age());
				
			}
			//2、获取方法的注解
			Method [] methods=clazz.getMethods();
			for(Method md : methods)
			{
				if(md.isAnnotationPresent(DescriptionDemo.class))
				{
					DescriptionDemo dd=md.getAnnotation(DescriptionDemo.class);
					System.out.println(dd.age());
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				 
		
	}
}
