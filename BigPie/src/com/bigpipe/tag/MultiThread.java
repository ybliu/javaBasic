package com.bigpipe.tag;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name = "multiThread", tldTagClass = "com.bigpipe.tag.MultiThreadTag", description = "MultiThread Tag")
public class MultiThread extends Component {

	private String pageLetNum;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public String getPageLetNum() {
		return pageLetNum;
	}

	public void setPageLetNum(String pageLetNum) {
		this.pageLetNum = pageLetNum;
	}

	public MultiThread(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		super(stack);
		this.request = request;
		this.response = response;
	}
	
	private String bigPipeJSPath;
	
	public String getBigPipeJSPath() {
		return bigPipeJSPath;
	}

	public void setBigPipeJSPath(String bigPipeJSPath) {
		this.bigPipeJSPath = bigPipeJSPath;
	}
	
	@Override
	public boolean start(Writer writer) {
		boolean start = super.start(writer);
		try {
			writer.write("<script type='text/javascript' src='" + bigPipeJSPath
					+ "'></script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return start;
	}

	@Override
	public boolean end(Writer writer, String body) {
		boolean end = super.end(writer, body);
		CountDownLatch c = (CountDownLatch)request.getAttribute(MultiThreadTag.COUNT_DOWN);
		try {
			c.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return end;
	}
}
