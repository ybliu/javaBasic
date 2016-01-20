package com.bigpipe.tag;

import java.io.IOException;
import java.io.Writer;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name = "bigPipeFrom", tldTagClass = "com.bigpipe.tag.BigPipeFromTag", description = "BigPipeFrom Tag")
public class BigPipeFrom extends Component {

	private String name;

	private String bigPipeJSPath;

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

	public BigPipeFrom(ValueStack stack) {
		super(stack);
	}

	public boolean start(final Writer writer) {

		boolean result = super.start(writer);
		try {
			writer.flush();
			if (visiable)
			{
				writer.write("<div style='display:none' id='" + name + "_from'>");
			} else {
				writer.write("<div id='" + name + "_from'>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean end(Writer writer, String body) {

		boolean end = super.end(writer, body);
		try {
			writer.write("</div>");
			BigPipeWriter.instance().writeJavaScript(bigPipeJSPath, writer);
			BigPipeWriter.instance().writeFromToTo(name, writer, copy);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return end;
	}
}
