<!-- author Marcel Schroeter, 3690 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script>
	$(function() {
		$("#date").datepicker(
				{
					dateFormat : 'dd.mm.yy',
					firstDay : 0,
					currentText : '<s:text name="jQueryDatePickerToday" />',
					dayNamesMin : [
							'<s:text name="jQueryDatePickerMondayShort" />',
							'<s:text name="jQueryDatePickerTuesdayShort" />',
							'<s:text name="jQueryDatePickerWednesdayShort" />',
							'<s:text name="jQueryDatePickerThursdayShort" />',
							'<s:text name="jQueryDatePickerFridayShort" />',
							'<s:text name="jQueryDatePickerSaturdayShort" />',
							'<s:text name="jQueryDatePickerSundayShort" />' ],
					monthNames : [ '<s:text name="jQueryDatePickerJanuary" />',
							'<s:text name="jQueryDatePickerFebruary" />',
							'<s:text name="jQueryDatePickerMarch" />',
							'<s:text name="jQueryDatePickerApril" />',
							'<s:text name="jQueryDatePickerMay" />',
							'<s:text name="jQueryDatePickerJune" />',
							'<s:text name="jQueryDatePickerJuly" />',
							'<s:text name="jQueryDatePickerAugust" />',
							'<s:text name="jQueryDatePickerSeptember" />',
							'<s:text name="jQueryDatePickerOctober" />',
							'<s:text name="jQueryDatePickerNovember" />',
							'<s:text name="jQueryDatePickerDecember" />' ],
					maxDate : '+0m +0w +0d'
				});
	})
</script>
<s:form validate="true">
	<s:token />
	<s:hidden name="examAttendanceId"  />
	<s:hidden name="frmStudent" />
	<s:hidden name="frmSubject" />

	<s:textfield name="frmStudent" key="lblStudentName" disabled="true" />
	<s:textfield name="frmSubject" key="lblExamSubject" disabled="true" />
	<s:textfield key="lblDate" id="date"
		name="frmSupplementalOralExaminationDate" required="true" />
	<s:select key="lblExamGrade" list="oralExamGrades" required="true"
		name="frmSupplementalOralExaminationGrade" />

	<s:submit key="btnSave" action="OralExamination" method="save" />
	<s:submit key="btnCancel" action="OralExamination" method="execute" />
</s:form>
