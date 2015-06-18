package be.vdab.servlets;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.PizzaDAO;


@WebServlet("/pizzas/tussenprijzen.htm")
public class PizzasTussenPrijzenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzastussenprijzen.jsp";
	private final transient PizzaDAO pizzaDAO = new PizzaDAO();
	
	@Resource(name = PizzaDAO.JNDI_NAME) 
	void setDataSource(DataSource dataSource) {
		pizzaDAO.setDataSource(dataSource); 
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getQueryString() != null) {										 //de method getQueryString geeft het onderdeel query string van de huidige request URL of null als de URL geen query string bevat. Als het resultaat null is doet de gebruiker een request om de pagina met lege form te zien, anders heeft de gebruiker de form gesubmit
			Map<String, String> fouten = new HashMap<>(); 							 // deze map bevat fouten die de gebruiker maakte bij het intikken van de form. De entry key is de naam van het foutieve invoervak, value is een bijhorende foutmelding
			BigDecimal van = null;
			try {
				van = new BigDecimal(request.getParameter("van"));
			} catch (NumberFormatException ex) {
				fouten.put("van", "tik een getal"); 								// je converteert de inhoud van het invoervak naar BigDecimal. Je krijgt een NumberFormatException als dit mislukt. Je voegt dan een entry toe aan de map fouten 
			}
			BigDecimal tot = null;
			try {
				tot = new BigDecimal(request.getParameter("tot"));
			} catch (NumberFormatException ex) {
				fouten.put("tot", "tik een getal");
			}
			if (fouten.isEmpty()) {
				request.setAttribute("pizzas", pizzaDAO.findByPrijsBetween(van, tot));
			} else {
				request.setAttribute("fouten", fouten);
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}