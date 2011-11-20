<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:url action="ExamSubjectsFilter"
	method="getAvailableExamSubjectsForStudent"
	var="examStudensSubjectsFilterUrl">
	<s:param name="studentId" value="-1" />
</s:url>
<s:url action="NotAttendedExamsForStudentFilter"
	method="getExamsForStudent" var="examStudensExamsFilterUrl">
	<s:param name="studentId" value="-12" />
	<s:param name="examSubjectId" value="-13" />
</s:url>
<script>
	var allStudents = <s:property value="allStudents" escape="false"/>;
	var examStudensSubjectsFilterUrl = "<s:property value='examStudensSubjectsFilterUrl' escape='false' />";
	var examStudensExamsFilterUrl = "<s:property value='examStudensExamsFilterUrl' escape='false' />";

	var lastSelectedStudentId = {
		id : -1
	};
	var lastSelectedSubjectId = {
		id : -1
	};

	$(function() {
		hamatoAutocomplete($("#studentI"), $("#studentIid"), allStudents);
		$("#studentIid").change(
				function() {
					updateSelectbox($("#studentIid"), $('#examSubjectI'),
							lastSelectedStudentId, function() {
								return examStudensSubjectsFilterUrl.replace(
										"-1", $("#studentIid").val());
							});
				});
		$("#examSubjectI").change(
				function() {
					updateSelectbox($("#examSubjectI"), $('#examI'),
							lastSelectedSubjectId, function() {
								return examStudensExamsFilterUrl.replace(
										"&amp;", "&").replace("-12",
										$("#studentIid").val()).replace("-13",
										$("#examSubjectI").val());
							}, function() {
								return $("#examSubjectI").is(':disabled');
							});
					if ($("#examSubjectI").is(':disabled')) {
						$("#examI").find('option').remove();
						$("#examI").attr('disabled', 'disabled');
						$("#examI").trigger("change");
					}
				});
		$("#examI").change(
				function() {
					setTimeout(function() {
						if (!$("#examSubjectI").is(':disabled')
								&& $("#examI").find('option').length == 0) {
							$("#selectionWarning").show(300);
							$("#FileSingleExamAttendance_btnSave").attr(
									'disabled', 'disabled');
						}

						if ($("#examSubjectI").is(':disabled')) {
							$("#selectionWarning").hide(300);
							$("#FileSingleExamAttendance_btnSave").removeAttr(
									'disabled');
						}

						if (!$("#examSubjectI").is(':disabled')
								&& $("#examI").find('option').length > 0) {
							$("#selectionWarning").hide(300);
							$("#FileSingleExamAttendance_btnSave").removeAttr(
									'disabled');
						}
					}, 100);
				});
		$("form").submit(function() {
			if ($("#FileSingleExamAttendance_btnSave").is(':disabled')) {
				return false;
			}
			return true;
		});
	});

	function updateSelectbox(idField, selectBox, lastSelectedReference,
			urlFunction, disableSelectCallback) {
		var choosenStudentId = idField.val();
		if (lastSelectedReference.id == choosenStudentId
				|| choosenStudentId == undefined) {
			// nichts machen
			return;
		}
		lastSelectedReference.id = choosenStudentId;
		selectBox.find('option').remove();
		if (choosenStudentId == "-1"
				|| (typeof disableSelectCallback != "undefined" && disableSelectCallback() == true)) {
			// Selectbox deaktivieren
			selectBox.attr('disabled', 'disabled');
			selectBox.trigger("change");
		} else {
			// elemente nachladen
			$.getJSON(urlFunction(), function(data) {
				// noch einmal prüfen, ob studentId noch valide ist um race conditions zu vermeiden
				var sId = idField.val();
				if (sId == -1 || sId != choosenStudentId) {
					// es gab zwischenzeitlich eine Änderung, abfrage nochmal starten. 
					updateSelectbox();
					return;
				}
				for ( var i = 0; i < data.length; i++) {
					var e = data[i];
					selectBox.append("<option value='"+e.id+"'>"
							+ e.stringValue + "</option>");
				}
				// selectbox aktivieren
				selectBox.removeAttr('disabled');
				selectBox.trigger("change");
			});
		}
	}
</script>
<s:debug />
<div class="ui-state-error ui-corner-all"
	style="padding: 0 .7em; display: none;" id="selectionWarning">
	<p>
		<span class="ui-icon ui-icon-alert"
			style="float: left; margin-right: .3em;"></span>
		<s:text name="wrnSingleAttendanceNoExamAvailable" />
	</p>

</div>
<s:form validate="true" onkeypress="allowSubmit">
	<s:token />
	<s:textfield name="studentN" id="studentI" key="lblStudentName"
		required="true" />
	<s:hidden name="studentNid" id="studentIid" />
	<s:select name="examSubjectN" id="examSubjectI" key="lblExamSubject"
		list="{}" disabled="true" required="true" />
	<s:select key="lblExam" list="{}" id="examI" name="examN"
		disabled="true" required="true" />
	<s:select key="lblExamGrade" name="examGrade" list="allGrades"
		required="true" />
	<s:submit key="btnSave" action="FileSingleExamAttendance" method="save"
		disabled="true" />
</s:form>

