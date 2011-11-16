<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<s:form validate="true">
	<s:token />
	<s:select name="lecturer" key="lblLecturer"
		list="{'Zimmermann','Ahrens'}" required="true" />
	<s:textfield name="lecturer" key="lblLecturer" required="true"
		readonly="true" />

	<table id="examList" class="hatoma_dataTable">
		<thead>
			<tr>
				<td>ID</td>
				<td>Name</td>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="allExaminers" status="status">
				<tr>
					<td><s:property value="id" /></td>

					<td><s:property value="forename" /> <s:property
							value="surename" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>


	<s:select name="examSubject" key="lblExamSubject" required="true"
		list="{'B000', 'I123'}" />
	<s:select name="examType" key="lblExamType" required="true"
		list="{'Klausur', 'Mündliche Prüfung', 'Hausarbeit'}" />
	<s:submit key="btnSave" action="FileSingleExamAttendance" method="save" />
	<s:submit key="btnCancel" action="SaveNewExam" name="btnCancel" />
</s:form>