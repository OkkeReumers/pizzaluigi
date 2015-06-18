package be.vdab.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import be.vdab.dao.PizzaDAO;

@WebServlet("/pizzas/detail.htm")
public class PizzaDetailServlet extends HttpServlet {
private static final long serialVersionUID= 1L;
private static final String VIEW = "/WEB-INF/JSP/pizzadetail.jsp";
private final transient PizzaDAO pizzaDAO = new PizzaDAO();

@Resource(name = PizzaDAO.JNDI_NAME) 
void setDataSource(DataSource dataSource) {
	pizzaDAO.setDataSource(dataSource); 
}

@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
	try {
		request.setAttribute("pizza",
			pizzaDAO.read(Long.parseLong(request.getParameter("id"))));
	} catch (NumberFormatException ex) { // request param bevat geen getal
		request.setAttribute("fout", "Nummer niet correct");
	}
	request.getRequestDispatcher(VIEW).forward(request, response);
	}
}