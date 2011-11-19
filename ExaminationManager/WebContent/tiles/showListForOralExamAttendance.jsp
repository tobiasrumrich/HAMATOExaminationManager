<!-- author Marcel Schroeter, 3690 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="de.hatoma.exman.model.Student, java.util.Collection, java.util.List, java.util.Collection, java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:url action="FileSingleOralExamAttendance"
	id="fileSingleOralExamAttendanceUrl" />

<script type="text/javascript">
	$(function() {

		$('#attendance_list').dataTable({
			"bAutoWidth" : false,
			"bProcessing" : true,
			"bStateSave" : true,
			"aoColumnDefs" : [ {
				"sWidth" : "40px",
				"aTargets" : [ 0 ]
			}, {
				"sWidth" : "50px",
				"aTargets" : [ 1 ]
			}, {
				"sWidth" : "230px",
				"aTargets" : [ 2 ]
			}, {
				"sWidth" : "40px",
				"aTargets" : [ 3 ]
			}, {
				"sWidth" : "140px",
				"aTargets" : [ 4 ]
			}, {
				"sWidth" : "140px",
				"aTargets" : [ 5 ]
			}, {
				"sWidth" : "60px",
				"aTargets" : [ 6 ]
			}, {
				"sType" : "numeric",
				"aTargets" : [ 1 ]
			}, {
				"sType" : "numeric",
				"aTargets" : [ 3 ]
			}, {
				"sType" : "numeric",
				"aTargets" : [ 6 ]
			}, {
				"bSortable" : false,
				"aTargets" : [ 0 ]
			} ],
			"oLanguage" : {
				"oPaginate" : {
					"sPrevious" : "<s:text name="jQueryDataTablesPrevious" />",
					"sNext" : "<s:text name="jQueryDataTablesNext" />",
					"sFirst" : "<s:text name="jQueryDataTablesFirst" />",
					"sLast" : "<s:text name="jQueryDataTablesLast" />",
				},
				"sSearch" : "<s:text name="jQueryDataTablesSearch" />",
				"sEmptyTable" : "<s:text name="jQueryDataTablesEmptyTable" />",
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

<s:text name="lblInfotextListStudentsOral" />
<s:form validate="true">
	<s:token />


	<div style="float: left;">
		<s:select key="lblManiple" name="selectedManiple" list="maniples"
			listKey="Id" />
	</div>

	<s:submit name="submit" key="lblShowOtherManipleSubmit"
		action="OralExamination" method="execute" />
</s:form>


<table id="attendance_list" class="hatoma_dataTable">
	<thead>
		<tr>
			<td>&nbsp;</td>
			<td>Versuch</td>
			<td>Prüfung</td>
			<td>Note</td>
			<td>Vorname</td>
			<td>Nachname</td>
			<td>Mat.-Nr.</td>
		</tr>
	</thead>
	<tbody>


		<s:iterator value="examAttendances" status="iteratorStatus">
			<s:url id="currentUrl" action="OralExamination" method="input">
				<s:param name="examAttendanceId" value="%{id}" />
			</s:url>
			<tr>
				<td><s:a href="%{currentUrl}"
						tooltip="%{txtADNFileSingleOralExamAttendance}">
						<img src="resources/img/icons/comment_edit.png">
					</s:a></td>
				<td><s:property value="attempt" /></td>
				<td><s:property value="exam.examSubject.title" /></td>
				<td><s:property value="examGrade.getAsExpression()" /></td>
				<td><s:property value="student.forename" /></td>
				<td><s:property value="student.lastname" /></td>
				<td><s:property value="student.matriculationNumber" /></td>
			</tr>
		</s:iterator>
	</tbody>
</table>