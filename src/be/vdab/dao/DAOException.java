package be.vdab.dao;

public class DAOException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public DAOException(Throwable cause) {					 // een constructor die een andere exception als parameter binnenkrijgt
		super(cause); 										 // exception doorgeven aan de constructor van ej base class
	}
}