/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.sql.*;

/**
 *
 * @author not
 */
public class PersonDB {
    
     
	private ArrayList personsList;

	private Connection con=null;
        private PreparedStatement ps = null;
        private ResultSet resultSet=null;

//***************************************************************************
	public PersonDB()
	{
		personsList = new ArrayList();
		initConn();
	}

//****************************************************************************
	public void initConn()
	{
		try
		{
                    Class.forName("org.sqlite.JDBC");	            
                    con = DriverManager.getConnection("jdbc:sqlite:contactbook150.sqlite");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

//****************************************************************************
	public ArrayList searchPerson(String n)
	{
		try
		{
                    String sql = "SELECT * FROM Person WHERE name=?";

                    PreparedStatement ps = con.prepareStatement(sql);
                    ps.setString(1,n);


                    ResultSet rs = ps.executeQuery();

                    String name = "";
                    String address = "";
                    String email = "";
                    int phone;

                    while(rs.next())
                    {                  
                        name = rs.getString("name");
                        address = rs.getString("address");
                        phone = rs.getInt("phone");
                        email = rs.getString("email");


                        PersonInfo person = new PersonInfo(name, address, phone, email);
                        personsList.add(person);
                    }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
           
		return personsList;

	}

//*****************************************************************************************
	public void savePerson(PersonInfo person)
	{
		try
		{
			String sql = "INSERT INTO Person(name, address, phone, email) VALUES (?,?,?,?)";
 			ps = con.prepareStatement(sql);
	
			ps.setString(1 , person.getName());
			ps.setString(2 , person.getAddress());
			ps.setInt(3 , person.getPhone());
			ps.setString(4 , person.getEmail());
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("EX");
		}
	}

//*********************************************************************************************
	public void updatePerson(PersonInfo person)
	{
		try
		{
			String sql = "UPDATE Person SET address=? , phone=? , email=? where name=?";

			// Create a statement
 			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setString(1 , person.getAddress());
			ps.setInt(2, person.getPhone());
			ps.setString(3 , person.getEmail());
			ps.setString(4 , person.getName());

			// execute the statement
			ps.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println("Error update");
		}
	}

//************************************************************************************************
	public void removePerson(String name)
	{
 

		try
		{
			String sql = "DELETE FROM Person WHERE name = ?";
 			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);

                        ps.executeUpdate();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}
        
//************************************************************************************************
        

//*************************************************************************************************
	protected void finalize()
	{
		try
		{
                  if (con != null) {
			  con.close();
                  }
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

    
}
