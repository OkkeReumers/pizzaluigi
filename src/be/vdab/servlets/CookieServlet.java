package be.vdab.servlets;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// enkele imports ...
@WebServlet("/cookies.htm")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/cookies.jsp";
	private static final int COOKIE_MAXIMUM_LEEFTIJD =
			60 /* seconden */ * 30 /* minuten */;
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Cookie cookie = new Cookie("naam",
				URLEncoder.encode(request.getParameter("naam"), "UTF-8"));  	// de request parameter naam bevat tekst in UTF-8 formaat , je converteert de tekst naar URL encoded formaat met de static URLEncoder method encode, 
				cookie.setMaxAge(COOKIE_MAXIMUM_LEEFTIJD);						//de 1e parameter bevat de te converteren tekst, de 2e parameter bevat het formaat van de tekst
				response.addCookie(cookie);
				response.sendRedirect(request.getRequestURI()); 				// je doet een redirect naar de URL van de request. Hier de URL van de huidige servlet
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if ("naam".equals(cookie.getName())) {
					request.setAttribute("naam",
							URLDecoder.decode(cookie.getValue(), "UTF-8")); 	// de cookie naam bevat tekst in URL encoded formaat, je converteert de tekst naar UTF-8 met static URLDecoder method decode, 
							break;												// de 1e parameter bevat de tekst, de 2e parameter bevat het formaat van de geconverteerde tekst
				}
			}
		}
		String locale = request.getParameter("locale");
		if (locale != null) {
		request.getSession().setAttribute("locale", locale);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}