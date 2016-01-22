package com.ybliu.annotation.test;

 
@DescriptionDemo(name="ybliu",age=20)
public class Person {
	
	private String name;
	
	private String sex;
	
	private int age;
	
	@DescriptionDemo(name="myMethoed",age=22)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	 
}
