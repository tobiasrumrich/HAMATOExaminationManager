<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
	$(function() {

		$('#examList').dataTable({
			"bAutoWidth" : true,
			"bProcessing" : true,
			"aoColumnDefs" : [ {
				"sType" : "string",
				"sWidth" : "120px",
				"aTargets" : [ 0 ]
			}, {
				"sWidth" : "120px",
				"aTargets" : [ 1 ]
			}, {
				"sWidth" : "20px",
				"bSortable" : false,
				"aTargets" : [ 4 ]
			} ],
			"bStateSave" : true,
			"oLanguage" : {
				"oPaginate" : {
					"sPrevious" : "<s:text name="jQueryDataTablesPrevious" />",
					"sNext" : "<s:text name="jQueryDataTablesNext" />",
					"sFirst" : "<s:text name="jQueryDataTablesFirst" />",
					"sLast" : "<s:text name="jQueryDataTablesLast" />)",
				},
				"sSearch" : "<s:text name="jQueryDataTablesSearch" />",
				"sEmptyTable" : "<s:text name ="jQueryDataTablesEmptyTable" />",
				"sInfo" : "<s:text name="jQueryDataTablesInfo" />",
				"sInfoEmpty" : "<s:text name="jQueryDataTablesInfoEmpty" />",
				"sInfoFiltered" : "<s:text name="jQueryDataTablesInfoFiltered" />",
				"sInfoThousands" : "<s:text name="jQueryDataTablesInfoThousands" />", //Tausender-Trennzeichen
				"sLengthMenu" : "<s:text name="jQueryDataTablesLengthMenu" />",
				"sProcessing" : "<s:text name="jQueryDataTablesProcessing" />",
				"sZeroRecords" : "<s:text name="jQueryDataTablesZeroRecords" />"

			}
		});
	});
</script>

<style type="text/css">
.examGradeError {
	border: solid 2px red;
	background: #eee;
	color: red;
}

table ul.errorMessage {
	padding: 0px;
	margin: 0px;
	list-style-type: none;
	background: none;
}
</style>

<s:if test="!hasActionErrors()">
	<div style="background: #d3e7f3">
		<table>
			<tr>
				<td><s:text name="lblExamSubject" />:</td>
				<td><s:property value="examSubject.title" /></td>
			</tr>

			<tr>
				<td><s:text name="lblExam" />:</td>
				<td><s:date name="exam.date"
						format="%{getText('examDateFormat')}" /> *** <s:property
						value="exam.examiner" /></td>
			</tr>

		</table>
	</div>
</s:if>

<s:if test="hasActionMessages()">
<div class="ui-widget">
	<div class="ui-state-highlight ui-corner-all"
		style="margin-top: 20px; padding: 0 .7em;">
		<p>
			<span class="ui-icon ui-icon-info"
				style="float: left; margin-right: .3em;"></span> <strong><s:text name="txtUiNotificationHeader" /></strong>
			<s:actionmessage />
		</p>
	</div>
</div>
</s:if>

<s:if test="hasActionErrors()">
<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
	<p>
		<span class="ui-icon ui-icon-alert"
			style="float: left; margin-right: .3em;"></span> <strong><s:text
				name="txtErrorHead" /></strong>
	</p>
	<s:actionerror />
</div>
</s:if>

<s:if test="!hasActionErrors()">
	<s:form method="post" action="ExamAttendanceBulkUpdate">
		<s:hidden name="examId" value="%{selectedExamId}" />
		<table id="examList" class="hatoma_dataTable">
			<thead>
				<tr>
					<td><s:text name="lblForename" /></td>
					<td><s:text name="lblLastname" /></td>
					<td><s:text name="lblMatriculationNo" /></td>
					<td><s:text name="lblPreviousExamGrade" /></td>
					<td><s:text name="lblExamGrade" /></td>
					<td><s:text name="lblAttempt" /></td>
				</tr>
			</thead>
			<tbody>

				<s:iterator value="myEntities">
					<tr>
						<td><s:property value="student.forename" /></td>
						<td><s:property value="student.lastname" /></td>
						<td><s:property value="student.matriculationNumber" /></td>
						<td><s:property
								value="previousExamAttendance.examGrade.asExpression" /></td>
						<td><s:textfield
								name="myEntitiesMap['%{student.id}'].newGrade"
								value="%{newGrade}" theme="simple"
								cssErrorClass="examGradeError" /> <s:fielderror>
								<s:param value="%{'myEntitiesMap['+student.id+'].newGrade'}" />
							</s:fielderror></td>
						<td><s:property value="numAttempt" />. <s:text name="lblAttempt" /></td>
					</tr>
				</s:iterator>


			</tbody>
		</table>
		<s:submit action="ExamAttendanceBulkUpdate" key="btnSave"
			method="insertNewExamAttendances" />
	</s:form>
</s:if>