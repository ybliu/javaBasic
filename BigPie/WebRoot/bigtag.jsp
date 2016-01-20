<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="b" uri="/WEB-INF/bigpipe.tld"%>  
<%long pstart = System.currentTimeMillis();%>
<table border="1" width="100%" height="500">
  <caption>单线程例子</caption>
  <tr>
    <td><b:bigPipeTo name="index1">编号：1<img src="images/loading.gif"/></b:bigPipeTo></td>
  </tr>
   
</table>

 