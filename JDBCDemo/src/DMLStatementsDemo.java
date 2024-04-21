import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DMLStatementsDemo {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//1)Load and register the Driver.
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//2/Establish the connection
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDatabase?useSSL=false", "root", "root");
		System.out.println("Database connected");
		
		//3)Prepare either Statement objects
		Statement statement = conn.createStatement();
		
		//4)Write and execute SQL Queries.
		
		//insert example
		//String sqlQuery = "insert into Employee values (10, 'Divyesh', 'IT', 'SDE')";
		//int numberOfRowsAfftected = statement.executeUpdate(sqlQuery);
		
		//Update example
		//String sqlQuery = "Update Employee set empDesignation = 'sse' where empId=10";
		//int numberOfRowsAfftected = statement.executeUpdate(sqlQuery);
		
		//Delete example
		String sqlQuery = "Delete from Employee where empId=10";
		int numberOfRowsAfftected = statement.executeUpdate(sqlQuery);
		
		//5)Process the result
		System.out.println("Number of rows impacted are : " + numberOfRowsAfftected);
		
		//6)close the connection
		conn.close();
	}

}
