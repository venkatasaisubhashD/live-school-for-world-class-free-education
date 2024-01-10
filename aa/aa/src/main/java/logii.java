import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class logii extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("usernameLogin");
        String password = request.getParameter("passwordLogin");

        // JDBC URL, username, and password of MySQL server
        String jdbcURL = "jdbc:mysql://localhost:3306/school";
        String dbUser = "root";
        String dbPassword = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);

            String sql = "SELECT * FROM accounts WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                String userType = result.getString("type");
                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                if (userType.equals("student")) {
                    response.sendRedirect("student.html");
                } else if (userType.equals("teacher")) {
                    response.sendRedirect("teacher.html");
                }
                else if (userType.equals("admin")) {
                    response.sendRedirect("admi");
                }
                
            } else {
                response.sendRedirect("stree.html"); // Redirect back to sign-in page if credentials don't match
            }

            connection.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}