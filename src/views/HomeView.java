package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import models.Animateur;
import models.Eleve;
import models.MyConnection;

import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import controllers.AnimateurController;
import controllers.EleveController;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;

public class HomeView {

	private JFrame frmApplicationGestion;
	public static MyConnection myConnection = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeView window = new HomeView();
					window.frmApplicationGestion.setVisible(true);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public HomeView() throws ClassNotFoundException, SQLException {
		myConnection = new MyConnection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmApplicationGestion = new JFrame();
		frmApplicationGestion.setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\User\\Polytechnique\\4\u00E9me GI\\Semestre 1\\Java niveau2 Mr malek\\Mini-projet_Hassen_Tasnim\\Images\\imgGard.png"));
		frmApplicationGestion.setTitle("Application - Gestion Garderie");
		frmApplicationGestion.setBounds(100, 10, 1010, 803);
		frmApplicationGestion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmApplicationGestion.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Fichier");
//		mnNewMenu.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				AnimateurView window;
//				try {
//					window = new AnimateurView();
//					window.frmGestionAnimaateur.setVisible(true);
//				} catch (ClassNotFoundException | SQLException e1) {
//					JOptionPane.showMessageDialog(null, e1.getMessage());
//				}
//				
//			}
//		});
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("\u00C0 propos");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, " Une application permettant au directeur de la garderie de gérer son système \n d’information (gestion d’un ensemble d’élèves, d'animateurs, de classes \n et d'activités).","\u00C0 propos",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Fermer");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retour =JOptionPane.showConfirmDialog(null, " Voulez-vous vraiment quitter?","Quitter",JOptionPane.OK_CANCEL_OPTION);
				if(retour==0) { System.exit(JFrame.EXIT_ON_CLOSE);}
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Gestion");
//		mnNewMenu_1.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				try {
//					AddAndUpdateAnimateurVue window = new AddAndUpdateAnimateurVue();
//					window.frmAddupdateAnimateur.setVisible(true);
//				} catch (Exception ee) {
//					ee.printStackTrace();
//				}
//			}
//		});
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmGestionAnimateur = new JMenuItem("Gestion Animateurs");
		mntmGestionAnimateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AnimateurView window;
				try {
					window = new AnimateurView();
					window.frmGestionAnimaateur.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
		mnNewMenu_1.add(mntmGestionAnimateur);
		
		JMenuItem mntmGestionAdresse = new JMenuItem("Gestion Adresse");
		mntmGestionAdresse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdresseView window;
				try {
					window = new AdresseView();
					window.frmGestionAdresse.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		mnNewMenu_1.add(mntmGestionAdresse);
		
		JMenuItem mntmGestionActivite = new JMenuItem("Gestion Activite");
		mntmGestionActivite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ActiviteView window;
				try {
					window = new ActiviteView();
					window.frmGestionActivite.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		mnNewMenu_1.add(mntmGestionActivite);
		
		JMenuItem mntmGestionEleve = new JMenuItem("Gestion \u00C9l\u00E8ve");
		mntmGestionEleve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EleveView window;
				try {
					window = new EleveView();
					window.frmGestionEleve.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		mnNewMenu_1.add(mntmGestionEleve);
		
		JMenuItem mntmGestionClasse = new JMenuItem("Gestion Classe");
		mntmGestionClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClasseView window;
				try {
					window = new ClasseView();
					window.frmGestionClasse.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		mnNewMenu_1.add(mntmGestionClasse);
		
		JMenu mnImprimer = new JMenu("Imprimer");
		menuBar.add(mnImprimer);
		
		JMenuItem mntmListeAnimateurs = new JMenuItem("Liste Animateurs");
		mntmListeAnimateurs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		       
		        BufferedWriter b;
				try {
					AnimateurController C = new AnimateurController();
			        ArrayList<Animateur> liste = C.listerAnimateur();
		        b = new BufferedWriter(
						new FileWriter("Animateurs.txt"));
		        b.write("\t\t\t\t\t\t\t\t Liste des animateurs");
		        b.newLine();
		        b.newLine();
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
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				finally
				{
					JOptionPane.showMessageDialog(null, " La liste des animateurs prête ","Info",JOptionPane.INFORMATION_MESSAGE);
//					System.out.println("fin d'ecriture");
				}
			}
		});
		mnImprimer.add(mntmListeAnimateurs);
		
		JMenuItem mntmListeEleves = new JMenuItem("Liste \u00C9l\u00E8ves");
		mntmListeEleves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 BufferedWriter b;
					try {
						EleveController E = new EleveController();
				        ArrayList<Eleve> liste = E.listerEleve();
			        b = new BufferedWriter(
							new FileWriter("Elèves.txt"));
			        b.write("\t\t\t\t\t\t\t\t Liste des élèves");
			        b.newLine();
			        b.newLine();
			        for (int i=0;i<liste.size();i++)
			        {
			        	Eleve currentEleve = liste.get(i);
			        b.write("CIN: "+currentEleve.getCodeE()+"\t\t Nom: "+currentEleve.getNom()+
			        		"\t\t Prénom: "+currentEleve.getPrenom()+"\t\t Téléphone: "+currentEleve.getTelephone()+
			        		"\t\t Père: "+currentEleve.getPere()+"\t\t Date Naissance: "+currentEleve.getDate_naissance()+
			        		"\t\t NomM: "+currentEleve.getNomM()+"\t\t PenomM: "+currentEleve.getPrenomM()+
			        		"\t\t Niveau: "+currentEleve.getNiveauS()+"\t\t Cour1: "+currentEleve.getListeCoursS().toString()+
			        		"\t\t Cour2: "+currentEleve.getCoursS2().toString()+"\t\t Cour3: "+currentEleve.getCoursS3().toString()+
			        		"\t\t Adresse: "+currentEleve.getAdresse());
					b.newLine();
			        }
					b.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
					finally
					{
						JOptionPane.showMessageDialog(null, " La liste des élèves prête ","Info",JOptionPane.INFORMATION_MESSAGE);
//						System.out.println("fin d'ecriture");
					}
				
			}
		});
		mnImprimer.add(mntmListeEleves);
		
		JMenu mnPayement = new JMenu("Payement");
		mnPayement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PayementView window;
				try {
					window = new PayementView();
					window.frmPayement.setVisible(true);
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		
		menuBar.add(mnPayement);
		frmApplicationGestion.getContentPane().setLayout(null);
		
		JButton btnQuitter = new JButton("Quitter");
		btnQuitter.setBackground(new Color(255, 69, 0));
		btnQuitter.setFont(new Font("Calibri", Font.BOLD, 16));
		btnQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int retour =JOptionPane.showConfirmDialog(null, " Voulez-vous vraiment quitter?","Quitter",JOptionPane.OK_CANCEL_OPTION);
				if(retour==0) { System.exit(JFrame.EXIT_ON_CLOSE);}
				//System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		
		JButton btnAPropos = new JButton("\u00C0 propos");
		btnAPropos.setBackground(new Color(255, 69, 0));
		btnAPropos.setFont(new Font("Calibri", Font.BOLD, 16));
		btnAPropos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, " Une application permettant au directeur de la garderie de gérer son système \n d’information (gestion d’un ensemble d’élèves, d'animateurs, de classes \n et d'activités).","\u00C0 propos",JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnAPropos.setForeground(new Color(255, 255, 255));
		btnAPropos.setBounds(521, 667, 120, 37);
		frmApplicationGestion.getContentPane().add(btnAPropos);
		btnQuitter.setForeground(new Color(255, 255, 255));
		btnQuitter.setBounds(686, 667, 120, 37);
		frmApplicationGestion.getContentPane().add(btnQuitter);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("E:\\User\\Polytechnique\\4\u00E9me GI\\Semestre 1\\Java niveau2 Mr malek\\Mini-projet_Hassen_Tasnim\\images\\Accueil final.png"));
		label.setBounds(0, 0, 1000, 773);
		frmApplicationGestion.getContentPane().add(label);
	}
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
