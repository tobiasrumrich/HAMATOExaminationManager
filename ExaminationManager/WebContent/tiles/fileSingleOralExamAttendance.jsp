<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script>
	$(function() {
		$( "#date" ).datepicker({ dateFormat: 'dd.mm.yy' });
	});
	</script>
<!-- <s:date name="date" format="%{getText('examDateFormat')}" /> -->

<s:form validate="true">
	<s:token />
	<s:hidden name="examAttendanceId" value="%{examAttendanceId}" />
	<s:textfield name="student" key="lblStudentName" value="%{selectedStudent.forename} %{selectedStudent.lastname}" disabled="true" />
	<s:textfield name="examSubject" key="lblExamSubject" value="%{selectedExamSubject.title}" disabled="true" />
	<s:textfield key="lblDate" id="date" name="frmSupplementalOralExaminationDate" required="true" />
	<s:select key="lblExamGrade" list="oralExamGrades" 
		required="true" name="frmSupplementalOralExaminationGrade" />

	<s:submit key="btnSave" action="OralExamination" method="save" />
	<s:submit key="btnCancel" action="OralExamination" method="execute" />
</s:form>
