<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<div class="ui-widget">
	<div class="ui-state-highlight ui-corner-all"
		style="margin-top: 20px; padding: 0 .7em;">
		<p>
			<span class="ui-icon ui-icon-info"
				style="float: left; margin-right: .3em;"></span> <strong><s:text name="txtUiNotificationHeader" /></strong>
			<s:actionmessage />
		</p>
	</div>
</div>


<div style="background: #d3e7f3">
		<table>
			<tr>
				<td><s:text name="lblExamSubject" />:</td>
				<td><s:property value="examSubject.title" /></td>
			</tr>

			<tr>
				<td><s:text name="lblExam" />:</td>
				<td><s:date name="exam.date"
						format="%{getText('examDateFormat')}" /> *** <s:property
						value="exam.examiner" /></td>
			</tr>

		</table>
	</div>

<p><s:text name="txtUiProtocolHeader" /></p>
<table id="examList" class="hatoma_dataTable" style="width:100%; padding:0px; margin:0px;">
	<thead>
		<tr>
			<td>Mat.-Nr.</td>
			<td>Name</td>
			<td>Modul</td>
			<td>Datum der Prüfung</td>
			<td>Erzielte Note der Prüfung</td>
			<td>Datum der mündlichen Prüfung</td>
			<td>Erzielte Note der mündlichen Prüfung</td>
		</tr>
	</thead>
	<tbody>


		<s:iterator value="protocolledExamAttendances">
			<tr>
				<td><s:property value="student.matriculationNumber" /></td>
				<td><s:property value="student.forename" /> <s:property value="student.lastname" /></td>
				<td><s:property value="examSubject.title" /></td>
				<td><s:property value="date" /></td>
				<td><s:property value="examGrade" /></td>
				<td><s:property value="supplementalOralExamDate" /></td>
				<td><s:property value="supplementalOralExamGrade" /></td>
			</tr>
		</s:iterator>
		

	</tbody>
</table>