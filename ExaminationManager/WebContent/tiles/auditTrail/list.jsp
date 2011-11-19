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
			}, {
				"sWidth" : "20px",
				"bSortable" : false,
				"aTargets" : [ 4 ]
			} ],
			"bStateSave" : true,
			"oLanguage" : {
				"oPaginate" : {
					"sPrevious" : "<s:text name="jQueryDataTablesPrevious" />",
					"sNext" : "<s:text name="jQueryDataTablesNext" />",
					"sFirst" : "<s:text name="jQueryDataTablesFirst" />",
					"sLast" : "<s:text name="jQueryDataTablesLast" />)",
				},
				"sSearch" : "<s:text name="jQueryDataTablesSearch" />",
				"sEmptyTable" : "<s:text name ="jQueryDataTablesEmptyTable" />",
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
					<td><s:text name="lblForename" /><s:property value="student.forename"/></td>
					<td><s:text name="lblLastname" /></td>
					<td><s:text name="lblMatriculationNo" /></td>
					<td><s:text name="lblPreviousExamGrade" /></td>
					<td><s:text name="lblExamGrade" /></td>
					<td><s:text name="lblAttempt" /></td>
				</tr>
			</thead>
			<tbody>


<s:iterator value="attendancesList">
<tr><s:

</s:iterator>
</tbody>
</table>