package ui;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.PrintStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

 
public class Log extends JFrame {


	private static final long serialVersionUID = 4586896957207204231L;

	public JTextArea textArea;
     
    //private PrintStream standardOut;
    private JPanel panel;
    private JCheckBox chckbxActiv;
    private JComboBox comboBox;
    private JLabel lblNiveauInformartion;
    private OutputStreamIntercepte sortieConsole;
    public static int niveauRequis =  0;
    
    public Log() {
        super("Log Films3000");
        textArea = new JTextArea(30, 10);
        textArea.setEditable(false);
        sortieConsole = new OutputStreamIntercepte(textArea);
        PrintStream printStream = new PrintStream(sortieConsole);
        //standardOut = System.out;

        System.setOut(printStream);
        System.setErr(printStream);
 
        // creates the GUI
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.rowHeights = new int[]{0, 0};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0};
        gridBagLayout.columnWeights = new double[]{0.0, 1.0};
        getContentPane().setLayout(gridBagLayout);
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(10, 0, 0, 0);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 0;
        getContentPane().add(getPanel(), gbc_panel);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.WEST;
         
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
         
        getContentPane().add(new JScrollPane(textArea), constraints);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 356);
        setLocationRelativeTo(null);
    }
	public JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0};
			gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			GridBagConstraints gbc_chckbxActiv = new GridBagConstraints();
			gbc_chckbxActiv.insets = new Insets(0, 0, 0, 5);
			gbc_chckbxActiv.gridx = 1;
			gbc_chckbxActiv.gridy = 0;
			panel.add(getChckbxActiv(), gbc_chckbxActiv);
			GridBagConstraints gbc_lblNiveauInformartion = new GridBagConstraints();
			gbc_lblNiveauInformartion.insets = new Insets(0, 0, 0, 5);
			gbc_lblNiveauInformartion.anchor = GridBagConstraints.EAST;
			gbc_lblNiveauInformartion.gridx = 2;
			gbc_lblNiveauInformartion.gridy = 0;
			panel.add(getLblNiveauInformartion(), gbc_lblNiveauInformartion);
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 0, 5);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 3;
			gbc_comboBox.gridy = 0;
			panel.add(getComboBox(), gbc_comboBox);
		}
		return panel;
	}
	public JCheckBox getChckbxActiv() {
		if (chckbxActiv == null) {
			chckbxActiv = new JCheckBox("Activé");
		}
		return chckbxActiv;
	}
	
	public boolean getActif(){
		return this.chckbxActiv.isSelected();
	}
	
	public int getNiveau(){
		return comboBox.getSelectedIndex();
	}
	
	
	public JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"0 - urgence", "1 - alerte", "2 - critique", "3 - erreur", "4 - avertissement", "5 - avis", "6 - information", "7 - !déboguage!"}));
		}
		return comboBox;
	}
	public JLabel getLblNiveauInformartion() {
		if (lblNiveauInformartion == null) {
			lblNiveauInformartion = new JLabel("Niveau d'informartion");
		}
		return lblNiveauInformartion;
	}
}