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

		$('#attendance_list')
				.dataTable(
						{
							"bAutoWidth" : false,
							"bProcessing" : true,
							"bStateSave" : true,
							"aoColumnDefs" : [ {
								"sWidth" : "25px",
								"aTargets" : [ 0 ]
							}, {
								"sWidth" : "50px",
								"aTargets" : [ 5 ]
							}, {
								"sWidth" : "230px",
								"aTargets" : [ 4 ]
							}, {
								"sWidth" : "140px",
								"aTargets" : [ 2 ]
							}, {
								"sWidth" : "140px",
								"aTargets" : [ 3 ]
							}, {
								"sWidth" : "60px",
								"aTargets" : [ 1 ]
							}, {
								"sType" : "numeric",
								"aTargets" : [ 3 ]
							}, {
								"sType" : "numeric",
								"aTargets" : [ 1 ]
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

<s:text name="txtInfotextListStudentsOral" />
<s:form validate="true">
	<s:token />
	<s:select key="lblManiple" name="selectedManiple" list="maniples"
		listKey="Id" />
	<s:submit name="submit" key="lblShowOtherManipleSubmit"
		action="OralExamination" method="execute" />
</s:form>


<table id="attendance_list" class="hatoma_dataTable">
	<thead>
		<tr>
			<td>&nbsp;</td>
			<td>Mat.-Nr.</td>
			<td>Vorname</td>
			<td>Nachname</td>
			<td>Prüfung</td>
			<td>Versuch</td>
		</tr>
	</thead>
	<tbody>


		<s:iterator value="examAttendances" status="iteratorStatus">
			<s:url id="currentUrl" action="OralExamination" method="input">
				<s:param name="examAttendanceId" value="%{id}" />
			</s:url>
			<tr>
				<td><s:a href="%{currentUrl}">
						<img src="resources/img/icons/comment_edit.png"
							title="<s:text name="txtADNFileSingleOralExamAttendance" />">
					</s:a></td>
				<td><s:property value="student.matriculationNumber" /></td>
				<td><s:property value="student.forename" /></td>
				<td><s:property value="student.lastname" /></td>
				<td><s:property value="exam.examSubject.title" /></td>
				<td><s:property value="attempt" /></td>


			</tr>
		</s:iterator>
	</tbody>
</table>
<div id="legend">
	<s:text name="txtCaption" />
	:<br /> <img src="resources/img/icons/comment_edit.png"
		title="<s:text name="txtADNFileSingleOralExamAttendance" />">
	<s:text name="txtADNFileSingleOralExamAttendance" />
</div>