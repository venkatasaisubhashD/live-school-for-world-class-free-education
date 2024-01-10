import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;

public class ddss extends HttpServlet {

    // Assuming you have a method to establish the database connection
    // Replace these with your database credentials and connection logic
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/school";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = (String) request.getSession().getAttribute("username");

        out.println("<html><head><title>Delete Account Confirmation</title></head><body style='text-align: center;'>");
        out.println("<h1>ARE YOU SURE YOU WANT TO DELETE YOUR ACCOUNT?</h1>");
        out.println("<form method='post'>");
        out.println("<input type='submit' value='YES' style='background-color: red; color: white; padding: 10px 20px;'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            String username = (String) request.getSession().getAttribute("username");
            
            preparedStatement = connection.prepareStatement("DELETE FROM accounts WHERE username = ?");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();

            out.println("<html><head><title>Account Deleted</title></head><body style='text-align: center;'>");
            out.println("<h1>Your account has been successfully deleted.</h1>");
            out.println("</body></html>");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}