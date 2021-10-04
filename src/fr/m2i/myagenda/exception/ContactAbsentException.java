package fr.m2i.myagenda.exception;

public class ContactAbsentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContactAbsentException() {
		super("Impossible d'enregistrer un événement sans contact");
	}

}
