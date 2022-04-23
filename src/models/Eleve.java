package models;

import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import views.HomeView;


public class Eleve extends Personne {

	private String codeE;
	private String prenomGP;
	private String nomM;
	private String prenomM;
	private int niveauS;
	private Activite listeCoursS = new Activite();
	private Activite coursS2 = new Activite();
	private Activite coursS3 = new Activite();
	
	public Eleve(String nom, String prenom, String telephone, String pere, Date date_naissance, Adresse adresse,
			InputStream image, String codeE, String prenomGP, String nomM, String prenomM, int niveauS,
			Activite listeCoursS,Activite coursS2,Activite coursS3) {
		super(nom, prenom, telephone, pere, date_naissance, adresse, image);
		this.codeE = codeE;
		this.prenomGP = prenomGP;
		this.nomM = nomM;
		this.prenomM = prenomM;
		this.niveauS = niveauS;
		this.listeCoursS = listeCoursS;
		this.coursS2 = coursS2;
		this.coursS3 = coursS3;
	}
	public Eleve() {
		// TODO Auto-generated constructor stub
	}
	public String getCodeE() {
		return codeE;
	}
	public void setCodeE(String codeE) {
		this.codeE = codeE;
	}
	public String getPrenomGP() {
		return prenomGP;
	}
	public void setPrenomGP(String prenomGP) {
		this.prenomGP = prenomGP;
	}
	public String getNomM() {
		return nomM;
	}
	public void setNomM(String nomM) {
		this.nomM = nomM;
	}
	public String getPrenomM() {
		return prenomM;
	}
	public void setPrenomM(String prenomM) {
		this.prenomM = prenomM;
	}
	public int getNiveauS() {
		return niveauS;
	}
	public void setNiveauS(int niveauS) {
		this.niveauS = niveauS;
	}
	public Activite getListeCoursS() {
		return listeCoursS;
	}
	public void setListeCoursS(Activite listeCoursS) {
		this.listeCoursS = listeCoursS;
	}
	public Activite getCoursS2() {
		return coursS2;
	}
	public void setCoursS2(Activite coursS2) {
		this.coursS2 = coursS2;
	}
	public Activite getCoursS3() {
		return coursS3;
	}
	public void setCoursS3(Activite coursS3) {
		this.coursS3 = coursS3;
	}
	
	public static Eleve getEleve(String code_eleve) throws SQLException
	{
		
		String MyRequest = "select * from eleve where code_eleve='"+ code_eleve +"'";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		if(rs.next())
		{
			return new Eleve(
					rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6), 
					Adresse.getAdresse(rs.getString(7)),
					rs.getUnicodeStream(15),rs.getString(1),
					rs.getString(8),rs.getString(9),rs.getString(10),
					rs.getInt(11),Activite.getActivite(rs.getString(12)),Activite.getActivite(rs.getString(13)),Activite.getActivite(rs.getString(14)));
		}
		else
			return null;
		
	}
	
	@Override
	public boolean add() throws SQLException {
		
		String MyRequest = "Insert into eleve values('"+
				this.codeE + "','"+this.getNom()+"','"+ getPrenom()+
				"', "+getTelephone()+",'"+ getPere()+"','"+
				getDate_naissance()+"','"+ getAdresse().getId()+"','"+
				getPrenomGP()+"','"+ getNomM()+"','"+
				getPrenomM()+"','"+ getNiveauS()+"','"+
				getListeCoursS().getCode()+"','"+
				getCoursS2().getCode()+"','"+
				getCoursS3().getCode()+"','"+
				getImage()+"')";
			if(HomeView.myConnection.executeUpdate(MyRequest)==0)
				return false;
			else
				return true;	
	}
	
	@Override
	public boolean delete(String _codeE) throws SQLException {
		String MyRequest = "delete from eleve "
				+ " where code_eleve='"+ _codeE +"'";
			if(HomeView.myConnection.executeUpdate(MyRequest)==0)
				return false;
			else
				return true;	
	}
	
	@Override
	public boolean update() throws SQLException {
		String MyRequest = "UPDATE eleve SET "+
			" nom_eleve ='" + getNom() + "' ,"+
			" prenom_eleve ='" + getPrenom() + "' ,"+
			" NumTelephone ='" + getTelephone() + "' ,"+
			" pere_eleve ='" + getPere() + "' ,"+
			" date_naissance_eleve ='" +getDate_naissance() + "' ,"+
			" adresse_eleve ='" + getAdresse().getId() + "' ,"+
			" PrenomGP_eleve ='" + getPrenomGP() + "' ,"+
			" NomM_eleve ='" + getNomM() + "' ,"+
			" PrenomM_eleve ='" + getPrenomM() + "' ,"+
			" NiveauS_eleve ='" + getNiveauS() + "' ,"+
			" ListeCoursS_eleve ='" + getListeCoursS().getCode() + "' ,"+
			" coursS2_eleve ='" + getCoursS2().getCode() + "' ,"+
			" coursS3_eleve ='" + getCoursS3().getCode() + "' ,"+
			" image_eleve ='" + getImage() + "' "+
			" WHERE code_eleve='"+ this.getCodeE() +"';";		//this.cin
			if(HomeView.myConnection.executeUpdate(MyRequest)==0)
				return false;
			else
				return true;
	}
	
	
	public ArrayList<Eleve> lister() throws SQLException {
		ArrayList<Eleve> listeEleves = new ArrayList<Eleve>();
		String MyRequest = "select * from eleve";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{
			listeEleves.add(new Eleve(
					rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6), 
					Adresse.getAdresse(rs.getString(7)),
					rs.getUnicodeStream(15),rs.getString(1),
					rs.getString(8),rs.getString(9),rs.getString(10),
					rs.getInt(11),Activite.getActivite(rs.getString(12)),Activite.getActivite(rs.getString(13)),Activite.getActivite(rs.getString(14))));
		}
		return listeEleves;
	}	
	
	public Eleve listerByCode(String _codeE) throws SQLException {
		Eleve El = new Eleve();
		String MyRequest = "select * from eleve where  code_eleve='"+ _codeE+"'";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{
		
			
			El=new Eleve(
					rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6), 
					Adresse.getAdresse(rs.getString(7)),
					rs.getUnicodeStream(15),rs.getString(1),
					rs.getString(8),rs.getString(9),rs.getString(10),
					rs.getInt(11),Activite.getActivite(rs.getString(12)),Activite.getActivite(rs.getString(13)),Activite.getActivite(rs.getString(14)));
		}
		return El;
	}
}
