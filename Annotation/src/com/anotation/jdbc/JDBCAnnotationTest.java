package com.anotation.jdbc;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.anotation.beans.User;



@JDBCAnnotation(
		url="jdbc:mysql://localhost:3306/wisewarehouse",
		user="root",
		password="123456")
public class JDBCAnnotationTest {

	static String createSelectSql(Object obj){
		StringBuffer sql = new StringBuffer("select ");
		String tableName = obj.getClass().getAnnotation(JDBCTableAnnotation.class).tableName();
	    Field[] fields = obj.getClass().getDeclaredFields();
	    int i = 0;
	    for (Field field : fields) {
			String colName = field.getAnnotation(JDBCColumAnnotation.class).colName();
			sql.append(colName);
			if (fields.length-1 != i) {
				sql.append(",");
			}
			i++;
		}
	    sql.append(" from " + tableName);
	    
		return sql.toString();
	}
	
	static List queryObjectList(Object object) throws IllegalArgumentException, IllegalAccessException, InstantiationException{
		String queryUserSql = createSelectSql(object);
		List<Object> objects = new ArrayList<Object>();
		try {
			PreparedStatement preparedStatement = connection().prepareStatement(queryUserSql);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Object obj = object.getClass().newInstance();
				Field [] fields = obj.getClass().getDeclaredFields();
				for (int i = 0; i < fields.length; i++) {
					Field field = fields[i];
					Object colValue = resultSet.getObject(i+1);
					System.out.println(colValue);
					field.set(obj, colValue);
					
				}
				objects.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return objects;
	}
	public static void testUserQuery(){
		System.out.println(createSelectSql(new User()));
	}
	
	static Connection connection() throws SQLException{
		JDBCAnnotation jdbcAnnotation = JDBCAnnotationTest.class.getAnnotation(JDBCAnnotation.class);
		Connection connection = DriverManager.getConnection(jdbcAnnotation.url(),jdbcAnnotation.user(), jdbcAnnotation.password());
		return connection;
	}
	
	static void testAnnotationJDBC() throws SQLException{
		
		PreparedStatement preparedStatement = connection().prepareStatement("select * from account");
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1));
		}
	}
	
	public static void main(String[] args) throws SQLException, IllegalArgumentException, IllegalAccessException, InstantiationException {
		List users =queryObjectList(new User());
		for (Object object : users) {
			User user = (User) object;
			System.out.println(user);
		}
	}
}
