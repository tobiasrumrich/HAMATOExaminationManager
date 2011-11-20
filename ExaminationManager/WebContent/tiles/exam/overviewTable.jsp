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


<table id="examList" class="hatoma_dataTable">
	<thead>
		<tr>
			<td><s:text name="lblManiple" /></td>
			<td><s:text name="lblModule" /></td>
			<td><s:text name="lblModulename" /></td>
			<td><s:text name="lblDate" /></td>
			<td><s:text name="lblExaminer" /></td>
			<td><s:text name="lblExamType" /></td>
			<td></td>
		</tr>
	</thead>
	<tbody>


		<s:iterator value="examList" status="iteratorStatus">

			<tr>
				<td><s:property value="examSubject.maniple" /></td>

				<td><s:property value="examSubject.moduleIdentifier" /></td>

				<td><s:property value="examSubject.title" /></td>

				<td><s:date name="date" format="%{getText('examDateFormatNoTimeFormat')}" />
				</td>

				<td><s:property value="examiner" /></td>

				<td><s:property value="%{getText(examType.key)}" /></td>
				<td><s:url action="ExamAttendanceBulkUpdate"
						id="examAttendanceBulkUpdateUrl">
						<s:param name="examId" value="id" />
					</s:url> <s:a href="%{examAttendanceBulkUpdateUrl}"
						tooltip="txtLinkToExamAttendanceBulkUpdate">
						<img src="resources/img/icons/table_add.png" />
					</s:a> <s:if test="%{isExamEditable(id)}">
						<s:url action="EditExam" id="editExam">
							<s:param name="examId" value="id" />
						</s:url>
						<s:a href="%{editExam}" tooltip="txtEditExam">
							<img src="resources/img/icons/pencil_go.png" />
						</s:a>
					</s:if> <s:else>
						<img src="resources/img/icons/lock.png"
							alt="<s:text name="txtNotEditable" />" />
					</s:else></td>
			</tr>
		</s:iterator>
	</tbody>
</table>