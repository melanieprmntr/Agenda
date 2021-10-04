package fr.m2i.myagenda.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.m2i.myagenda.dto.MyAgendaObject;

public class SQLFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3080253720865184120L;

	public SQLFrame(String titre, List<?> listMyAgendaObject) {

		JPanel panel = (JPanel) this.getContentPane();
		panel.setLayout(new BorderLayout());

		JLabel jlTitre = new JLabel(titre);
		jlTitre.setPreferredSize(new Dimension(200, 40));

		StringBuilder sql = new StringBuilder();
		if (listMyAgendaObject != null) {
			for (Object o : listMyAgendaObject) {
				MyAgendaObject myAgendaObject = (MyAgendaObject) o;
				sql.append(myAgendaObject.getInformation()).append("\n");
			}
		} else {
			sql.append("Rien à afficher");
		}

		JTextArea jtSql = new JTextArea(sql.toString());
		JScrollPane jsSql = new JScrollPane(jtSql);

		panel.add(jlTitre, BorderLayout.NORTH);
		panel.add(jsSql, BorderLayout.CENTER);

		this.setSize(500, 500);
		this.setVisible(true);
	}
}
