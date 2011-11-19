<!-- author Marcel Schroeter, 3690 -->
<!-- author Tobias Rumrich, 3638 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<style type="text/css">
table {
	border: solid 1px black;
	margin-bottom: 10px;
	width: 100%;
	spacing: 0px;
	/*	border-collapse: collapse;*/
	border-spacing: 0px;
}

table thead {
	font-weight: bold;
	vertical-align: middle;
}

table tr {
	vertical-align: top;
	text-align: left;
}
</style>
<script type="text/javascript">
	function toggleTrailTable(numberOfTrailtable) {

		if (document.getElementById("trailTable_" + numberOfTrailtable).style.display == "block") {
			document.getElementById("trailTable_" + numberOfTrailtable).style.display = "none";
		} else {
			document.getElementById("trailTable_" + numberOfTrailtable).style.display = "block";
		}
	}
</script>

<p><s:text name="lblInfotextAuditTrail" /></p>
<table>
	<tr>
		<td><strong><s:text name="lblStudent" /></strong></td>
		<td><s:property value="student" /></td>
	</tr>
	<tr>
		<td><strong><s:text name="lblExamSubject" /></strong></td>
		<td><s:property value="examSubject" /></td>
	</tr>
</table>
<hr>
<s:iterator value="map" status="mapStatus">
<div>
		<table>
			<thead>
				<tr>
					<td class="emphasized"><s:text name="lblAttempt" /></td>
					<td><s:text name="lblStatus" /></td>
					<td><s:text name="lblExamDate2" /></td>
					<td><s:text name="lblExamType" /></td>
					<td><s:text name="lblExaminer" /></td>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="emphasized"><s:property value="entity.attempt" /></td>
					<td></td>
					<td><s:date name="%{examMap[key].date}"
							format="%{getText('examDateFormat')}" /></td>

					<td><s:property
							value="%{getText(examMap[key].examType.getKey())}" /></td>
					<td><s:property value="%{examMap[key].examiner}" /></td>
				</tr>

				<tr>
					<td>&nbsp;</td>
					<td colspan="5"><a href="javascript: void(0);"
						onClick="toggleTrailTable('<s:text name="%{#mapStatus.count}" />');">
							<s:text name="txtHistoryOfDataset" />
					</a> <br />
						<table class="trailTable"
							id="trailTable_<s:text name="%{#mapStatus.count}" />">
							<thead>
								<tr>
									<th rowspan="2"><s:text name="lblDateOfEdit" /></th>
									<th rowspan="2"><s:text name="lblUser" /></th>
									<th rowspan="2"><s:text name="lblExamGrade" />Note</th>
									<th colspan="2" style="text-align: center;"><s:text name="lblOralExam" /></th>
								</tr>
								<tr>
									<th><s:text name="lblDate" /></th>
									<th><s:text name="lblExamGrade" /></th>

								</tr>
							</thead>
							<tbody>
								<s:iterator value="value">
									<tr>
										<td><s:date
												name="%{revisionEntity.getChangedOnAsDate() }"
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
						</table></td>
				</tr>
			</tbody>
		</table>
		</div>
</s:iterator>
