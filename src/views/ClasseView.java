package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import javax.imageio.stream.FileImageInputStream;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxEditor;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import controllers.AdresseController;
import controllers.AnimateurController;
import controllers.ClasseController;
import models.Activite;
import models.Adresse;
import models.Animateur;
import models.Classe;
import models.Eleve;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//import com.sun.tools.classfile.Opcode.Set;
import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;

public class ClasseView {

	JFrame frmGestionClasse;
	private JTextField txt_code;
	private JTextField rech_classe;
	private JTable table;
	
	private DefaultTableModel model = new DefaultTableModel();
	private String selectedCODE=null;
	private JComboBox comboBox; 
	private JComboBox comboBoxAn;
	
	private String s;
	private JTextField txt_Nom;
	private JComboBox comboBoxEl;
	private JComboBox comboBoxAc;
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
	public ClasseView() throws ClassNotFoundException, SQLException{
		initialize();
		remplirEleve();
		remplirActivite();
		remplirAnimateur(); 
//		remplirJTable();
		
	}

	private void remplirJTable() throws SQLException, ClassNotFoundException
	{ 
		model = new DefaultTableModel();
		model.addColumn("Code");
		model.addColumn("Nom");
		model.addColumn("Eleves");
		model.addColumn("Activites");
		model.addColumn("Animateur");
	        ClasseController C = new ClasseController();
	        ArrayList<Classe> liste = C.listerClasse();
	        for (int i=0;i<liste.size();i++)
	        {
	        	Classe currentClasse = liste.get(i);
	        model.addRow(new Object[]{currentClasse.getCodeC(),
	        		currentClasse.getNomC(),
	        		currentClasse.getListeEleves().toString(),
	        currentClasse.getListeActivites().toString(),
	        currentClasse.getListeAnimateurs().toString()});
	        }
	        table.setModel(model);
	}
	
	private void remplirEleve() 
	{ 
//		
		try {
		String MyRequest = "select * from eleve";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{

			comboBoxEl.addItem(rs.getString("code_eleve"));
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void remplirActivite() 
	{ 
//		
		try {
		String MyRequest = "select * from activite";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{

			comboBoxAc.addItem(rs.getString("code_activite"));
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void remplirAnimateur() 
	{ 
//		
		try {
		String MyRequest = "select * from animateur";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{

			comboBoxAn.addItem(rs.getString("cin_animateur"));
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionClasse = new JFrame();
		frmGestionClasse.setTitle("Gestion Classe");
		frmGestionClasse.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\User\\Polytechnique\\4\u00E9me GI\\Semestre 1\\Java niveau2 Mr malek\\Mini-projet_Hassen_Tasnim\\Images\\imgGard.png"));
		frmGestionClasse.addWindowFocusListener(new WindowFocusListener() {
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
		frmGestionClasse.setBounds(100, 100, 950, 300);//450 300
		frmGestionClasse.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		frmGestionClasse.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblInterfaceAnimateur = new JLabel("Interface Classe");
		lblInterfaceAnimateur.setFont(new Font("Calibri", Font.BOLD, 23));
		lblInterfaceAnimateur.setForeground(new Color(255, 255, 255));
		panel.add(lblInterfaceAnimateur);
		
		JPanel panel_1 = new JPanel();
		frmGestionClasse.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Add-Update", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel panel_18 = new JPanel();
		panel_12.add(panel_18);
		panel_18.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_18.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCodeC = new JLabel("code");
		panel_4.add(lblCodeC);
		
		txt_code = new JTextField();
		panel_4.add(txt_code);
		txt_code.setColumns(10);
		
		JPanel panel_4_1 = new JPanel();
		panel_18.add(panel_4_1);
		panel_4_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNom_1 = new JLabel("Nom");
		panel_4_1.add(lblNom_1);
		
		txt_Nom = new JTextField();
		txt_Nom.setColumns(10);
		panel_4_1.add(txt_Nom);
		
		JPanel panel_5 = new JPanel();
		panel_18.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNom = new JLabel("Eleves");
		panel_5.add(lblNom);
		
		comboBoxEl = new JComboBox();
		panel_5.add(comboBoxEl);
		
		JPanel panel_8 = new JPanel();
		panel_18.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblActivites = new JLabel("Activites");
		panel_8.add(lblActivites);
		
		comboBoxAc = new JComboBox();
		panel_8.add(comboBoxAc);
		
		
		JPanel panel_10 = new JPanel();
		panel_18.add(panel_10);
		panel_10.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblAnimateurs = new JLabel("Animateurs");
		panel_10.add(lblAnimateurs);
		
		comboBoxAn = new JComboBox();
		
		comboBoxAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
			}
		});
		panel_10.add(comboBoxAn);
		
		JPanel panel_13 = new JPanel();
		panel_2.add(panel_13, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( ( (!txt_code.getText().equals("")) && !txt_Nom.getText().equals("")
						)
						) {
				try {
					//instancier un animateur à ajouter dans la base d données:
					Classe newClasse = new Classe();
					newClasse.setCodeC(txt_code.getText());
					newClasse.setNomC(txt_Nom.getText());
					
					newClasse.setListeEleves(Eleve.getEleve(comboBoxEl.getSelectedItem().toString()));
					newClasse.setListeActivites(Activite.getActivite(comboBoxAc.getSelectedItem().toString()));
					newClasse.setListeAnimateurs(Animateur.getAnimateur(comboBoxAn.getSelectedItem().toString()));
				
					
					//instancier le controleur:
					ClasseController c = new ClasseController();
					if(c.addClasse(newClasse))
					{
						JOptionPane.showMessageDialog(null, "Classe ajouté "
								+ " avec succès.");
					}
					
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Classe non ajouté"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Classe non ajouté"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					}
				
			}else {
				JOptionPane.showMessageDialog(null, " Remplir les champs vides","Attention",JOptionPane.WARNING_MESSAGE);
			}
			}
		});
		panel_13.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.setBackground(new Color(0, 0, 128));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( ( (!txt_code.getText().equals("")) && !txt_Nom.getText().equals(""))
						
						) {
				try {
					//instancier un animateur à modifié dans la base d données:
					Classe newClasse = new Classe();
					newClasse.setCodeC(txt_code.getText());
					newClasse.setNomC(txt_Nom.getText());
					
					newClasse.setListeEleves(Eleve.getEleve(comboBoxEl.getSelectedItem().toString()));
					newClasse.setListeActivites(Activite.getActivite(comboBoxAc.getSelectedItem().toString()));
					newClasse.setListeAnimateurs(Animateur.getAnimateur(comboBoxAn.getSelectedItem().toString()));
					//instancier le controleur:
					ClasseController c = new ClasseController();
					if(c.updateClasse(newClasse))
					{
						JOptionPane.showMessageDialog(null, "Classe modifié "
								+ " avec succès.");
					}
					
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Classe non modifié"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Classe non modifié"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					}
				}else {
					JOptionPane.showMessageDialog(null, " Remplir les champs vides");
				}
			}
		});
		panel_13.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.setBackground(new Color(255, 0, 0));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//on recupere le cin de l'animateur à supprimer:
				if(selectedCODE == null)
				{
					JOptionPane.showMessageDialog(null,
							"Veuillez selectionner le Code de l'classe !!");
				}
				else
				{
					
					try {
						ClasseController ac = new ClasseController();
						if(ac.deleteClasse(selectedCODE))
						{
							JOptionPane.showMessageDialog(null,
									"Classe supprimé avec succès");
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
				txt_code.setText("");
				txt_Nom.setText("");
			}
		});
		btnNewButton_3.setBackground(new Color(255, 215, 0));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
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
				selectedCODE = (String)table.getValueAt(table.getSelectedRow(),
						table.getSelectedColumn());
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Rechercher", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_15, BorderLayout.NORTH);
		
		JLabel lblCinAnimateur = new JLabel("Cin Classe");
		panel_15.add(lblCinAnimateur);
		
		rech_classe = new JTextField();
		rech_classe.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
					ClasseController cl;
					try {
						cl = new ClasseController();
						Classe C=new Classe();
						String code=rech_classe.getText();
						C=cl.listerClasseByCode(code);
						
					if(C!= null) {
						txt_code.setText(C.getCodeC());
						txt_Nom.setText(C.getNomC());
	
					
						
						//txt_Adresse.setText(A.getAdresse().getId());
						comboBoxEl.setSelectedItem(C.getListeEleves().getCodeE());
						comboBoxAc.setSelectedItem(C.getListeActivites().getCode());
						comboBoxAn.setSelectedItem(C.getListeAnimateurs().getCin());
					
					}else
					{
						txt_code.setText("");
						txt_Nom.setText("");
						

					}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					
						
					
			
			}
		});
		panel_15.add(rech_classe);
		rech_classe.setColumns(10);
	}

}
