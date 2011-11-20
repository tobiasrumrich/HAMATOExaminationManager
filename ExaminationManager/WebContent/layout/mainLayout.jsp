<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

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
<script type="text/javascript"
	src="resources/jquery/js/jquery.cookie.js"></script>
<script type="text/javascript" src="resources/hatoma.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function() {

		var accordion = $("#hatoma_accordion");
		var index = $.cookie("accordion");
		var active;
		if (index !== null) {
			active = accordion.find("h3:eq(" + index + ")");
		} else {
			active = 0;
		}

		$("#hatoma_accordion").accordion({
			header : "h3",
			collapsible : false,
			fillSpace : true,
			active : active,
			animated : true,
			change : function(event, ui) {
				var index = $(this).find("h3").index(ui.newHeader[0]);
				$.cookie("accordion", index, {
					path : "/"
				});
			}

		});

		$("#hatoma_accordion").show("fade");
		$(".hatoma_dataTable").show("fade");

	});
</script>
<style type="text/css">
@import "resources/jquery/js/datatables/media/css/demo_table.css";
</style>
<link rel="stylesheet" type="text/css" media="screen"
	href="resources/css/hatoma_screen.css" />
<link rel="stylesheet" type="text/css" media="print"
	href="resources/css/hatoma_print.css" />

</head>
<body>

	<div id="headerbar">
		<div id="header">
			<span id="sessioninfo"><s:text name="txtLoggedInAs" /> <strong><sec:authentication
						property="principal.username" /></strong> (<a
				href="<s:url value="j_spring_security_logout" />">Logout</a>)</span>
			<h1>
				<s:text name="%{#attr.title}" />
			</h1>
		</div>
	</div>

	<div id="container">
		<div id="navigation-wrapper">
			<div id="navigation">
				<div id="hatoma_accordion">
					<div>
						<h3 id="acc001">
							<a href="#"><img src="resources/img/icons/report.png" /> <s:text
									name="lblNavReports" /></a>
						</h3>
						<div>
							<p><s:url action="ExamAttendanceOverview" id="examAttendanceOverviewStudentUrl" method="student">
								</s:url>
								<s:a href="%{examAttendanceOverviewStudentUrl}">
								<img src="resources/img/icons/user.png" />
								<s:text name="lblNavExamGradeOverviewByPerson" />
								</s:a>
							</p>
							<p>
							<s:url action="ExamAttendanceOverview" id="examAttendanceOverviewManipleUrl" method="maniple">
								</s:url>
								<s:a href="%{examAttendanceOverviewManipleUrl}">
								<img src="resources/img/icons/group.png" />
								<s:text name="lblNavExamGradeOverviewByManiple" />
								</s:a>
							</p>
						</div>
					</div>

					<div id="acc002">
						<h3>
							<a href="#"><img
								src="resources/img/icons/award_star_gold_2.png" /> <s:text
									name="lblNavExamResults" /></a>
						</h3>
						<div>
							<p>
								<s:url action="ExamOverview" id="examAttendanceBulkUpdateUrl">
									<s:param name="target">bulkInsert</s:param>
								</s:url>
								<s:a href="%{examAttendanceBulkUpdateUrl}">
									<img src="resources/img/icons/group_add.png" />
									<s:text name="lblNavExamAttendanceBulkInsert" />
								</s:a>
							</p>

							<p>
								<s:url action="FileSingleExamAttendance"
									id="fileSingleExamAttendanceUrl" />
								<s:a href="%{fileSingleExamAttendanceUrl}">
									<img src="resources/img/icons/user_add.png" />
									<s:text name="lblNavExamAttendanceSingleInsert" />
								</s:a>

							</p>
							<p>
								<s:url action="OralExamination" id="oralExaminationUrl" />
								<s:a href="%{oralExaminationUrl}">
									<img src="resources/img/icons/comments_add.png" />
									<s:text name="lblNavExamAttendanceOralExamination" />
								</s:a>
							</p>
						</div>
					</div>

					<div id="acc003">
						<h3>
							<a href="#"><img src="resources/img/icons/calendar.png" /> <s:text
									name="lblNaVExamOverviewHeader" /></a>
						</h3>
						<div>
							<p>
								<s:url action="CreateExam" id="createExamUrl" />
								<s:a href="%{createExamUrl}">
									<img src="resources/img/icons/date_add.png" />
									<s:text name="lblNavCreateNewExam" />
								</s:a>
							</p>

							<p>
								<s:url action="ExamOverview" id="examOverviewUrl" />
								<s:a href="%{examOverviewUrl}">
									<img src="resources/img/icons/calendar_edit.png" />
									<s:text name="lblNavAdministerExams" />
								</s:a>
							</p>
						</div>
					</div>

					<div id="acc004">
						<h3>
							<a href="#"><img src="resources/img/icons/cog.png" /> <s:text
									name="lblNavOthers" /></a>
						</h3>
						<div>
							<p>
								<s:url action="Trainman" id="trainManUrl" />
								<s:a href="%{trainManUrl}">
									<img src="resources/img/icons/database_go.png" />
									<s:text name="lblNavTrainman" />
								</s:a>
							</p>
						</div>
					</div>
				</div>
			</div>
			<div id="left-panel-info">
				<span class="left-panel-info">Eine J2EE Anwendung von</span><br />
				Hannes Lemberg, 3547<br /> Tobias Rumrich, 3638<br /> Marcel
				Schroeter, 3690<br />
				<hr>
				Nordakademie 2011
			</div>
		</div>
		<div id="main-wrapper">
			<div id="main">
				<h1>
					<s:text name="%{#attr.actionDisplayName}" />
				</h1>

				<!-- Hinweismeldungen -->
				<s:if test="hasActionMessages()">
					<div class="ui-widget">
						<div class="ui-state-highlight ui-corner-all"
							style="margin-top: 20px; padding: 0 .7em;">
							<p>
								<span class="ui-icon ui-icon-info"
									style="float: left; margin-right: .3em;"></span> <strong><s:text
										name="txtUiNotificationHeader" /></strong>
								<s:actionmessage />
							</p>
						</div>
					</div>
				</s:if>

				<s:if test="hasActionErrors()">
					<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
						<p>
							<span class="ui-icon ui-icon-alert"
								style="float: left; margin-right: .3em;"></span> <strong><s:text
									name="txtErrorHead" /></strong>
						</p>
						<s:actionerror />
					</div>
				</s:if>

				<tiles:insertAttribute name="content" />

			</div>
		</div>
	</div>

</body>
</html>