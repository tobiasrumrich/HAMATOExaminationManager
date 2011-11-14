<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="de.hatoma.exman.model.Student, java.util.Collection, java.util.List, java.util.Collection, java.util.ArrayList"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:url action="FileSingleOralExamAttendance"
	id="fileSingleOralExamAttendanceUrl" />

<script type="text/javascript">
	$(function() {

		$('#student_list').dataTable({
			"bAutoWidth" : false,
			"bProcessing" : true,
			"aoColumnDefs" : [ {
				"sWidth" : "30px",
				"aTargets" : [ 0 ]
			}, {
				"sType" : "numeric",
				"aTargets" : [ 0 ]
			}, {
				"sWidth" : "160px",
				"aTargets" : [ "_all" ]
			} ],
			"bStateSave" : true,
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
		action="ShowStudentListForOralExamination" method="execute" />
</s:form>


<table id="student_list" class="hatoma_dataTable">
	<thead>
		<tr>
			<td>ID</td>
			<td>Nachname</td>
			<td>Vorname</td>
		</tr>
	</thead>
	<tbody>


		<s:iterator value="students" status="iteratorStatus">

			<tr>
				<td><s:a href="%{fileSingleOralExamAttendanceUrl}">
						<s:property value="id" />
					</s:a></td>
				<td><s:a href="%{fileSingleOralExamAttendanceUrl}">
						<s:property value="lastname" />
					</s:a></td>
				<td><s:a href="%{fileSingleOralExamAttendanceUrl}">
						<s:property value="forename" />
					</s:a></td>

			</tr>
		</s:iterator>
	</tbody>
</table>