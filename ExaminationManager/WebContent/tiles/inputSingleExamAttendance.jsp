<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:form validate="true">
	<s:token />
	<s:textfield name="studentN" id="studentI" key="lblStudentName"
		required="true" />
	<s:hidden name="studentNid" id="studentIid" />
	<s:textfield name="examSubject" key="lblExamSubject" required="true" />
	<s:select name="exam" key="lblExam" list="{}" disabled="true"
		required="true" />
	<s:select key="lblExamGrade" list="allGrades" required="true" />

	<s:submit key="btnSave" action="FileSingleExamAttendance" method="save" />
	<s:submit key="btnCancel" action="SaveNewExam" name="btnCancel" />
</s:form>