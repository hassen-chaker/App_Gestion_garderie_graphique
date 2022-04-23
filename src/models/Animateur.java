package models;

import java.io.InputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.ImageIcon;

import com.mysql.cj.jdbc.Blob;
import com.mysql.cj.protocol.Resultset;

import controllers.AnimateurController;
import views.HomeView;

public class Animateur extends Personne {
	private String cin;
	

	public Animateur(String cin, String nom, String prenom, String pere,
			String tel, Date date_N, Adresse adresse,
			InputStream imageProfil) {
		super(nom, prenom, pere, tel, date_N, adresse, imageProfil);
		this.cin = cin;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public Animateur()
	{
		
	}

	public static Animateur getAnimateur(String _cin) throws SQLException
	{
		
		String MyRequest = "select * from animateur where cin_animateur='"+ _cin +"'";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		if(rs.next())
		{
			return new Animateur(rs.getString(1),
					rs.getString(2),rs.getString(3),rs.getString(4),
					rs.getString(5),rs.getDate(6), 
					Adresse.getAdresse(rs.getString(7)),
					rs.getUnicodeStream(8));
		}
		else
			return null;
		
	}
	@Override
	public boolean add() throws SQLException {
		
		String MyRequest = "Insert into animateur values('"+
				this.cin + "','"+this.getNom()+"','"+ getPrenom()+
				"', "+getTelephone()+",'"+ getPere()+"','"+
				getDate_naissance()+"','"+ getAdresse().getId()+"','"+ 
				getImage()+"')";
			if(HomeView.myConnection.executeUpdate(MyRequest)==0)
				return false;
			else
				return true;	
	}
	
	

	@Override
	public boolean delete(String _cin) throws SQLException {
		String MyRequest = "delete from animateur "
				+ " where cin_animateur='"+ _cin +"'";
			if(HomeView.myConnection.executeUpdate(MyRequest)==0)
				return false;
			else
				return true;	
	}

	@Override
	public boolean update() throws SQLException {
		String MyRequest = "UPDATE animateur SET "+
			" nom_animateur ='" + getNom() + "' ,"+
			" prenom_animateur ='" + getPrenom() + "' ,"+
			" NumTelephone ='" + getTelephone() + "' ,"+
			" pere_animateur ='" + getPere() + "' ,"+
			" date_naissance_animateur ='" +getDate_naissance() + "' ,"+
			" adresse_animateur ='" + getAdresse().getId() + "' ,"+
			" image_animateur ='" + getImage() + "' "+
			" WHERE cin_animateur='"+ this.getCin() +"';";		//this.cin
			if(HomeView.myConnection.executeUpdate(MyRequest)==0)
				return false;
			else
				return true;
	}

	
	

	public ArrayList<Animateur> lister() throws SQLException {
		ArrayList<Animateur> listeAnimateurs = new ArrayList<Animateur>();
		String MyRequest = "select * from animateur";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{
			listeAnimateurs.add(new Animateur(rs.getString(1),
					rs.getString(2),rs.getString(3),rs.getString(4),
					rs.getString(5),rs.getDate(6), 
					Adresse.getAdresse(rs.getString(7)),
					rs.getUnicodeStream(8)));
		}
		return listeAnimateurs;
	}	
	

	public Animateur listerByCin(String _cin) throws SQLException {
		Animateur An = new Animateur();
		String MyRequest = "select * from animateur where cin_animateur='"+ _cin+"'";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{
		
			
			An=new Animateur(rs.getString(1),
					rs.getString(2),rs.getString(3),rs.getString(4),
					rs.getString(5),rs.getDate(6), 
					Adresse.getAdresse(rs.getString(7)),
					rs.getUnicodeStream(8));
		}
		return An;
	}
	
}
