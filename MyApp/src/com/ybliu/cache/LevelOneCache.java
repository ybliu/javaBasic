package com.ybliu.cache;

/**纯Java代码模拟Hibernate一级缓存原理，简单易懂。**/


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class LevelOneCache {
	 //这个对象就是用来模拟hibernate一级缓存的
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
		    System.out.println("从缓存中取数据");
		    return stus.get(id);
		  } else {
		    System.out.println("从数据库中取数据");
		    Student s=MyDB.getStudentById(id);
		    //将从数据库中取得的数据放入缓存
		    stus.put(id, s);
		    return s;
		  }
		  
	}
}
//模拟数据库
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
		  //初始化数据库
		  System.out.println("初始化数据库");
		  list.add(s1);
		  list.add(s2);
		  list.add(s3);
	 }
	 //数据库中提供公共的查询方法
	 public static Student getStudentById(Integer id){
		  for(Student s:list){
		   if(s.getId().equals(id)){
		    return s;
		   }
		  }
		  //查询不到则返回空
		  return null;
	 }
}
//domain对象
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
