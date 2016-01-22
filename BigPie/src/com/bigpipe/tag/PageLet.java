package com.bigpipe.tag;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name = "pageLet", tldTagClass = "com.bigpipe.tag.PageLetTag", description = "PageLet Tag")
public class PageLet extends Component {

	private String name;
	
	private HttpServletRequest request;
	private HttpServletResponse response;

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

	public PageLet(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		super(stack);
		this.request = request;
		this.response = response;
	}

	public boolean start(final Writer writer) {
		boolean result = super.start(writer);
		try {
			writer.write("<div id='"+name+"'>");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean end(final Writer writer, String body) {
		boolean end = super.end(writer, body);
		try {
			writer.write("</div>");
			writer.flush();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		MultiThreadTag.exe.execute(new Runnable() {
			@Override
			public void run() {
				CountDownLatch attribute = (CountDownLatch)request.getAttribute(MultiThreadTag.COUNT_DOWN);
				try
				{
					if (null != dealClass && !"".equals(dealClass))
					{
						IPageLetDealer pld = (IPageLetDealer)Class.forName(dealClass).newInstance();
						PageAndModel<String, Object> deal = pld.deal(getStack(), request, response);
						StringWriter sw = new StringWriter();
						FreeMarkerInstance.instance(request).getConfiguration()
								.getTemplate(deal.getPage())
								.process(deal.getModel(), sw);
						writer.write("<script type='text/javascript'>ii('"+name+"','"+sw.getBuffer().toString()+"');</script>");
					}
				}  catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						writer.flush();
						attribute.countDown();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		return end;
	}

}
