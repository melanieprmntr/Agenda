package fr.m2i.myagenda.view.groupe;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.m2i.myagenda.dto.GroupeDto;

public class GroupeTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7716157448045176990L;

	private List<GroupeDto> listGroupes = new ArrayList<GroupeDto>();

	private String[] colonnes = { "nom" };

	public List<GroupeDto> getListGroupes() {
		return listGroupes;
	}

	public void setListGroupes(List<GroupeDto> listGroupes) {
		this.listGroupes = listGroupes;
	}

	public String[] getColonnes() {
		return colonnes;
	}

	public void setColonnes(String[] colonnes) {
		this.colonnes = colonnes;
	}

	public String getColumnName(int column) {
		return this.colonnes[column];
	}

	@Override
	public int getRowCount() {
		return this.listGroupes.size();
	}

	@Override
	public int getColumnCount() {
		return this.colonnes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		GroupeDto groupe = (GroupeDto) this.listGroupes.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return groupe.getNom();

		}
		throw new NullPointerException("impossible de déterminer la colonne à afficher pour " + columnIndex);
	}

}
