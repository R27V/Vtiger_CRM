package com.GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility {

	Connection con = null;
	/**
	 * This method will connect to the database
	 * @throws SQLException
	 */
	public void connectToDB() throws SQLException
	{
		//Step1: Register database
		Driver driver = new Driver();
		DriverManager.registerDriver(driver);
		
		//Step2: Get connection for database
		con = DriverManager.getConnection(IPathConstant.DBURL, IPathConstant.DBUsername, IPathConstant.DBPassword);
	}
	
	/**
	 * This method will execute the query and validate the data
	 * @param query
	 * @param colIndex
	 * @param expectData
	 * @throws SQLException
	 */
	public void executeAndgetData(String query, int colIndex, String expectData) throws SQLException
	{
		//Step3: Create statement
		Statement state = con.createStatement();
		
		//Step4: Execute query or Update query
		ResultSet result = state.executeQuery(query);
		boolean flag = false;
		while(result.next())
		{
			String actual = result.getString(colIndex);
			
			if(actual.contains(expectData))
			{
				flag = true;
				break;
			}
		}
		if(flag==true)
		{
			System.out.println("--data is verified--");
		}
		else
		{
			System.out.println("--data is not present--");
		}
		
	}
	
	/**
	 * This method will close the database connection
	 * @throws SQLException
	 */
	public void closeDb() throws SQLException
	{
		//Step5: Close database connection
		con.close();
	
	}
	
	/**
	 * This method will  create , update and delete the value in database
	 * @param query
	 * @throws SQLException
	 */
	public void updateQuery(String query) throws SQLException
	{
		//Step3: Create statement
		Statement state = con.createStatement();
		
		//Step4: Execute query or Update query
		int result = state.executeUpdate(query);
		System.out.println("Data is added into the database");
	}
}
