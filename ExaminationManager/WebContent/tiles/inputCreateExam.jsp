<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<s:debug />
<script type="text/javascript">
	var availableExaminers = <s:property value="availableExaminersJson" escape="false" />;
	var availableExamSubjectsByManiple = <s:property value="availableExamSubjectsByManipleJson" escape="false" />;
	var availableManiples = <s:property value="availableManiplesJson" escape="false" />;

	Array.prototype.findBy = function(callbfn) {
		for (i in this) {
			o = this[i];
			if (callbfn(o)) {
				return o;
			}
		}
	};

	function hamatoAutocomplete(textfieldObj, hiddeninpObj, kvArray) {
		// Autocomplete für das Textfeld aktivieren
		textfieldObj.autocomplete({
			source : kvArray,
			autofocus : true,
			focus : function(event, ui) {
				textfieldObj.val(ui.item.value);
				textfieldObj.trigger("change");
				return false;
			},
			select : function(event, ui) {
				textfieldObj.val(ui.item.value);
				textfieldObj.trigger("change");
				hiddeninpObj.val(ui.item.key);
				hiddeninpObj.trigger("change");

				return false;
			},
			change : function(event, ui) {
				item = kvArray.findBy(function(e) {
					return e.key == hiddeninpObj.val();
				});
				if (item == undefined || item.value != textfieldObj.val()) {
					// Wenn die ID im hidden Input != dem Hinterlegten Wert im InputField
					// beides zurücksetzen
					hiddeninpObj.val(-1);
					hiddeninpObj.trigger("change");
					textfieldObj.val("");
					textfieldObj.trigger("change");
				} else {
					hiddeninpObj.trigger("change");
					textfieldObj.trigger("change");
				}
			},
			close : function(event, ui) {
				hiddeninpObj.trigger("change");
				textfieldObj.trigger("change");
			}
		});
		// Prüfen, ob bereits ein wert im Textfeld gesetzt wurde
		if (textfieldObj.val() != undefined && textfieldObj.val() != "") {
			// Überprüfen, ob in Liste vorhanden.
			foundItem = kvArray.findBy(function(e) {
				return e.value == textfieldObj.val();
			});
			if (foundItem != undefined) {
				// beide setzen
				textfieldObj.val(foundItem.value);
				hiddeninpObj.val(foundItem.key);
				hiddeninpObj.trigger("change");
				textfieldObj.trigger("change");
			}
		} else {
			// beide zurücksetzen
			textfieldObj.val("");
			hiddeninpObj.val("-1");
			hiddeninpObj.trigger("change");
			textfieldObj.trigger("change");
		}
	}

	$(function() {
		hamatoAutocomplete($("#lecturerI"), $("#lecturerIid"),
				availableExaminers);
		hamatoAutocomplete($("#examManipleI"), $("#examManipleIid"),
				availableManiples);

		$("#examDateI").datepicker({
			dateFormat : '<s:text name="examDateFormatNoTimeJQuery" />'
		});

		$("#examManipleIid").change(checkIsManipleSet);
		// Überprüfung, ob manipel gesetzt ist, 
		checkIsManipleSet();
	});

	function checkIsManipleSet() {
		manipleId = $("#examManipleIid").val();
		if (manipleId == -1) {
			$('#examSubjectI').attr('disabled', 'disabled');
			$('#examSubjectI').val('');
			$('#examSubjectIid').val(-1);
		} else {
			$('#examSubjectI').removeAttr('disabled');
			kvSubjects = availableExamSubjectsByManiple[manipleId];
			hamatoAutocomplete($("#examSubjectI"), $("#examSubjectIid"),
					kvSubjects);
			foundItem = kvSubjects.findBy(function(e) {
				return e.value == $('#examSubjectI').val();
			});
			if (foundItem == undefined) {
				$('#examSubjectI').val("");
				$('#examSubjectIid').val(-1);
			}
		}
	}
</script>
<s:form validate="true">
	<s:token />
	<s:textfield required="true" name="lecturerN" key="lblLecturer"
		id="lecturerI" onfocus="$(this).autocomplete('search');" />
	<s:hidden name="lecturerNid" id="lecturerIid" value="-1" />

	<s:textfield required="true" name="examManipleN" key="lblManiple"
		id="examManipleI" />
	<s:hidden name="examManipleNid" id="examManipleIid" value="-1" />

	<s:textfield required="true" name="examSubjectN" key="lblExamSubject"
		id="examSubjectI" disabled="true" />
	<s:hidden name="examSubjectNid" id="examSubjectIid" value="-1" />

	<s:select list="examTypes" key="lblExamType" name="examType"
		required="true" />

	<s:textfield required="true" name="examDateN" id="examDateI"
		key='lblDate' />
	<s:hidden name="examId" />
	<s:submit key="btnSave" action="%{targetAction}"
		method="%{targetMethod}" />
	<s:submit key="btnCancel" action="SaveNewExam" name="btnCancel" />
</s:form>