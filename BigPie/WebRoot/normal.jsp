<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%long pstart = System.currentTimeMillis();%>
<table border="1" width="100%" height="500">
  <caption>普通例子</caption>
  <tr>
    <td>
    	<%
			long start = System.currentTimeMillis();
			Thread.sleep(1000);
			long seconds = System.currentTimeMillis() - start;
		%>
		1秒的内容<br>
		加载耗时：<%=seconds%> 毫秒;
    </td>
    <td>
    	<%
			start = System.currentTimeMillis();
			Thread.sleep(2000);
			seconds = System.currentTimeMillis() - start;
		%>
		2秒的内容<br>
		加载耗时：<%=seconds%> 毫秒;
    </td>
    <td>
    	<%
			start = System.currentTimeMillis();
			Thread.sleep(3000);
			seconds = System.currentTimeMillis() - start;
		%>
		3秒的内容<br>
		加载耗时：<%=seconds%> 毫秒;
    </td>
  </tr>
   <tr>
    <td>
    	<%
			start = System.currentTimeMillis();
			Thread.sleep(4000);
			seconds = System.currentTimeMillis() - start;
		%>
		4秒的内容<br>
		加载耗时：<%=seconds%> 毫秒;
    </td>
    <td>
    	<%
			start = System.currentTimeMillis();
			Thread.sleep(5000);
			seconds = System.currentTimeMillis() - start;
		%>
		5秒的内容<br>
		加载耗时：<%=seconds%> 毫秒;
    </td>
    <td>
    	<%
			start = System.currentTimeMillis();
			Thread.sleep(6000);
			seconds = System.currentTimeMillis() - start;
		%>
		6秒的内容<br>
		加载耗时：<%=seconds%> 毫秒;
    </td>
  </tr>
</table>
<%seconds = System.currentTimeMillis() - pstart;%>
整个页面加载耗费了：<%=seconds%>毫秒