<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title><s:text name="txtTitle"/></title>
		<s:head/>
	</head>
	<body>
		<h1><s:text name="txtTitle"/></h1>
		<h4><s:text name="txtRoomList"/></h4>
		<s:form>
			<s:actionerror/>
			<table>
				<tr>
					<th>&nbsp;</th>
					<th><s:text name="txtBuilding"/></th>
					<th><s:text name="txtRoom"/></th>
				</tr>
				<s:iterator value="rooms">
					<tr>
						<td><s:radio name="selectedId" list="#{id:''}" theme="simple"/></td>
						<td><s:property value="building"/></td>
						<td><s:property value="roomNumber"/></td>
					</tr>
				</s:iterator>
				<tr>
					<td colspan="3">
						<s:submit key="btnAdd" theme="simple" action="AddRoom"/>
						<s:submit key="btnEdit" theme="simple" action="ShowRoom"/>
						<s:submit key="btnDelete" theme="simple" action="DeleteRoom"/>
					</td>
				</tr>
			</table>
		</s:form>
	</body>
</html>