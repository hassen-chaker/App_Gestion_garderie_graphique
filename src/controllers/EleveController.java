package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Eleve;


public class EleveController {

	public EleveController() throws ClassNotFoundException, SQLException
	{
		
	}

	
	public boolean addEleve(Eleve e) throws SQLException
	{
		return e.add();
	}
		
	public boolean updateEleve(Eleve e) throws SQLException
	{
		return e.update();
	}
	public boolean deleteEleve(String _codeE) throws SQLException
	{
		return new Eleve().delete(_codeE);
	}
	public ArrayList<Eleve> listerEleve() throws SQLException
	{
		return new Eleve().lister();
	}
	public Eleve listerEleveByCode(String _codeE) throws SQLException
	{
		return new Eleve().listerByCode(_codeE);
	}
}
