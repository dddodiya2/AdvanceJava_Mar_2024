import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionManagementDemo {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//nonTransactionalWay();
		transactionalWay();
	}

	public static void nonTransactionalWay() throws ClassNotFoundException, SQLException {
		//1)Load and register the Driver.
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//2/Establish the connection
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDatabase?useSSL=false", "root", "root");
		System.out.println("Database connected");
		
		//3)Prepare either Statement objects
		Statement statement = conn.createStatement();		

		//4)Write and execute SQL Queries.
		//update example
		String sqlQuery1 = "Update Employee set empDesignation = 'Manager' where empId=10";
		int numberOfRowsAfftected1 = statement.executeUpdate(sqlQuery1);
		
		System.out.println("Intentional exception : " + 100/0);
		
		//Update example
		System.out.println("executing second update statement");
		String sqlQuery2 = "Update Employee set empDesignation = 'Manager' where empId=2";
		int numberOfRowsAfftected2 = statement.executeUpdate(sqlQuery2);
		
		//update example
		String sqlQuery3 = "Update Employee set empDesignation = 'Manager' where empId=1002";
		int numberOfRowsAfftected3 = statement.executeUpdate(sqlQuery3);
		
		//5)Process the result
		System.out.println("Number of rows impacted are : " + numberOfRowsAfftected1);
		System.out.println("Number of rows impacted are : " + numberOfRowsAfftected2);
		System.out.println("Number of rows impacted are : " + numberOfRowsAfftected3);
		
		//6)close the connection
		conn.close();
	}
	
	public static void transactionalWay() throws ClassNotFoundException, SQLException{
		//1)Load and register the Driver.
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		//2/Establish the connection
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDatabase?useSSL=false", "root", "root");
		conn.setAutoCommit(false);
		System.out.println("Database connected");
		
		//3)Prepare either Statement objects
		Statement statement = conn.createStatement();		

		try {
		//4)Write and execute SQL Queries.
		//update example
		String sqlQuery1 = "Update Employee set empDesignation = 'DIRECTOR' where empId=10";
		int numberOfRowsAfftected1 = statement.executeUpdate(sqlQuery1);
		System.out.println("First update done: ");
		
		//System.out.println("Intentional exception : " + 100/0);
		
		//Update example
		System.out.println("executing second update statement");
		String sqlQuery2 = "Update Employee set empDesignation = 'DIRECTOR' where empId=2";
		int numberOfRowsAfftected2 = statement.executeUpdate(sqlQuery2);
		System.out.println("Second update done: ");
		
		//update example
		String sqlQuery3 = "Update Employee set empDesignation = 'DIRECTOR' where empId=1002";
		int numberOfRowsAfftected3 = statement.executeUpdate(sqlQuery3);
		System.out.println("Third update done: ");
		
		//5)Process the result
		System.out.println("Number of rows impacted are : " + numberOfRowsAfftected1);
		System.out.println("Number of rows impacted are : " + numberOfRowsAfftected2);
		System.out.println("Number of rows impacted are : " + numberOfRowsAfftected3);
		System.out.println("Doing commit since NO exception occurred: ");
		conn.commit();
		System.out.println("Commit completed: ");
	
		//6)close the connection
		conn.close();
		}
		catch(ArithmeticException e ) {
			System.out.println("Doing rollback since exception occurred: ");
			conn.rollback();
			System.out.println("Rollback completed: ");
		}
	}
}


