<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form validate="true">
	<s:token />
	<s:select name="lecturer" key="lblLecturer"
		list="{'Zimmermann','Ahrens'}" required="true" />
	<s:textfield name="lecturer" key="lblLecturer" required="true"
		readonly="true" />

	<s:component template="/my/custom/component.vm">
		<s:param name="key1" value="value1" />
		<s:param name="key2" value="value2" />
	</s:component>

	<s:select name="examSubject" key="lblExamSubject" required="true"
		list="{'B000', 'I123'}" />
	<s:select name="examType" key="lblExamType" required="true"
		list="{'Klausur', 'Mündliche Prüfung', 'Hausarbeit'}" />
	<s:submit key="btnSave" action="FileSingleExamAttendance" method="save" />
	<s:submit key="btnCancel" action="SaveNewExam" name="btnCancel" />
</s:form>