<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="b" uri="/WEB-INF/bigpipe.tld"%>  
<%long pstart = System.currentTimeMillis();%>
<table border="1" width="100%" height="500">
  <caption>单线程例子</caption>
  <tr>
    <td><b:bigPipeTo name="index1">编号：1<img src="images/loading.gif"/></b:bigPipeTo></td>
    <td><b:bigPipeTo name="index2">编号：2<img src="images/loading.gif"/></b:bigPipeTo></td>
    <td><b:bigPipeTo name="index3">编号：3<img src="images/loading.gif"/></b:bigPipeTo></td>
  </tr>
   <tr>
    <td><b:bigPipeTo name="index4">编号：4<img src="images/loading.gif"/></b:bigPipeTo></td>
    <td><b:bigPipeTo name="index5">编号：5<img src="images/loading.gif"/></b:bigPipeTo></td>
    <td><b:bigPipeTo name="index6">编号：6<img src="images/loading.gif"/></b:bigPipeTo></td>
  </tr>
</table>

<b:bigPipeFrom name="index6" bigPipeJSPath="js/bigpipe.js">
<%
	long start = System.currentTimeMillis();
	Thread.sleep(6000);
	long seconds = System.currentTimeMillis() - start;
%>
6秒的内容<br>
加载耗时：<%=seconds%> 毫秒;
</b:bigPipeFrom>

<b:bigPipeFrom name="index5" bigPipeJSPath="js/bigpipe.js">
<%
	long start = System.currentTimeMillis();
	Thread.sleep(5000);
	long seconds = System.currentTimeMillis() - start;
%>
5秒的内容<br>
加载耗时：<%=seconds%> 毫秒;
</b:bigPipeFrom>

<b:bigPipeFrom name="index4" bigPipeJSPath="js/bigpipe.js">
<%
	long start = System.currentTimeMillis();
	Thread.sleep(4000);
	long seconds = System.currentTimeMillis() - start;
%>
4秒的内容<br>
加载耗时：<%=seconds%> 毫秒;
</b:bigPipeFrom>

<b:bigPipeFrom name="index3" bigPipeJSPath="js/bigpipe.js">
<%
	long start = System.currentTimeMillis();
	Thread.sleep(3000);
	long seconds = System.currentTimeMillis() - start;
%>
3秒的内容<br>
加载耗时：<%=seconds%> 毫秒;
</b:bigPipeFrom>

<b:bigPipeFrom name="index2" bigPipeJSPath="js/bigpipe.js">
<%
	long start = System.currentTimeMillis();
	Thread.sleep(2000);
	long seconds = System.currentTimeMillis() - start;
%>
2秒的内容<br>
加载耗时：<%=seconds%> 毫秒;
</b:bigPipeFrom>


<b:bigPipeFrom name="index1" bigPipeJSPath="js/bigpipe.js">
<%
	long start = System.currentTimeMillis();
	Thread.sleep(1000);
	long seconds = System.currentTimeMillis() - start;
%>
1秒的内容<br>
加载耗时：<%=seconds%> 毫秒;
</b:bigPipeFrom>

<%long secs = System.currentTimeMillis() - pstart;%>
整个页面加载耗费了：<%=secs%>毫秒