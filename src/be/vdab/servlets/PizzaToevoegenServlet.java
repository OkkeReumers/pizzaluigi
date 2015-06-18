package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import be.vdab.dao.PizzaDAO;
import be.vdab.entities.Pizza;

// enkele imports ...
@WebServlet("/pizzas/toevoegen.htm")
@MultipartConfig 																	// zorgen dat we foto's kunnen toevoegen
public class PizzaToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzatoevoegen.jsp";
	//private static final String SUCCESS_VIEW = "/WEB-INF/JSP/pizzas.jsp"; 			  // deze vervangen want anders hebben we een probleem als de pagina gerefreshed wordt wanneer we een pizza toevoegen, dan wordt hij 2 keer toegevoegd
	private static final String REDIRECT_URL ="%s/pizzas.htm"; 						  // als er nu een pizza toegevoegd wordt worden we teruggestuurd naar de pizzas pagina
	private final PizzaDAO pizzaDAO = new PizzaDAO();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {									 // de gebruiker doet een GET request naar de servlet om een lege html form te zien
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException { 									// de gebruiker tikt in die form de gegevens van de nieuwe pizza en submit de form. De browser doet op dat moment een POST request naar de servlet.
		request.setCharacterEncoding("UTF-8"); 										// zorgen dat we bv Hawaï kunnen ingeven
		Map<String, String> fouten = new HashMap<>();
			String naam = request.getParameter("naam");
			if (! Pizza.isNaamValid(naam)) {
				fouten.put("naam", "verplicht");
			}
			BigDecimal prijs = null;
			try {
				prijs = new BigDecimal(request.getParameter("prijs"));
				if (! Pizza.isPrijsValid(prijs)) {
					fouten.put("prijs", "tik een positief getal");
				}
			} catch (NumberFormatException ex) {
				fouten.put("prijs", "tik een getal");
			}
			Part fotoPart = request.getPart("foto"); // vraag de parameter foto die de opgeladen foto bevat
			boolean fotoIsOpgeladen = fotoPart != null && fotoPart.getSize() != 0; // controleer of de gebruiker een foto eheft opgeladen
			if (fotoIsOpgeladen && ! fotoPart.getContentType().contains("jpeg")) {
			fouten.put("foto", "geen JPEG foto");
			}
			if (fouten.isEmpty()) {
				boolean pikant = "pikant".equals(request.getParameter("pikant"));
				Pizza pizza = new Pizza(naam, prijs, pikant);
				pizzaDAO.create(pizza);
				if (fotoIsOpgeladen) {
					String pizzaFotosPad =
							this.getServletContext().getRealPath("/pizzafotos"); //de servlet context bevat een method getRealPäth. Je geef een directory binnen de website mee. Je krijgt een string terug met het absolute pad van de directory
					fotoPart.write(String.format("%s/%d.jpg", pizzaFotosPad, pizza.getId())); // bewaar de opgeladen foto in deze directory, bestandsnaam is pizzanummer .jpg
				}
				//request.setAttribute("pizzas", pizzaDAO.findAll()); 				// de nieuwe pizza is toegevoegd aan de database. Je leest alle pizza's uit de database en toont die aan de gebruiker
				//request.getRequestDispatcher(SUCCESS_VIEW).forward(request, response); // we zorgen dat bij een refresh de pizzas niet opnieuw worden toegevoegd
				//response.sendRedirect(String.format(REDIRECT_URL, request.getContextPath())); // we redirecten naar de pizzas pagina om dubbele ingaves te voorkomen bij een refresh van de post
				response.sendRedirect(response.encodeRedirectURL( String.format(REDIRECT_URL, request.getContextPath())));
			} else {
				request.setAttribute("fouten", fouten);
				request.getRequestDispatcher(VIEW).forward(request, response);
			}
	}
}