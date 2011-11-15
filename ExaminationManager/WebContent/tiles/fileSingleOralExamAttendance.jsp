<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script>
	$(function() {
		$( "#date" ).datepicker();
	});
	</script>
<!-- <s:date name="date" format="%{getText('examDateFormat')}" /> -->

<s:form validate="true">
	<s:token />
	<s:textfield name="selectedStudent.forename" key="lblStudentName" value="%{selectedStudent.forename} %{selectedStudent.lastname}" disabled="true" />
	<s:textfield name="examSubject" key="lblExamSubject" disabled="true" />
	<s:textfield key="lblDate" id="date" name="date" required="true" />
	<s:select key="lblExamGrade" list="{1.0,2.0,'a','sample','list'}"
		required="true" />

	<s:submit key="btnSave" action="FileSingleOralExamAttendance" />
	<s:submit key="btnCancel" action="" name="btnCancel" />
</s:form>
