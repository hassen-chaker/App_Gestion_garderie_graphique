package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import models.Animateur;
import models.MyConnection;

public class AnimateurController {
	
	
	public AnimateurController() throws ClassNotFoundException, SQLException
	{
		
	}

	
	public boolean addAnimateur(Animateur a) throws SQLException
	{
		return a.add();
	}
		
	public boolean updateAnimateur(Animateur a) throws SQLException
	{
		return a.update();
	}
	public boolean deleteAnimateur(String _cin) throws SQLException
	{
		return new Animateur().delete(_cin);
	}
	public ArrayList<Animateur> listerAnimateur() throws SQLException
	{
		return new Animateur().lister();
	}
	public Animateur listerAnimateurByCin(String _cin) throws SQLException
	{
		return new Animateur().listerByCin(_cin);
	}
}
