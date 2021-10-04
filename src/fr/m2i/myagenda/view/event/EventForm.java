package fr.m2i.myagenda.view.event;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.m2i.myagenda.composantui.combobox.contact.ContactCombobox;
import fr.m2i.myagenda.composantui.datepicker.DatePicker;
import fr.m2i.myagenda.dto.EventDto;
import fr.m2i.myagenda.exception.ContactAbsentException;
import fr.m2i.myagenda.service.EventService;

public class EventForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EventView view;

	private EventDto eventCourant;

	private JLabel labelNom = new JLabel("nom", 10);
	private JTextField nom = new JTextField("nom", 40);

	private JLabel labelContact = new JLabel("contact");
	private ContactCombobox contact = new ContactCombobox();

	private JLabel labelDateHeure = new JLabel("date heure");

	private DatePicker dateHeure = new DatePicker();

	// private JDatePanelImpl dateHeure = new JDatePanelImpl("date heure", 10);

	private JLabel labelAdresse = new JLabel("adresse");
	private JTextField adresse = new JTextField("adresse", 80);

	public EventForm(EventView view) {
		this.view = view;

		JPanel content = new JPanel(new GridLayout(0, 2));
		content.setPreferredSize(new Dimension(200, 200));

		this.add(content);

		JButton saveBtn = new JButton("enregistrer");
		saveBtn.addActionListener(e -> save());
		content.add(saveBtn);

		JButton closeBtn = new JButton("fermer");
		closeBtn.addActionListener(e -> close());
		content.add(closeBtn);

		content.add(labelNom);
		content.add(nom);

		content.add(labelContact);
		content.add(contact);

		content.add(labelDateHeure);
		content.add(dateHeure);

		content.add(labelAdresse);
		content.add(adresse);

		this.setVisible(false);
	}

	private void save() {
		writeBean();
		try {
			EventService.save(this.eventCourant);
			this.view.updateTable(this.eventCourant);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} catch (ContactAbsentException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(this,
					e.getMessage() + ". Pensez à sélectionner un contact dans le sélecteur de contact",
					"Enregistrement impossible", JOptionPane.WARNING_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Enregistrement impossible", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void close() {
		this.setEventCourant(null);
	}

	public EventDto getEventCourant() {
		return eventCourant;
	}

	public void setEventCourant(EventDto eventCourant) {
		this.eventCourant = eventCourant;
		if (this.eventCourant != null) {
			readBean();
			this.setVisible(true);
		} else {
			this.setVisible(false);
		}
	}

	public void readBean() {
		this.nom.setText(this.eventCourant.getNom());

		this.contact.updateComboBox();
		this.contact.setSelectedItem(this.eventCourant.getContact());
		
		this.dateHeure.setDate(this.eventCourant.getDate());
		this.adresse.setText(this.eventCourant.getAdresse());
	}

	public void writeBean() {
		this.eventCourant.setNom(this.nom.getText());
		this.eventCourant.setContact(contact.getSelectedItem());
		this.eventCourant.setDate(this.dateHeure.getDate());
		this.eventCourant.setAdresse(this.adresse.getText());
	}
}
