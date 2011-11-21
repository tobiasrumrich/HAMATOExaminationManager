<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!-- Die Action Nachricht nochmal für den Druck. Standardmäßig ausgeblendet. -->
<div class="protocolPrint"><s:actionmessage /></div>
<!-- Ein Druckbutton. Verschwindet beim Druck. -->
<FORM id="printButton">
	<INPUT TYPE="button" onClick="window.print()"
		value="<s:text name="btnPrint" />" />
</FORM>
<!-- Tabelle mit allen abgelegten Datensätzen. -->
<table id="examList" style="width:100%; padding:0px; margin:0px;">
	<thead>
		<tr>
			<td>Mat.-Nr.</td>
			<td>Name</td>
			<td>Modul</td>
			<td>Datum Prüfung</td>
			<td>Note Prüfung</td>
			<td>Datum mündliche Prüfung</td>
			<td>Note mündliche Prüfung</td>
		</tr>
	</thead>
	<tbody>


		<s:iterator value="protocolledExamAttendances">
			<tr>
				<td><s:property value="student.matriculationNumber" /></td>
				<td><s:property value="student.forename" /> <s:property value="student.lastname" /></td>
				<td><s:property value="exam.examSubject.title" /></td>
				<td><s:property value="exam.date.toLocaleString().substring(0,10)" /></td>
				<td class="centered"><s:property value="examGrade.getAsExpression()" /></td>
				<td><s:property value="supplementalOralExamDate.toLocaleString().substring(0,10)" /></td>
				<td class="centered"><s:property value="supplementOralExamGrade.getAsExpression()" /></td>
			</tr>
		</s:iterator>
		

	</tbody>
</table>