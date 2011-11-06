<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<tiles:useAttribute name="title" id="title" />
<title><s:text name="%{#attr.title}" /></title>
<s:head />
</head>
<body>
	<!-- Header -->
	<h1>
		<s:text name="txtTitle" />
	</h1>
	<ul>
		<li><s:url action="Home" id="homeUrl" /> <s:a href="%{homeUrl}">asd</s:a></li>
		<li><s:url action="InputSingle" id="inputSingleUrl" /> <s:a
				href="%{inputSingleUrl}">Prüfungsergebnisse erfassen (einzelnd)</s:a></li>
	</ul>
	<hr />
	<!-- Content -->
	<tiles:insertAttribute name="content" />
</body>
</html>