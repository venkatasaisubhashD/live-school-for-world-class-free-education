import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/trybest")
public class trybest extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/school";
    private static final String USERNAME = "root";
    private static final String PASSWORD = ""; // Add your database password here

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the parameter sent from the HTML form
        String closureData = request.getParameter("closureData");

        // Static username
        String username = "DHHSHS";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Store the received data in the database using JDBC
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO streaming (val, username) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, closureData);
            statement.setString(2, username);
            statement.executeUpdate();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}