<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<tiles:useAttribute name="applicationName" id="title" />
<tiles:useAttribute name="actionDisplayName" id="actionDisplayName" />

<title><s:text name="%{#attr.actionDisplayName}" /> - <s:text
		name="%{#attr.title}" /></title>
<s:head />


<link type="text/css"
	href="resources/jquery/css/smoothness/jquery-ui-1.8.16.custom.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="resources/jquery/js/jquery-1.6.2.min.js"></script>

<script type="text/javascript"
	src="resources/jquery/js/datatables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="resources/jquery/js/jquery-ui-1.8.16.custom.min.js"></script>

<script type="text/javascript">
	$(function() {
		$("#hatoma_accordeon").accordion({
			header : "h3",
			active : 0,
			collapsible : false,
			fillSpace : true,
			animated : true
		});

		$("#sessioninfo").html(
				'<sessioninfo>Angemeldet als <u>Hannelore Pehlke</u>');
	});
</script>
<style type="text/css">
html {
	height: 100%;
}

body {
	height: 100%;
	font-family: "Lucida Grande", "Lucida Sans Unicode", tahoma, Verdana,
		Geneva, Arial, Helvetica, sans-serif;
	margin: 0px;
	padding: 0px;
	background-attachment: fixed;
}

div#headerbar {
	position: fixed;
	top: 0;
	left: 0;
	height: 54px;
	width: 100%;
	line-height: 54px;
	padding: 0px 0px 0px 13px;
	color: white;
	z-index: 2;
	background-image: url(resources/img/header_bg.gif);
	background-repeat: repeat-x;
}

div#header {
	width: 1000px;
}

div#header h1 {
	font-size: 120%;
	padding: 0px;
	margin: 0px;
}

div#header span#sessioninfo {
	float: right;
}

div#container {
	margin: 60px 0px 0px 13px;
	padding-top: 5px;
	width: 1000px;
	z-index: 1;
}
</style>
</head>
<body>

	<div id="headerbar">
		<div id="header">
			<span id="sessioninfo">&lt;sessioninfo&gt;</span>
			<h1>HATOMA Examination Manager</h1>
		</div>
	</div>
	<div id="container">
		<h1>
			<s:text name="%{#attr.actionDisplayName}" />
		</h1>

		<tiles:insertAttribute name="content" />

	</div>



</body>
</html>