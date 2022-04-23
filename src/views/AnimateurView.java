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
import models.Adresse;
import models.Animateur;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

public class AnimateurView {

	JFrame frmGestionAnimaateur;
	private JTextField txt_cin;
	private JTextField txt_nom;
	private JTextField txt_prenom;
	private JTextField txt_pere;
	private JTextField txt_telephone;
	private JTextField rech_animateur;
	private JTable table;
	
	private DefaultTableModel model = new DefaultTableModel();
	private String selectedCIN=null;
	private JComboBox comboBox; 
	private JComboBox comboBoxAd;
	
	private String s;
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
	 */
	public AnimateurView() throws ClassNotFoundException, SQLException{
		initialize();
		remplirAdresse();
		remplirJTable();
	}

	private void remplirJTable() throws SQLException, ClassNotFoundException
	{ 
		model = new DefaultTableModel();
		model.addColumn("CIN");
		model.addColumn("Nom");
		model.addColumn("Prénom");
		model.addColumn("Téléphone");
		model.addColumn("Père");
		model.addColumn("Date Naissance");
		model.addColumn("Adresse");
		model.addColumn("Image");
	        AnimateurController C = new AnimateurController();
	        ArrayList<Animateur> liste = C.listerAnimateur();
	        for (int i=0;i<liste.size();i++)
	        {
	        Animateur currentAnimateur = liste.get(i);
	        model.addRow(new Object[]{currentAnimateur.getCin(),
	        currentAnimateur.getNom(), currentAnimateur.getPrenom(),
	        currentAnimateur.getTelephone(),
	        currentAnimateur.getPere(),
	        currentAnimateur.getDate_naissance().toString(),
	        currentAnimateur.getAdresse().toString(),
	        currentAnimateur.getImage()});
	        }
	        table.setModel(model);
	}
	
	private void Writer() throws SQLException, ClassNotFoundException
	{ 
	        AnimateurController C = new AnimateurController();
	        ArrayList<Animateur> liste = C.listerAnimateur();
	       
	        BufferedWriter b;
			try {
	        b = new BufferedWriter(
					new FileWriter("Animateurs.txt"));
	        for (int i=0;i<liste.size();i++)
	        {
	        Animateur currentAnimateur = liste.get(i);
	        b.write("CIN: "+currentAnimateur.getCin()+"\t\t Nom: "+currentAnimateur.getNom()+
	        		"\t\t Prénom: "+currentAnimateur.getPrenom()+"\t\t Téléphone: "+currentAnimateur.getTelephone()+
	        		"\t\t Père: "+currentAnimateur.getPere()+"\t\t Date Naissance: "+currentAnimateur.getDate_naissance()+
	        		"\t\t Adresse: "+currentAnimateur.getAdresse());
			b.newLine();
	        }
			b.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			finally
			{
				JOptionPane.showMessageDialog(null, " fin d'ecriture","Info",JOptionPane.INFORMATION_MESSAGE);
//				System.out.println("fin d'ecriture");
			}
	       

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
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionAnimaateur = new JFrame();
		frmGestionAnimaateur.setTitle("Gestion Animaateur");
		frmGestionAnimaateur.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\User\\Polytechnique\\4\u00E9me GI\\Semestre 1\\Java niveau2 Mr malek\\Mini-projet_Hassen_Tasnim\\Images\\imgGard.png"));
		frmGestionAnimaateur.addWindowFocusListener(new WindowFocusListener() {
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
		frmGestionAnimaateur.setBounds(100, 100, 950, 400);//450 300
		frmGestionAnimaateur.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 128));
		frmGestionAnimaateur.getContentPane().add(panel, BorderLayout.NORTH);
		
		JLabel lblInterfaceAnimateur = new JLabel("Interface Animateur");
		lblInterfaceAnimateur.setForeground(new Color(255, 255, 255));
		lblInterfaceAnimateur.setFont(new Font("Calibri", Font.BOLD, 25));
		lblInterfaceAnimateur.setBackground(new Color(255, 255, 255));
		panel.add(lblInterfaceAnimateur);
		
		JPanel panel_1 = new JPanel();
		frmGestionAnimaateur.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Add-Update", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_2.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new GridLayout(1, 1, 0, 0));
		
		JPanel panel_18 = new JPanel();
		panel_12.add(panel_18);
		panel_18.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_18.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblCin = new JLabel("Cin");
		panel_4.add(lblCin);
		
		txt_cin = new JTextField();
		panel_4.add(txt_cin);
		txt_cin.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_18.add(panel_5);
		panel_5.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNom = new JLabel("Nom");
		panel_5.add(lblNom);
		
		txt_nom = new JTextField();
		panel_5.add(txt_nom);
		txt_nom.setColumns(10);
		
		JPanel panel_6 = new JPanel();
		panel_18.add(panel_6);
		panel_6.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		panel_6.add(lblPrnom);
		
		txt_prenom = new JTextField();
		panel_6.add(txt_prenom);
		txt_prenom.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_18.add(panel_7);
		panel_7.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblPrnomPre = new JLabel("Pr\u00E9nom P\u00E9re");
		panel_7.add(lblPrnomPre);
		
		txt_pere = new JTextField();
		panel_7.add(txt_pere);
		txt_pere.setColumns(10);
		
		JPanel panel_8 = new JPanel();
		panel_18.add(panel_8);
		panel_8.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblTlephone = new JLabel("T\u00E9lephone");
		panel_8.add(lblTlephone);
		
		txt_telephone = new JTextField();
		panel_8.add(txt_telephone);
		txt_telephone.setColumns(10);
		
		JPanel panel_9 = new JPanel();
		panel_18.add(panel_9);
		panel_9.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblDateNaissance = new JLabel("Date Naissance");
		panel_9.add(lblDateNaissance);
		
		JDateChooser txt_datenaissance = new JDateChooser();
		panel_9.add(txt_datenaissance);
		
		
		JPanel panel_10 = new JPanel();
		panel_18.add(panel_10);
		panel_10.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblAdresse = new JLabel("Adresse");
		panel_10.add(lblAdresse);
		
		comboBoxAd = new JComboBox();
		
		comboBoxAd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
			}
		});
		panel_10.add(comboBoxAd);
		
		JPanel panel_16 = new JPanel();
		panel_12.add(panel_16);
		panel_16.setLayout(new GridLayout(0, 1, 0, 0));
		
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
		btnParcourir.setBackground(new Color(176, 196, 222));
		btnParcourir.setForeground(new Color(255, 255, 255));
		btnParcourir.setFont(new Font("Calibri", Font.BOLD, 13));
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
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewButton.setBackground(new Color(0, 128, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( ( (!txt_cin.getText().equals("")) && !txt_nom.getText().equals("")
						&& !txt_prenom.getText().equals("")	&& !txt_telephone.getText().equals("")
						&& !txt_pere.getText().equals("")  )

						) {
					if((Integer.parseInt(txt_cin.getText())>=10000000) && (Integer.parseInt(txt_cin.getText())<=99999999)) {
				
						if((Integer.parseInt(txt_telephone.getText())>=10000000) && (Integer.parseInt(txt_telephone.getText())<=99999999)) {
						try {
					//instancier un animateur à ajouter dans la base d données:
					Animateur newAnimateur = new Animateur();
					newAnimateur.setCin(txt_cin.getText());
					newAnimateur.setNom(txt_nom.getText());
					newAnimateur.setPrenom(txt_prenom.getText());
					newAnimateur.setTelephone(txt_telephone.getText());
					newAnimateur.setPere(txt_pere.getText());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(txt_datenaissance.getDate());
					Date newDate = Date.valueOf(date);
					newAnimateur.setDate_naissance(newDate);
					newAnimateur.setAdresse(Adresse.getAdresse(comboBoxAd.getSelectedItem().toString()));
				
					//newAnimateur.setAdresse(Adresse.getAdresse(txt_Adresse.getText()));
					//comboBoxAd.setSelectedItem(A.getAdresse().getId());
					try {
						InputStream imgg=new FileInputStream(new File(s));
						newAnimateur.setImage(imgg);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					//newAnimateur.setImage(txt_image.getText());
					
					//instancier le controleur:
					AnimateurController ac = new AnimateurController();
					if(ac.addAnimateur(newAnimateur))
					{
						JOptionPane.showMessageDialog(null, "Animateur ajouté "
								+ " avec succès.");
					}
					
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Animateur non ajouté"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Animateur non ajouté"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					}
				
						}else {
							JOptionPane.showMessageDialog(null, " Numéro Télèphone incorrect","Attention",JOptionPane.WARNING_MESSAGE);
						}
					}else {
					JOptionPane.showMessageDialog(null, " Numéro cin incorrect","Attention",JOptionPane.WARNING_MESSAGE);
				}
				
			}else {
				JOptionPane.showMessageDialog(null, " Remplir les champs vides","Attention",JOptionPane.WARNING_MESSAGE);
			}
			}
		});
		panel_13.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewButton_1.setBackground(new Color(0, 0, 128));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if( ( (!txt_cin.getText().equals("")) && !txt_nom.getText().equals("")
						&& !txt_prenom.getText().equals("")	&& !txt_telephone.getText().equals("")
						&& !txt_pere.getText().equals(""))
						
						) {
					if((Integer.parseInt(txt_cin.getText())>=10000000) && (Integer.parseInt(txt_cin.getText())<=99999999)) {
						
						if((Integer.parseInt(txt_telephone.getText())>=10000000) && (Integer.parseInt(txt_telephone.getText())<=99999999)) {
				try {
					//instancier un animateur à modifié dans la base d données:
					Animateur newAnimateur = new Animateur();
					newAnimateur.setCin(txt_cin.getText());
					newAnimateur.setNom(txt_nom.getText());
					newAnimateur.setPrenom(txt_prenom.getText());
					newAnimateur.setTelephone(txt_telephone.getText());
					newAnimateur.setPere(txt_pere.getText());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String date = sdf.format(txt_datenaissance.getDate());
					Date newDate = Date.valueOf(date);
					newAnimateur.setDate_naissance(newDate);
					//newAnimateur.setAdresse(Adresse.getAdresse(txt_Adresse.getText()));
//					newAnimateur.setImage(txt_image.getText());
					newAnimateur.setAdresse(Adresse.getAdresse(comboBoxAd.getSelectedItem().toString()));
					//instancier le controleur:
					AnimateurController ac = new AnimateurController();
					if(ac.updateAnimateur(newAnimateur))
					{
						JOptionPane.showMessageDialog(null, "Animateur modifié "
								+ " avec succès.");
					}
					
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "Animateur non modifié"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					} catch (ClassNotFoundException e1) {
						JOptionPane.showMessageDialog(null, "Animateur non modifié"
								+ "  suite à cette erreur : \n"+ e1.getMessage());					
					}
						}else {
							JOptionPane.showMessageDialog(null, " Numéro Télèphone incorrect","Attention",JOptionPane.WARNING_MESSAGE);
						}
					}else {
					JOptionPane.showMessageDialog(null, " Numéro cin incorrect","Attention",JOptionPane.WARNING_MESSAGE);
				}
				}else {
					JOptionPane.showMessageDialog(null, " Remplir les champs vides","Attention",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		panel_13.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Supprimer");
		btnNewButton_2.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewButton_2.setForeground(new Color(255, 255, 255));
		btnNewButton_2.setBackground(new Color(255, 0, 0));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//on recupere le cin de l'animateur à supprimer:
				if(selectedCIN == null)
				{
					JOptionPane.showMessageDialog(null,
							"Veuillez selectionner le numéro CIN de l'animateur !!");
				}
				else
				{
					
					try {
						AnimateurController ac = new AnimateurController();
						if(ac.deleteAnimateur(selectedCIN))
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
		btnNewButton_3.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewButton_3.setForeground(new Color(255, 255, 255));
		btnNewButton_3.setBackground(new Color(255, 215, 0));
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
				selectedCIN = (String)table.getValueAt(table.getSelectedRow(),
						table.getSelectedColumn());
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128), 1, true), "Rechercher", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.add(panel_15, BorderLayout.NORTH);
		
		JLabel lblCinAnimateur = new JLabel("Cin Animateur");
		panel_15.add(lblCinAnimateur);
		
		rech_animateur = new JTextField();
		rech_animateur.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
					AnimateurController ac;
					try {
						ac = new AnimateurController();
						Animateur A=new Animateur();
						String cin=rech_animateur.getText();
						A=ac.listerAnimateurByCin(cin);
						
					if(A!= null) {
						txt_cin.setText(A.getCin());
						txt_nom.setText(A.getNom());
						txt_prenom.setText(A.getPrenom());
						txt_telephone.setText(A.getTelephone());
						txt_pere.setText(A.getPere());
	
						Date date2=A.getDate_naissance();
						txt_datenaissance.setDate(date2);
						
						//txt_Adresse.setText(A.getAdresse().getId());
						comboBoxAd.setSelectedItem(A.getAdresse().getId());
						//txt_image.set(A.getImage());
						
						//labimage.setIcon((Icon) A.getImage());
						
						
						String MyRequest = "select * from animateur where  cin_animateur='"+ A.getCin()+"'";
						ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
						
						if(rs.next())
						{
						
							
							byte[] img = rs.getBytes("image_animateur");
							ImageIcon image=new ImageIcon(img);
							java.awt.Image im = image.getImage();
							java.awt.Image myImg = im.getScaledInstance(labimage.getWidth(), labimage.getHeight(), java.awt.Image.SCALE_SMOOTH);
							ImageIcon imggg = new ImageIcon(myImg);
							labimage.setIcon(imggg);
						}
						
						
					}else
					{
						txt_cin.setText("");
						txt_nom.setText("");
						txt_prenom.setText("");
						txt_telephone.setText("");
						txt_pere.setText("");
//						comboBoxAd.setSelectedItem("selected");
					//	txt_image.setText("");
						
					}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					
						
					
			
			}
		});
		panel_15.add(rech_animateur);
		rech_animateur.setColumns(10);
	}

}
