<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<h2>Prüfung anlegen</h2>
<s:form validate="true" action="SaveNewExam">
	<s:textfield name="lecturer" key="lblLecturer" required="true" />
	<s:textfield name="date" key="lblDate" required="true" />
	<s:submit key="btnSave" action="SaveNewExam" name="type" value="submit" />
	<s:submit key="btnCancel" action="SaveNewExam" name="type"
		value="cancle" />
</s:form>