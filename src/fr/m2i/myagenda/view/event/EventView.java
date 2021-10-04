package fr.m2i.myagenda.view.event;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import fr.m2i.myagenda.dto.EventDto;
import fr.m2i.myagenda.service.EventService;

public class EventView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private EventDto eventCourant;

	private EventTableModel tableModel = new EventTableModel();

	private JTable table = new JTable(this.tableModel);

	private JScrollPane scroll = new JScrollPane(this.table);

	private EventForm form = new EventForm(this);

	public EventView() throws ClassNotFoundException, SQLException {

		this.tableModel.getListEvents().addAll(EventService.findAll());

		this.table.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				int row = table.getSelectedRow();
				EventDto event = (EventDto) tableModel.getListEvents().get(row);
				System.out.println("Ligne sélectionnée " + event);
				form.setEventCourant(event);
			}

		});

		JPanel content = new JPanel();

		JButton addEvent = new JButton("Ajouter");
		content.add(addEvent);
		addEvent.addActionListener(e -> {
			EventDto event = EventService.getInstance();
			this.setEventCourant(event);
		});

		content.add(this.scroll);

		content.add(this.form);

		this.add(content);
		this.setSize(1800, 1000);
		this.setVisible(false);
	}

	public EventDto getEventCourant() {
		return this.eventCourant;
	}

	public void setEventCourant(EventDto event) {
		this.eventCourant = event;
		if (this.eventCourant != null) {
			this.form.setEventCourant(event);
			this.setVisible(true);
		} else {
			this.form.setEventCourant(null);
			this.setVisible(false);
		}
	}

	public EventTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(EventTableModel tableModel) {
		this.tableModel = tableModel;
	}
	
	public void updateTable(EventDto event) {
		if (!this.getTableModel().getListEvents().contains(event)) {
			this.getTableModel().getListEvents().add(event);
		}
		this.getTableModel().fireTableDataChanged();
	}

}
