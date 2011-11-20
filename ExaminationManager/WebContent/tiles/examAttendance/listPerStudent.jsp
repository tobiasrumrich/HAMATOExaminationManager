<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
	//Advanced AJAX Support
	//See: http://datatables.net/plug-ins/api
	$.fn.dataTableExt.oApi.fnReloadAjax = function(oSettings, sNewSource,
			fnCallback, bStandingRedraw) {
		if (typeof sNewSource != 'undefined' && sNewSource != null) {
			oSettings.sAjaxSource = sNewSource;
		}
		this.oApi._fnProcessingDisplay(oSettings, true);
		var that = this;
		var iStart = oSettings._iDisplayStart;

		oSettings.fnServerData(oSettings.sAjaxSource, [], function(json) {
			/* Clear the old information from the table */
			that.oApi._fnClearTable(oSettings);

			/* Got the data - add it to the table */
			var aData = (oSettings.sAjaxDataProp !== "") ? that.oApi
					._fnGetObjectDataFn(oSettings.sAjaxDataProp)(json) : json;

			for ( var i = 0; i < json.aaData.length; i++) {
				that.oApi._fnAddData(oSettings, json.aaData[i]);
			}

			oSettings.aiDisplay = oSettings.aiDisplayMaster.slice();
			that.fnDraw();

			if (typeof bStandingRedraw != 'undefined'
					&& bStandingRedraw === true) {
				oSettings._iDisplayStart = iStart;
				that.fnDraw(false);
			}

			that.oApi._fnProcessingDisplay(oSettings, false);

			/* Callback user function - for event handlers etc */
			if (typeof fnCallback == 'function' && fnCallback != null) {
				fnCallback(oSettings);
			}
		}, oSettings);
	}
	//END 

	var availableStudents = <s:property value="studentsAsJson" escape="false" />;
	$(function() {

		hamatoAutocomplete($("#student"), $("#studentId"), availableStudents);

		$("#studentId")
				.change(
						function() {
							if ($("#studentId").val() == -1
									|| $("#student").val == "") {
								$("#tableContainer").hide();
							} else {
								$("#tableContainer").show();
							}
							dataTable
									.fnReloadAjax('ExamAttendanceOverview!jsonResponderStudent?jsonStudentId='
											+ $("#studentId").val());

						});

		var dataTable = $('#examList')
				.dataTable(
						{
							"bAutoWidth" : false,
							"bProcessing" : true,
							"sAjaxSource" : "ExamAttendanceOverview!jsonResponderStudent?jsonStudentId=",
							"aoColumnDefs" : [ {
								"sType" : "string",
								"sWidth" : "300px",
								"aTargets" : [ 0 ]
							}, {
								"sType" : "string",
								"sWidth" : "120px",
								"aTargets" : [ 1 ]
							}, {
								"sType" : "string",
								"sWidth" : "50px",
								"aTargets" : [ 2 ]
							}, {
								"sType" : "string",
								"sWidth" : "120px",
								"aTargets" : [ 3 ]
							}, {

								"sType" : "string",
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
	<s:textfield required="true" name="studentN" key="lblStudentName"
		id="student" onfocus="$(this).autocomplete('search');" />
	<s:hidden name="studentId" id="studentId" value="-1" />
</s:form>

<div id="tableContainer" style="display: none;">
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
</div>