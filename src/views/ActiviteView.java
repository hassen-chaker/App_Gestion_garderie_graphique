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
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controllers.ActiviteController;
import controllers.AdresseController;
import controllers.AnimateurController;
import models.Activite;
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
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;

public class ActiviteView {

	JFrame frmGestionActivite;
	private JTextField txt_code;
	private JTextField txt_designationA;
	private JTextField txt_fraisInscription;
	private JTextField rech_code;
	private JTable table;
	
	private DefaultTableModel model = new DefaultTableModel();
	private String selectedCode=null; 
	private JComboBox comboBoxAc;
	private JRadioButton Niveau3;
	private JRadioButton Niveau2;
	private JRadioButton Niveau1;
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
	public ActiviteView() throws ClassNotFoundException, SQLException{
		initialize();
		remplirAnimateur();
		remplirJTable();
	}

	private void remplirJTable() throws SQLException, ClassNotFoundException
	{ 
		model = new DefaultTableModel();
		model.addColumn("Code");
		model.addColumn("Designation");
		model.addColumn("Animateur Responsable");
		model.addColumn("fraisInscription");
		model.addColumn("Niveau");
		ActiviteController C = new ActiviteController();
	        ArrayList<Activite> liste = C.listerActivite();
	        for (int i=0;i<liste.size();i++)
	        {
	        Activite currentActivite = liste.get(i);
	        model.addRow(new Object[]{currentActivite.getCode(),
	        		currentActivite.getDesignationA(),
	        		" Cin:"+currentActivite.getAnimateur().getCin().toString() +" Nom:"+ currentActivite.getAnimateur().getNom().toString() +" Prenom:"+ currentActivite.getAnimateur().getPrenom().toString(),
	        		currentActivite.getFraisInscription(),
	        		currentActivite.getNiveau()});
	        }
	        table.setModel(model);
	}
	
	private void remplirAnimateur() 
	{ 
//		
		try {
		String MyRequest = "select * from animateur";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{

			comboBoxAc.addItem(rs.getString("cin_animateur"));
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionActivite = new JFrame();
		frmGestionActivite.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\User\\Polytechnique\\4\u00E9me GI\\Semestre 1\\Java niveau2 Mr malek\\Mini-projet_Hassen_Tasnim\\Images\\imgGard.png"));
		frmGestionActivite.setTitle("Gestion Activite");
		frmGestionActivite.addWindowFocusListener(new WindowFocusListener() {
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
		frmGestionActivite.setBounds(100, 100, 950, 300);//450 300
		frmGestionActivite.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		panel.setForeground(new Color(255, 255, 255));
		frmGestionActivite.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblInterfaceAnimateur = new JLabel("Interface Activite");
		lblInterfaceAnimateur.setFont(new Font("Calibri", Font.BOLD, 25));
		lblInterfaceAnimateur.setForeground(new Color(255, 255, 255));
		panel.add(lblInterfaceAnimateur);
		
		JPanel panel_1 = new JPanel();
		frmGestionActivite.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Add-Update", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_12.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCode = new JLabel("Code ");
		panel_4.add(lblCode);
		
		txt_code = new JTextField();
		panel_4.add(txt_code);
		txt_code.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_12.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblDesignationA = new JLabel("Designation");
		panel_5.add(lblDesignationA);
		
		txt_designationA = new JTextField();
		panel_5.add(txt_designationA);
		txt_designationA.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_12.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblAnimateurR = new JLabel("Animateur Responsable");
		panel_6.add(lblAnimateurR);
		
		comboBoxAc = new JComboBox();
		panel_6.add(comboBoxAc);
		
		JPanel panel_7 = new JPanel();
		panel_12.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblFraisInscription = new JLabel("Frais Inscription");
		panel_7.add(lblFraisInscription);
		
		txt_fraisInscription = new JTextField();
		panel_7.add(txt_fraisInscription);
		txt_fraisInscription.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_12.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNiveau = new JLabel("Niveau");
		panel_8.add(lblNiveau);
		
		Niveau1 = new JRadioButton("1");
		Niveau1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Niveau1.isSelected()) {
					Niveau2.setSelected(false);
					Niveau3.setSelected(false);
				}
					
			}
		});
		
		JLabel label = new JLabel("");
		panel_8.add(label);
		panel_8.add(Niveau1);
		
		Niveau2 = new JRadioButton("2");
		Niveau2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Niveau2.isSelected()) {
					Niveau1.setSelected(false);
					Niveau3.setSelected(false);
				}
			}
		});
		panel_8.add(Niveau2);
		
		Niveau3 = new JRadioButton(" 3");
		Niveau3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Niveau3.isSelected()) {
					Niveau1.setSelected(false);
					Niveau2.setSelected(false);
				}
			}
		});
		panel_8.add(Niveau3);
		
		JPanel panel_13 = new JPanel();
		panel_2.add(panel_13, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBackground(new Color(0, 0, 128));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( ( (!txt_code.getText().equals("")) && !txt_designationA.getText().equals("")
						&& !txt_fraisInscription.getText().equals(""))
						
						) {
					if(Float.parseFloat(txt_fraisInscription.getText())>=0 ) {
						
				try {
					//instancier une Activite à ajouter dans la base d données:
					Activite newActivite = new Activite();
					newActivite.setCode(txt_code.getText());
					newActivite.setDesignationA(txt_designationA.getText());
			//		newActivite.setAnimateurR(txt_AnimateurR.getText());
					newActivite.setAnimateur(Animateur.getAnimateur(comboBoxAc.getSelectedItem().toString()));
					
					if(Niveau1.isSelected()) {
					newActivite.setNiveau(1);
					}else if (Niveau2.isSelected()) {
						newActivite.setNiveau(2);
					}else
						newActivite.setNiveau(3);
					
					newActivite.setFraisInscription(Float.parseFloat(txt_fraisInscription.getText()));
				
					//instancier le controleur:
					ActiviteController ac = new ActiviteController();
					if(ac.addActivite(newActivite))
					{
						JOptionPane.showMessageDialog(null, "Activite ajouté "
								+ " avec succès.");
					}
					
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Activite non ajouté"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					}
					}else {
						JOptionPane.showMessageDialog(null, " Frais d'inscription incorrect","Attention",JOptionPane.WARNING_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, " Remplir les champs vides","Attention",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel_13.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.setBackground(new Color(0, 128, 0));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( ( (!txt_code.getText().equals("")) && !txt_designationA.getText().equals("")
						&& !txt_fraisInscription.getText().equals(""))
						
						) {
					if(Integer.parseInt(txt_fraisInscription.getText())>=0 ) {
				try {
					//instancier une Activite à Modifier dans la base d données:
					Activite newActivite = new Activite();
					newActivite.setCode(txt_code.getText());
					newActivite.setDesignationA(txt_designationA.getText());
//					newActivite.setAnimateurR(txt_AnimateurR.getText());
					newActivite.setAnimateur(Animateur.getAnimateur(comboBoxAc.getSelectedItem().toString()));
					
					if(Niveau1.isSelected()) {
						newActivite.setNiveau(1);
						}else if (Niveau2.isSelected()) {
							newActivite.setNiveau(2);
						}else
							newActivite.setNiveau(3);
					
					newActivite.setFraisInscription(Float.parseFloat(txt_fraisInscription.getText()));
				
					//instancier le controleur:
					ActiviteController ac = new ActiviteController();
					if(ac.updateActivite(newActivite))
					{
						JOptionPane.showMessageDialog(null, "Activite modifié "
								+ " avec succès.");
					}
					
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Activite non modifié"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					}
					}else {
						JOptionPane.showMessageDialog(null, " Frais d'inscription incorrect","Attention",JOptionPane.WARNING_MESSAGE);
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
		btnNewButton_2.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//on recupere le cin de l'Activite à supprimer:
				if(selectedCode == null)
				{
					JOptionPane.showMessageDialog(null,
							"Veuillez selectionner le numéro code de l'activite !!");
				}
				else
				{
					
					try {
						ActiviteController ac = new ActiviteController();
						if(ac.deleteActivite(selectedCode))
						{
							JOptionPane.showMessageDialog(null,
									"Activite supprimé avec succès");
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,
								e1.getMessage());
					}
				}
			}
		});
		panel_13.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Vider");
		btnNewButton_3.setBackground(new Color(255, 215, 0));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_code.setText("");
				txt_designationA.setText("");
				

				Niveau1.setSelected(false);
				Niveau2.setSelected(false);
				Niveau3.setSelected(false);

				txt_fraisInscription.setText("");
			}
		});
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
				selectedCode = (String)table.getValueAt(table.getSelectedRow(),
						table.getSelectedColumn());
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Rechercher", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_15, BorderLayout.NORTH);
		
		JLabel lblCodeActivite = new JLabel("Code Activite");
		panel_15.add(lblCodeActivite);
		
		rech_code = new JTextField();
		rech_code.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				ActiviteController ac;
					try {
						ac = new ActiviteController();
						Activite Ac=new Activite();
						String Code=rech_code.getText();
						Ac=ac.listerActiviteByCode(Code);
						
					if(Ac!= null) {
						txt_code.setText(Ac.getCode());
						txt_designationA.setText(Ac.getDesignationA());
//						txt_AnimateurR.setText(Ac.getDesignation());
						comboBoxAc.setSelectedItem(Ac.getAnimateur().getCin());
						//txt_Niveau.setText(Integer.toString(Ac.getNiveau()));
						txt_fraisInscription.setText(Float.toString(Ac.getFraisInscription()));
						
						if(Ac.getNiveau()==1) {
							Niveau1.setSelected(true);
							Niveau2.setSelected(false);
							Niveau3.setSelected(false);
							}else if (Ac.getNiveau()==2) {
								Niveau2.setSelected(true);
								Niveau1.setSelected(false);
								Niveau3.setSelected(false);
							}else
								{Niveau3.setSelected(true);
								Niveau1.setSelected(false);
								Niveau2.setSelected(false);}
					
					}else
					{
						txt_code.setText("");
						txt_designationA.setText("");
						
						Niveau1.setSelected(false);
						Niveau2.setSelected(false);
						Niveau3.setSelected(false);
						txt_fraisInscription.setText("");
						
					}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					
						
					
			
			}
		});
		panel_15.add(rech_code);
		rech_code.setColumns(10);
	}

}
