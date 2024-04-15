import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DatabaseMainApp {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		//1)Load and register the Driver.
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//2/Establish the connection
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDatabase?useSSL=false", "root", "root");
		System.out.println("Database connected");
		
		Scanner s = new Scanner(System.in);
		System.out.println("Enter employee id that you want to search");
		String empIdInput = s.nextLine();
		
		//3)Prepare either Statement objects
		Statement statement = conn.createStatement();
		
		//4)Write and execute SQL Queries.
		String sqlQuery = "Select * from employee where empId=" + empIdInput;
		ResultSet resultSet = statement.executeQuery(sqlQuery);
		
		//5)Process the result
		while(resultSet.next()) {
			int empId = resultSet.getInt("empId");
			String empName = resultSet.getString("empName");
			String empDept = resultSet.getString("empDept");
			String empDesgn = resultSet.getString("empDesignation");
			System.out.println("empId : " + empId + ", empName : " + empName + 
					", empDept is : " + empDept + ", empDesignation is : " + empDesgn) ;
		}
		
		//6)close the connection
		conn.close();
	}

}

