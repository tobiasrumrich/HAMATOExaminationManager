<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.hatoma.exman.model.Student, java.util.Collection, java.util.List, java.util.Collection, java.util.ArrayList" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<% List<String> l = new ArrayList<String>();
	l.add("1234, Peter Schmidt, B05 - Künstlerisches Gestalten, 30.10.2011, 3. Versuch");
	l.add("1235, Karlsson vom Dach, B04 - Eurythmie, 29.10.2011, 1. Versuch");
	l.add("1236, Nils Holgersson, B03 - Folklore, 28.10.2011, 2. Versuch");
   pageContext.setAttribute("tempstudents", l);
%>
<s:url action="FileSingleOralExamAttendance" id="fileSingleOralExamAttendanceUrl" />

<script type="text/javascript">
	$(function() {
	
		 $('#student_list').dataTable( {
			 	"bAutoWidth": false,
			 	"bProcessing": true,
			 	"aoColumnDefs": [ 
			 	                { "sWidth": "30px", "aTargets": [ 0 ] },
			 	                { "sWidth": "160px", "aTargets": [ "_all" ] }
			 	            ],
			 	"bStateSave": true,
		        "oLanguage": {
		            "oPaginate": {
		                "sPrevious": "Vorherige Seite",
		                "sNext": "Nächste Seite",
		                "sFirst": "Erste Seite",
		                "sLast": "Letzte Seite",
		            },
		            "sSearch": "Suche:",
		            "sEmptyTable": "Keine Einträge gefunden.",
					"sInfo": "Zeige Eintrag _START_ bis _END_ von _TOTAL_.",
					"sInfoEmpty": "Zeige 0 von 0 Einträgen.",
					"sInfoFiltered": " - gefiltert aus _MAX_ Einträgen.",
					"sInfoThousands": ".", //Tausender-Trennzeichen
					"sLengthMenu": "Zeige _MENU_ Einträge.",
					"sProcessing": "Bitte warten",
					"sZeroRecords": "Keine Daten vorhanden."
					
		        }
		    } );
	});
</script>

<s:text name="lblInfotextListStudentsOral" />
<s:form validate="true">
	<s:token />
	<s:select name="maniple" key="lblManiple" list="{'Bachelor of Arts, Test 2008','Bachelor of Arts, WTest 2009'}" required="true" />
</s:form>


<table id="student_list" class="hatoma_dataTable">
<thead>
<tr><td>ID</td><td>Nachname</td><td>Vorname</td></tr>
</thead>
<tbody>


<s:iterator value="students" status="iteratorStatus">

  <tr>
  	<td>
 		<s:a href="%{fileSingleOralExamAttendanceUrl}">
			<s:property value="id" />
 		</s:a> 
  	</td>
  	<td>
 		<s:a href="%{fileSingleOralExamAttendanceUrl}">
 			<s:property value="lastname" /> 
 		</s:a>
  	</td>
  	<td>
 		<s:a href="%{fileSingleOralExamAttendanceUrl}">
 			<s:property value="forename" /> 
 		</s:a>
  	</td>
  	
  </tr>
</s:iterator>
</tbody>
</table>