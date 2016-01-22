package com.ybliu.aop;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
 

@Controller("PersonControl")
public class PersonControl {
	
	//���淽�� ������Ҫ��֤�����ķ���
	@ValidateGroup(fileds = {
			//index=0 ��ʾ���淽���ĵ�һ��������Ҳ����person  nutNull=true ��ʾ����Ϊ��
			@ValidateFiled(index=0 , notNull=true ) ,
			//index=0 ��ʾ��һ������  filedName��ʾ�ò�����һ������ ��Ҳ����person.id ��СֵΪ3 Ҳ���� person.id ��СֵΪ3
			@ValidateFiled(index=0 , notNull=true , filedName="id" , minVal = 3) ,
			//��ʾ��һ��������name Ҳ����person.name������󳤶�Ϊ10����С����Ϊ3
			@ValidateFiled(index=0 , notNull=true , filedName="name" , maxLen = 10 , minLen = 3 ) ,
			//index=1 ��ʾ�ڶ���������󳤶�Ϊ5����С����Ϊ2
			@ValidateFiled(index=1 , notNull=true , maxLen = 5 , minLen = 2 ) ,
			@ValidateFiled(index=2 , notNull=true , maxVal = 100 , minVal = 18),
			@ValidateFiled(index=3 , notNull=false , regStr= "^\\w+@\\w+\\.com$" )
	})
	@RequestMapping("savePerson")
	public ModelAndView savePerson(Person person , String name , int age , String email){
		ModelAndView mav = new ModelAndView("/index.jsp");
		System.out.println("addPerson()�������óɹ���");
		return mav ;		//����index.jsp��ͼ
	}
	
}
