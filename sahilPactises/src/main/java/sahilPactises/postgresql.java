package sahilPactises;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class postgresql {

	public static void main(String[] args) throws ClassNotFoundException {

		String url = "jdbc:postgresql://localhost:5432/registration";
		String user = "postgres";
		String password = "admin";
		Class.forName("org.postgresql.Driver");
		try {
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select * from employee");
			while(rs.next()) {
				System.out.println(rs.getString("Eid"));
				System.out.println(rs.getString("Name"));
			}
			System.out.println("Database Connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
