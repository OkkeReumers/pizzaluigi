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
		<h1>Statistiek</h1>
		<div>${aantalMandjes} mandje(s)</div>
		<dl>
			<dt>Welkom</dt>
			<dd>${indexRequests}</dd> 										<!-- je leest een servlet context attribuut met EL -->
			<dt>Pizzas</dt>
			<dd>${pizzasRequests}</dd>
		</dl>
	</body>
</html>

<!-- het aantal keren dat de pagina wordt geopend tonen, deze gegevens halen we uit de index en pizzaservlet -->