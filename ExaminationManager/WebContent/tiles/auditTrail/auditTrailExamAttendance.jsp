<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<p>Dies ist der Audit Trail für folgenden Geschäftsvorfall:</p>
<table>

</table>

<s:iterator value="map">
	<h3>
		<s:property value="key" />
	</h3>
	<table>
		<thead>
			<tr>
				<td>Datum</td>
				<td>User</td>
				<td>Datum</td>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="value">
			<tr>
				<td>
				<s:property value="revisionEntity.changedOn"/>
				<s:set name="date">%{revisionEntity.changedOn}</s:set>
				<s:date name="date" format="%{getText('examDateFormat')}" />
				<s:date name="value.revisionEntity.changedOn"  format="%{getText('examDateFormat')}" /></td>
				<td><s:property value="revisionEntity.changedBy" /></td>
				<td><s:property value="%{entity.examGrade.getAsExpression() }" /></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
</s:iterator>

