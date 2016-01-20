package com.bigpipe.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class BigPipeFromTag extends ComponentTagSupport {

	private static final long serialVersionUID = 1L;

	private String name;
	
	private boolean copy = false;
	
	private boolean visiable = true;
	
	public boolean isCopy() {
		return copy;
	}

	public boolean isVisiable() {
		return visiable;
	}

	public void setCopy(boolean copy) {
		this.copy = copy;
	}

	public void setVisiable(boolean visiable) {
		this.visiable = visiable;
	}

	private String bigPipeJSPath;
	
	public String getBigPipeJSPath() {
		return bigPipeJSPath;
	}

	public void setBigPipeJSPath(String bigPipeJSPath) {
		this.bigPipeJSPath = bigPipeJSPath;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public Component getBean(ValueStack vs, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new BigPipeFrom(vs);
	}

	protected void populateParams() {
		super.populateParams();
		BigPipeFrom pages = (BigPipeFrom)component;         
        pages.setName(name);  
        pages.setBigPipeJSPath(bigPipeJSPath);
        pages.setVisiable(visiable);
        pages.setCopy(copy);
	}
}
