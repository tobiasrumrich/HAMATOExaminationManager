<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:form validate="true">
	<s:token />
	<s:textfield name="student" key="lblStudentName" disabled="true" />
	<s:textfield name="examSubject" key="lblExamSubject" disabled="true" />
	<s:select name="date" key="lblExamDate" list="{}" required="true" />
	<s:select key="lblExamGrade" list="{1.0,2.0,'a','sample','list'}" required="true"/>

	<s:submit key="btnSave" action="FileSingleOralExamAttendance" />
	<s:submit key="btnCancel" action="" name="btnCancel" />
</s:form>
