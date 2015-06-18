<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>				<!-- Unicode/ISO 10646-tekens opslaan als een stroom van bytes -->
<%@taglib uri='http://vdab.be/tags' prefix='vdab'%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='fmt' uri='http://java.sun.com/jsp/jstl/fmt'%>
<!doctype html> 
<html lang='nl'>																	<!-- taal is nederlands -->
	 <head> 												
		<title>Pizza Luigi</title>													<!-- titel van de pagina komt in het browsertabblad te staan -->
		<!-- <link rel='shortcut icon' href='images/favicon.ico' type='image/x-icon'/> --><!-- icoontje voor in het browsertabblad, staat in de images map-->
		<!-- <meta name='viewport' content='width=device-width,initial-scale=1'>  --><!-- de breedte moet gelijk zijn aan de scherm breedte van het device -->
		<!-- <link rel='stylesheet' href='styles/default.css'> -->					<!-- css declareren , in de map styles -->
<vdab:head title='Tik hier wat bij value van c:param stond'/>																<!-- head.jsp importeren en de parameter title meegeven  -->
	</head>
	<body>
	<vdab:menu/>										<!-- menu.jsp inladen -->
		<h1>Pizza Luigi</h1> 														<!-- titel van de pagina -->
		<img src=<c:url value="/images/pizza.jpg"/> alt='pizza' class='fullwidth'>					<!-- afbeelding , volledige breedte van het scherm -->
		<h2>${begroeting}</h2> 														<!-- de jsp vervangt ${begroeting} door de inhoud van het request attribuut begroeting -->
		<h2>De zaakvoerder</h2>
		<dl>
			<dt>Naam</dt><dd>${zaakvoerder.naam }</dd> 								<!-- de jsp vervangt ${zaakvoerder.naam } door de inhoud van het request attribuut zaakvoerder.naam -->
			<dt>Aantal kinderen</dt><dd>${zaakvoerder.aantalKinderen }</dd>			<!-- de jsp vervangt ${zaakvoerder.aantalKinderen } door de inhoud van het request attribuut zaakvoerder.aantalKinderen -->
			<dt>Gehuwd</dt><dd>${zaakvoerder.gehuwd ? 'Ja' : 'Nee' }</dd>			<!-- de jsp vervangt ${zaakvoerder.gehuwd } door de inhoud van het request attribuut en toont dan ja of nee -->
<%-- 			<dt>Adres</dt><dd>${zaakvoerder.adres.straat }							<!-- de jsp vervangt ${zaakvoerder.adres.straat} door de overeenkomende geneste attributen -->
							  ${zaakvoerder.adres.huisNr }<br>
							  ${zaakvoerder.adres.postcode }
							  ${zaakvoerder.adres.gemeente }</dd> --%>
				<dt>Vandaag</dt><dd><fmt:formatDate value='${nu}' type='date' dateStyle='long'/></dd>
			<dt>Aantal pizza's verkocht</dt>
			<dd><fmt:formatNumber value='${aantalPizzasVerkocht}'/></dd>
		</dl>
		<div>Deze pagina werd ${aantalKeerBekeken } keer bekeken.</div>
	</body>
</html>