<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<h4>
   File Name : <s:property value="fileUploadFileName"/> 
</h4> 
 
<h4>
   Content Type : <s:property value="fileUploadContentType"/> 
</h4> 
 
<h4>
   File : <s:property value="fileUpload"/> 
</h4> 