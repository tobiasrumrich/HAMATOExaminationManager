<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="de.hatoma.exman.model.Student, java.util.Collection, java.util.List, java.util.Collection, java.util.ArrayList" %>
<%@ taglib uri="/struts-tags" prefix="s"%>

<% List<String> l = new ArrayList<String>();
	l.add("1234, Peter Schmidt, B05 - Künstlerisches Gestalten, 30.10.2011, 3. Versuch");
	l.add("1235, Karlsson vom Dach, B04 - Eurythmie, 29.10.2011, 1. Versuch");
	l.add("1236, Nils Holgersson, B03 - Folklore, 28.10.2011, 2. Versuch");
   pageContext.setAttribute("students", l);
%>

<script type="text/javascript">
	$(function() {
		$("#student_list").dataTable();
	});
</script>

<s:text name="lblInfotextListStudentsOral" />
<s:form validate="true">
	<s:token />
	<s:select name="maniple" key="lblManiple" list="{'Bachelor of Arts, Test 2008','Bachelor of Arts, WTest 2009'}" required="true" />
</s:form>
<table id="student_list">
<thead>
<tr><td>Student</td></tr>
</thead>
<tbody>
<s:iterator value="%{#attr.students}">
  <tr><td><s:property /></td></tr>
</s:iterator>
</tbody>
</table>
