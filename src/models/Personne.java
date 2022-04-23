package models;

import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Blob;

public abstract class Personne {
	private String nom;
	private String prenom;
	private String telephone;
	private String pere;	
	private Date date_naissance;
	private Adresse adresse;
	private InputStream image;
	
	
	public Personne(String nom, String prenom, String telephone,  
			String pere, Date date_naissance, Adresse adresse,
			InputStream image) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.pere = pere;
		this.telephone = telephone;
		this.date_naissance = date_naissance;
		this.adresse = adresse;
		this.image = image;
	}
	public Personne()
	{
		
	}
	public  abstract  boolean add()  throws SQLException ;
	public  abstract  boolean delete(String _cin) throws SQLException ;
	//public  abstract  Animateur listerByCin(String _cin) throws SQLException ;
	public  abstract  boolean update() throws SQLException ;
	//public  abstract  ArrayList<Animateur> lister() throws SQLException ;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getPere() {
		return pere;
	}
	public void setPere(String pere) {
		this.pere = pere;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}
	public InputStream getImage() {
		return image;
	}
	public void setImage(InputStream image) {
		this.image = image;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	
	
	

}
