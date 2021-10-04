package fr.m2i.myagenda.hibernate;

import java.io.File;
import java.io.FileNotFoundException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import fr.m2i.myagenda.dto.ContactDto;
import fr.m2i.myagenda.dto.EventDto;
import fr.m2i.myagenda.dto.GroupeDto;

public class HibernateConfiguration {

	private static final String FILE_NAME_HIBERNATE_CONFIGURATION = "myagenda.cfg.xml";

	private SessionFactory factory;

	// Création d'une instance 'file' permettant d'atteindre le fichier de
	// configuration sur le disque dur.
	// Ce fichier XML comporte la configuration de connexion à la base de données,
	// le driver définissant la nature de la base de données employé et son dialect.
	// Peut également comporter le flag d'affichage et de production dans la console
	// des requêtes SQL
	// Peut permettre la configuration du PoolConnect de gestion des connexions à la
	// base de données ainsi que la gestion de la mémoire allouée à Hibernate
	File file = new File(FILE_NAME_HIBERNATE_CONFIGURATION);

	// Obtention de l'instance de configuration d'hibernate, en lui fournissant le
	// fichier XML contenant la configuration
	Configuration configuration;

	public HibernateConfiguration() throws FileNotFoundException {

		if (!file.exists()) {
			throw new FileNotFoundException("Le fichier de configuration nécessaire à hibernate, '"
					+ FILE_NAME_HIBERNATE_CONFIGURATION + "' n'existe pas !");
		}

		buildConfiguration(file);

		// Instancation d'un builder : on va notamment produire le gestionnaire d'entity
		// nécessaire pour gérer l'ensemble des DTO devant être persisté en database
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();

		// Instanciation du service registry : c'est la classe qui administre l'ensemble
		// des événements nécessaires aux sessions transactionnelles avec la base de
		// données
		ServiceRegistry serviceRegistry = builder.applySettings(configuration.getProperties()).build();

		this.factory = configuration.buildSessionFactory(serviceRegistry);
	}

	public void buildConfiguration(File fileHibernateConfiguration) {
		this.configuration = new Configuration().configure(fileHibernateConfiguration);

		this.configuration.addAnnotatedClass(GroupeDto.class);
		this.configuration.addAnnotatedClass(ContactDto.class);
		this.configuration.addAnnotatedClass(EventDto.class);

	}

	public SessionFactory getFactory() {
		return factory;
	}
}
