<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<p>Die folgenden Daten existieren zu diesem Geschäftsvorfall:</p>
<table>
	<tr>
		<td>Student</td>
		<td><s:property value="student" /></td>
	</tr>
	<tr>
		<td>Prüfungsfach</td>
		<td><s:property value="examSubject" /></td>
	</tr>
</table>

<s:iterator value="map">
	<div>
		<table>
			<thead>
				<tr>
					<td>Status</td>
					<td>Versuch</td>
					<td>Prüfungsdatum</td>
					<td>Form</td>
					<td>Prüfer</td>
				</tr>
			</thead>
			<tbody></tbody>
			<tr>
			<td>
			<td><s:property value="entity.attempt"/></td>
				<td><s:date name="%{examMap[key].date}"
						format="%{getText('examDateFormat')}" /></td>

				<td><s:property
						value="%{getText(examMap[key].examType.getKey())}" /></td>


				<td><s:property value="%{examMap[key].examiner}" />
			</tr>
		</table>
	</div>
	<h3>
		<s:property value="key" />
		<s:property value="%{getText(examMap[key].examType.getKey())}" />
		<s:debug />
	</h3>
	<table>
		<thead>
			<tr>
				<td>Datum</td>
				<td>User</td>
				<td>Note</td>
				<td>Datum der mündlichen Ergänzungsprüfung</td>
				<td>Ergebnis der mündlichen Ergänzungsprüfung</td>
				
			</tr>
		</thead>
		<tbody>
			<s:iterator value="value">
				<tr>
					<td><s:property value="revisionEntity.changedOn" /> <s:set
							name="date">%{revisionEntity.changedOn}</s:set> <s:date
							name="date" format="%{getText('examDateFormat')}" /> <s:date
							name="%{map[key].getRevisionEntity().getChangedOn()}"
							format="%{getText('examDateFormat')}" /></td>
					<td><s:property value="revisionEntity.changedBy" /></td>
					<td><s:property value="%{entity.examGrade.getAsExpression() }" /></td>
					<td><s:property value="entity.supplementalOralExamDate" /></td>
					<td><s:property value="entity.supplementalOralExamGrade" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
</s:iterator>

