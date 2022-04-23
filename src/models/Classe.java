package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import views.HomeView;



public class Classe {
	
	private String codeC;
	private String nomC;
	private Eleve listeEleves = new Eleve() ;
	private Activite listeActivites = new Activite();
	private Animateur listeAnimateurs = new Animateur();
	
	public Classe(String codeC, String nomC, Eleve listeEleves, Activite listeActivites, Animateur listeAnimateurs) {
		super();
		this.codeC = codeC;
		this.nomC = nomC;
		this.listeEleves = listeEleves;
		this.listeActivites = listeActivites;
		this.listeAnimateurs = listeAnimateurs;
	}

	public Classe() {
		// TODO Auto-generated constructor stub
	}

	public String getCodeC() {
		return codeC;
	}

	public void setCodeC(String codeC) {
		this.codeC = codeC;
	}

	public String getNomC() {
		return nomC;
	}

	public void setNomC(String nomC) {
		this.nomC = nomC;
	}

	public Eleve getListeEleves() {
		return listeEleves;
	}

	public void setListeEleves(Eleve listeEleves) {
		this.listeEleves = listeEleves;
	}

	public Activite getListeActivites() {
		return listeActivites;
	}

	public void setListeActivites(Activite listeActivites) {
		this.listeActivites = listeActivites;
	}

	public Animateur getListeAnimateurs() {
		return listeAnimateurs;
	}

	public void setListeAnimateurs(Animateur listeAnimateurs) {
		this.listeAnimateurs = listeAnimateurs;
	}

	
	public static Classe getClasse(String code_classe) throws SQLException
	{
		
		String MyRequest = "select * from classe where codeC_classe='"+ code_classe +"'";
		ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
		
		if(rs.next())
		{
			return new Classe(rs.getString(1),
					rs.getString(2),Eleve.getEleve(rs.getString(3)),
					Activite.getActivite(rs.getString(4)),
					Animateur.getAnimateur(rs.getString(5))
					);
		}
		else
			return null;
		
	}
	
public boolean add() throws SQLException {
		
		String MyRequest = "Insert into classe values('"+
				this.codeC + "','"+this.getNomC()+"','"
				+ getListeEleves().getCodeE()+"','"+ 
				getListeActivites().getCode()+"','"+ 
				getListeAnimateurs().getCin()+"')";
			if(HomeView.myConnection.executeUpdate(MyRequest)==0)
				return false;
			else
				return true;	
	}

public boolean delete(String _codeC) throws SQLException {
	String MyRequest = "delete from classe "
			+ " where codeC_classe='"+ _codeC +"'";
		if(HomeView.myConnection.executeUpdate(MyRequest)==0)
			return false;
		else
			return true;	
}

public boolean update() throws SQLException {
	String MyRequest = "UPDATE classe SET "+
		" nomC_classe ='" + getNomC() + "' ,"+
		" listeEleves_classe ='" + getListeEleves().getCodeE() + "' ,"+
		" listeActivites_classe ='" + getListeActivites().getCode() + "' ,"+
		" listeAnimateurs_classe ='" + getListeAnimateurs().getCin() + "' "+
		" WHERE codeC_classe='"+ this.getCodeC() +"';";		//this.cin
		if(HomeView.myConnection.executeUpdate(MyRequest)==0)
			return false;
		else
			return true;
}

public ArrayList<Classe> lister() throws SQLException {
	ArrayList<Classe> listeClasses = new ArrayList<Classe>();
	String MyRequest = "select * from classe";
	ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
	
	while(rs.next())
	{
		listeClasses.add(new Classe(rs.getString(1),
				rs.getString(2),Eleve.getEleve(rs.getString(3)),
				Activite.getActivite(rs.getString(4)),
				Animateur.getAnimateur(rs.getString(5))
				));
	}
	return listeClasses;
}

public Classe listerByCode(String _codeE) throws SQLException {
	Classe C = new Classe();
	String MyRequest = "select * from classe where codeC_classe='"+ _codeE+"'";
	ResultSet rs = HomeView.myConnection.executeQuery(MyRequest);
	
	while(rs.next())
	{
	
		
		C=new Classe(rs.getString(1),
				rs.getString(2),Eleve.getEleve(rs.getString(3)),
				Activite.getActivite(rs.getString(4)),
				Animateur.getAnimateur(rs.getString(5))
				);
	}
	return C;
}
}

// ALTER TABLE classe add CONSTRAINT fk_Eleves FOREIGN KEY(listeEleves_classe) REFERENCES eleve(code_eleve);