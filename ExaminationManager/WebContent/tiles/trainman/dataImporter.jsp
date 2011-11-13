<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<s:form action="TrainmanResultAction" namespace="/" method="POST" enctype="multipart/form-data" validate="true">

	<s:token />

	<s:select key="lblTemplateFile"
		list="{'NordakademieTest.sql','NurWirtschaftsinformatiker.sql'}"
		required="true" />

	<s:file name="fileUpload" key="lblFileToUpload" size="40" />

	<s:submit value="submit" name="submit" />
	
	
	
	

</s:form>