<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>



<script type="text/javascript">
	$(function() {

		$('#examList').dataTable({
			"bAutoWidth" : true,
			"bProcessing" : true,
			"aoColumnDefs" : [ {
				"sWidth" : "70px",
				"aTargets" : [ 0 ]
			}, {
				"sType" : "string",
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
			<td>Manipel</td>
			<td>Modul</td>
			<td>Modulbezeichnung</td>
			<td>Datum</td>
			<td>Prüfer</td>
			<td>Prüfungsform</td>
			<td></td>
		</tr>
	</thead>
	<tbody>


		<s:iterator value="examList" status="iteratorStatus">

			<tr>
				<td><s:property value="examSubject.maniple" /></td>

				<td><s:property value="examSubject.moduleIdentifier" /></td>

				<td><s:property value="examSubject.title" /></td>

				<td><s:date name="date" format="%{getText('examDateFormat')}" />
				</td>

				<td>
						<s:property value="examiner" />
					</td>

				<td><s:property value="examType.key" /></td>

				<td>
				<s:if test="targetActionName=='EditExam'">
						<s:if test="%{isExamEditable(id)}">

							<s:url action="EditExamination" id="editExamaninationUrl">
								<s:param name="examId"></s:param>
							</s:url>
							<s:a href="%{editExamaninationUrl}">
							<span class="ui-icon ui-icon-pencil"></span>
							<s:text name="txtEditExam" /></s:a>

						</s:if>
					</s:if> <s:if test="targetActionName=='ExamAttendanceBulkUpdate'">
						<s:url action="ExamAttendanceBulkUpdate"
							id="examAttendanceBulkUpdateUrl">
							<s:param name="examId" value="id" />
						</s:url>
						<s:a href="%{examAttendanceBulkUpdateUrl}">
							<s:text name="txtLinkToExamAttendanceBulkUpdate" />
						</s:a>
					</s:if></td>
			</tr>
		</s:iterator>
	</tbody>
</table>