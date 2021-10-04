package fr.m2i.myagenda.composantui.dialog.groupe;

import java.awt.Label;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JDialog;

import fr.m2i.myagenda.composantui.table.groupe.GroupeTable;
import fr.m2i.myagenda.dto.GroupeDto;
import fr.m2i.myagenda.service.GroupeService;

public class GroupeSelect extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GroupeTable groupeTable = new GroupeTable();

	public GroupeSelect() {

		Label label = new Label("Veuillez sélectionner un groupe");

		this.add(label);
		this.add(groupeTable);

		this.setVisible(true);
		this.setSize(400, 400);

		groupeTable.setListGroupes(GroupeService.findAll());

	}

	public void addMouseListener(MouseListener mouseListener) {
		this.groupeTable.getTable().addMouseListener(mouseListener);
	}

	public List<GroupeDto> getListGroupes() {
		return this.groupeTable.getTableModel().getListGroupes();
	}

	public int getSelectedRow() {
		return this.groupeTable.getTable().getSelectedRow();
	}
}
