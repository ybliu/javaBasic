package com.ybliu.round;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Roundtest {
//http://blog.csdn.net/chenssy/article/details/12719811
		public static void main(String args[])
		{
			System.out.println("12.5的四舍五入值：" + Math.round(12.5));  
	        System.out.println("-12.5的四舍五入值：" + Math.round(-12.5));  
	        System.out.println("-----------------------------------------------");  

	        //下面实例是使用银行家舍入法：
	        
	        BigDecimal d = new BigDecimal(100000);      //存款  
	        BigDecimal r = new BigDecimal(0.001875*3);   //利息  
	        BigDecimal i = d.multiply(r).setScale(2,RoundingMode.HALF_EVEN);     //使用银行家算法   
	        System.out.println("银行家算法--季利息是："+i);
	        System.out.println("-----------------------------------------------");  

	        //方法一：四舍五入
	        double   f   =   111231.5585;  
	        BigDecimal   b   =   new   BigDecimal(f);  
	        double   f1   =   b.setScale(2,   RoundingMode.HALF_UP).doubleValue();  
	        System.out.println("四舍五入方法一："+f1);  
	        System.out.println("-----------------------------------------------"); 
	        java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");  
	       
	        System.out.println("四舍五入方法二："+ df.format(111231.5585));  
	        System.out.println("-----------------------------------------------"); 
	        /*	     
            floor 向下取整   
	        ceil  向上取整   
	        round 则是4舍5入的计算，round方法，它表示“四舍五入”，
	         算法为Math.floor(x+0.5)，即将原来的数字加上0.5后再向下取整，所以,
	        Math.round(11.5)的结果为12，Math.round(-11.5)的结果为-11。
	        */
	        System.out.println(Math.floor(1.4));
	        System.out.println(Math.floor(1.6));
	        System.out.println(Math.floor(-1.4));
	        System.out.println(Math.ceil(1.4));
	        System.out.println(Math.ceil(1.6));
	        System.out.println(Math.ceil(-1.4));
		}
}
