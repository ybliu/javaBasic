package com.bigpipe.tag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.util.ValueStack;

public class Index4 implements IPageLetDealer {

	@Override
	public PageAndModel<String, Object> deal(ValueStack vs,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PageAndModel<String, Object> pm = new PageAndModel<String, Object>();
		pm.setPage("index.ftl");
		PageResult pr = new PageResult();
		long start = System.currentTimeMillis();
		pr.setSecond(4);
		Thread.sleep(4000);
		pr.setSecs(System.currentTimeMillis() - start);
		pm.setModel(pr);
		return pm;
	}
}
