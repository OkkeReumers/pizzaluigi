<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<!doctype html>
<html lang='nl'>
<head>
<vdab:head title='Tik hier wat bij value van c:param stond'/>
</head>
<body>
	<vdab:menu/>
	<h1>Problemen bij het ophalen van data</h1>
	<img src='<c:url value="/images/datafout.jpg"/>' alt='data fout'>
	<p>
		We kunnen de gevraagde data niet ophalen wegens een technische
		storing.<br> Gelieve de helpdesk te contacteren.
	</p>
</body>
</html>