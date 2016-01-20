package com.bigpipe.tag;

import java.io.IOException;
import java.io.Writer;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name = "bigPipeTo", tldTagClass = "com.bigpipe.tag.BigPipeToTag", description = "BigPipeTo Tag")
public class BigPipeTo extends Component {

	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public BigPipeTo(ValueStack stack) {
		super(stack);
	}

	public boolean start(final Writer writer) {
		
		boolean result = super.start(writer);
		try {
			writer.write("<div id='"+name+"'>");
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
		} catch (IOException e) {
			e.printStackTrace();
		}
		return end;
	}
	
}
