package ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import films3000.Jozin;
import films3000.TraitementEnCours;

public class Config extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblFilms;
	private JLabel lblVersion;
	private JLabel lblditionstarshipEnterprise;
	private JPanel panel_1;
	private JLabel lblRacineDesFilms;
	private JLabel lblRacineDesImages;
	private Jozin jozin;
	private JTextField textFieldRacineFilms;
	private JTextField textFieldRacineImages;
	private JCheckBox chckbxCache;
	private JLabel lblCacherLesImages;
	private JLabel lblLangueCherche;
	private JComboBox comboBox;
	private JButton btnCommencerLanalyse;
	private JLabel lblDemanderConfirmation;
	private JComboBox comboBox_1;
	private JButton btnConsulterLesFilms;

	/**
	 * Create the frame.
	 */
	public Config(Jozin jozin) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPanel(), BorderLayout.NORTH);
		contentPane.add(getPanel_1(), BorderLayout.CENTER);
		this.jozin = jozin;
	}
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 50, 54, 0};
			gbl_panel.rowHeights = new int[]{0, 14, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			GridBagConstraints gbc_lblFilms = new GridBagConstraints();
			gbc_lblFilms.anchor = GridBagConstraints.NORTH;
			gbc_lblFilms.insets = new Insets(0, 0, 5, 5);
			gbc_lblFilms.gridx = 1;
			gbc_lblFilms.gridy = 0;
			panel.add(getLblFilms(), gbc_lblFilms);
			GridBagConstraints gbc_lblVersion = new GridBagConstraints();
			gbc_lblVersion.insets = new Insets(0, 0, 5, 5);
			gbc_lblVersion.anchor = GridBagConstraints.NORTH;
			gbc_lblVersion.gridx = 1;
			gbc_lblVersion.gridy = 1;
			panel.add(getLblVersion(), gbc_lblVersion);
			GridBagConstraints gbc_lblditionstarshipEnterprise = new GridBagConstraints();
			gbc_lblditionstarshipEnterprise.insets = new Insets(0, 0, 0, 5);
			gbc_lblditionstarshipEnterprise.gridx = 1;
			gbc_lblditionstarshipEnterprise.gridy = 2;
			panel.add(getLblditionstarshipEnterprise(), gbc_lblditionstarshipEnterprise);
		}
		return panel;
	}
	public JLabel getLblFilms() {
		if (lblFilms == null) {
			lblFilms = new JLabel("Films 3000");
		}
		return lblFilms;
	}
	public JLabel getLblVersion() {
		if (lblVersion == null) {
			lblVersion = new JLabel("version 0.0.1");
		}
		return lblVersion;
	}
	public JLabel getLblditionstarshipEnterprise() {
		if (lblditionstarshipEnterprise == null) {
			lblditionstarshipEnterprise = new JLabel("Édition «Starship Enterprise»");
		}
		return lblditionstarshipEnterprise;
	}
	public JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			GridBagLayout gbl_panel_1 = new GridBagLayout();
			gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0, 0};
			gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel_1.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
			panel_1.setLayout(gbl_panel_1);
			GridBagConstraints gbc_lblRacineDesFilms = new GridBagConstraints();
			gbc_lblRacineDesFilms.anchor = GridBagConstraints.EAST;
			gbc_lblRacineDesFilms.insets = new Insets(0, 0, 5, 5);
			gbc_lblRacineDesFilms.gridx = 1;
			gbc_lblRacineDesFilms.gridy = 1;
			panel_1.add(getLblRacineDesFilms(), gbc_lblRacineDesFilms);
			GridBagConstraints gbc_textFieldRacineFilms = new GridBagConstraints();
			gbc_textFieldRacineFilms.anchor = GridBagConstraints.WEST;
			gbc_textFieldRacineFilms.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldRacineFilms.gridx = 2;
			gbc_textFieldRacineFilms.gridy = 1;
			panel_1.add(getTextFieldRacineFilms(), gbc_textFieldRacineFilms);
			GridBagConstraints gbc_lblRacineDesImages = new GridBagConstraints();
			gbc_lblRacineDesImages.anchor = GridBagConstraints.EAST;
			gbc_lblRacineDesImages.insets = new Insets(0, 0, 5, 5);
			gbc_lblRacineDesImages.gridx = 1;
			gbc_lblRacineDesImages.gridy = 2;
			panel_1.add(getLblRacineDesImages(), gbc_lblRacineDesImages);
			GridBagConstraints gbc_textFieldRacineImages = new GridBagConstraints();
			gbc_textFieldRacineImages.anchor = GridBagConstraints.WEST;
			gbc_textFieldRacineImages.insets = new Insets(0, 0, 5, 5);
			gbc_textFieldRacineImages.gridx = 2;
			gbc_textFieldRacineImages.gridy = 2;
			panel_1.add(getTextFieldRacineImages(), gbc_textFieldRacineImages);
			GridBagConstraints gbc_lblCacherLesImages = new GridBagConstraints();
			gbc_lblCacherLesImages.insets = new Insets(0, 0, 5, 5);
			gbc_lblCacherLesImages.gridx = 1;
			gbc_lblCacherLesImages.gridy = 3;
			panel_1.add(getLblCacherLesImages(), gbc_lblCacherLesImages);
			GridBagConstraints gbc_chckbxCache = new GridBagConstraints();
			gbc_chckbxCache.anchor = GridBagConstraints.WEST;
			gbc_chckbxCache.insets = new Insets(0, 0, 5, 5);
			gbc_chckbxCache.gridx = 2;
			gbc_chckbxCache.gridy = 3;
			panel_1.add(getChckbxCache(), gbc_chckbxCache);
			GridBagConstraints gbc_lblLangueCherche = new GridBagConstraints();
			gbc_lblLangueCherche.anchor = GridBagConstraints.EAST;
			gbc_lblLangueCherche.insets = new Insets(0, 0, 5, 5);
			gbc_lblLangueCherche.gridx = 1;
			gbc_lblLangueCherche.gridy = 4;
			panel_1.add(getLblLangueCherche(), gbc_lblLangueCherche);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.anchor = GridBagConstraints.WEST;
			gbc_comboBox.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 4;
			panel_1.add(getComboBox(), gbc_comboBox);
			GridBagConstraints gbc_lblDemanderConfirmation = new GridBagConstraints();
			gbc_lblDemanderConfirmation.anchor = GridBagConstraints.EAST;
			gbc_lblDemanderConfirmation.insets = new Insets(0, 0, 5, 5);
			gbc_lblDemanderConfirmation.gridx = 1;
			gbc_lblDemanderConfirmation.gridy = 5;
			panel_1.add(getLblDemanderConfirmation(), gbc_lblDemanderConfirmation);
			GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
			gbc_comboBox_1.anchor = GridBagConstraints.WEST;
			gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
			gbc_comboBox_1.gridx = 2;
			gbc_comboBox_1.gridy = 5;
			panel_1.add(getComboBox_1(), gbc_comboBox_1);
			GridBagConstraints gbc_btnCommencerLanalyse = new GridBagConstraints();
			gbc_btnCommencerLanalyse.anchor = GridBagConstraints.WEST;
			gbc_btnCommencerLanalyse.insets = new Insets(0, 0, 5, 5);
			gbc_btnCommencerLanalyse.gridx = 2;
			gbc_btnCommencerLanalyse.gridy = 6;
			panel_1.add(getBtnCommencerLanalyse(), gbc_btnCommencerLanalyse);
			GridBagConstraints gbc_btnConsulterLesFilms = new GridBagConstraints();
			gbc_btnConsulterLesFilms.insets = new Insets(0, 0, 0, 5);
			gbc_btnConsulterLesFilms.gridx = 2;
			gbc_btnConsulterLesFilms.gridy = 8;
			panel_1.add(getBtnConsulterLesFilms(), gbc_btnConsulterLesFilms);
		}
		return panel_1;
	}
	public JLabel getLblRacineDesFilms() {
		if (lblRacineDesFilms == null) {
			lblRacineDesFilms = new JLabel("Racine des films");
		}
		return lblRacineDesFilms;
	}
	public JLabel getLblRacineDesImages() {
		if (lblRacineDesImages == null) {
			lblRacineDesImages = new JLabel("Racine des images");
		}
		return lblRacineDesImages;
	}
	public JTextField getTextFieldRacineFilms() {
		if (textFieldRacineFilms == null) {
			textFieldRacineFilms = new JTextField();
			textFieldRacineFilms.setColumns(10);
		}
		return textFieldRacineFilms;
	}
	public JTextField getTextFieldRacineImages() {
		if (textFieldRacineImages == null) {
			textFieldRacineImages = new JTextField();
			textFieldRacineImages.setEnabled(false);
			textFieldRacineImages.setColumns(10);
		}
		return textFieldRacineImages;
	}
	public JCheckBox getChckbxCache() {
		if (chckbxCache == null) {
			chckbxCache = new JCheckBox("");
			chckbxCache.setEnabled(false);
		}
		return chckbxCache;
	}
	public JLabel getLblCacherLesImages() {
		if (lblCacherLesImages == null) {
			lblCacherLesImages = new JLabel("Mise en cache images");
		}
		return lblCacherLesImages;
	}
	public JLabel getLblLangueCherche() {
		if (lblLangueCherche == null) {
			lblLangueCherche = new JLabel("Langue recherche");
		}
		return lblLangueCherche;
	}
	public JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"fr", "en"}));
		}
		return comboBox;
	}
	public JButton getBtnCommencerLanalyse() {
		if (btnCommencerLanalyse == null) {
			btnCommencerLanalyse = new JButton("Commencer l'analyse !");
			btnCommencerLanalyse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			btnCommencerLanalyse.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					//AnalyseFichiers analyse = new AnalyseFichiers(getTextFieldRacineFilms().getText(), jozin);
					//analyse.run();
					javax.swing.SwingUtilities.invokeLater(new Runnable() {
			            public void run() {
			            	TraitementEnCours traitement = new TraitementEnCours(getTextFieldRacineFilms().getText(),jozin);
			            	traitement.setVisible(true);
			            	traitement.commencerTraitement();
			            }
			        });
				}
			});
		}
		return btnCommencerLanalyse;
	}
	public JLabel getLblDemanderConfirmation() {
		if (lblDemanderConfirmation == null) {
			lblDemanderConfirmation = new JLabel("Demander confirmation");
		}
		return lblDemanderConfirmation;
	}
	public JComboBox getComboBox_1() {
		if (comboBox_1 == null) {
			comboBox_1 = new JComboBox();
			comboBox_1.setEnabled(false);
			comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Toujours", "Problème avec le fichier", "Jamais"}));
		}
		return comboBox_1;
	}
	public JButton getBtnConsulterLesFilms() {
		if (btnConsulterLesFilms == null) {
			btnConsulterLesFilms = new JButton("Consulter les films");
			btnConsulterLesFilms.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
		}
		return btnConsulterLesFilms;
	}
}
