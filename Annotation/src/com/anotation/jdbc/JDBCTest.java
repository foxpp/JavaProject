package com.anotation.jdbc;
import java.sql.*;


public class JDBCTest {

	static void jdbcQueryTest() throws ClassNotFoundException, SQLException{
		//String driver = "com.mysql.jdbc.Driver";
		//Class.forName(driver);
		String dbName = "wisewarehouse";
		String url = "jdbc:mysql://localhost:3306/" + dbName;
		Connection connection = DriverManager.getConnection(url, "root","123456");
		PreparedStatement  preparedStatement = connection.prepareStatement("select * from account");
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1));
			
		}
	}
	
	
	static void userQueryTest(){
		
	}
	public static void main(String[] args) throws SQLException {
		try {
			jdbcQueryTest();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
