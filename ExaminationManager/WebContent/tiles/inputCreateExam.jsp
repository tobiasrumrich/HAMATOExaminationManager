<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<s:debug />
<script type="text/javascript">
	var availableExaminers = <s:property value="availableExaminersJson" escape="false" />;
	var availableExamSubjectsByManiple = <s:property value="availableExamSubjectsByManipleJson" escape="false" />;
	var availableManiples = <s:property value="availableManiplesJson" escape="false" />;


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