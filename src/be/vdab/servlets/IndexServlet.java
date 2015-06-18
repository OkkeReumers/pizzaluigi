package be.vdab.servlets;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Begroeting;
import be.vdab.entities.Persoon;

@WebServlet(urlPatterns = "/index.htm", name = "indexservlet") 								// url en naam om in de xml file naar te verwijzen
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp"; 							//combineren met de index.jsp
	private final Persoon zaakvoerder = new Persoon();										// een nieuw object persoon aanmaken
	private final AtomicInteger aantalKeerBekeken = new AtomicInteger(); 					// automatisch op 0 geïnitialiseerd
	private static final String INDEX_REQUESTS = "indexRequests"; 							// constante om het aantal browser requests per servlet bij te houden
	
//public IndexServlet() {																	// DEZE CONSTRUCTOR WORDT VERVANGEN DOOR DE METHOD INIT wanneer we de gegevens uit de xml file lezen
//	zaakvoerder.setVoornaam("Luigi"); 														// attributen van de variabele invullen in een constructor
//	zaakvoerder.setFamilienaam("Peperone");
//	zaakvoerder.setAantalKinderen(7);
//	zaakvoerder.setGehuwd(true);
//	Adres adres = new Adres(); 																// een nieuw object adres maken binnen de constructor
//	adres.setStraat("Grote markt");
//	adres.setHuisNr("3");
//	adres.setPostcode(9700);
//	adres.setGemeente("Oudenaarde");
//	zaakvoerder.setAdres(adres);
//}
	
	
@Override
public void init() throws ServletException { 													// met de servletmethod getInitParameter lees je de waarde van 1 initialisatieparameter
	ServletContext context = this.getServletContext();											//met context verwijzen naar de servletContext in de xml file
	//zaakvoerder.setVoornaam(this.getInitParameter("voornaam"));								// je geeft de paramternaam mee aan de method bv "voornaam"
	//zaakvoerder.setFamilienaam(this.getInitParameter("familienaam"));							// je krijgt de paramterwaarde als een String of null als de parameter niet bestaat bv "Peperone"
	//zaakvoerder.setAantalKinderen(Integer.parseInt(this.getInitParameter("aantalKinderen")));
	//zaakvoerder.setGehuwd(Boolean.parseBoolean(this.getInitParameter("gehuwd")));
	zaakvoerder.setVoornaam(context.getInitParameter("voornaam")); 								//context gebruiken ipv this om de parameters in te lezen
	zaakvoerder.setFamilienaam(context.getInitParameter("familienaam"));
	zaakvoerder.setAantalKinderen(Integer.parseInt(context.getInitParameter("aantalKinderen")));
	zaakvoerder.setGehuwd(Boolean.parseBoolean(context.getInitParameter("gehuwd")));
	context.setAttribute(INDEX_REQUESTS, new AtomicInteger());
}
	
	
	
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	//int uur = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); 							// integer uur is accurate uur van de dag
	//request.setAttribute("begroeting", uur >= 6 && uur < 12 ? "Goede morgen" : 			// een request attribuut begroeting maken met als inhoud de af te beelden boodschap
			//uur >= 12 && uur < 18 ? "Goede middag": "Goede avond"); 
	request.setAttribute("nu", Calendar.getInstance().getTime());
	request.setAttribute("aantalPizzasVerkocht", 23000);
	request.setAttribute("aantalKeerBekeken" , aantalKeerBekeken.incrementAndGet());
	request.setAttribute("begroeting", new Begroeting()); 									// EL voert op een object in een request attribuut de method toString uit en neemt het resultaat op in de response
	request.setAttribute("zaakvoerder", zaakvoerder);										// via dit attribuut kunnen we de gegevens die in de constructor werden ingegeven opvragen
	request.getRequestDispatcher(VIEW).forward(request, response); 							// een requestdispatcher object maken en de request en response doorgeven naar de jsp
	((AtomicInteger) this.getServletContext().getAttribute(INDEX_REQUESTS)).incrementAndGet();	
}
}

