<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
	
	<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title><tiles:insertAttribute name="title" /></title>

<style type="text/css">
    #container {
        width: 100%;
        margin: 0px auto;
          text-align:center;
        border: 0px solid #bcbcbc;
      }
      #header {
        padding: 5px;
        margin-bottom: 5px;
        border: 0px solid #bcbcbc;
         background-color: olive;
      }
      #sidebar-left {
        width: 15%;
        height:700px;
        padding: 5px;
        margin-right: 5px;
        margin-bottom: 5px;
        float: left;
         background-color: lime;
        border: 0px solid #bcbcbc;
        font-size:10px;
      }
      #content {
        width: 75%;
        padding: 5px;
        margin-right: 5px;
        float: left;
        border: 0px solid #bcbcbc;
      }
      #footer {
        clear: both;
        padding: 5px;
        border: 0px solid #bcbcbc;
         background-color: lightblue;
      }


</style>

</head>
<body id="container">

	<div id="header">
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	</div>
	<div id="sidebar-left">
	<tiles:insertAttribute name="side"></tiles:insertAttribute>
	</div>
	<div id="content">
	<tiles:insertAttribute name="body"></tiles:insertAttribute>
	</div>
	<div id="footer">
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>

</body>
</html>