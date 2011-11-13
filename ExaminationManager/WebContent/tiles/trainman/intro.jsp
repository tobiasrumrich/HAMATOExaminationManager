<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<p>Hinweis: Die für den Betrieb der Anwendung zwingend erforderlichen Komponenten sind standardmäßig ausgewählt und können nicht von der Erzeugung ausgenommen werden.</p>
<s:form method="POST" validate="true">
	<s:token />
	<s:checkbox name="createStudyBranches" disabled="true" value="true" key="lblTrainmanCheckCreateStudyBranches"/>
	<s:checkbox name="createManiples" disabled="true" value="true" key="lblTrainmanCheckCreateManiples"/>
	<s:checkbox name="createExaminers" disabled="true" value="true" key="lblTrainmanCheckCreateExaminers"/>
	<s:checkbox name="createStudents" disabled="true" value="true" key="lblTrainmanCheckCreateStudents"/>
	
	<s:checkbox name="createExamAttendances" value="false" key="lblTrainmanCheckCreateExamAttendances" />
	<s:submit name="submit" action="Trainman" method="insertData" value="%{getText('lblTrainmanSubmitDataCreation')}" />
	
</s:form>