package fr.m2i.myagenda.singleton;

import fr.m2i.myagenda.hibernate.HibernateConfiguration;

public class MyAgendaSingleton {

	private static MyAgendaSingleton myAgendaSingleton;

	private HibernateConfiguration hibernateConfiguration;

	private static Long sequence = 0L;

	public static MyAgendaSingleton getInstance() {
		if (myAgendaSingleton == null) {
			myAgendaSingleton = new MyAgendaSingleton();
		}
		return myAgendaSingleton;
	}

	public static Long nextSequence() {
		return sequence++;
	}

	public HibernateConfiguration getHibernateConfiguration() {
		return hibernateConfiguration;
	}

	public void setHibernateConfiguration(HibernateConfiguration hibernateConfiguration) {
		this.hibernateConfiguration = hibernateConfiguration;
	}

}
