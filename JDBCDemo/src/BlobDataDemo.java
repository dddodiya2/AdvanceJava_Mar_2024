import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BlobDataDemo {

	/*
	 create table blob_demo (id int, document BLOB, UNIQUE (id));
	 select * from blob_demo;
	 */

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		// writingBlobObject();
		readinBlobObject();
	}

	public static void writingBlobObject() throws ClassNotFoundException, SQLException, IOException {

		// 1)Load and register the Driver.
		Class.forName("com.mysql.cj.jdbc.Driver");

		// 2/Establish the connection
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDatabase?useSSL=false",
				"root", "root");
		System.out.println("Database connected");

		// 3)Prepare either Statement objects
		String sqlQuery = "insert into blob_demo values (?, ?)";
		PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);

		preparedStatement.setInt(1, 101);

		File file = new File(
				"C:\\Users\\Dodiya's\\Documents\\Divyesh_Study_Material\\ADV.JAVA MATERIAL\\Advance_java_Mar_2024\\JDBC\\1_Introduction.txt");
		FileInputStream inputStream = new FileInputStream(file);

		preparedStatement.setBinaryStream(2, inputStream);

		preparedStatement.executeUpdate();

		// 6)close the connection
		inputStream.close();
		conn.close();
		System.out.println("Database connection closed");
	}

	public static void readinBlobObject() throws ClassNotFoundException, SQLException, IOException {

		// 1)Load and register the Driver.
		Class.forName("com.mysql.cj.jdbc.Driver");

		// 2/Establish the connection
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeDatabase?useSSL=false",
				"root", "root");
		System.out.println("Database connected");

		// 3)Prepare either Statement objects
		String sqlQuery = "select document from blob_demo where id=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
		preparedStatement.setInt(1, 101);
		ResultSet result = preparedStatement.executeQuery();

		File outputFile = new File("Introduction_File_read_from_blob.txt");
		FileOutputStream os = new FileOutputStream(outputFile);
		while (result.next()) {
			InputStream inputStream = result.getBinaryStream("document");
			byte[] buffer = new byte[1024];
			while (inputStream.read(buffer) > 0) {
				os.write(buffer);
			}
			inputStream.close();
		}

		os.close();
		// 6)close the connection
		conn.close();
		System.out.println("Database connection closed");
	}

}
