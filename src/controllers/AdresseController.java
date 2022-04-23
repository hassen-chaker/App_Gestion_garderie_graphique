package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Adresse;

public class AdresseController {
	public AdresseController() throws ClassNotFoundException, SQLException
	{
		
	}

	
	public boolean addAdresse(Adresse ad) throws SQLException
	{
		return ad.add();
	}
		
	public boolean updateAdresse(Adresse ad) throws SQLException
	{
		return ad.update();
	}
	public boolean deleteAdresse(String Id) throws SQLException
	{
		return new Adresse().delete(Id);
	}
	public ArrayList<Adresse> listerAdresse() throws SQLException
	{
		return new Adresse().lister();
	}
	public Adresse listerAdresseById(String Id) throws SQLException
	{
		return new Adresse().listerById(Id);
	}

}
