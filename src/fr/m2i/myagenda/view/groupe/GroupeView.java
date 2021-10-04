package fr.m2i.myagenda.view.groupe;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.m2i.myagenda.dto.GroupeDto;
import fr.m2i.myagenda.service.GroupeService;

public class GroupeView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GroupeDto groupeCourant;

	private GroupeTableModel tableModel = new GroupeTableModel();

	private JTable table = new JTable(this.tableModel);

	private JScrollPane scroll = new JScrollPane(this.table);

	private GroupeForm form = new GroupeForm(this);

	public GroupeView() throws ClassNotFoundException, SQLException {

		this.tableModel.getListGroupes().addAll(GroupeService.findAll());

		this.table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.getSelectedRow();
				GroupeDto groupe = (GroupeDto) tableModel.getListGroupes().get(row);
				System.out.println("Ligne sélectionnée " + groupe);
				form.setGroupeCourant(groupe);
			}

		});

		JPanel content = new JPanel();

		JButton addGroupe = new JButton("Ajouter");
		content.add(addGroupe);
		addGroupe.addActionListener(e -> {
			GroupeDto groupe = GroupeService.getInstance();
			this.setGroupeCourant(groupe);
		});

		content.add(this.scroll);

		content.add(this.form);

		this.add(content);
		this.setSize(1800, 1000);
		this.setVisible(false);
	}

	public GroupeDto getGroupeCourant() {
		return this.groupeCourant;
	}

	public void setGroupeCourant(GroupeDto groupe) {
		this.groupeCourant = groupe;
		if (this.groupeCourant != null) {
			this.form.setGroupeCourant(groupe);
			this.setVisible(true);
		} else {
			this.form.setGroupeCourant(null);
			this.setVisible(false);
		}
	}

	public GroupeTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(GroupeTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public void updateTable(GroupeDto groupe) {
		if (!this.getTableModel().getListGroupes().contains(groupe)) {
			this.getTableModel().getListGroupes().add(groupe);
		}
		this.getTableModel().fireTableDataChanged();
	}

	public void removeGroup(GroupeDto groupe) {
		if (this.getTableModel().getListGroupes().contains(groupe)) {
			this.getTableModel().getListGroupes().remove(groupe);
		}
		this.getTableModel().fireTableDataChanged();
	}

}
