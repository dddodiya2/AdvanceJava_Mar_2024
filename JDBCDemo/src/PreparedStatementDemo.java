import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PreparedStatementDemo {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//1)Load and register the Driver.
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//2/Establish the connection
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDatabase?useSSL=false", "root", "root");
		System.out.println("Database connected");
		
		//3)Prepare either Statement objects
		//Statement statement = conn.createStatement();
		Scanner s = new Scanner(System.in);
		System.out.println("Do you want to enter a new employee type yes or no");
		String input = s.nextLine();
		boolean wantTocontinue = true;
		if (input.equalsIgnoreCase("no")) {
			wantTocontinue = false;
		}
		
		PreparedStatement preparedStatement = conn.prepareStatement("insert into Employee values (?, ?, ?, ?)");
		 
		while(wantTocontinue) {
			System.out.println("Enter empId :");
			int empId = Integer.parseInt(s.nextLine());
			System.out.println("Enter empname:");
			String name = s.nextLine();
			System.out.println("Enter empDept:");
			String empDept = s.nextLine();
			System.out.println("Enter empDesignation:");
			String empDesignation = s.nextLine();
			
			preparedStatement.setInt(1, empId);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, empDept);
			preparedStatement.setString(4, empDesignation);
			int numberOfRowsAfftected = preparedStatement.executeUpdate();
			System.out.println("Number of rows impacted are : " + numberOfRowsAfftected);
			
			System.out.println("Do you want to enter a new employee type yes or no");
			String inputNew = s.nextLine();
			if (inputNew.equalsIgnoreCase("no")) {
				wantTocontinue = false;
			}
		}		
		
		//4)Write and execute SQL Queries.
		
		
		//5)Process the result
		
		
		//6)close the connection
		conn.close();
	}
}
