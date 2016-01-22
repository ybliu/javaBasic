package com.ybliu.main;

import com.ybliu.entity.User;
import com.ybliu.session.Session;

/**
 * Ä£Äâsession
 * http://blog.csdn.net/wx_962464/article/details/7670471
 * 
 * 
 * http://bbs.csdn.net/topics/340117590
 */

public class hibernateSession {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			User user = new User();  
	        user.setId(1);  
	        user.setUsername("ÍõÐÂ");  
	        user.setPwd("wangxin");  
	          
	        Session session = new Session();  
	        session.save(user);  
			
		}

}


