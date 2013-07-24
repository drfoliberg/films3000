package ui.infoRapide;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanInfoRapide extends JPanel {
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JLabel lblLgende;
	private JPanel panel_7;
	private JLabel lblnote;
	private JLabel lblAlternatif;
	private JPanel panel_8;
	private JLabel lblDtailsSurLe;

	/**
	 * 
	 * @param legende Légende
	 * @param note Note entre parenthèses
	 * @param alternatif Alternatif
	 * @param special champ d'information
	 */
	
	public PanInfoRapide(String legende, String note, String alternatif, String special) {
		setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{2.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 0;
		gbc_panel_1.gridy = 0;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 3.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 5, 0);
		gbc_panel_3.fill = GridBagConstraints.VERTICAL;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 0;
		panel_1.add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{0, 0, 0};
		gbl_panel_3.rowHeights = new int[]{0, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		panel_6 = new JPanel();
		GridBagConstraints gbc_panel_6 = new GridBagConstraints();
		gbc_panel_6.anchor = GridBagConstraints.WEST;
		gbc_panel_6.insets = new Insets(0, 0, 0, 5);
		gbc_panel_6.gridx = 0;
		gbc_panel_6.gridy = 0;
		panel_3.add(panel_6, gbc_panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{0, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_6.setLayout(gbl_panel_6);
		
		panel_5 = new JPanel();
		GridBagConstraints gbc_panel_5 = new GridBagConstraints();
		gbc_panel_5.anchor = GridBagConstraints.SOUTHWEST;
		gbc_panel_5.insets = new Insets(0, 0, 5, 0);
		gbc_panel_5.gridx = 0;
		gbc_panel_5.gridy = 0;
		panel_6.add(panel_5, gbc_panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[]{411, 0};
		gbl_panel_5.rowHeights = new int[]{38, 0};
		gbl_panel_5.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_5.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_5.setLayout(gbl_panel_5);
		
		lblLgende = new JLabel("Légende de la fenêtre ");
		lblLgende.setFont(new Font("Dialog", Font.BOLD, 32));
		GridBagConstraints gbc_lblLgende = new GridBagConstraints();
		gbc_lblLgende.anchor = GridBagConstraints.SOUTHWEST;
		gbc_lblLgende.gridx = 0;
		gbc_lblLgende.gridy = 0;
		panel_5.add(lblLgende, gbc_lblLgende);
		
		panel_4 = new JPanel();
		GridBagConstraints gbc_panel_4 = new GridBagConstraints();
		gbc_panel_4.anchor = GridBagConstraints.NORTH;
		gbc_panel_4.gridx = 0;
		gbc_panel_4.gridy = 1;
		panel_6.add(panel_4, gbc_panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[]{67, 0};
		gbl_panel_4.rowHeights = new int[]{15, 0};
		gbl_panel_4.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_4.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_4.setLayout(gbl_panel_4);
		
		lblAlternatif = new JLabel("Alternatif");
		GridBagConstraints gbc_lblAlternatif = new GridBagConstraints();
		gbc_lblAlternatif.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblAlternatif.gridx = 0;
		gbc_lblAlternatif.gridy = 0;
		panel_4.add(lblAlternatif, gbc_lblAlternatif);
		
		panel_7 = new JPanel();
		GridBagConstraints gbc_panel_7 = new GridBagConstraints();
		gbc_panel_7.anchor = GridBagConstraints.WEST;
		gbc_panel_7.gridx = 1;
		gbc_panel_7.gridy = 0;
		panel_3.add(panel_7, gbc_panel_7);
		GridBagLayout gbl_panel_7 = new GridBagLayout();
		gbl_panel_7.columnWidths = new int[]{94, 0};
		gbl_panel_7.rowHeights = new int[]{29, 0};
		gbl_panel_7.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_7.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_7.setLayout(gbl_panel_7);
		
		lblnote = new JLabel("(NOTE)");
		lblnote.setFont(new Font("Dialog", Font.BOLD, 24));
		GridBagConstraints gbc_lblnote = new GridBagConstraints();
		gbc_lblnote.fill = GridBagConstraints.VERTICAL;
		gbc_lblnote.gridx = 0;
		gbc_lblnote.gridy = 0;
		panel_7.add(lblnote, gbc_lblnote);
		
		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		panel_8 = new JPanel();
		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 0;
		panel_2.add(panel_8, gbc_panel_8);
		GridBagLayout gbl_panel_8 = new GridBagLayout();
		gbl_panel_8.columnWidths = new int[]{0, 0};
		gbl_panel_8.rowHeights = new int[]{0, 0};
		gbl_panel_8.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_8.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_8.setLayout(gbl_panel_8);
		
		lblDtailsSurLe = new JLabel("Détails sur le truc");
		lblDtailsSurLe.setFont(new Font("Dialog", Font.BOLD, 23));
		GridBagConstraints gbc_lblDtailsSurLe = new GridBagConstraints();
		gbc_lblDtailsSurLe.gridx = 0;
		gbc_lblDtailsSurLe.gridy = 0;
		panel_8.add(lblDtailsSurLe, gbc_lblDtailsSurLe);

	}

}
