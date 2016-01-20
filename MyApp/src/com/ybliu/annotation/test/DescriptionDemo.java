package com.ybliu.annotation.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*
   ElemenetType.CONSTRUCTOR 构造器声明。
   ElemenetType.FIELD 域声明（包括 enum 实例）。
   ElemenetType.LOCAL_VARIABLE 局部变量声明。
   ElemenetType.METHOD 方法声明。
   ElemenetType.PACKAGE 包声明。
   ElemenetType.PARAMETER 参数声明。
   ElemenetType.TYPE 类，接口（包括注解类型）或enum声明。
   
   RetentionPolicy.SOURCE 注解将被编译器丢弃。
   RetentionPolicy.CLASS 注解在class文件中可用，但会被VM丢弃。
   RetentionPolicy.RUNTIME VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息。
 */
@Target({ElementType.METHOD,ElementType.TYPE})//作用域
@Retention(RetentionPolicy.RUNTIME)//生命周期(运行机制)
@Inherited//允许子类继承父类中的注解
@Documented//将此注解包含在 javadoc 中
public @interface DescriptionDemo {
	
	String name() default "ybliu";
	
	int age() default 18;
}
