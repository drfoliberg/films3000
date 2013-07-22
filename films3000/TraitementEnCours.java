package films3000;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import thread.AnalyseFichiers;



public class TraitementEnCours extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 781621681860700659L;
	private JPanel contentPane;
	private JLabel lblTraitementEnCours;
	private JLabel lblRacineCourante;
	private JLabel lblAvancement;
	private JLabel lblFichierEnCours;
	private JProgressBar progressBar;
	private JButton btnArrterLanalyse;
	String racine;
	Jozin jozin;
	boolean annule;
	/**
	 * Create the frame.
	 */
	public TraitementEnCours(String racine, Jozin jozin) {
		this.racine = racine;
		this.jozin = jozin;
		this.annule = false;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 468, 329);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{424, 0};
		gbl_contentPane.rowHeights = new int[]{19, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 2.0, 0.0, 0.0, 0.0, 1.0, 0.0, 2.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		GridBagConstraints gbc_lblTraitementEnCours = new GridBagConstraints();
		gbc_lblTraitementEnCours.insets = new Insets(0, 0, 5, 0);
		gbc_lblTraitementEnCours.anchor = GridBagConstraints.NORTH;
		gbc_lblTraitementEnCours.gridx = 0;
		gbc_lblTraitementEnCours.gridy = 0;
		contentPane.add(getLblTraitementEnCours(), gbc_lblTraitementEnCours);
		GridBagConstraints gbc_lblRacineCourante = new GridBagConstraints();
		gbc_lblRacineCourante.insets = new Insets(0, 0, 5, 0);
		gbc_lblRacineCourante.gridx = 0;
		gbc_lblRacineCourante.gridy = 1;
		contentPane.add(getLblRacineCourante(), gbc_lblRacineCourante);
		GridBagConstraints gbc_lblAvancement = new GridBagConstraints();
		gbc_lblAvancement.insets = new Insets(0, 0, 5, 0);
		gbc_lblAvancement.gridx = 0;
		gbc_lblAvancement.gridy = 3;
		contentPane.add(getLblAvancement(), gbc_lblAvancement);
		GridBagConstraints gbc_lblFichierEnCours = new GridBagConstraints();
		gbc_lblFichierEnCours.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblFichierEnCours.insets = new Insets(0, 0, 5, 0);
		gbc_lblFichierEnCours.gridx = 0;
		gbc_lblFichierEnCours.gridy = 4;
		contentPane.add(getLblFichierEnCours(), gbc_lblFichierEnCours);
		GridBagConstraints gbc_progressBar = new GridBagConstraints();
		gbc_progressBar.insets = new Insets(0, 0, 5, 0);
		gbc_progressBar.gridx = 0;
		gbc_progressBar.gridy = 5;
		contentPane.add(getProgressBar(), gbc_progressBar);
		GridBagConstraints gbc_btnArrterLanalyse = new GridBagConstraints();
		gbc_btnArrterLanalyse.insets = new Insets(0, 0, 5, 0);
		gbc_btnArrterLanalyse.gridx = 0;
		gbc_btnArrterLanalyse.gridy = 7;
		contentPane.add(getBtnArrterLanalyse(), gbc_btnArrterLanalyse);
		progressBar.setIndeterminate(true);
	}

	public JLabel getLblTraitementEnCours() {
		if (lblTraitementEnCours == null) {
			lblTraitementEnCours = new JLabel("Traitement en cours");
			lblTraitementEnCours.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return lblTraitementEnCours;
	}
	public JLabel getLblRacineCourante() {
		if (lblRacineCourante == null) {
			lblRacineCourante = new JLabel("Analyse de la racine");
		}
		return lblRacineCourante;
	}
	public JLabel getLblAvancement() {
		if (lblAvancement == null) {
			lblAvancement = new JLabel("Découverte des fichiers en cours...");
			lblAvancement.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblAvancement;
	}
	public JLabel getLblFichierEnCours() {
		if (lblFichierEnCours == null) {
			lblFichierEnCours = new JLabel("Découverte...");
			lblFichierEnCours.setMaximumSize(new Dimension(500, 14));
		}
		return lblFichierEnCours;
	}
	
	public void setAvancement(String texte){
		this.lblAvancement.setText(texte);
		this.update(getGraphics());
	}
	
	public void setFic(String fichier){
		this.lblFichierEnCours.setText(fichier);
		this.update(getGraphics());
	}
	public JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar();
			progressBar.setPreferredSize(new Dimension(250, 20));
		}
		return progressBar;
	}
	public JButton getBtnArrterLanalyse() {
		if (btnArrterLanalyse == null) {
			btnArrterLanalyse = new JButton("Arrêter l'analyse");
			btnArrterLanalyse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					TraitementEnCours.this.annule = true;
					getLblAvancement().setText("Analyse arrêtée!");
				}
			});
		}
		return btnArrterLanalyse;
	}
	
	public void commencerTraitement() {

		Thread t = new Thread() {
			public void run() {
				// Instanciation et lancement du traitement
				AnalyseFichiers analyse = new AnalyseFichiers(racine, jozin,TraitementEnCours.this);
				analyse.run();
			}
		};
		t.start();
	}
	
	public boolean estAnnule(){
		return this.annule;
	}
}
