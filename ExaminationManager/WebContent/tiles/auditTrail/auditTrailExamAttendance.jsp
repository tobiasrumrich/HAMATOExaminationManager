<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<style type="text/css">
table {
	border: solid 1px;
	margin-bottom: 10px;
	width: 100%;
	spacing: 0px;
}

table thead {
	font-weight: bold;
}

table tr {
	vertical-align: top;
	text-align: left;
}
</style>

<p>Die folgenden Daten existieren zu diesem Geschäftsvorfall:</p>
<table>
	<tr>
		<td><strong>Student</strong></td>
		<td><s:property value="student" /></td>
	</tr>
	<tr>
		<td><strong>Prüfungsfach</strong></td>
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
				<td><s:property value="entity.attempt" /></td>
				<td><s:date name="%{examMap[key].date}"
						format="%{getText('examDateFormat')}" /></td>

				<td><s:property
						value="%{getText(examMap[key].examType.getKey())}" /></td>


				<td><s:property value="%{examMap[key].examiner}" />
			</tr>
			<tr>
				<th colspan="5"></th>
			</tr>
		</table>


		<table>
			<thead>
				<tr>
					<th rowspan="2">Datum</th>
					<th rowspan="2">User</th>
					<th rowspan="2">Note</th>
					<th colspan="2">Mündlich. Ergänzungsprüfung</th>
				</tr>
				<tr>
					<td>Datum</td>
					<td>Ergebnis</td>

				</tr>
			</thead>
			<tbody>
				<s:iterator value="value">
					<tr>
						<td><s:date name="%{revisionEntity.getChangedOnAsDate() }"
								format="%{getText('examDateFormat')}" /></td>
						<td><s:property value="revisionEntity.changedBy" /></td>
						<td><s:property
								value="%{entity.examGrade.getAsExpression() }" /></td>
						<td><s:date name="entity.supplementalOralExamDate"
								format="%{getText('examDateFormat')}" /></td>
						<td><s:property
								value="%{entity.supplementalOralExamGrade.getAsExpression() }" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
		</div>
</s:iterator>
<hr />
