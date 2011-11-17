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
				"sWidth" : "50px",
				"aTargets" : [ 0 ]
			}, {
				"sWidth" : "230px",
				"aTargets" : [ 1 ]
			}, {
				"sWidth" : "40px",
				"aTargets" : [ 2 ]
			}, {
				"sWidth" : "140px",
				"aTargets" : [ 3 ]
			}, {
				"sWidth" : "140px",
				"aTargets" : [ 4 ]
			}, {
				"sWidth" : "60px",
				"aTargets" : [ 5 ]
			}, {
				"sType" : "numeric",
				"aTargets" : [ 0 ]
			}, {
				"sType" : "numeric",
				"aTargets" : [ 2 ]
			}, {
				"sType" : "numeric",
				"aTargets" : [ 5 ]
			} ],
			"oLanguage" : {
				"oPaginate" : {
					// TODO Hier keys aus message.properties verwenden!
					"sPrevious" : "Vorherige Seite",
					"sNext" : "Nächste Seite",
					"sFirst" : "Erste Seite",
					"sLast" : "Letzte Seite",
				},
				// TODO Hier keys aus message.properties verwenden!
				"sSearch" : "Suche:",
				"sEmptyTable" : "Keine Einträge gefunden.",
				"sInfo" : "Zeige Eintrag _START_ bis _END_ von _TOTAL_.",
				"sInfoEmpty" : "Zeige 0 von 0 Einträgen.",
				"sInfoFiltered" : " - gefiltert aus _MAX_ Einträgen.",
				"sInfoThousands" : ".", //Tausender-Trennzeichen
				"sLengthMenu" : "Zeige _MENU_ Einträge.",
				"sProcessing" : "Bitte warten",
				"sZeroRecords" : "Keine Daten vorhanden."

			}
		});
	});
</script>

<s:text name="lblInfotextListStudentsOral" />
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
			<s:url id="currentUrl" action="OralExamination"
				method="input">
				<s:param name="examAttendanceId" value="%{id}" />
			</s:url>
			<tr>
				<td><s:property value="attempt" /></td>
				<td><s:a href="%{currentUrl}"><s:property value="exam.examSubject.title" /></s:a></td>
				<td><s:property value="examGrade.getAsExpression()" /></td>
				<td><s:a href="%{currentUrl}"><s:property value="student.forename" /></s:a></td>
				<td><s:a href="%{currentUrl}"><s:property value="student.lastname" /></s:a></td>
				<td><s:property value="student.matriculationNumber" /></td>
			</tr>
		</s:iterator>
	</tbody>
</table>