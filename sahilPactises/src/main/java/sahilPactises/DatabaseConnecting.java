package sahilPactises;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DatabaseConnecting
 */
public class DatabaseConnecting extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	String header = "<!DOCTYPE html>\r\n" + "<html lang=\"en\">\r\n" + "<head>\r\n"
			+ "  <title>Bootstrap Example</title>\r\n" + "  <meta charset=\"utf-8\">\r\n"
			+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
			+ "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css\">\r\n"
			+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n"
			+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js\"></script>\r\n"
			+ "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js\"></script>\r\n"
			+ "</head>\r\n" + "<body>\r\n" + "\r\n" + "<div class=\"container\">\r\n" + "  <h2>Database</h2>\r\n"
			+ "  <p>These are the contents of database</p>            \r\n"
			+ "  <table class=\"table\">\r\n" + "    <thead>\r\n" + "      <tr>\r\n" + "        <th>ID</th>\r\n"
			+ "        <th>Name</th>\r\n" + "      </tr>\r\n" + "    </thead>\r\n" + "    <tbody>\r\n";

	String table = "      <tr>\r\n" + "        <td>%s</td>\r\n" + "        <td>%s</td>\r\n" + "      </tr>\r\n";
	String footer = "    </tbody>\r\n" + "  </table>\r\n" + "</div>\r\n" + "\r\n" + "</body>\r\n" + "</html>\r\n" + "";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "jdbc:postgresql://localhost:5432/registration";
		String user = "postgres";
		String password = "admin";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (request.getParameter("username").equals(user) && request.getParameter("password").equals(password)) {
			try {

				Connection conn = DriverManager.getConnection(url, user, password);
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery("select * from employee");
				response.getWriter().print("<b><h1>Welcome to my application</h1></b><br>Database Connected");
				response.getWriter().println(header);
				while (rs.next()) {
					String id = /* response.getWriter().print( */rs.getString("Eid");
					String name = /* response.getWriter().print( */rs.getString("Name");

					response.getWriter().println(String.format(table, id, name));
				}
				response.getWriter().println(footer);

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			response.getWriter().print("Invalid");
		}
	}

}
