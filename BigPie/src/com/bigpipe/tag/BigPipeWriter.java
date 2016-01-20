package com.bigpipe.tag;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class BigPipeWriter {

	private static BigPipeWriter jsWriter = new BigPipeWriter();

	private BigPipeWriter() {
	}

	public static BigPipeWriter instance() {
		return jsWriter;
	}

	private static final String KEY = "hasWriteMainJS";

	public void writeFromToTo(String name, Writer writer, boolean copy) {
		try {
			writer.write("<script type='text/javascript'>moveFrom('" + name + "_from','" + name + "', "+copy+");</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeJavaScript(String filePath, Writer writer) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Object attribute = request.getAttribute(KEY);
		if (attribute == null && filePath != null && !"".equals(filePath)) {
			request.setAttribute(KEY, new Object());
			try {
				writer.write("<script type='text/javascript' src='" + filePath
						+ "'></script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
