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
				"sWidth":"300px",
				"aTargets" : [ 0 ]
			}, {
				"sWidth" : "60px",
				"aTargets" : [ 1 ]
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
				<td><s:property value="previousExamAttendance.examGrade.gradeString" /></td>
				<td><s:textfield
						name="myEntitiesMap['%{id}'].previousExamAttendance.examGrade" value="%{previousExamAttendance.examGrade}"
						theme="simple" />
						</td>
				<td>1. Versuch</td>
			</tr>
		</s:iterator>

	</tbody>
</table>