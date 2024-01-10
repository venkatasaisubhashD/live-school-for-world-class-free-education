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

@WebServlet("/trialservlet")
public class trialservlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost/school";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve the parameter sent from the HTML form
        String closureData = request.getParameter("closureData");

        // Static username
        String username = "sbsb"; // Change this to the desired static username

        // Store the received data in the database using JDBC
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "INSERT INTO streaming (val, username) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, closureData);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}