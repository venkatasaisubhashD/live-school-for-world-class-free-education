import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/NotificationServlet")
public class NotificationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");

            out.println("<html><head><title>Create Notification</title>");
            out.println("<style>");
            out.println("body {");
            out.println("  display: flex;");
            out.println("  justify-content: center;");
            out.println("  align-items: center;");
            out.println("  height: 100vh;");
            out.println("  margin: 0;");
            out.println("  font-family: Arial, sans-serif;");
            out.println("}");
            out.println(".content {");
            out.println("  text-align: center;");
            out.println("}");
            out.println("</style></head><body>");
            
            out.println("<div class='content'>");
            out.println("<h1>CREATE NOTIFICATION FOR STUDENTS</h1>");
            out.println("<form method='post'>");
            out.println("Notification: <input type='text' name='notification'><br><br>");
            out.println("Urgency: ");
            out.println("<select name='urgency'>");
            out.println("  <option value='low'>&#x1F7E2; Low</option>");
            out.println("  <option value='medium'>&#x1F7E1; Medium</option>");
            out.println("  <option value='high'>&#x1F534; High</option>");
            out.println("</select><br><br>");
            out.println("<input type='submit' value='Send Notification'>");
            out.println("</form>");
            out.println("</div>");
            
            out.println("</body></html>");
        } else {
            // Redirect to login page if session is not available
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String notification = request.getParameter("notification");
        String urgency = request.getParameter("urgency");

        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");

            try {
                // Establish connection to your database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "");

                // Insert data into the 'notification' table
                String sql = "INSERT INTO notification (teacher, type, info, topic, urgency) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, "notification");
                statement.setString(3, notification);
                statement.setString(4, "");
                statement.setString(5, urgency);
                statement.executeUpdate();

                statement.close();
                connection.close();

                 // Redirect to success page
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
           // Redirect to login page if session is not available
        }
    }
}