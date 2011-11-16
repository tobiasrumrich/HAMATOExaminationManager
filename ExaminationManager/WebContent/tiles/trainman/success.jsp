<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:url action="Home" id="homeUrl" />

<script>
	$(function() {

		$("#dialog-message").dialog({
			modal : true,
			buttons : {
				Ok : function() {
					$(this).dialog("close");
					location.href = '<s:property value="homeUrl"/>';
				}
			}
		});
	});
</script>

<div class="demo">

	<div id="dialog-message" title="Download complete">
		<p>
			<span class="ui-icon ui-icon-circle-check"
				style="float: left; margin: 0 7px 50px 0;"></span> Die Daten wurden
			erfolgreich in die Datenbank geschrieben.
		</p>
		<p>
			Bitten klicken Sie auf OK um zur Anwendung zurückzukehren.</b>.
		</p>
	</div>