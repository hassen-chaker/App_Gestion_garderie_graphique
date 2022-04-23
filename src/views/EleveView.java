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
import controllers.EleveController;
import models.Activite;
import models.Adresse;
import models.Animateur;
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
import javax.swing.JRadioButton;
import java.awt.Toolkit;
import java.awt.Font;

public class EleveView {

	JFrame frmGestionEleve;
	private JTextField txt_codeE;
	private JTextField txt_nom;
	private JTextField txt_prenom;
	private JTextField txt_pere;
	private JTextField txt_telephone;
	private JTextField rech_Eleve;
	private JTable table;
	
	private DefaultTableModel model = new DefaultTableModel();
	private String selectedCode=null;
	private JComboBox comboBox; 
	private JComboBox comboBoxAd;
	
	private String s;
	private JTextField txt_PrenomGP;
	private JTextField txt_NomM;
	private JTextField txt_PrenomM;
	private JRadioButton Niveau1;
	private JRadioButton Niveau2;
	private JRadioButton Niveau3;
	private JComboBox comboBoxAc;
	private JComboBox comboBoxAc2;
	private JComboBox comboBoxAc3;
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
	public EleveView() throws ClassNotFoundException, SQLException{
		initialize();
		remplirAdresse();
		//remplirActivite();
		remplirJTable();
		
	}

	private void remplirJTable() throws SQLException, ClassNotFoundException
	{ 
		model = new DefaultTableModel();
		model.addColumn("Numéro");
		model.addColumn("Nom");
		model.addColumn("Prénom");
		model.addColumn("Téléphone");
		model.addColumn("Père");
		model.addColumn("Date Naissance");
		model.addColumn("Adresse");
		model.addColumn("Image");
		model.addColumn("Grand Père");
		model.addColumn("Nom Mère");
		model.addColumn("Prénom Mère");
		model.addColumn("Niveau");
		model.addColumn("Activite");
		model.addColumn("Activite2");
		model.addColumn("Activite3");
	        EleveController C = new EleveController();
	        ArrayList<Eleve> liste = C.listerEleve();
	        for (int i=0;i<liste.size();i++)
	        {
	        Eleve currentEleve = liste.get(i);
	        model.addRow(new Object[]{currentEleve.getCodeE(),
	        currentEleve.getNom(), currentEleve.getPrenom(),
	        currentEleve.getTelephone(),
	        currentEleve.getPere(),
	        currentEleve.getDate_naissance().toString(),
	        currentEleve.getAdresse().toString(),
	        currentEleve.getImage(),
	        currentEleve.getPrenomGP(),
	        currentEleve.getNomM(),
	        currentEleve.getPrenomM(),
	        currentEleve.getNiveauS(),
	        currentEleve.getListeCoursS().toString(),
	        currentEleve.getCoursS2().toString(),
	        currentEleve.getCoursS3().toString()});
	        }
	        table.setModel(model);
	}
	
	private void remplirAdresse() 
	{ 
//		
		try {
		String MyRequest = "select * from adresse";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{

			comboBoxAd.addItem(rs.getString("Id_adresse"));
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	private void remplirActivite(int niveau) 
	{ 
//		
		try {
		String MyRequest = "select * from activite where niveau_activite='"+ niveau +"'";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{

			comboBoxAc.addItem(rs.getString("code_activite"));
			comboBoxAc2.addItem(rs.getString("code_activite"));
			comboBoxAc3.addItem(rs.getString("code_activite"));			
		}
		
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionEleve = new JFrame();
		frmGestionEleve.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\User\\Polytechnique\\4\u00E9me GI\\Semestre 1\\Java niveau2 Mr malek\\Mini-projet_Hassen_Tasnim\\Images\\imgGard.png"));
		frmGestionEleve.setTitle("Gestion \u00C9l\u00E8ve");
		frmGestionEleve.addWindowFocusListener(new WindowFocusListener() {
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
		frmGestionEleve.setBounds(100, 100, 1050, 500);//450 300
		frmGestionEleve.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		frmGestionEleve.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblInterfaceEleve = new JLabel("Interface \u00C9l\u00E8ve");
		lblInterfaceEleve.setFont(new Font("Calibri", Font.BOLD, 25));
		lblInterfaceEleve.setForeground(new Color(255, 255, 255));
		panel.add(lblInterfaceEleve);
		
		JPanel panel_1 = new JPanel();
		frmGestionEleve.getContentPane().add(panel_1, BorderLayout.CENTER);
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
		
		JLabel lblCodeE = new JLabel("Num\u00E9ro d'inscription");
		panel_4.add(lblCodeE);
		
		JPanel panel_5 = new JPanel();
		panel_18.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNom = new JLabel("Nom");
		panel_5.add(lblNom);
		
		JPanel panel_6 = new JPanel();
		panel_18.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		panel_6.add(lblPrnom);
		
		JPanel panel_7 = new JPanel();
		panel_18.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblPrnomPre = new JLabel("Pr\u00E9nom P\u00E9re");
		panel_7.add(lblPrnomPre);
		
		JPanel panel_8 = new JPanel();
		panel_18.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblTlephone = new JLabel("T\u00E9lephone");
		panel_8.add(lblTlephone);
		
		JPanel panel_9 = new JPanel();
		panel_18.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblDateNaissance = new JLabel("Date Naissance");
		panel_9.add(lblDateNaissance);
		
		
		JPanel panel_10 = new JPanel();
		panel_18.add(panel_10);
		panel_10.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblAdresse = new JLabel("Adresse");
		panel_10.add(lblAdresse);
		
		JPanel panel_5_1 = new JPanel();
		panel_18.add(panel_5_1);
		panel_5_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblPrenomGP = new JLabel("Prenom Grand P\u00E9re");
		panel_5_1.add(lblPrenomGP);
		
		JPanel panel_5_1_1 = new JPanel();
		panel_18.add(panel_5_1_1);
		panel_5_1_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNomM = new JLabel("Nom M\u00E8re");
		panel_5_1_1.add(lblNomM);
		
		JPanel panel_5_1_1_1 = new JPanel();
		panel_18.add(panel_5_1_1_1);
		panel_5_1_1_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblPrenomM = new JLabel("Prenom M\u00E8re");
		panel_5_1_1_1.add(lblPrenomM);
		
		JPanel panel_8_1 = new JPanel();
		panel_18.add(panel_8_1);
		panel_8_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNiveau = new JLabel("Niveau");
		panel_8_1.add(lblNiveau);
		
		JLabel label = new JLabel("");
		panel_8_1.add(label);
		
		JPanel panel_10_1 = new JPanel();
		panel_18.add(panel_10_1);
		panel_10_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblActivite = new JLabel("Activit\u00E9s");
		panel_10_1.add(lblActivite);
		
		JPanel panel_23 = new JPanel();
		panel_18.add(panel_23);
		
		JPanel panel_11 = new JPanel();
		panel_18.add(panel_11);
		
		JPanel panel_21 = new JPanel();
		panel_12.add(panel_21);
		panel_21.setLayout(new GridLayout(0, 1, 0, 0));
		
		txt_codeE = new JTextField();
		panel_21.add(txt_codeE);
		txt_codeE.setColumns(10);
		
		txt_nom = new JTextField();
		panel_21.add(txt_nom);
		txt_nom.setColumns(10);
		
		txt_prenom = new JTextField();
		panel_21.add(txt_prenom);
		txt_prenom.setColumns(10);
		
		txt_pere = new JTextField();
		panel_21.add(txt_pere);
		txt_pere.setColumns(10);
		
		txt_telephone = new JTextField();
		panel_21.add(txt_telephone);
		txt_telephone.setColumns(10);
		
		JDateChooser txt_datenaissance = new JDateChooser();
		panel_21.add(txt_datenaissance);
		
		comboBoxAd = new JComboBox();
		panel_21.add(comboBoxAd);
		
		txt_PrenomGP = new JTextField();
		panel_21.add(txt_PrenomGP);
		txt_PrenomGP.setColumns(10);
		
		txt_NomM = new JTextField();
		panel_21.add(txt_NomM);
		txt_NomM.setColumns(10);
		
		txt_PrenomM = new JTextField();
		panel_21.add(txt_PrenomM);
		txt_PrenomM.setColumns(10);
		
		JPanel panel_22 = new JPanel();
		panel_21.add(panel_22);
		
		Niveau1 = new JRadioButton("1");
		panel_22.add(Niveau1);
		
		Niveau2 = new JRadioButton("2");
		panel_22.add(Niveau2);
		
		Niveau3 = new JRadioButton(" 3");
		panel_22.add(Niveau3);
		
		comboBoxAc = new JComboBox();
		panel_21.add(comboBoxAc);
		
		comboBoxAc2 = new JComboBox();
		panel_21.add(comboBoxAc2);
		
		comboBoxAc3 = new JComboBox();
		panel_21.add(comboBoxAc3);
		Niveau3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Niveau3.isSelected()) {
					Niveau1.setSelected(false);
					Niveau2.setSelected(false);
					comboBoxAc.removeAllItems();
					comboBoxAc2.removeAllItems();
					comboBoxAc3.removeAllItems();
					remplirActivite(3); 
				}
			}
		});
		Niveau2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Niveau2.isSelected()) {
					Niveau1.setSelected(false);
					Niveau3.setSelected(false);
					comboBoxAc.removeAllItems();
					comboBoxAc2.removeAllItems();
					comboBoxAc3.removeAllItems();
					remplirActivite(2); 
				}
			}
		});
		Niveau1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(Niveau1.isSelected()) {
					Niveau2.setSelected(false);
					Niveau3.setSelected(false);
					comboBoxAc.removeAllItems();
					comboBoxAc2.removeAllItems();
					comboBoxAc3.removeAllItems();
					remplirActivite(1); 
				}
			}
		});
		
		comboBoxAd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
			}
		});
		
		JPanel panel_16 = new JPanel();
		panel_12.add(panel_16);
		panel_16.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_25 = new JPanel();
		panel_16.add(panel_25);
		
		JPanel panel_24 = new JPanel();
		panel_16.add(panel_24);
		
		JPanel panel_17 = new JPanel();
		panel_17.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_17.setBounds(327,83,137,122);
		panel_16.add(panel_17);
		panel_17.setLayout(new GridLayout(1, 1));
		
		JLabel labimage = new JLabel("");
		panel_17.add(labimage);
		
		JPanel panel_20 = new JPanel();
		panel_16.add(panel_20);
		
		JButton btnParcourir = new JButton("Parcourir Image");
		btnParcourir.setBackground(new Color(176, 224, 230));
		btnParcourir.setForeground(new Color(255, 255, 255));
		panel_20.add(btnParcourir);
		btnParcourir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JFileChooser fileChooser =new JFileChooser();
				fileChooser.setCurrentDirectory(new File("E:\\User\\Polytechnique\\4éme GI\\Semestre 1\\Java niveau2 Mr malek\\Mini-projet_Hassen_Tasnim\\Images"));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE", "jpg" ,"png" , "gif");
				fileChooser.addChoosableFileFilter(filter);
				int result = fileChooser.showSaveDialog(null);
				
				if(result == JFileChooser.APPROVE_OPTION)
				{
					File selectedfile = fileChooser.getSelectedFile();
					String path = selectedfile.getAbsolutePath();
					ImageIcon myImage = new ImageIcon(path);
					java.awt.Image img = myImage.getImage();
					java.awt.Image newImage = img.getScaledInstance(labimage.getWidth(), labimage.getHeight(), java.awt.Image.SCALE_SMOOTH);
					ImageIcon finalImg = new ImageIcon(newImage);
					labimage.setIcon(finalImg);
					s=path;
				}else
				{
					if(result == JFileChooser.CANCEL_OPTION)
						JOptionPane.showMessageDialog(null, " T'as rien choisi");
				}
				 
			}
		});
		
		JPanel panel_19 = new JPanel();
		panel_16.add(panel_19);
		
		JPanel panel_13 = new JPanel();
		panel_2.add(panel_13, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Ajouter");
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if( ( (!txt_codeE.getText().equals("")) && !txt_nom.getText().equals("")
						&& !txt_prenom.getText().equals("")	&& !txt_telephone.getText().equals("")
						&& !txt_pere.getText().equals("") && !txt_PrenomGP.getText().equals("")
						&& !txt_NomM.getText().equals("") && !txt_PrenomM.getText().equals(""))
						
						) {
					if((Integer.parseInt(txt_telephone.getText())>=10000000) && (Integer.parseInt(txt_telephone.getText())<=99999999)) {
				try {
					//instancier un eleve à ajouter dans la base d données:
					Eleve newEleve = new Eleve();
					newEleve.setCodeE(txt_codeE.getText());
					newEleve.setNom(txt_nom.getText());
					newEleve.setPrenom(txt_prenom.getText());
					newEleve.setTelephone(txt_telephone.getText());
					newEleve.setPere(txt_pere.getText());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(txt_datenaissance.getDate());
					Date newDate = Date.valueOf(date);
					newEleve.setDate_naissance(newDate);
					newEleve.setAdresse(Adresse.getAdresse(comboBoxAd.getSelectedItem().toString()));
				
					
					try {
						InputStream imgg=new FileInputStream(new File(s));
						newEleve.setImage(imgg);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					newEleve.setPrenomGP(txt_PrenomGP.getText());
					newEleve.setNomM(txt_NomM.getText());
					newEleve.setPrenomM(txt_PrenomM.getText());
					
					if(Niveau1.isSelected()) {
						newEleve.setNiveauS(1);
						}else if (Niveau2.isSelected()) {
							newEleve.setNiveauS(2);
						}else
							newEleve.setNiveauS(3);
						
					newEleve.setListeCoursS(Activite.getActivite(comboBoxAc.getSelectedItem().toString()));
					newEleve.setCoursS2(Activite.getActivite(comboBoxAc2.getSelectedItem().toString()));
					newEleve.setCoursS3(Activite.getActivite(comboBoxAc3.getSelectedItem().toString()));
					//instancier le controleur:
					
					EleveController ac = new EleveController();
					if(ac.addEleve(newEleve))
					{
						JOptionPane.showMessageDialog(null, "Eleve ajouté "
								+ " avec succès.");
					}
					
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Eleve non ajouté"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Eleve non ajouté"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					}
				}else {
					JOptionPane.showMessageDialog(null, " Numéro Télèphone incorrect","Attention",JOptionPane.WARNING_MESSAGE);
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
				
				if( ( (!txt_codeE.getText().equals("")) && !txt_nom.getText().equals("")
						&& !txt_prenom.getText().equals("")	&& !txt_telephone.getText().equals("")
						&& !txt_pere.getText().equals("") && !txt_PrenomGP.getText().equals("")
						&& !txt_NomM.getText().equals("") && !txt_PrenomM.getText().equals(""))
						
						) {
					if((Integer.parseInt(txt_telephone.getText())>=10000000) && (Integer.parseInt(txt_telephone.getText())<=99999999)) {
				try {
					//instancier un eleve à ajouter dans la base d données:
					Eleve newEleve = new Eleve();
					newEleve.setCodeE(txt_codeE.getText());
					newEleve.setNom(txt_nom.getText());
					newEleve.setPrenom(txt_prenom.getText());
					newEleve.setTelephone(txt_telephone.getText());
					newEleve.setPere(txt_pere.getText());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(txt_datenaissance.getDate());
					Date newDate = Date.valueOf(date);
					newEleve.setDate_naissance(newDate);
					newEleve.setAdresse(Adresse.getAdresse(comboBoxAd.getSelectedItem().toString()));
					
				
					
					try {
						InputStream imgg=new FileInputStream(new File(s));
						newEleve.setImage(imgg);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					newEleve.setPrenomGP(txt_PrenomGP.getText());
					newEleve.setNomM(txt_NomM.getText());
					newEleve.setPrenomM(txt_PrenomM.getText());
					
					if(Niveau1.isSelected()) {
						newEleve.setNiveauS(1);
						}else if (Niveau2.isSelected()) {
							newEleve.setNiveauS(2);
						}else
							newEleve.setNiveauS(3);
						
					newEleve.setListeCoursS(Activite.getActivite(comboBoxAc.getSelectedItem().toString()));
					newEleve.setCoursS2(Activite.getActivite(comboBoxAc2.getSelectedItem().toString()));
					newEleve.setCoursS3(Activite.getActivite(comboBoxAc3.getSelectedItem().toString()));
					
					//instancier le controleur:
					EleveController el = new EleveController();
					if(el.updateEleve(newEleve))
					{
						JOptionPane.showMessageDialog(null, "Eleve modifié "
								+ " avec succès.");
					}
					
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Eleve non modifié"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Eleve non modifié"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					}
					}else {
						JOptionPane.showMessageDialog(null, " Numéro Télèphone incorrect","Attention",JOptionPane.WARNING_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, " Remplir les champs vides","Attention",JOptionPane.WARNING_MESSAGE);
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
				if(selectedCode == null)
				{
					JOptionPane.showMessageDialog(null,
							"Veuillez selectionner le numéro d'inscription de l'eleve !!");
				}
				else
				{
					
					try {
						EleveController el = new EleveController();
						if(el.deleteEleve(selectedCode))
						{
							JOptionPane.showMessageDialog(null,
									"Animateur supprimé avec succès");
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
		btnNewButton_3.setBackground(new Color(255, 215, 0));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_codeE.setText("");
				txt_nom.setText("");
				txt_prenom.setText("");
				txt_telephone.setText("");
				txt_pere.setText("");
				txt_PrenomGP.setText("");
				txt_NomM.setText("");
				txt_PrenomM.setText("");
				Niveau1.setSelected(false);
				Niveau2.setSelected(false);
				Niveau3.setSelected(false);
				comboBoxAc.removeAllItems();
				comboBoxAc2.removeAllItems();
				comboBoxAc3.removeAllItems();
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
		
		JLabel lblNumE = new JLabel("Num\u00E9ro Eleve");
		panel_15.add(lblNumE);
		
		rech_Eleve = new JTextField();
		rech_Eleve.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				EleveController el;
					try {
						el = new EleveController();
						Eleve E=new Eleve();
						String code=rech_Eleve.getText();
						E=el.listerEleveByCode(code);
						
					if(E!= null) {
						txt_codeE.setText(E.getCodeE());
						txt_nom.setText(E.getNom());
						txt_prenom.setText(E.getPrenom());
						txt_telephone.setText(E.getTelephone());
						txt_pere.setText(E.getPere());
	
						Date date2=E.getDate_naissance();
						txt_datenaissance.setDate(date2);
						
						comboBoxAd.setSelectedItem(E.getAdresse().getId());
						
						txt_PrenomGP.setText(E.getPrenomGP());
						txt_NomM.setText(E.getNomM());
						txt_PrenomM.setText(E.getPrenomM());
						
						if(E.getNiveauS()==1) {
							Niveau1.setSelected(true);
							Niveau2.setSelected(false);
							Niveau3.setSelected(false);
							comboBoxAc.removeAllItems();
							comboBoxAc2.removeAllItems();
							comboBoxAc3.removeAllItems();
							remplirActivite(1); 
							
							}else if (E.getNiveauS()==2) {
								Niveau2.setSelected(true);
								Niveau1.setSelected(false);
								Niveau3.setSelected(false);
								comboBoxAc.removeAllItems();
								comboBoxAc2.removeAllItems();
								comboBoxAc3.removeAllItems();
								remplirActivite(2); 
							}else
								{Niveau3.setSelected(true);
								Niveau1.setSelected(false);
								Niveau2.setSelected(false);
								comboBoxAc.removeAllItems();
								comboBoxAc2.removeAllItems();
								comboBoxAc3.removeAllItems();
								remplirActivite(3); }
						
						comboBoxAc.setSelectedItem(E.getListeCoursS().getCode());
						comboBoxAc2.setSelectedItem(E.getCoursS2().getCode());
						comboBoxAc3.setSelectedItem(E.getCoursS3().getCode());
						
						String MyRequest = "select * from eleve where  code_eleve='"+ E.getCodeE()+"'";
						ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
						
						if(rs.next())
						{
						
							
							byte[] img = rs.getBytes("image_eleve");
							ImageIcon image=new ImageIcon(img);
							java.awt.Image im = image.getImage();
							java.awt.Image myImg = im.getScaledInstance(labimage.getWidth(), labimage.getHeight(), java.awt.Image.SCALE_SMOOTH);
							ImageIcon imggg = new ImageIcon(myImg);
							labimage.setIcon(imggg);
						}
						
						
					}else
					{
						
						txt_codeE.setText("");
						txt_nom.setText("");
						txt_prenom.setText("");
						txt_telephone.setText("");
						txt_pere.setText("");
						txt_PrenomGP.setText("");
						txt_NomM.setText("");
						txt_PrenomM.setText("");
						Niveau1.setSelected(false);
						Niveau2.setSelected(false);
						Niveau3.setSelected(false);
						comboBoxAc.removeAllItems();
						comboBoxAc2.removeAllItems();
						comboBoxAc3.removeAllItems();
						
					}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					
						
					
			
			}
		});
		panel_15.add(rech_Eleve);
		rech_Eleve.setColumns(10);
	}

}
