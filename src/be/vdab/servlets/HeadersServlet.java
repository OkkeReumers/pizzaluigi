package be.vdab.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// enkele imports ...
@WebServlet("/headers.htm")
public class HeadersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/headers.jsp";
	//private final Map<String, String> browsers = new HashMap<>(); // de entry value is de browsersoort
//	public HeadersServlet() {
//		browsers.put("firefox", "Firefox");
//		browsers.put("chrome", "Chrome");
//		browsers.put("msie", "Internet Explorer");
//		browsers.put("Trident", "Internet Explorer");
//	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String userAgent = request.getHeader("user-agent").toLowerCase(); // je vraagt de request header user-agent en je converteert hem naar kleine letters
//		String browser = "onbekend"; // er bestaan nog andere browsers als firefox , chrome en IE, je toont dan de tekst onbekend aan de gebruiker
//		for (Map.Entry<String, String> entry : browsers.entrySet()) { //je overloopt de gekende browsers
//			if (userAgent.contains(entry.getKey())) { // als de head user-agent het woord bevat dat enkel bij de huidige browser past heb je de browser gevonden
//				browser = entry.getValue();
//			break; //je stopt dan de for loop
//			}
		Map<String, String> headers = new LinkedHashMap<>();
		for (Enumeration<String> headerNames = request.getHeaderNames();
				headerNames.hasMoreElements(); ){
			String headerName = headerNames.nextElement();
			headers.put(headerName, request.getHeader(headerName));
		}
		//request.setAttribute("browser", browser);
		request.setAttribute("headers", headers);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
