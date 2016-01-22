package com.ybliu.aop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 

@Controller("PersonControl")
public class PersonControl {
	
	//下面方法 ，是需要验证参数的方法
	@ValidateGroup(fileds = {
			//index=0 表示下面方法的第一个参数，也就是person  nutNull=true 表示不能为空
			@ValidateFiled(index=0 , notNull=true ) ,
			//index=0 表示第一个参数  filedName表示该参数的一个属性 ，也就是person.id 最小值为3 也就是 person.id 最小值为3
			@ValidateFiled(index=0 , notNull=true , filedName="id" , minVal = 3) ,
			//表示第一个参数的name 也就是person.name属性最大长度为10，最小长度为3
			@ValidateFiled(index=0 , notNull=true , filedName="name" , maxLen = 10 , minLen = 3 ) ,
			//index=1 表示第二个参数最大长度为5，最小长度为2
			@ValidateFiled(index=1 , notNull=true , maxLen = 5 , minLen = 2 ) ,
			@ValidateFiled(index=2 , notNull=true , maxVal = 100 , minVal = 18),
			@ValidateFiled(index=3 , notNull=false , regStr= "^\\w+@\\w+\\.com$" )
	})
	@RequestMapping("savePerson")
	public ModelAndView savePerson(Person person , String name , int age , String email){
		ModelAndView mav = new ModelAndView("/index.jsp");
		System.out.println("addPerson()方法调用成功！");
		return mav ;		//返回index.jsp视图
	}
	
}
