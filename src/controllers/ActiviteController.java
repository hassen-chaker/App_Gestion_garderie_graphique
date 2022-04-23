package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Activite;


public class ActiviteController {
{
		
	}

	
	public boolean addActivite(Activite a) throws SQLException
	{
		return a.add();
	}
		
	public boolean updateActivite(Activite a) throws SQLException
	{
		return a.update();
	}
	public boolean deleteActivite(String _cin) throws SQLException
	{
		return new Activite().delete(_cin);
	}
	public ArrayList<Activite> listerActivite() throws SQLException
	{
		return new Activite().lister();
	}
	public Activite listerActiviteByCode(String _code) throws SQLException
	{
		return new Activite().listerByCode(_code);
	}

}
