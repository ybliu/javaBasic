package com.ybliu.round;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Roundtest {
//http://blog.csdn.net/chenssy/article/details/12719811
		public static void main(String args[])
		{
			System.out.println("12.5����������ֵ��" + Math.round(12.5));  
	        System.out.println("-12.5����������ֵ��" + Math.round(-12.5));  
	        System.out.println("-----------------------------------------------");  

	        //����ʵ����ʹ�����м����뷨��
	        
	        BigDecimal d = new BigDecimal(100000);      //���  
	        BigDecimal r = new BigDecimal(0.001875*3);   //��Ϣ  
	        BigDecimal i = d.multiply(r).setScale(2,RoundingMode.HALF_EVEN);     //ʹ�����м��㷨   
	        System.out.println("���м��㷨--����Ϣ�ǣ�"+i);
	        System.out.println("-----------------------------------------------");  

	        //����һ����������
	        double   f   =   111231.5585;  
	        BigDecimal   b   =   new   BigDecimal(f);  
	        double   f1   =   b.setScale(2,   RoundingMode.HALF_UP).doubleValue();  
	        System.out.println("�������뷽��һ��"+f1);  
	        System.out.println("-----------------------------------------------"); 
	        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
	       
	        System.out.println("�������뷽������"+ df.format(111231.5585));  
	        System.out.println("-----------------------------------------------"); 
	        /*	     
            floor ����ȡ��   
	        ceil  ����ȡ��   
	        round ����4��5��ļ��㣬round����������ʾ���������롱��
	         �㷨ΪMath.floor(x+0.5)������ԭ�������ּ���0.5��������ȡ��������,
	        Math.round(11.5)�Ľ��Ϊ12��Math.round(-11.5)�Ľ��Ϊ-11��
	        */
	        System.out.println(Math.floor(1.4));
	        System.out.println(Math.floor(1.6));
	        System.out.println(Math.floor(-1.4));
	        System.out.println(Math.ceil(1.4));
	        System.out.println(Math.ceil(1.6));
	        System.out.println(Math.ceil(-1.4));
		}
}
