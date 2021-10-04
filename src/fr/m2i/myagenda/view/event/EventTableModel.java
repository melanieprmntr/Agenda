package fr.m2i.myagenda.view.event;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.m2i.myagenda.dto.EventDto;
import fr.m2i.myagenda.dto.MyAgendaObject;

public class EventTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7716157448045176990L;

	private List<MyAgendaObject> listEvents = new ArrayList<MyAgendaObject>();

	private String[] colonnes = { "nom" };

	public List<MyAgendaObject> getListEvents() {
		return listEvents;
	}

	public void setListEvents(List<MyAgendaObject> listEvents) {
		this.listEvents = listEvents;
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
		return this.listEvents.size();
	}

	@Override
	public int getColumnCount() {
		return this.colonnes.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		EventDto event = (EventDto) this.listEvents.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return event.getNom();
		
		}
		throw new NullPointerException("impossible de déterminer la colonne à afficher pour " + columnIndex);
	}

}
