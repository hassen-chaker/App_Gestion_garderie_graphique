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
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Font;

public class PayementView {

	JFrame frmPayement;
	private JTextField rech_Eleve;
	private JTable table;
	
	private DefaultTableModel model = new DefaultTableModel();
	private String selectedCode=null;
	private JComboBox comboBox; 
	
	private String s;
	private JTextField txt_Montant;
	private JTextField Txt_cour1;
	private JTextField Txt_cour2;
	private JTextField Txt_cour3;
	private JTextField txt_fc1;
	private JTextField txt_fc2;
	private JTextField txt_fc3;
	private JTextField txt_Niveau;
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
	public PayementView() throws ClassNotFoundException, SQLException{
		initialize();
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
	
//	private void remplirAdresse() 
//	{ 
////		
//		try {
//		String MyRequest = "select * from adresse";
//		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
//		
//		while(rs.next())
//		{
//
//			comboBoxAd.addItem(rs.getString("Id_adresse"));
//		}
//		
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
//	private void remplirActivite(int niveau) 
//	{ 
////		
//		try {
//		String MyRequest = "select * from activite where niveau_activite='"+ niveau +"'";
//		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
//		
//		while(rs.next())
//		{
//
//			comboBoxAc.addItem(rs.getString("code_activite"));
//			comboBoxAc2.addItem(rs.getString("code_activite"));
//			comboBoxAc3.addItem(rs.getString("code_activite"));			
//		}
//		
//		}catch (Exception e) {
//			// TODO: handle exception
//		}
//	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPayement = new JFrame();
		frmPayement.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\User\\Polytechnique\\4\u00E9me GI\\Semestre 1\\Java niveau2 Mr malek\\Mini-projet_Hassen_Tasnim\\Images\\imgGard.png"));
		frmPayement.setTitle("Payement");
		frmPayement.addWindowFocusListener(new WindowFocusListener() {
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
		frmPayement.setBounds(100, 100, 550, 601);//450 300
		frmPayement.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		panel.setForeground(new Color(255, 255, 255));
		frmPayement.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblInterfaceEleve = new JLabel("Interface Payement");
		lblInterfaceEleve.setForeground(new Color(255, 255, 255));
		lblInterfaceEleve.setFont(new Font("Calibri", Font.BOLD, 25));
		lblInterfaceEleve.setBackground(new Color(255, 255, 255));
		panel.add(lblInterfaceEleve);
		
		JPanel panel_1 = new JPanel();
		frmPayement.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_14 = new JPanel();
		panel_14.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Liste \u00C9l\u00E8ves", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		
		JPanel panel_3_1 = new JPanel();
		panel_1.add(panel_3_1);
		panel_3_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_3_1.add(panel_2);
		
		JPanel panel_15 = new JPanel();
		panel_2.add(panel_15);
		panel_15.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Choisir \u00C9l\u00E8ve", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel lblNumE = new JLabel("Num\u00E9ro \u00C9l\u00E8ve");
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
						
						
						float S=0;
						
						 S=S+E.getListeCoursS().getFraisInscription()+E.getCoursS2().getFraisInscription()+E.getCoursS3().getFraisInscription();
						 
						 txt_Montant.setText(""+S+"dt");
						 
						 if(E!=null) {
						 Txt_cour1.setText(""+E.getListeCoursS().getCode());
						 Txt_cour2.setText(""+E.getCoursS2().getCode());
						 Txt_cour3.setText(""+E.getCoursS3().getCode());
						 
						 txt_fc1.setText(""+E.getListeCoursS().getFraisInscription()+" dt");
						 txt_fc2.setText(""+E.getCoursS2().getFraisInscription()+" dt");
						 txt_fc3.setText(""+E.getCoursS3().getFraisInscription()+" dt");
						 
						 txt_Niveau.setText(""+E.getNiveauS()+"");
						 }else {
						 txt_Montant.setText("");
						 
						 Txt_cour1.setText("");
						 Txt_cour2.setText("");
						 Txt_cour3.setText("");
						 
						 txt_fc1.setText("");
						 txt_fc2.setText("");
						 txt_fc3.setText("");
						 
						 txt_Niveau.setText("");
						 }
						// lbl_cour1 = new JLabel(""+E.getListeCoursS().getFraisInscription()+"");
						 
//					if(E!= null) {
//						txt_codeE.setText(E.getCodeE());
//						txt_nom.setText(E.getNom());
//						txt_prenom.setText(E.getPrenom());
//						txt_telephone.setText(E.getTelephone());
//						txt_pere.setText(E.getPere());
//	
//						Date date2=E.getDate_naissance();
//						txt_datenaissance.setDate(date2);
//						
//						comboBoxAd.setSelectedItem(E.getAdresse().getId());
//						
//						txt_PrenomGP.setText(E.getPrenomGP());
//						txt_NomM.setText(E.getNomM());
//						txt_PrenomM.setText(E.getPrenomM());
//						
//						if(E.getNiveauS()==1) {
//							Niveau1.setSelected(true);
//							Niveau2.setSelected(false);
//							Niveau3.setSelected(false);
//							comboBoxAc.removeAllItems();
//							comboBoxAc2.removeAllItems();
//							comboBoxAc3.removeAllItems();
//							remplirActivite(1); 
//							
//							}else if (E.getNiveauS()==2) {
//								Niveau2.setSelected(true);
//								Niveau1.setSelected(false);
//								Niveau3.setSelected(false);
//								comboBoxAc.removeAllItems();
//								comboBoxAc2.removeAllItems();
//								comboBoxAc3.removeAllItems();
//								remplirActivite(2); 
//							}else
//								{Niveau3.setSelected(true);
//								Niveau1.setSelected(false);
//								Niveau2.setSelected(false);
//								comboBoxAc.removeAllItems();
//								comboBoxAc2.removeAllItems();
//								comboBoxAc3.removeAllItems();
//								remplirActivite(3); }
//						
//						comboBoxAc.setSelectedItem(E.getListeCoursS().getCode());
//						comboBoxAc2.setSelectedItem(E.getCoursS2().getCode());
//						comboBoxAc3.setSelectedItem(E.getCoursS3().getCode());
//						
//						String MyRequest = "select * from eleve where  code_eleve='"+ E.getCodeE()+"'";
//						ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
//						
//						if(rs.next())
//						{
//						
//							
//							byte[] img = rs.getBytes("image_eleve");
//							ImageIcon image=new ImageIcon(img);
//							java.awt.Image im = image.getImage();
//							java.awt.Image myImg = im.getScaledInstance(labimage.getWidth(), labimage.getHeight(), java.awt.Image.SCALE_SMOOTH);
//							ImageIcon imggg = new ImageIcon(myImg);
//							labimage.setIcon(imggg);
//						}
//						
//						
//					}else
//					{
//						
//						txt_codeE.setText("");
//						txt_nom.setText("");
//						txt_prenom.setText("");
//						txt_telephone.setText("");
//						txt_pere.setText("");
//						txt_PrenomGP.setText("");
//						txt_NomM.setText("");
//						txt_PrenomM.setText("");
//						Niveau1.setSelected(false);
//						Niveau2.setSelected(false);
//						Niveau3.setSelected(false);
//						comboBoxAc.removeAllItems();
//						comboBoxAc2.removeAllItems();
//						comboBoxAc3.removeAllItems();
//						
//					}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
					
						
					
			
			}
		});
		panel_15.add(rech_Eleve);
		rech_Eleve.setColumns(10);
		
		JLabel lblMontant = new JLabel("Montant \u00E0 payer");
		panel_2.add(lblMontant);
		
		txt_Montant = new JTextField();
		panel_2.add(txt_Montant);
		txt_Montant.setColumns(10);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "D\u00E9tails activit\u00E9s \u00E9l\u00E8ve", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3_1.add(panel_4);
		
		JPanel panel_5_2 = new JPanel();
		panel_4.add(panel_5_2);
		
		JLabel lblNiveau = new JLabel("Niveau :");
		panel_5_2.add(lblNiveau);
		
		txt_Niveau = new JTextField();
		txt_Niveau.setColumns(10);
		txt_Niveau.setBackground(SystemColor.menu);
		panel_5_2.add(txt_Niveau);
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		
		JLabel lblLes = new JLabel("Liste des cours suivi :");
		panel_5.add(lblLes);
		
		Txt_cour1 = new JTextField();
		panel_5.add(Txt_cour1);
		Txt_cour1.setBackground(SystemColor.menu);
		Txt_cour1.setColumns(10);
		
		Txt_cour2 = new JTextField();
		panel_5.add(Txt_cour2);
		Txt_cour2.setColumns(10);
		Txt_cour2.setBackground(SystemColor.menu);
		
		Txt_cour3 = new JTextField();
		panel_5.add(Txt_cour3);
		Txt_cour3.setColumns(10);
		Txt_cour3.setBackground(SystemColor.menu);
		
		JPanel panel_5_1 = new JPanel();
		panel_4.add(panel_5_1);
		
		JLabel lblFraisInscription = new JLabel("Frais Inscription  :");
		panel_5_1.add(lblFraisInscription);
		
		txt_fc1 = new JTextField();
		txt_fc1.setColumns(10);
		txt_fc1.setBackground(SystemColor.menu);
		panel_5_1.add(txt_fc1);
		
		txt_fc2 = new JTextField();
		txt_fc2.setColumns(10);
		txt_fc2.setBackground(SystemColor.menu);
		panel_5_1.add(txt_fc2);
		
		txt_fc3 = new JTextField();
		txt_fc3.setColumns(10);
		txt_fc3.setBackground(SystemColor.menu);
		panel_5_1.add(txt_fc3);
	}

}
