package fr.m2i.myagenda.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JPanel;

import fr.m2i.myagenda.dto.ContactDto;
import fr.m2i.myagenda.dto.EventDto;
import fr.m2i.myagenda.dto.GroupeDto;
import fr.m2i.myagenda.service.ContactService;
import fr.m2i.myagenda.service.EventService;
import fr.m2i.myagenda.service.GroupeService;
import fr.m2i.myagenda.view.contact.ContactView;
import fr.m2i.myagenda.view.event.EventView;
import fr.m2i.myagenda.view.groupe.GroupeView;

public class MainMenu extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6060348925021230980L;

	private ContactView contactView;

	private EventView eventView;

	private GroupeView groupeView;

	public MainMenu() throws ClassNotFoundException, SQLException {

		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		this.setPreferredSize(new Dimension(500, 500));

		contactView = new ContactView();

		eventView = new EventView();

		groupeView = new GroupeView();

		JPanel content = new JPanel();
		content.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 50));
		content.add(createPanelContact());
		content.add(createPanelEvent());
		content.add(createPanelGroupe());
		this.add(content);
		this.setVisible(true);
	}

	public JPanel createPanelContact() {
		ModulePanel panel = new ModulePanel("Gestion des contacts", "Gestion", true);

		panel.getAddBtn().addActionListener(e -> {
			ContactDto contact = ContactService.getInstance();
			this.contactView.setContactCourant(contact);
		});

		panel.getGestionBtn().addActionListener(e -> this.contactView.setVisible(true));

		panel.getSqlBtn().addActionListener(e -> {
			new SQLFrame("Liste des contacts", ContactService.findAll());
		});

		return panel;
	}

	public JPanel createPanelEvent() {
		ModulePanel panel = new ModulePanel("Gestion des événements", "Gestion", true);

		panel.getAddBtn().addActionListener(e -> {
			EventDto event = EventService.getInstance();
			this.eventView.setEventCourant(event);
		});

		panel.getGestionBtn().addActionListener(e -> this.eventView.setVisible(true));

		panel.getSqlBtn().addActionListener(e -> {
			try {
				new SQLFrame("Liste des événements", EventService.findAll());
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		});

		return panel;

	}

	public JPanel createPanelGroupe() {
		ModulePanel panel = new ModulePanel("Gestion des groupes", "Gestion", true);

		panel.getAddBtn().addActionListener(e -> {
			GroupeDto groupe = GroupeService.getInstance();
			this.groupeView.setGroupeCourant(groupe);
		});

		panel.getGestionBtn().addActionListener(e -> this.groupeView.setVisible(true));

		panel.getSqlBtn().addActionListener(e -> {
			new SQLFrame("Liste des groupes", GroupeService.findAll());
		});

		return panel;

	}
}
