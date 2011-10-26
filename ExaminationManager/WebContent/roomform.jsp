<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="txtTitle" /></title>
<s:head />
</head>
<body>
	<h1>
		<s:text name="txtTitle" />
	</h1>
	<h4>
		<s:text name="txtRoomDetails" />
	</h4>
	<s:form>
		<s:actionerror />
		<s:textfield name="room.building" key="lblBuildingId" required="true"
			readonly="#{room.id != 0}" />
		<s:textfield name="room.roomNumber" key="lblRoomId" required="true"
			readonly="#{room.id != 0}" />
		<s:textfield name="room.seats" key="lblSeats" required="true" />
		<s:checkbox name="room.beamer" key="lblBeamerPresent" />
		<s:submit key="btnBack" action="ShowRoomList" />
		<s:submit key="btnSave" action="SaveRoom" />
	</s:form>
</body>
</html>