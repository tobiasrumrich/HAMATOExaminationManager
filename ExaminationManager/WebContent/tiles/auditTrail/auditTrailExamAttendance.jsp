<!-- author Tobias Rumrich, 3638 -->
<!-- author Marcel Schroeter, 3690 -->
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
			document.getElementById("trailPlus_" + numberOfTrailtable).style.display = "inline";
			document.getElementById("trailMinus_" + numberOfTrailtable).style.display = "none";
		} else {
			document.getElementById("trailTable_" + numberOfTrailtable).style.display = "block";
			document.getElementById("trailPlus_" + numberOfTrailtable).style.display = "none";
			document.getElementById("trailMinus_" + numberOfTrailtable).style.display = "inline";
		}
	}
</script>

<p>
	<s:text name="lblInfotextAuditTrail" />
</p>
<div id="printButton">
	<form>
		<input type="button" onClick="window.print()"
			value="<s:text name="btnPrint" />" />
	</form>
</div>
<!-- kurze Übersichtstabelle, die zeigt um wen und was es grad geht. -->
<table>
	<tr>
		<td><strong><s:text name="lblStudentName" /></strong></td>
		<td><s:property value="student" /></td>
	</tr>
	<tr>
		<td><strong><s:text name="lblExamSubject" /></strong></td>
		<td><s:property value="examSubject" /></td>
	</tr>
</table>
<hr>
<!-- Iterator erzeugt eine eigene kleine Tabelle für jeden Versuch. -->
<!-- Innerhalb der Tabelle wird eine weitere Tabelle eingeschachtelt, die die Versionshistorie anzeigt.  -->
<s:iterator value="map" status="mapStatus">
	<div>
		<table>
			<thead>
				<tr>
					<th class="emphasized"><s:text name="lblAttempt" /></th>
					<th><s:text name="lblExamGrade" /></th>
					<th><s:text name="lblStatus" /></th>
					<th><s:text name="lblExamDate2" /></th>
					<th><s:text name="lblExamType" /></th>
					<th><s:text name="lblExaminer" /></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td class="emphasized"><s:property
							value="%{value.get(0).getEntity().getAttempt()}" /></td>
					<td><s:property
							value="%{value.get(0).getEntity().getExamGrade().getAsExpression()}" /></td>
					<td><s:text
							name="txtIsCurrentRevision%{value.get(0).isCurrentRevision() }" /></td>
					<td><s:date name="%{examMap[key].date}"
							format="%{getText('examDateFormat')}" /></td>

					<td><s:property
							value="%{getText(examMap[key].examType.getKey())}" /></td>
					<td><s:property value="%{examMap[key].examiner}" /></td>
				</tr>

				<tr>
					<!-- linken Rand freilassen -->
					<td>&nbsp;</td>
					<td colspan="5">
					<!-- einen Link anbieten zum auf und zu klappen. Ganz fancy mit einer plus und minus grafik -->
					<!-- die Links und Tabellen werden mit eindeutiger ID versehen mit Hilfe des Interator Counts -->
					<a href="javascript: void(0);"
						class="historyLink"
						onClick="toggleTrailTable('<s:property value="%{#mapStatus.count}" />');"
						title="<s:text name="showHistory" />"> <img
							id="trailPlus_<s:property value="%{#mapStatus.count}" />"
							class="trailPlus"
							src="resources/img/icons/bullet_toggle_plus.png" /> <img
							id="trailMinus_<s:property value="%{#mapStatus.count}" />"
							class="trailMinus"
							src="resources/img/icons/bullet_toggle_minus.png" /> <s:text
								name="txtHistoryOfDataset" /></a> <br />
						<table class="trailTable"
							id="trailTable_<s:property value="%{#mapStatus.count}" />">
							<thead>
								<tr>
									<th rowspan="2"><s:text name="txtRecordType" /></th>
									<th rowspan="2"><s:text name="lblDateOfEdit" /></th>
									<th rowspan="2"><s:text name="lblUser" /></th>
									<th rowspan="2"><s:text name="lblExamGrade" /></th>
									<th colspan="2" style="text-align: center;"><s:text
											name="lblOralExam" /></th>
								</tr>
								<tr>
									<th><s:text name="lblDate" /></th>
									<th><s:text name="lblExamGrade" /></th>
								</tr>
							</thead>
							<tbody>
							<!-- itarieren über revisionshistorie -->
								<s:iterator value="value">
									<tr>
										<td><s:text
												name="txtIsCurrentRevision%{isCurrentRevision() }" /></td>
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
