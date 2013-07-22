package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class ConfirmationFilm extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5002115418135717022L;
	private JPanel contentPane;
	private JLabel lblNomFic;
	private JLabel lblTitre;
	private JLabel lblAnnee;
	private JLabel lblIdTmdb;
	private JLabel texteImage;
	private JLabel lblimage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfirmationFilm frame = new ConfirmationFilm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ConfirmationFilm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 673, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{14, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		GridBagConstraints gbc_lblNomFic = new GridBagConstraints();
		gbc_lblNomFic.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomFic.anchor = GridBagConstraints.NORTH;
		gbc_lblNomFic.gridx = 0;
		gbc_lblNomFic.gridy = 0;
		contentPane.add(getLblNomFic(), gbc_lblNomFic);
		GridBagConstraints gbc_lblTitre = new GridBagConstraints();
		gbc_lblTitre.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitre.gridx = 0;
		gbc_lblTitre.gridy = 1;
		contentPane.add(getLblTitre(), gbc_lblTitre);
		GridBagConstraints gbc_lblAnnee = new GridBagConstraints();
		gbc_lblAnnee.insets = new Insets(0, 0, 5, 5);
		gbc_lblAnnee.gridx = 0;
		gbc_lblAnnee.gridy = 2;
		contentPane.add(getLblAnnee(), gbc_lblAnnee);
		GridBagConstraints gbc_lblIdTmdb = new GridBagConstraints();
		gbc_lblIdTmdb.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdTmdb.gridx = 0;
		gbc_lblIdTmdb.gridy = 3;
		contentPane.add(getLblIdTmdb(), gbc_lblIdTmdb);
		GridBagConstraints gbc_texteImage = new GridBagConstraints();
		gbc_texteImage.insets = new Insets(0, 0, 0, 5);
		gbc_texteImage.gridx = 0;
		gbc_texteImage.gridy = 4;
		contentPane.add(getTexteImage(), gbc_texteImage);
		GridBagConstraints gbc_lblimage = new GridBagConstraints();
		gbc_lblimage.gridx = 1;
		gbc_lblimage.gridy = 4;
		contentPane.add(getLblimage(), gbc_lblimage);
	}

	public JLabel getLblNomFic() {
		if (lblNomFic == null) {
			lblNomFic = new JLabel("Nom du fichier :");
		}
		return lblNomFic;
	}
	public JLabel getLblTitre() {
		if (lblTitre == null) {
			lblTitre = new JLabel("titre :");
		}
		return lblTitre;
	}
	public JLabel getLblAnnee() {
		if (lblAnnee == null) {
			lblAnnee = new JLabel("année:");
		}
		return lblAnnee;
	}
	public JLabel getLblIdTmdb() {
		if (lblIdTmdb == null) {
			lblIdTmdb = new JLabel("id tmdb:");
		}
		return lblIdTmdb;
	}
	public JLabel getTexteImage() {
		if (texteImage == null) {
			texteImage = new JLabel("Image");
		}
		return texteImage;
	}
	public JLabel getLblimage() {
		if (lblimage == null) {
			lblimage = new JLabel("New label");
		}
		return lblimage;
	}
}
