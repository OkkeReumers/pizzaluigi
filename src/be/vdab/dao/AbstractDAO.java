package be.vdab.dao;

import javax.sql.DataSource;
abstract class AbstractDAO { 
	public final static String JNDI_NAME = "jdbc/pizzaluigi"; 
	protected DataSource dataSource; 
	public void setDataSource(DataSource dataSource) { 
		this.dataSource = dataSource;
	}
}

//(4) de class is abstract, het is niet de bedoeling van de class een instance te maken, 
//	de class is enkel bereikbaar in DAO classes
//(5) de JNDI naam hebben we in veel stukken code nodig, dus vermelden we hem hier 1 keer, 
//	dan kunnen we vervolgens verwijzen naar de constante JNDI_NAME
//(6) je maakt de DataSource protected , zo kunnen de derived DAO classes hem aanspreken
//(7) je zal deze setter oproepen vanuit de servlets