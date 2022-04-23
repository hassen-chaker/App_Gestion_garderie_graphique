package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import views.HomeView;

public class Adresse {
	
	private String id;
	private String numeroRue;
	private String libelle;
	private String ville;
	private String postal;
	private String gouvernorat;
		
	public Adresse(String id, String num, String designation, 
			String ville, String codePostal, String gouvernorat) {
		this.setId(id);
		this.numeroRue = num;
		this.libelle = designation;
		this.ville = ville;
		this.postal = codePostal;
		this.gouvernorat = gouvernorat;
	}
	
	public Adresse() {
		// TODO Auto-generated constructor stub
	}

	public String getNum() {
		return numeroRue;
	}
	public void setNum(String num) {
		this.numeroRue = num;
	}
	public String getDesignation() {
		return libelle;
	}
	public void setDesignation(String designation) {
		this.libelle = designation;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getCodePostal() {
		return postal;
	}
	public void setCodePostal(String codePostal) {
		this.postal = codePostal;
	}
	public String getGouvernorat() {
		return gouvernorat;
	}
	public void setGouvernorat(String gouvernorat) {
		this.gouvernorat = gouvernorat;
	}
	
	public static Adresse getAdresse(String id_adresse) throws SQLException
	{
		
		String MyRequest = "select * from adresse where Id_adresse='"+ id_adresse +"'";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		if(rs.next())
		{
			return new Adresse(rs.getString(1),rs.getString(2),
					rs.getString(3), rs.getString(4),rs.getString(5),
					rs.getString(6));
		}
		else
			return null;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return numeroRue + " " + libelle + " " + ville + " "+ postal +
				" " + gouvernorat;
	}
	
	
	public boolean add() throws SQLException {
		String MyRequest = "Insert into adresse values('"+
				this.id + "','"+this.getNum()+"','"+ getDesignation()+
				"', '"+getVille()+"','"+ getCodePostal()+"','"+
				getGouvernorat()+"')";
			if(HomeView.myConnection.executeUpdate(MyRequest)==0)
				return false;
			else
				return true;	
	}
	
	public boolean delete(String Id) throws SQLException {
		String MyRequest = "delete from adresse "
				+ " where Id_adresse='"+ Id +"'";
			if(HomeView.myConnection.executeUpdate(MyRequest)==0)
				return false;
			else
				return true;	
	}
	
	public boolean update() throws SQLException {
		String MyRequest = "UPDATE Adresse SET "+
			" numeroRue_adresse ='" + getNum() + "' ,"+
			" libelle_adresse ='" + getDesignation() + "' ,"+
			" ville_adresse ='" + getVille() + "' ,"+
			" postal_adresse ='" +getCodePostal() + "' ,"+
			" gouvernorat_adresse ='" + getGouvernorat() + "' "+
			" WHERE Id_adresse='"+ this.getId() +"';";	
			if(HomeView.myConnection.executeUpdate(MyRequest)==0)
				return false;
			else
				return true;
	}
	
	public ArrayList<Adresse> lister() throws SQLException {
		ArrayList<Adresse> listeAdresses = new ArrayList<Adresse>();
		String MyRequest = "select * from adresse";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{
			listeAdresses.add(new Adresse(rs.getString(1),
					rs.getString(2),rs.getString(3),rs.getString(4),
					rs.getString(5),rs.getString(6)));
		}
		return listeAdresses;
	}	
	
	
	public Adresse listerById(String Id) throws SQLException {
		Adresse Ad = new Adresse();
		String MyRequest = "select * from adresse where  Id_adresse='"+ Id+"'";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{
			Ad=new Adresse(rs.getString(1),
					rs.getString(2),rs.getString(3),rs.getString(4),
					rs.getString(5),rs.getString(6));
		}
		return Ad;
	}
	
}
