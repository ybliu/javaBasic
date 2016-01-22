package com.bigpipe.tag;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import freemarker.template.Configuration;
import freemarker.template.ObjectWrapper;

public class FreeMarkerInstance {

	private static FreeMarkerInstance instance = null;
	
	private static Configuration cfg = new Configuration();
	
	private FreeMarkerInstance() {}
	
	public Configuration getConfiguration()
	{
		return cfg;
	}
	
	public static FreeMarkerInstance instance(HttpServletRequest request)
	{
		if (instance == null)
		{
			try {
				cfg.setDirectoryForTemplateLoading(
						new File(request.getRealPath("/WEB-INF/template")));
				cfg.setObjectWrapper(ObjectWrapper.BEANS_WRAPPER);
			} catch (IOException e) {
				e.printStackTrace();
			}
			instance = new FreeMarkerInstance();
		}
		return instance;
	}
}
