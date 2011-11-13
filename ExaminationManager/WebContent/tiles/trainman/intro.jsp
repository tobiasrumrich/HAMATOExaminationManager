<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<s:form method="POST">

	<s:token />
	<s:submit value="submit" name="submit" action="Trainman" method="insertData" />
	
</s:form>