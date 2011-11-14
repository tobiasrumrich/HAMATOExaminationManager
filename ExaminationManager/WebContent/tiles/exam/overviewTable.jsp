<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:url action="FileSingleOralExamAttendance"
	id="fileSingleOralExamAttendanceUrl" />

<script type="text/javascript">
	$(function() {

		$('#examList').dataTable({
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
			<td>Manipel</td>
			<td>Modulnummer</td>
			<td>Modul</td>
			<td>Prüfer</td>
		</tr>
	</thead>
	<tbody>


		<s:iterator value="examList" status="iteratorStatus">

			<tr>
				<td><s:property value="examSubject.maniple" /></td>

				<td><s:property value="examSubject.moduleIdentifier" /></td>

				<td><s:property value="examSubject.title" /></td>

				<td><s:a href="%{fileSingleOralExamAttendanceUrl}">
						<s:property value="examiner" />
					</s:a></td>

			</tr>
		</s:iterator>
	</tbody>
</table>