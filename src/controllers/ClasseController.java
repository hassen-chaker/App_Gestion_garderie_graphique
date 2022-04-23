package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Animateur;
import models.Classe;
import models.MyConnection;

public class ClasseController {
	
	
	public ClasseController() throws ClassNotFoundException, SQLException
	{
		
	}

	
	public boolean addClasse(Classe c) throws SQLException
	{
		return c.add();
	}
		
	public boolean updateClasse(Classe c) throws SQLException
	{
		return c.update();
	}
	public boolean deleteClasse(String _code) throws SQLException
	{
		return new Classe().delete(_code);
	}
	public ArrayList<Classe> listerClasse() throws SQLException
	{
		return new Classe().lister();
	}
	public Classe listerClasseByCode(String _code) throws SQLException
	{
		return new Classe().listerByCode(_code);
	}
}
