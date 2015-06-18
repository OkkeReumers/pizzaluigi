package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import be.vdab.dao.PizzaDAO;
import be.vdab.entities.Pizza;

@WebServlet("/pizzas/bestellen.htm")
public class PizzaBestellenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzabestellen.jsp";
	private static final String MANDJE = "mandje";
	private final transient PizzaDAO pizzaDAO = new PizzaDAO(); 					// als java de servlet via serialization wegschrijft, moet java het DAO object niet meeschrijven
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException { 									// je gebruiker ziet met een GET request de invoerpagina van het mandje
		request.setAttribute("allePizzas", pizzaDAO.findAll()); 					// je plaatst alle pizza's op request scope, om ze te tonen in de JSP
		HttpSession session = request.getSession(false); 							// je haalt de session van de gebruiker op (als hij al een session heeft)
		if (session != null) {
			@SuppressWarnings("unchecked") 											// je cast bij de volgende lijn een object onder de gedaant van Object (het resultaat van session.getAttribute) naar een Set<Long> je onderdrukt hier de compiler warning die de cast veroorzaakt
			Set<Long> mandje = (Set<Long>) session.getAttribute(MANDJE); 			// je leest de inhoud van het session attribuut mandje
			if (mandje != null) { 													// de gebruiker heeft in zijn session al een attribuut mandje
				List<Pizza> pizzasInMandje = new ArrayList<>();
			for (long id : mandje) { 												// het mandje bevat enkel pizzanummers, je leest per nummer de bijhorende pizza, de plaatst de pizza's in een verzameling
				pizzasInMandje.add(pizzaDAO.read(id));
			}
			request.setAttribute("pizzasInMandje", pizzasInMandje); 				// je plaatst de verzameling op request scope, je kan zo de pizza's in het mandje tonen in de JSP
			}
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Resource(name = PizzaDAO.JNDI_NAME)
	void setDataSource(DataSource dataSource) {
	pizzaDAO.setDataSource(dataSource); 
	}
	
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterValues("id") != null) {
			HttpSession session = request.getSession(); 							// je haalt de session van de huidige gebruiker op of je maakt voor de huidige gebruiker een session als hij er nog geen had.
			@SuppressWarnings("unchecked")
			Set<Long> mandje = (Set<Long>)session.getAttribute(MANDJE); 			// je leest de inhoud van het session attribuut mandje
			if (mandje == null) { 													// de gebruiker heeft in zijn session nog geen attribuut mandje
				mandje = new LinkedHashSet<>();  									// je maakt een leeg mandje voor de gebruiker
			}
			for (String id : request.getParameterValues("id")) {					// je voegt de nummers van de geselecteerde pizza's toe aan het mandje
				mandje.add(Long.parseLong(id)); 
			}
			session.setAttribute(MANDJE, mandje); 									// je wijzigt het session attribuut mandje met het bijgewerkte mandje
		}
		response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
	}
}