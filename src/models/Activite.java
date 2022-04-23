package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import views.HomeView;

public class Activite {

	private String code;
	private String designationA;
	private Animateur animateur; 
	private float fraisInscription ;
	private int niveau;
	
	public Activite(String code, String designationA, Animateur animateur, float fraisInscription, int niveau) {
		super();
		this.code = code;
		this.designationA = designationA;
		this.animateur = animateur;
		this.fraisInscription = fraisInscription;
		this.niveau = niveau;
	}
	public Activite()
	{
		
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignationA() {
		return designationA;
	}

	public void setDesignationA(String designationA) {
		this.designationA = designationA;
	}

	public Animateur getAnimateur() {
		return animateur;
	}

	public void setAnimateur(Animateur animateur) {
		this.animateur = animateur;
	}

	public float getFraisInscription() {
		return fraisInscription;
	}

	public void setFraisInscription(float fraisInscription) {
		this.fraisInscription = fraisInscription;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	@Override
	public String toString() {
		return "Activite [code=" + code + ", designationA=" + designationA + ", animateur=" + animateur
				+ ", fraisInscription=" + fraisInscription + ", niveau=" + niveau + "]";
	}
	
	public static Activite getActivite(String codeA) throws SQLException
	{
		
		String MyRequest = "select * from activite where code_activite='"+ codeA +"'";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		if(rs.next())
		{
			return new Activite(rs.getString(1),
					rs.getString(2),Animateur.getAnimateur(rs.getString(3)),rs.getFloat(4),
					rs.getInt(5));
		}
		else
			return null;
		
	}
	
	public boolean add() throws SQLException {
		
		String MyRequest = "Insert into activite values('"+
				this.code + "','"+this.getDesignationA()+"','"+ getAnimateur().getCin()+"','"+ 
				getFraisInscription()+"','"+ getNiveau()+"')";
			if(HomeView.myConnection.executeUpdate(MyRequest)==0)
				return false;
			else
				return true;	
	}
	
	public boolean delete(String _code) throws SQLException {
		String MyRequest = "delete from activite "
				+ " where code_activite='"+ _code +"'";
			if(HomeView.myConnection.executeUpdate(MyRequest)==0)
				return false;
			else
				return true;	
	}
	
	public boolean update() throws SQLException {
		String MyRequest = "UPDATE activite SET "+
			" designation_activite ='" + getDesignationA() + "' ,"+
			" animateur_activite ='" + getAnimateur().getCin() + "' ,"+
			" fraisInscription_activite ='" + getFraisInscription() + "', "+
			" niveau_activite ='" + getNiveau() + "' "+
			" WHERE code_activite='"+ this.getCode() +"';";		//this.cin
			if(HomeView.myConnection.executeUpdate(MyRequest)==0)
				return false;
			else
				return true;
	}
	
	public ArrayList<Activite> lister() throws SQLException {
		ArrayList<Activite> listeActivites = new ArrayList<Activite>();
		String MyRequest = "select * from activite";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{
			listeActivites.add(new Activite(rs.getString(1),
					rs.getString(2),Animateur.getAnimateur(rs.getString(3)),rs.getFloat(4),
					rs.getInt(5)));
		}
		return listeActivites;
	}
	
	public Activite listerByCode(String _code) throws SQLException {
		Activite Ac = new Activite();
		String MyRequest = "select * from activite where  code_activite='"+ _code+"'";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		while(rs.next())
		{
		
			
			Ac=new Activite(rs.getString(1),
					rs.getString(2),Animateur.getAnimateur(rs.getString(3)),rs.getFloat(4),
					rs.getInt(5));
		}
		return Ac;
	}
}
//