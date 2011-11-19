<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script>
	$(function() {
		$("#date").datepicker({
			dateFormat : 'dd.mm.yy',
			firstDay : 0,
			currentText : '<s:text name="lblToday" />',
			dayNamesMin: ['<s:text name="lblMondayShort" />','<s:text name="lblTuesdayShort" />','<s:text name="lblWednesdayShort" />','<s:text name="lblThursdayShort" />','<s:text name="lblFridayShort" />','<s:text name="lblSaturdayShort" />','<s:text name="lblSundayShort" />'],
			monthNames: ['<s:text name="lblJanuary" />','<s:text name="lblFebruary" />','<s:text name="lblMarch" />','<s:text name="lblApril" />','<s:text name="lblMay" />','<s:text name="lblJune" />','<s:text name="lblJuly" />','<s:text name="lblAugust" />','<s:text name="lblSeptember" />','<s:text name="lblOctober" />','<s:text name="lblNovember" />','<s:text name="lblDecember" />'],
			maxDate: '+0m +0w +0d' 
		});
	})
</script>
<!-- <s:date name="date" format="%{getText('examDateFormat')}" /> -->

<s:form validate="true">
	<s:token />
	<s:hidden name="examAttendanceId" value="%{examAttendanceId}" />
	<s:textfield name="student" key="lblStudentName"
		value="%{selectedStudent.forename} %{selectedStudent.lastname}"
		disabled="true" />
	<s:textfield name="examSubject" key="lblExamSubject"
		value="%{selectedExamSubject.title}" disabled="true" />
	<s:textfield key="lblDate" id="date"
		name="frmSupplementalOralExaminationDate" required="true" />
	<s:select key="lblExamGrade" list="oralExamGrades" required="true"
		name="frmSupplementalOralExaminationGrade" />

	<s:submit key="btnSave" action="OralExamination" method="save" />
	<s:submit key="btnCancel" action="OralExamination" method="execute" />
</s:form>
