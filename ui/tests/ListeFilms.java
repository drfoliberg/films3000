package ui.tests;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import modeles.films.Film;


import films3000.Jozin;

public class ListeFilms extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2275613531198792708L;
	private JList<Film> list;
	private JPanel panel;
	private JScrollPane scrollPane_1;
	private ModeleFilms modele;

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public ListeFilms(Jozin jozin, final ModeleFilms modele) throws SQLException {
		this.modele = modele;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {150, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setAutoscrolls(true);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 0;
		add(scrollPane_1, gbc_scrollPane_1);
		
		panel = new JPanel();
		scrollPane_1.setViewportView(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		list = new JList<Film>();
		panel.add(list);
		list.setModel(new ModeleFilms(jozin));
		
		ListSelectionListener listSelectionListener = new ListSelectionListener() {
		      public void valueChanged(ListSelectionEvent listSelectionEvent) {
		        modele.fireFilmChange(list.getSelectedValue());
		      }
		    };
		    list.addListSelectionListener(listSelectionListener);

		    MouseListener mouseListener = new MouseAdapter() {
		      public void mouseClicked(MouseEvent mouseEvent) {
		        JList theList = (JList) mouseEvent.getSource();
		        if (mouseEvent.getClickCount() == 2) {
		          int index = theList.locationToIndex(mouseEvent.getPoint());
		          if (index >= 0) {
		            Object o = theList.getModel().getElementAt(index);
		            System.out.println("Double-clicked on: " + o.toString());
		          }
		        }
		      }
		    };
		    list.addMouseListener(mouseListener);
	}
	
	public int getTmdbSelected(){
		list.getSelectedIndex();

		return 0;
	}

}
