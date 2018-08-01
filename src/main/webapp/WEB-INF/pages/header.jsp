<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<html>
	<head>
		<meta name="viewport" 
		content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta charset="UTF-8">
		
		  <!-- JQuery -->
		  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.js"></script>
		  
		<script src="http://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		  
		  <!-- PopperJS -->
		  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
		  <!-- Moment JS -->
		  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.21.0/moment.min.js"></script>
		  <!-- Bootstrap CSS -->
		  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
		  <!-- Bootstrap JS -->
		  <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/js/bootstrap.min.js"></script>
		  <!-- Font Awesome CSS -->
		<link href="https://use.fontawesome.com/releases/v5.0.6/css/all.css" rel="stylesheet">
		<!-- CSS Propio-->
		<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
		
		<title>Control de Gastos</title>
		
		<!-- ToolTips -->
		<script>
        	$(document).ready(function(){
    			$('[rel=tooltip]').tooltip({ trigger: "hover" });
			});
      </script>
	</head>
	
	<body>
	<c:set var="root" value="${pageContext.request.contextPath}"/>