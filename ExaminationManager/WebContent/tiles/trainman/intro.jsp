<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="ui-state-highlight ui-corner-all"
	style="margin-top: 20px; padding: 0 .7em;">
	<p>
		<span class="ui-icon ui-icon-info"
			style="float: left; margin-right: .3em;"></span> <strong><s:text
				name="txtUiNotificationHeader" /></strong>
		<s:text name="txtTrainmanNotification" />
	</p>
</div>

<s:form method="POST" validate="true">
	<s:token />
	<s:checkbox name="createStudyBranches" disabled="true" value="true"
		key="lblTrainmanCheckCreateStudyBranches" />
	<s:checkbox name="createManiples" disabled="true" value="true"
		key="lblTrainmanCheckCreateManiples" />
	<s:checkbox name="createExaminers" disabled="true" value="true"
		key="lblTrainmanCheckCreateExaminers" />

	<s:checkbox name="createStudents" disabled="true" value="true"
		key="lblTrainmanCheckCreateStudents" />

	<s:radio name="selectedMethod"
		list="#{'bootstrapper': getText('lblTrainmanCheckBootstrapper'), 'examOnly':getText('lblTrainmanCheckCreateExams'),'complete':getText('lblTrainmanCheckCreateExamAttendances')}"
		key="lblTrainmanAddAdditionalData" required="true" />


	<s:textfield name="minStudentsPerManiple"
		key="lblTrainmanMinStudentsPerManiple" required="true"/>
	<s:textfield name="maxStudentsPerManiple"
		key="lblTrainmanMaxStudentsPerManiple" required="true"/>

	<s:submit name="submit" action="Trainman" method="insertData"
		value="%{getText('lblTrainmanSubmitDataCreation')}" />

</s:form>