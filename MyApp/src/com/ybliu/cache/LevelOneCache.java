package com.ybliu.cache;

/**��Java����ģ��Hibernateһ������ԭ�����׶���**/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class LevelOneCache {
	 //��������������ģ��hibernateһ�������
	 private static Map<Integer, Student> stus=new HashMap<Integer, Student>();
	
	 public static void main(String[] args) {
		  getStudent(1);
		  getStudent(1);
		  getStudent(1);
		  getStudent(2);
		  getStudent(2);
	 }
	 public static Student getStudent(Integer id){
		 
		  if(stus.containsKey(id)){
		    System.out.println("�ӻ�����ȡ����");
		    return stus.get(id);
		  } else {
		    System.out.println("�����ݿ���ȡ����");
		    Student s=MyDB.getStudentById(id);
		    //�������ݿ���ȡ�õ����ݷ��뻺��
		    stus.put(id, s);
		    return s;
		  }
		  
	}
}
//ģ�����ݿ�
class MyDB{
	 private static List<Student> list=new ArrayList<Student>();
	 static{
		  Student s1=new Student();
		  s1.setName("Name1");
		  s1.setId(1);
		  Student s2=new Student();
		  s2.setName("Name2");
		  s2.setId(2);
		  Student s3=new Student();
		  s3.setName("Name3");
		  s3.setId(3);
		  //��ʼ�����ݿ�
		  System.out.println("��ʼ�����ݿ�");
		  list.add(s1);
		  list.add(s2);
		  list.add(s3);
	 }
	 //���ݿ����ṩ�����Ĳ�ѯ����
	 public static Student getStudentById(Integer id){
		  for(Student s:list){
		   if(s.getId().equals(id)){
		    return s;
		   }
		  }
		  //��ѯ�����򷵻ؿ�
		  return null;
	 }
}
//domain����
class Student{
	
	 private Integer id;
	 
	 private String name;
	 
	 public Integer getId() {
	    return id;
	 }
	 
	 public void setId(Integer id) {
	    this.id = id;
	 }
	 
	 public String getName() {
	    return name;
	 }
	 
	 public void setName(String name) {
	    this.name = name;
	 }
}
