package com.bigpipe.tag;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class MultiThreadTag extends ComponentTagSupport {

	public static final String COUNT_DOWN = "countDown";

	private static final long serialVersionUID = 1L;
	
	public static ExecutorService exe = Executors.newFixedThreadPool(20);   
	
	private String pageLetNum;
	
	private String bigPipeJSPath;
	
	public String getBigPipeJSPath() {
		return bigPipeJSPath;
	}

	public void setBigPipeJSPath(String bigPipeJSPath) {
		this.bigPipeJSPath = bigPipeJSPath;
	}

	public String getPageLetNum() {
		return pageLetNum;
	}

	public void setPageLetNum(String pageLetNum) {
		this.pageLetNum = pageLetNum;
	}

	@Override
	public Component getBean(ValueStack vs, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		CountDownLatch end = new CountDownLatch(Integer.parseInt(pageLetNum));   
		arg1.setAttribute(COUNT_DOWN, end);
		return new MultiThread(vs, arg1, arg2);
	}

	protected void populateParams() {
		super.populateParams();
		MultiThread pages = (MultiThread)component;         
        pages.setPageLetNum(pageLetNum);      
        pages.setBigPipeJSPath(bigPipeJSPath);
	}
}
