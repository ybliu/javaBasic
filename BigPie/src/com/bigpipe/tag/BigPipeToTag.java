package com.bigpipe.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class BigPipeToTag extends ComponentTagSupport {

	private static final long serialVersionUID = 1L;

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public Component getBean(ValueStack vs, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new BigPipeTo(vs);
	}

	protected void populateParams() {
		super.populateParams();
		BigPipeTo pages = (BigPipeTo)component;         
        pages.setName(name);      
	}
}
