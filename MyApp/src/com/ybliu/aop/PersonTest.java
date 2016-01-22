package com.ybliu.aop;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
 
public class PersonTest {
	public static void main(String[] args) {
		
 		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		PersonControl ps = (PersonControl) ac.getBean("PersonControl");	//≤‚ ‘
		ps.savePerson(new Person(3, "qqq") , "sss" , 100 , "243970446@qq.com");
	}
}
