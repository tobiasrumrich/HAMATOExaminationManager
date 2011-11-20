<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">
						<p>
							<span class="ui-icon ui-icon-alert"
								style="float: left; margin-right: .3em;"></span> <strong><s:text
									name="txtErrorHead" /></strong>
						</p>
						<p><s:text
									name="txtTrainmanRunMeBecauseDatabaseNotCompliant" /></p>
									<p><s:url action="Trainman" id="trainManUrl" />
								<s:a href="%{trainManUrl}">
									<img src="resources/img/icons/database_go.png" />
									<s:text name="lblNavTrainman" />
								</s:a></p>
					</div>

