package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controllers.AdresseController;
import controllers.AnimateurController;
import models.Adresse;
import models.Animateur;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class AdresseView {

	JFrame frmGestionAdresse;
	private JTextField txt_id;
	private JTextField txt_numeroRue;
	private JTextField txt_libelle;
	private JTextField txt_ville;
	private JTextField txt_Postal;
	private JTextField rech_adresse;
	private JTable table;
	
	private DefaultTableModel model = new DefaultTableModel();
	private String selectedId=null; 
	private JTextField txt_gouvernorat;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnimateurView window = new AnimateurView();
					window.frmGestionAnimaateur.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public AdresseView() throws ClassNotFoundException, SQLException{
		initialize();
		remplirJTable();
	}

	private void remplirJTable() throws SQLException, ClassNotFoundException
	{ 
		model = new DefaultTableModel();
		model.addColumn("Id Adresse");
		model.addColumn("Numéro Rue");
		model.addColumn("Designation");
		model.addColumn("Ville");
		model.addColumn("Code Postal");
		model.addColumn("Gouvernorat");
			AdresseController C = new AdresseController();
	        ArrayList<Adresse> liste = C.listerAdresse();
	        for (int i=0;i<liste.size();i++)
	        {
	        Adresse currentAdresse = liste.get(i);
	        model.addRow(new Object[]{currentAdresse.getId(),
	        		currentAdresse.getNum(), currentAdresse.getDesignation(),
	        		currentAdresse.getVille(),
	        		currentAdresse.getCodePostal(),
	        		currentAdresse.getGouvernorat()});
	        }
	        table.setModel(model);
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionAdresse = new JFrame();
		frmGestionAdresse.setTitle("Gestion Adresse");
		frmGestionAdresse.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\User\\Polytechnique\\4\u00E9me GI\\Semestre 1\\Java niveau2 Mr malek\\Mini-projet_Hassen_Tasnim\\Images\\imgGard.png"));
		frmGestionAdresse.addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent e) {
				try {
					remplirJTable();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());					
				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());					
				}
			}
			public void windowLostFocus(WindowEvent e) {
			}
		});
		frmGestionAdresse.setBounds(100, 100, 940, 350);//450 300
		frmGestionAdresse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		frmGestionAdresse.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblInterfaceAnimateur = new JLabel("Interface Adresse");
		lblInterfaceAnimateur.setForeground(new Color(255, 255, 255));
		lblInterfaceAnimateur.setFont(new Font("Calibri", Font.BOLD, 25));
		panel.add(lblInterfaceAnimateur);
		
		JPanel panel_1 = new JPanel();
		frmGestionAdresse.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Add-Update", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(165, 42, 42)));
		panel_2.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_12.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblId = new JLabel("Id adresse");
		panel_4.add(lblId);
		
		txt_id = new JTextField();
		panel_4.add(txt_id);
		txt_id.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_12.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNumeroRue = new JLabel("Num\u00E9ro Rue");
		panel_5.add(lblNumeroRue);
		
		txt_numeroRue = new JTextField();
		panel_5.add(txt_numeroRue);
		txt_numeroRue.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_12.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblLibelle = new JLabel("Libelle");
		panel_6.add(lblLibelle);
		
		txt_libelle = new JTextField();
		panel_6.add(txt_libelle);
		txt_libelle.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_12.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblVille = new JLabel("Ville");
		panel_7.add(lblVille);
		
		txt_ville = new JTextField();
		panel_7.add(txt_ville);
		txt_ville.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_12.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblPostal = new JLabel("Postal");
		panel_8.add(lblPostal);
		
		txt_Postal = new JTextField();
		panel_8.add(txt_Postal);
		txt_Postal.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_12.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblGouvernorat = new JLabel("Gouvernorat");
		panel_9.add(lblGouvernorat);
		
		txt_gouvernorat = new JTextField();
		panel_9.add(txt_gouvernorat);
		txt_gouvernorat.setColumns(10);
		
		
		JPanel panel_10 = new JPanel();
		panel_12.add(panel_10);
		panel_10.setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel_13 = new JPanel();
		panel_2.add(panel_13, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBackground(new Color(60, 179, 113));
		btnNewButton.setForeground(new Color(240, 255, 240));
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( ( (!txt_id.getText().equals("")) && !txt_numeroRue.getText().equals("")
						&& !txt_libelle.getText().equals("")	&& !txt_Postal.getText().equals("")
						&& !txt_ville.getText().equals("") && !txt_gouvernorat.getText().equals(""))
						
						) {
					if((Integer.parseInt(txt_Postal.getText())>=1000) && (Integer.parseInt(txt_Postal.getText())<=9999)) {
						
				try {
					//instancier une Adresse à ajouter dans la base d données:
					Adresse newAdresse = new Adresse();
					newAdresse.setId(txt_id.getText());
					newAdresse.setNum(txt_numeroRue.getText());
					newAdresse.setDesignation(txt_libelle.getText());
					newAdresse.setCodePostal(txt_Postal.getText());
					newAdresse.setVille(txt_ville.getText());
					newAdresse.setGouvernorat(txt_gouvernorat.getText());
				
					//instancier le controleur:
					AdresseController ad = new AdresseController();
					if(ad.addAdresse(newAdresse))
					{
						JOptionPane.showMessageDialog(null, "Adresse ajouté "
								+ " avec succès.");
					}
					
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Adresse non ajouté"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Adresse non ajouté"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					}
					}else {
						JOptionPane.showMessageDialog(null, " Code Postal incorrect","Attention",JOptionPane.WARNING_MESSAGE);
					}
			}else {
				JOptionPane.showMessageDialog(null, " Remplir les champs vides","Attention",JOptionPane.WARNING_MESSAGE);
			}
			}
		});
		panel_13.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.setBackground(new Color(0, 0, 128));
		btnNewButton_1.setForeground(new Color(240, 255, 240));
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 15));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( ( (!txt_id.getText().equals("")) && !txt_numeroRue.getText().equals("")
						&& !txt_libelle.getText().equals("")	&& !txt_Postal.getText().equals("")
						&& !txt_ville.getText().equals("") && !txt_gouvernorat.getText().equals(""))
						
						) {
					if((Integer.parseInt(txt_Postal.getText())>=1000) && (Integer.parseInt(txt_Postal.getText())<=9999)) {
				try {
					//instancier une Adresse à ajouter dans la base d données:
					Adresse newAdresse = new Adresse();
					newAdresse.setId(txt_id.getText());
					newAdresse.setNum(txt_numeroRue.getText());
					newAdresse.setDesignation(txt_libelle.getText());
					newAdresse.setCodePostal(txt_Postal.getText());
					newAdresse.setVille(txt_ville.getText());
					newAdresse.setGouvernorat(txt_gouvernorat.getText());
				
					//instancier le controleur:
					AdresseController ad = new AdresseController();
					if(ad.updateAdresse(newAdresse))
					{
						JOptionPane.showMessageDialog(null, "Adresse modifié "
								+ " avec succès.");
					}
					
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Adresse non modifié"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Adresse non modifié"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					}
					}else {
						JOptionPane.showMessageDialog(null, " Code Postal incorrect","Attention",JOptionPane.WARNING_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, " Remplir les champs vides","Attention",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel_13.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.setBackground(new Color(255, 0, 0));
		btnNewButton_2.setForeground(new Color(240, 255, 240));
		btnNewButton_2.setFont(new Font("Calibri", Font.BOLD, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//on recupere le cin de l'animateur à supprimer:
				if(selectedId == null)
				{
					JOptionPane.showMessageDialog(null,
							"Veuillez selectionner le numéro Id de l'adresse !!");
				}
				else
				{
					
					try {
						AdresseController ad = new AdresseController();
						if(ad.deleteAdresse(selectedId))
						{
							JOptionPane.showMessageDialog(null,
									"Adresse supprimé avec succès");
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,
								e1.getMessage());
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null,
								e1.getMessage());
					}
				}
			}
		});
		panel_13.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Vider");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_id.setText("");
				txt_numeroRue.setText("");
				txt_libelle.setText("");
				txt_Postal.setText("");
				txt_ville.setText("");
				txt_gouvernorat.setText("");
			}
		});
		btnNewButton_3.setBackground(new Color(255, 215, 0));
		btnNewButton_3.setForeground(new Color(240, 255, 240));
		btnNewButton_3.setFont(new Font("Calibri", Font.BOLD, 15));
		panel_13.add(btnNewButton_3);
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_3.add(panel_14, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_14.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedId = (String)table.getValueAt(table.getSelectedRow(),
						table.getSelectedColumn());
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Rechercher", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_15, BorderLayout.NORTH);
		
		JLabel lblIdAdresse = new JLabel("Id Adresse");
		panel_15.add(lblIdAdresse);
		
		rech_adresse = new JTextField();
		rech_adresse.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
					AdresseController ad;
					try {
						ad = new AdresseController();
						Adresse A=new Adresse();
						String Id=rech_adresse.getText();
						A=ad.listerAdresseById(Id);
						
					if(A!= null) {
						txt_id.setText(A.getId());
						txt_numeroRue.setText(A.getNum());
						txt_libelle.setText(A.getDesignation());
						txt_Postal.setText(A.getCodePostal());
						txt_ville.setText(A.getVille());
						txt_gouvernorat.setText(A.getGouvernorat());
						
					
					}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					
						
					
			
			}
		});
		panel_15.add(rech_adresse);
		rech_adresse.setColumns(10);
	}

}
