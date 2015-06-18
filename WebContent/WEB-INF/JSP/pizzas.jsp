<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%> 						<!-- de prefix c associeren met de URI van de core library die de tag forEach bevat -->
<!doctype html>
<html lang='nl'>
	<head>
		<!-- <title>Pizza's</title> -->
		<!-- <link rel='shortcut icon' href='images/favicon.ico' type='image/x-icon' /> -->
		<!-- <meta name='viewport' content='width=device-width,initial-scale=1'> -->
		<!-- <link rel='stylesheet' href='styles/default.css'> -->
<vdab:head title='Tik hier wat bij value van c:param stond'/>
	</head>
	<body>
	<vdab:menu/>
		<h1>Pizza's
			<c:forEach begin='1' end='5'>											<!-- 5 keer over de forEach itereren -->
				&#9733;																<!-- HTML code van een ster -->
			</c:forEach>
		</h1>
		<ul class='zebra'>															<!-- ITEREREN OVER EEN VERZAMELING JAVABEANS -->
			<%-- <c:forEach var='pizza' items='${pizzas}'>  --%>					<!-- over de list in het request attribuut pizzas itereren, de variabele pizza wijst bij elke iteratie naar een volgend element -->
				<!--<li>${pizza}</li>--> 											<!-- je toont het huidige element ITEREREN MET FOREACH-->
				<%-- <li>${pizza.naam } ${pizza.prijs }&euro;</li> --%> 			<!-- hiermee roep je de methods getNaam en getPrijs op van het Pizza object -->
			<%-- </c:forEach> --%> 													<!-- sluit de forEach af -->
<%-- 			 <c:forEach var='entry' items='${pizzas }'> 							<!-- over een map itereren -->
				<li>${entry.key}: 
					<c:out value='${entry.value.naam}'/> ${entry.value.prijs}&euro; <!-- & teken in &amp; veranderen -->
					<c:if test='${entry.value.pikant}'> pikant </c:if>		<!-- IF-STATEMENT kijken of pikant false of true is -->
					${entry.value.pikant ? "pikant" : "niet pikant" }		<!-- IF-ELSE-STATEMENT is korter -->
					<c:choose>														
						<c:when test='${entry.value.pikant }'>
							pikant
						</c:when>
						<c:otherwise>
							niet pikant
						</c:otherwise>
					</c:choose>														<!-- CHOOSE-STATEMENT als de boolean is true pikant anders niet pikant -->
				<c:url value='/pizzas/detail.htm' var='detailURL'>					<!-- detail link met elke pizza zijn eigen key -->
					<c:param name='id' value='${entry.key }'/>
				</c:url>
					<a href='${detailURL }'>Detail</a>
				</li> 
			</c:forEach> 															<!-- sluit de forEach af --> --%>
			<c:forEach var='pizza' items='${pizzas}'>
			<li>${pizza.id}:
				<c:out value='${pizza.naam}'/> ${pizza.prijs} &euro;
				${pizza.pikant ? "pikant" : "niet pikant"}
				<c:url value='/pizzas/detail.htm' var='detailURL'>
					<c:param name='id' value="${pizza.id}"/>
				</c:url>
				<a href="<c:out value='${detailURL}'/>">Detail</a>
				<c:if test='${pizzaIdsMetFoto.contains(pizza.id)}'>
					<c:url value='/pizzafotos/${pizza.id}.jpg' var='fotoURL' />
					<a href='${fotoURL}'>Foto</a>
				</c:if>
			</li>
			</c:forEach>
		</ul>
</body>
</html>
