package com.ybliu.entity;

import com.ybliu.annotation.Column;
import com.ybliu.annotation.Table;

@Table("tab_apple")
public class Apple {

	@Column("apple_name")
	private String name; // ����
	
	@Column("apple_price")
	private double price; // �۸�
	
	public String getName(){
		
	  return name;
	  
	}

	public void setName(String name){
		
	        this.name = name;
	 }
	
    public double getPrice(){
    	
	        return price;
	        
	 }

	public void setPrice(double price){
		
	       this.price = price;
	 }
}
