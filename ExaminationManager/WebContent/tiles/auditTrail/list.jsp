<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
	var availableExaminers = <s:property value="studentsAsJson" escape="false" />;
	$(function() {

		hamatoAutocomplete($("#student"), $("#studentId"), availableExaminers);

		/**
		$('#example').dataTable( {
			"bProcessing": true,
			"bServerSide": true,
			"sAjaxSource": "../examples_support/server_processing.php"
		} );
		
		 **/

		$('#examList')
				.dataTable(
						{
							"bAutoWidth" : true,
							"bProcessing" : true,
							"sAjaxSource" : "AuditTrail!jsonResponder?jsonStudentId=1",
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


<s:form>
	<s:textfield required="true" name="studentN" key="lblStudent"
		id="student" onfocus="$(this).autocomplete('search');" />
	<s:hidden name="studentId" id="studentId" value="-1" />
</s:form>


<table id="examList" class="hatoma_dataTable">
	<thead>
		<tr>

			<td><s:text name="lblExamSubject" /></td>
			<td>Datum letzte prüfung</td>
			<td><s:text name="lblExamGrade" /></td>
			<td><s:text name="lblAttempt" /></td>
		</tr>
	</thead>
	<tbody>

	</tbody>
</table>