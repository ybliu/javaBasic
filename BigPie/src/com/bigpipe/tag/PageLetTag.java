package com.bigpipe.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class PageLetTag extends ComponentTagSupport {

	private static final long serialVersionUID = 1L;

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	private String dealClass;
	
	public void setDealClass(String dealClass) {
		this.dealClass = dealClass;
	}
	
	public String getDealClass() {
		return dealClass;
	}

	@Override
	public Component getBean(ValueStack vs, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new PageLet(vs, arg1, arg2);
	}

	protected void populateParams() {
		super.populateParams();
		PageLet pages = (PageLet)component;         
        pages.setName(name);      
        pages.setDealClass(dealClass);      
	}
}
