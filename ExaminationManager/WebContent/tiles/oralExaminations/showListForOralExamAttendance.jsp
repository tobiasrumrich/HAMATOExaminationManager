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
								"aTargets" : [ 5 ]
							}, {
								"sWidth" : "50px",
								"aTargets" : [ 4 ]
							}, {
								"sWidth" : "230px",
								"aTargets" : [ 3 ]
							}, {
								"sWidth" : "140px",
								"aTargets" : [ 2 ]
							}, {
								"sWidth" : "140px",
								"aTargets" : [ 2 ]
							}, {
								"sWidth" : "60px",
								"aTargets" : [ 0 ]
							}, {
								"sType" : "numeric",
								"aTargets" : [ 2 ]
							}, {
								"sType" : "numeric",
								"aTargets" : [ 0 ]
							}, {
								"bSortable" : false,
								"aTargets" : [ 5 ]
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
		listKey="Id" onchange="document.forms(0).submit();" />
</s:form>


<table id="attendance_list" class="hatoma_dataTable">
	<thead>
		<tr>
			<td>Mat.-Nr.</td>
			<td>Vorname</td>
			<td>Nachname</td>
			<td>Prüfung</td>
			<td>Versuch</td>
			<td>&nbsp;</td>
		</tr>
	</thead>
	<tbody>


		<s:iterator value="examAttendances" status="iteratorStatus">
			<s:url id="currentUrl" action="OralExamination" method="input">
				<s:param name="examAttendanceId" value="%{id}" />
			</s:url>
			<tr>
				<td><s:property value="student.matriculationNumber" /></td>
				<td><s:property value="student.forename" /></td>
				<td><s:property value="student.lastname" /></td>
				<td><s:property value="exam.examSubject.title" /></td>
				<td><s:property value="attempt" /></td>
				<td><s:a href="%{currentUrl}">
						<img src="resources/img/icons/comment_edit.png"
							title="<s:text name="txtADNFileSingleOralExamAttendance" />">
					</s:a></td>
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