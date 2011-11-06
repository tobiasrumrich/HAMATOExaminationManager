<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:form validate="true">
	<s:token />
	<s:textfield name="formStudentId" key="lblStudentName" required="true" />
	<s:textfield name="examSubject" key="lblExamSubject" required="true" />
	<s:select name="exam" key="lblExam" list="{'apfelmuß','beerenschmand'}" required="true" />
	<s:select key="lblExamGrade" list="{1.0,2.0}" required="true"/>

	<s:submit key="btnSave" action="FileSingleExamAttendance" method="save" />
	<s:submit key="btnCancel" action="SaveNewExam" name="btnCancel" />
</s:form>