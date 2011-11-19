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
		<s:iterator value="value">
			<tr>
				<td><s:property /></td>
			</tr>
		</s:iterator>
	</table>
</s:iterator>

