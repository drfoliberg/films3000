package ui.tests;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

import modeles.films.Film;


import films3000.Jozin;

public class Bazin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9140237165325774695L;
	private JPanel contentPane;
	private JPanel panelListeFilms;
	private JSplitPane splitPane;
	private JPanel panel_1;
	private JLabel lblNewLabel;


	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Bazin(Jozin jozin) throws SQLException {
		ModeleFilms modele = new ModeleFilms(jozin);
		FilmListener listener = new FilmListener() {
			
			@Override
			public void filmSelectionneChange(Film film) {
				afficherFilm(film);
			}
		};
		modele.addFilmListener(listener);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 892, 585);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{100, 0};
		gbl_contentPane.rowHeights = new int[]{333, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		splitPane = new JSplitPane();
		GridBagConstraints gbc_splitPane = new GridBagConstraints();
		gbc_splitPane.fill = GridBagConstraints.BOTH;
		gbc_splitPane.gridx = 0;
		gbc_splitPane.gridy = 0;
		contentPane.add(splitPane, gbc_splitPane);
		
		panelListeFilms = new ListeFilms(jozin, modele);
		splitPane.setLeftComponent(panelListeFilms);
		
		panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 70, 0, 0};
		gbl_panel_1.rowHeights = new int[]{15, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		lblNewLabel = new JLabel("New label");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel_1.add(lblNewLabel, gbc_lblNewLabel);
		splitPane.setDividerLocation(250);
		
	}

	public void afficherFilm(Film film) {
		System.out.println(film.getResume());
		
	}
	
}
