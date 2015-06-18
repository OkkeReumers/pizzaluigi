package be.vdab.listeners;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

// enkele imports ...
@WebListener  // maak met @WebListener een class als listener kenbaar aan de webserver 
public class MandjeListener
implements ServletContextListener, HttpSessionAttributeListener { // implementeer ServletContextListener en reageert zo op starten en stoppen van de website. Implementeer HttpSessionAttributeListener en reageer zo op het toevoegen, verwijderen en wijzigen van session attributen.
	private static final String MANDJE = "mandje";
private static final String AANTAL_MANDJES = "aantalMandjes";
@Override
public void contextInitialized(ServletContextEvent event) { // de website start, je maakt een servlet context attribuut aantalMandjes
	event.getServletContext().setAttribute(AANTAL_MANDJES, new AtomicInteger());
}
@Override
public void contextDestroyed(ServletContextEvent event) {
}

@Override
public void attributeAdded(HttpSessionBindingEvent event) { // telkens een attribuut toegevoegd wordt aan een sessie, roept de webserver de method attributeAdded op
	if (MANDJE.equals(event.getName())) { // je controleert of het toegevoegde attribuut de naam mandje heeft
		((AtomicInteger)
				event.getSession().getServletContext().getAttribute(AANTAL_MANDJES))
				.getAndIncrement(); // je verhoogt de teller in het servlet context attribuut aantalMandjes
	}
}
@Override
public void attributeRemoved(HttpSessionBindingEvent event) { // telkens een attribuut verwijderd wordt uit een sessie, roept de webserver de method attributeRemoved op
	if (MANDJE.equals(event.getName())) {
		((AtomicInteger)
				event.getSession().getServletContext().getAttribute(AANTAL_MANDJES))
				.getAndDecrement(); // je verlaagt de teller in het servlet context attribuut aantalMandjes
	}
}
@Override
public void attributeReplaced(HttpSessionBindingEvent event) {
}
}
