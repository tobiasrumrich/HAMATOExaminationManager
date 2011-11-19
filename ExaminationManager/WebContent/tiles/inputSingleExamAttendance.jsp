<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:url action="FileSingleExamAttendance" method="getExamsForStudent"
	var="ajaxGetUrl">
	<s:param name="studentId" value="-1" />
</s:url>

<script>
	var allStudents = <s:property value="allStudents" escape="false"/>;
	var genericUrl = "<s:property value='ajaxGetUrl' />";

	$(function() {
		hamatoAutocomplete($("#studentI"), $("#studentIid"), allStudents);
		$("#studentIid").change(function() {
			$('#examI').find('option').remove();
			$.ajax({
				url : genericUrl.replace("-1", $("#studentIid").val()),
				context : document.body,
				success : function() {
					$(this).addClass("done");
				}
			});
		});

	});
</script>
<s:debug />
<s:form validate="true">
	<s:token />
	<s:textfield name="studentN" id="studentI" key="lblStudentName"
		required="true" />
	<s:hidden name="studentNid" id="studentIid" />
	<s:select name="examN" id="examI" key="lblExam" list="{}"
		disabled="true" required="true" />
	<s:select key="lblExamGrade" list="allGrades" required="true" />

	<s:submit key="btnSave" action="FileSingleExamAttendance" method="save" />
	<s:submit key="btnCancel" action="SaveNewExam" name="btnCancel" />
</s:form>

