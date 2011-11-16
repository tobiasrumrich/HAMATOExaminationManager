<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<table id="examList" class="hatoma_dataTable">
	<thead>
		<tr>
			<td>Vorname</td>
			<td>Nachname</td>
			<td>Matrikelnr.</td>
			<td>Bisherige Note</td>
			<td>Note</td>
			<td>Versuch</td>
		</tr>
	</thead>
	<tbody>


		<s:iterator value="myEntitiesConfirmations">
			<tr>
				<td><s:property value="student.forename" /></td>
				<td><s:property value="student.lastname" /></td>
				<td><s:property value="student.matriculationNumber" /></td>
				<td><s:property
						value="previousExamAttendance.examGrade.asExpression" /></td>
				<td><s:property value="newGrade" /></td>
				<td>1. Versuch</td>
			</tr>
		</s:iterator>
		

	</tbody>
</table>