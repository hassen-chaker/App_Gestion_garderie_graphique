package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	private Connection c =null;
	private Statement stmt;
	
	public MyConnection() throws ClassNotFoundException, SQLException
	{
		// chargement du pilote sp�cifique au pilote de BD
		// (soit ODBC ou Driver personnalis� du SGBD):
    	Class.forName("com.mysql.cj.jdbc.Driver");	    	
    	
    	// D�finition du contexte de la connection (URL de la connection):
    	String url="jdbc:mysql://localhost:3306/gestiongarderie?"
    			+ "serverTimezone=UTC";
    	// r�cuperer une r�ference vers la connexion 
    	// � la base de donn�es (source de donn�es) en question :
    	c=DriverManager.getConnection(url,"root","");
	}
	public Connection getC() {
		return c;
	}
	
	public int executeUpdate(String request) throws SQLException
	{
		stmt = c.createStatement();
		return stmt.executeUpdate(request);
		
	}
	
	public ResultSet executeQuery(String request) throws SQLException
	{
		stmt = c.createStatement();
		return stmt.executeQuery(request);
		
	}
	
}
