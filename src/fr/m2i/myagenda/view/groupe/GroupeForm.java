package fr.m2i.myagenda.view.groupe;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.m2i.myagenda.dto.GroupeDto;
import fr.m2i.myagenda.service.GroupeService;

public class GroupeForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GroupeView view;

	private GroupeDto groupeCourant;

	private JLabel labelNom = new JLabel("nom", 10);
	private JTextField nom = new JTextField("nom", 40);

	public GroupeForm(GroupeView view) {
		this.view = view;

		JPanel content = new JPanel(new GridLayout(0, 2));
		content.setPreferredSize(new Dimension(200, 200));

		this.add(content);

		JButton saveBtn = new JButton("enregistrer");
		saveBtn.addActionListener(e -> save());
		content.add(saveBtn);

		JButton deleteBtn = new JButton("supprimer");
		deleteBtn.addActionListener(e -> delete());
		content.add(deleteBtn);

		JButton closeBtn = new JButton("fermer");
		closeBtn.addActionListener(e -> close());
		content.add(closeBtn);

		content.add(labelNom);
		content.add(nom);

		this.setVisible(false);
	}

	private void save() {
		writeBean();
		GroupeService.save(this.groupeCourant);
		this.view.updateTable(this.groupeCourant);
	}

	public void delete() {
		int result = JOptionPane.showConfirmDialog(this,
				"Veuillez confirmer la suppression du groupe " + this.groupeCourant.getNom() + " ?",
				"Confirmation de suppression", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			GroupeService.delete(this.groupeCourant);
			this.view.removeGroup(this.groupeCourant);
			close();
		}
	}

	private void close() {
		this.setGroupeCourant(null);
	}

	public GroupeDto getGroupeCourant() {
		return groupeCourant;
	}

	public void setGroupeCourant(GroupeDto groupeCourant) {
		this.groupeCourant = groupeCourant;
		if (this.groupeCourant != null) {
			readBean();
			this.setVisible(true);
		} else {
			this.setVisible(false);
		}
	}

	public void readBean() {
		this.nom.setText(this.groupeCourant.getNom());
	}

	public void writeBean() {
		this.groupeCourant.setNom(this.nom.getText());
	}
}
