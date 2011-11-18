<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<s:url action="Home" id="homeUrl" />

<script>
	$(function() {

		$("#dialog-message").dialog({
			modal : true,
			resizable : false,
			closeOnEscape : false,
			disabled : true,
			width : 450,
			close: function(event,ui) {location.href = '<s:property value="homeUrl"/>';},
			buttons : {
				Ok : function() {
					$(this).dialog("close");
					
				}
			}
		});
	});
</script>

	<div id="dialog-message"
		title="<s:text name="lblTrainmanCheckConfirmDialogTitle" />">
		<p>
			<span class="ui-icon ui-icon-circle-check"
				style="float: left; margin: 0 7px 50px 0;"></span>
			<s:text name="lblTrainmanCheckConfirmDialogPrimaryMessage" />
		</p>
		<p>
			<s:text name="lblTrainmanCheckConfirmDialogSecondaryMessage" />
		</p>
	</div>