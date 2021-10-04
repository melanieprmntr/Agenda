package fr.m2i.myagenda.view.contact;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.m2i.myagenda.composantui.combobox.genre.GenreCombobox;
import fr.m2i.myagenda.composantui.dialog.groupe.GroupeSelect;
import fr.m2i.myagenda.composantui.table.groupe.GroupeTable;
import fr.m2i.myagenda.dto.ContactDto;
import fr.m2i.myagenda.dto.GroupeDto;
import fr.m2i.myagenda.service.ContactService;

public class ContactForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContactView view;

	private ContactDto contactCourant;

	private JLabel titre = new JLabel("Vous éditez ... ");

	private JLabel labelGenre = new JLabel("genre", 10);
	private GenreCombobox genre = new GenreCombobox();

	private JLabel labelNom = new JLabel("nom", 10);
	private JTextField nom = new JTextField("nom", 40);

	private JLabel labelPrenom = new JLabel("prénom");
	private JTextField prenom = new JTextField("prenom", 20);

	private JLabel labelTelephone = new JLabel("téléphone");
	private JTextField telephone = new JTextField("téléphone", 10);

	private JLabel labelAdresse = new JLabel("adresse");
	private JTextField adresse = new JTextField("adresse", 80);

	private JLabel labelEmail = new JLabel("email");
	private JTextField email = new JTextField("email", 20);

	private GroupeTable groupeTable = new GroupeTable();

	public ContactForm(ContactView view) {
		this.view = view;

		this.setLayout(new GridLayout(0, 1));

		this.add(getHeader());

		this.add(getTitre());

		this.add(getContent());

		this.add(getGroupe());

		this.setVisible(false);
	}

	public JPanel getHeader() {
		FlowLayout layout = new FlowLayout();
		JPanel header = new JPanel(layout);
		header.setPreferredSize(new Dimension(200, 10));

		JButton saveBtn = new JButton("enregistrer");
		saveBtn.addActionListener(e -> save());
		header.add(saveBtn);

		JButton closeBtn = new JButton("fermer");
		closeBtn.addActionListener(e -> close());
		header.add(closeBtn);

		return header;
	}

	public JPanel getTitre() {
		FlowLayout layout = new FlowLayout();
		JPanel panel = new JPanel(layout);
		panel.setPreferredSize(new Dimension(200, 10));

		titre.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		panel.add(titre);

		return panel;
	}

	public JPanel getContent() {
		JPanel content = new JPanel(new GridLayout(0,1));
		content.setPreferredSize(new Dimension(200, 200));

		JPanel informations = new JPanel(new FlowLayout());
		informations.setPreferredSize(new Dimension(200, 100));
		
		informations.add(labelGenre);
		informations.add(genre);

		informations.add(labelNom);
		informations.add(nom);

		informations.add(labelPrenom);
		informations.add(prenom);

		JPanel coordonnees = new JPanel(new FlowLayout());
		coordonnees.setPreferredSize(new Dimension(200, 100));
		
		coordonnees.add(labelAdresse);
		coordonnees.add(adresse);
		
		coordonnees.add(labelTelephone);
		coordonnees.add(telephone);

		coordonnees.add(labelEmail);
		coordonnees.add(email);

		content.add(informations);
		content.add(coordonnees);
		
		return content;
	}

	public JPanel getGroupe() {
		JPanel groupe = new JPanel(new FlowLayout());
		groupe.setPreferredSize(new Dimension(200, 200));

		JPanel tools = new JPanel(new FlowLayout());
		tools.setPreferredSize(new Dimension(200, 10));

		JButton addGroupeBtn = new JButton("Ajouter groupe");
		tools.add(addGroupeBtn);
		addGroupeBtn.addActionListener(e -> addGroupe());

		this.add(groupeTable);
		this.add(tools);

		return groupe;
	}

	private void save() {
		writeBean();
		ContactService.save(this.contactCourant);
		this.view.updateTable(this.contactCourant);
	}

	private void close() {
		this.setContactCourant(null);
	}

	public ContactDto getContactCourant() {
		return contactCourant;
	}

	public void setContactCourant(ContactDto contactCourant) {
		this.contactCourant = contactCourant;
		if (this.contactCourant != null) {
			readBean();
			this.setVisible(true);
		} else {
			this.setVisible(false);
		}
	}

	public void readBean() {
		if (this.contactCourant.getId() != null) {
			ContactService.loadLazy(this.contactCourant);
		}

		this.titre.setText(this.contactCourant.getPrenom() + " " + this.contactCourant.getNom());

		this.genre.setSelectedItem(this.contactCourant.getGenre());
		this.nom.setText(this.contactCourant.getNom());
		this.prenom.setText(this.contactCourant.getPrenom());
		this.telephone.setText(this.contactCourant.getTelephone());
		this.adresse.setText(this.contactCourant.getAdresse());
		this.email.setText(this.contactCourant.getEmail());

		this.groupeTable.setContactCourant(this.contactCourant);
	}

	public void writeBean() {
		this.contactCourant.setGenre(this.genre.getSelectedItem());
		this.contactCourant.setNom(this.nom.getText());
		this.contactCourant.setPrenom(this.prenom.getText());
		this.contactCourant.setTelephone(this.telephone.getText());
		this.contactCourant.setAdresse(this.adresse.getText());
		this.contactCourant.setEmail(this.email.getText());
	}

	public void addGroupe() {
		GroupeSelect groupeSelect = new GroupeSelect();

		groupeSelect.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int row = groupeSelect.getSelectedRow();
				GroupeDto groupe = (GroupeDto) groupeSelect.getListGroupes().get(row);
				groupeSelect.setVisible(false);
				groupeTable.addGroupe(groupe);
				contactCourant.getListGroupes().add(groupe);
				groupeTable.getTableModel().fireTableDataChanged();
			}

		});

	}
}
