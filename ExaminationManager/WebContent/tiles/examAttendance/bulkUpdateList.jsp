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
			} , {
				"sWidth" : "20px",
				"bSortable": false,
				"aTargets" : [ 4 ]
			} ],
			"bStateSave" : true,
			"oLanguage" : {
				"oPaginate" : {
					"sPrevious" : "Vorherige Seite",
					"sNext" : "Nächste Seite",
					"sFirst" : "Erste Seite",
					"sLast" : "Letzte Seite",
				},
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

<style type="text/css">

.examGradeError {
border: solid 2px red;
background: #eee;
color:red;
}

ul.errorMessage {
padding:0px;
margin:0px;
list-style-type:none;
}

</style>

<div style="background: #d3e7f3">
	<table>
		<tr>
			<td>Prüfungsfach:</td>
			<td><s:property value="examSubject.title" /></td>
		</tr>

		<tr>
			<td>Prüfung:</td>
			<td><s:date name="exam.date"
					format="%{getText('examDateFormat')}" /> *** <s:property
					value="exam.examiner" /></td>
		</tr>

	</table>
</div>

<s:actionerror />

<s:form method="post" action="ExamAttendanceBulkUpdate">
	<table id="examList" class="hatoma_dataTable">
		<thead>
			<tr>
				<td>Vorname</td>
				<td>Nachname</td>
				<td>Matrikelnr.</td>
				<td>Bisherige Note</td>
				<td>Note</td>
				<td>Versuch</td>
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
					<td>
					<s:textfield
							name="myEntitiesMap['%{student.id}'].newGrade"
							value="%{newGrade}" theme="simple" cssErrorClass="examGradeError" />
							<s:fielderror >
							<s:param value="%{'myEntitiesMap['+student.id+'].newGrade'}" />
						</s:fielderror></td>
					<td><s:property value="numAttempt" />. Versuch</td>
				</tr>
			</s:iterator>


		</tbody>
	</table>
	<s:submit action="ExamAttendanceBulkUpdate" value="Speichern"
		method="insertNewExamAttendances" />
</s:form>