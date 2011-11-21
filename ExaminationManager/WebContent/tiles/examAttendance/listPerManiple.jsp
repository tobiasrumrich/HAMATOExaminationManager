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

	var dataTable ;
	
	$(function() {

		//hamatoAutocomplete($("#student"), $("#studentId"), availableExaminers);

		$("#selectedManiple")
				.change(
						function() {
							dataTable
									.fnReloadAjax('ExamAttendanceOverview!jsonResponderManiple?jsonManipleId='
											+ $("#selectedManiple").val());

						});

		dataTable = $('#examAttendanceList')
				.dataTable(
						{
							"bAutoWidth" : false,
							"bProcessing" : true,
							"sAjaxSource" : "ExamAttendanceOverview!jsonResponderManiple?jsonManipleId="
									+ $("#selectedManiple").val(),
							"aoColumnDefs" : [ {
								"sType" : "string",
								"sWidth" : "50px",
								"aTargets" : [ 0 ]
							}, {
								"sType" : "string",
								"sWidth" : "100px",
								"aTargets" : [ 1 ]
							}, {
								"sType" : "string",
								"sWidth" : "100px",
								"aTargets" : [ 2 ]
							}, {
								"sType" : "string",

								"aTargets" : [ 3 ]
							}, {
								"sType" : "string",
								"sWidth" : "45px",
								"aTargets" : [ 4 ]
							}, {
								"sType" : "string",
								"sWidth" : "45px",
								"aTargets" : [ 5 ]
							}, {

								"sType" : "string",
								"sWidth" : "70px",
								"bSortable" : false,
								"aTargets" : [ 7 ]
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

	<s:url action="ExamAttendanceOverview" id="deleteExamAttendanceJsonUrl" method="jsonResponderDeleteExamAttendance" />
	function confirmDeleteOfExamAttendance(id) {
		$("#dialog-confirm")
				.dialog(
						{
							resizable : false,
							height : 400,
							width : 600,
							show : 'puff',
							modal : true,
							buttons : {
								"<s:text name="btnDelete" />" : function() {

									$
											.post(
													"<s:property value="deleteExamAttendanceJsonUrl" />?jsonExamAttendanceId="
															+ id,
													function(data) {
														if (data.result == "success") {
															dataTable
															.fnReloadAjax('ExamAttendanceOverview!jsonResponderManiple?jsonManipleId='
																	+ $(
																			"#selectedManiple")
																			.val());
															$(
																	"#dialog-result")
																	.attr("title",
																			"<s:text name="txtDoneDeleteExamAttendanceSuccessTitle"/>");
															$("#dialog-result")
																	.html(
																			"<s:text name="txtDoneDeleteExamAttendanceSuccessMessage"/>");
															
														} else {
															$(
																	"#dialog-result")
																	.attr("title",
																			"<s:text name="txtDoneDeleteExamAttendanceErrorTitle"/>");
															$("#dialog-result")
																	.html(
																			"<s:text name="txtDoneDeleteExamAttendanceErrorMessage"/>");
														}
														$("#dialog-result")
																.dialog(
																		{
																			resizable : false,
																			height : 400,
																			width : 600,
																			show : 'puff',
																			modal : true,
																			buttons : {
																				"OK" : function() {
																					$(
																							this)
																							.dialog(
																									"close");
																				}
																			}
																		});
													});
									$(this).dialog("close");

								},
								Cancel : function() {
									$(this).dialog("close");
								}
							}
						});
	}
</script>

<div id="dialog-confirm"
	title="<s:text name="txtConfirmReallyDeleteExamAttendanceTitle" /> "
	style="display: none;">
	<p>
		<span class="ui-icon ui-icon-alert"
			style="float: left; margin: 0 7px 20px 0;"></span>
		<s:text name="txtConfirmReallyDeleteExamAttendanceInfo" />
	</p>
	<p>
		<s:text name="txtConfirmReallyDeleteExamAttendanceAuditTrailInfo" />
	</p>
	<p>
		<s:text name="txtConfirmReallyDeleteExamAttendanceReverse" />
	</p>
</div>
<div id="dialog-result"
	title=""
	style="display: none;">
</div>

<s:form>
	<s:select key="lblManiple" list="maniplesList" id="selectedManiple"
		listKey="id" />
</s:form>

<div id="tableContainer">
	<table id="examAttendanceList" class="hatoma_dataTable">
		<thead>
			<tr>
				<td><s:text name="lblMatNo" /></td>
				<td><s:text name="lblForename" /></td>
				<td><s:text name="lblLastname" /></td>
				<td><s:text name="lblExamSubject" /></td>
				<td><s:text name="lblExamDate" /></td>
				<td><s:text name="lblExamGrade" /></td>
				<td><s:text name="lblAttemptShort" /></td>
				<td></td>
			</tr>
		</thead>
		<tbody>

		</tbody>
	</table>

	<div id="legend">
		<s:text name="txtCaption" />
		:<br /> <img src="resources/img/icons/database_key.png"
			title="<s:text name="txtShowAuditTrailForThisRecord" />">
		<s:text name="txtShowAuditTrailForThisRecord" />

		<br /> <img src="resources/img/icons/pencil_go.png"
			title="<s:text name="txtShowAuditTrailForThisRecord" />">
		<s:text name="lblEditExamAttendance" />

		<br /> <img src="resources/img/icons/cancel.png"
			title="<s:text name="lblDeleteExamAttendance" />">
		<s:text name="lblDeleteExamAttendance" />

	</div>
</div>