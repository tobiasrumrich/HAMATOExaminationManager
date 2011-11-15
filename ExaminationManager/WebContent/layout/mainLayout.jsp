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
<link type="text/css"
	href="resources/jquery/css/smoothness/jquery.ui.datepicker.css"
	rel="stylesheet" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<script type="text/javascript"
	src="resources/jquery/js/jquery-1.6.2.min.js"></script>	
<script type="text/javascript"
	src="resources/jquery/js/datatables/media/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="resources/jquery/js/jquery-ui-1.8.16.custom.min.js"></script>

<script type="text/javascript">
	$(function() {
		$("#hatoma_accordion").accordion({
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
<!-- CSS für DatPicker -->
<style type="text/css">
#ui-datepicker-div {
	background-color:white;
	border:1px solid grey;
}
</style>
<!-- CSS für DataTables -->
<style type="text/css">
@import "resources/jquery/js/datatables/media/css/demo_table.css";

.dataTables_info {
	font-size: 70%;
	font-weight: bold;
}
/*Spaltenüberschriften*/
.sorting_asc,.sorting_desc,.sorting {
	font-weight: bold;
}

.sorting_asc,.sorting_desc {
	font-weight: bold;
	background-color: #4fa0cd;
}

.sorting {
	font-weight: bold;
	background-color: #91c3e0;
}

.hatoma_dataTable {
	clear: both;
	border-collapse: collapse;
	border: 2px grey solid;
	margin: 05px;
}

.dataTables_wrapper {
	background-color: #eeeeee;
	padding: 10px;
	min-height: none !important;
}

tr.odd {
	background-color: #eef6fa;
}

tr.odd td.sorting_1 {
	background-color: #d3e7f3;
}

tr.even {
	background-color: white;
}

tr.even td.sorting_1 {
	background-color: #e1eff6;
}

td {
	padding: 0.4em;
}

.hatoma_dataTable a {
	text-decoration: underline;
	color: black;
	font-weight: bold;
}

.hatoma_dataTable a:hover {
	text-decoration: underline;
	color: orange;
	font-weight: bold;
}

.hatoma_dataTable {
font-size: 80%;
}
</style>
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
	background-image: url(resources/img/header_bg.gif);
	background-repeat: repeat-x;
}

div#header {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 54px;
	background-image: url(resources/img/header_bg.gif);
	background-repeat: repeat-x;
	line-height: 54px;
	padding: 0px 0px 0px 13px;
	color: white;
	z-index: 2;
}

div#navigation-wrapper {
	background: #b9d9eb;
	float: left;
	width: 250px;
	clear: both;
	height: 100%;
}

div#navigation {
	position: relative;
	top: 54px;
	height: 340px;
}

div#header h1 {
	font-size: 120%;
	padding: 0px;
	margin: 0px;
}

div#container {
	width: 1000px;
	height: 100%;
}

div#main-wrapper {
	height: 100%;
	overflow: auto;
}

div#main {
	position: relative;
	top: 54px;
	padding: 10px;
}

div#main h1 {
	margin: 0px;
}

/******/
.ui-accordion {
	margin: 0px;
	padding: 0px;
	border: 0px;
	height: 0px;
}

.ui-accordion-header {
	padding: 5px 0px 5px 10px;
	background: lightgray;
	margin: 0px;
	font-size: 90%;
}

.ui-accordion-header a {
	color: black;
	text-decoration: none;
}

.ui-accordion * :focus {
	outline: 0;
}

.ui-accordion-content {
	background: #eeeeee;
}

.ui-accordion-content-active { /*padding: 5px 0px 5px 10px*/
	margin: 0px;
}

span#sessioninfo {
	float: right;
	padding-right: 20px;
}
</style>
</head>
<body>

	<div id="header">
		<span id="sessioninfo">&lt;sessioninfo&gt;</span>
		<h1>HATOMA Examination Manager</h1>

	</div>

	<div id="container">
		<div id="navigation-wrapper">
			<div id="navigation">
				<div id="hatoma_accordion">
					<div>
						<h3>
							<a href="#">Reports</a>
						</h3>
						<div>
							<p>Notenübersicht zu Person</p>
							<p>Notenübersicht zu Manipel</p>
						</div>
					</div>

					<div>
						<h3>
							<a href="#">Prüfungsergebnisse</a>
						</h3>
						<div>
							<p>Batcherfassung</p>

							<p>
								<s:url action="FileSingleExamAttendance"
									id="fileSingleExamAttendanceUrl" />
								<s:a href="%{fileSingleExamAttendanceUrl}">Einzelerfassung</s:a>

							</p>
							<p>
								<s:url action="ShowStudentListForOralExamination"
									id="showStudentListForOralExaminationUrl" />
								<s:a href="%{showStudentListForOralExaminationUrl}">Erfassung mündlicher Noten</s:a>
							</p>
						</div>
					</div>

					<div>
						<h3>
							<a href="#">Prüfungsübersicht</a>
						</h3>
						<div>
							<p>
								<s:url action="CreateExam" id="createExamUrl" />
								<s:a href="%{createExamUrl}">Neue Prüfung anlegen</s:a>
							</p>
							<p>Prüfungen verwalten</p>
						</div>
					</div>

					<div>
						<h3>
							<a href="#">Sonstiges</a>
						</h3>
						<div>
							<p>dsadsd</p>
						</div>
					</div>
				</div>

			</div>
		</div>
		<div id="main-wrapper">
			<div id="main">
				<h1>
					<s:text name="%{#attr.actionDisplayName}" />
				</h1>

				<tiles:insertAttribute name="content" />

			</div>
		</div>
	</div>

</body>
</html>