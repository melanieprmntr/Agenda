package fr.m2i.myagenda.composantui.datepicker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

import javax.swing.JPanel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class DatePicker extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	UtilDateModel model = new UtilDateModel();
	Properties p = new Properties();

	JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
	JDatePickerImpl dateHeure = new JDatePickerImpl(datePanel, new DateLabelFormatter());

	public DatePicker() {

		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		datePanel = new JDatePanelImpl(model, p);
		dateHeure = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		this.add(dateHeure);

	}

	public LocalDateTime getDate() {
		java.util.Date date = (Date) dateHeure.getModel().getValue();
		return convertToLocalDateViaInstant(date);
	}

	public void setDate(LocalDateTime date) {
		dateHeure.getModel().setDate(date.getYear(), date.getMonth().getValue()-1, date.getDayOfMonth());
		dateHeure.getModel().setSelected(true);
	}

	public LocalDateTime convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public Date convertToDateViaInstant(LocalDateTime dateToConvert) {
		LocalDate localDate = dateToConvert.toLocalDate();
		return java.util.Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

}
