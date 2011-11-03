<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<h2>Prüfung anlegen</h2>
<s:form validate="true">
	<s:textfield name="message" key="lblLecturer" required="true" />
	<s:textfield name="message2" key="lblDate" required="true" />
	<s:submit key="btnSave" action="SaveNewExam" />
	<s:submit key="btnCancel" action="SaveNewExam" name="btnCancle" />
</s:form>