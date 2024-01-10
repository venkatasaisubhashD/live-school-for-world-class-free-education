import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DisplayNotificationsServlet")
public class DisplayNotificationsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");

            out.println("<html><head><title>Display Notifications</title>");
            out.println("<style>");
            out.println("body {");
            out.println("  display: flex;");
            out.println("  justify-content: center;");
            out.println("  align-items: center;");
            out.println("  height: 100vh;");
            out.println("  margin: 0;");
            out.println("  font-family: Arial, sans-serif;");
            out.println("}");
            out.println(".notification-container {");
            out.println("  text-align: center;");
            out.println("  border: 1px solid #ccc;");
            out.println("  border-radius: 5px;");
            out.println("  padding: 10px;");
            out.println("  margin: 10px;");
            out.println("}");
            out.println(".notification {");
            out.println("  border: 1px solid #ddd;");
            out.println("  border-radius: 5px;");
            out.println("  padding: 10px;");
            out.println("  margin: 10px 0;");
            out.println("}");
            out.println("</style></head><body>");

            out.println("<div class='notification-container'>");
            out.println("<h1>NOTIFICATIONS</h1>");

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "");
                String query = "SELECT teacher, info, urgency FROM notification WHERE teacher IN (SELECT teacher FROM teacher_student WHERE student = ?) AND type = 'notification' ORDER BY id DESC";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, username);

                ResultSet resultSet = statement.executeQuery();
                List<String[]> notifications = new ArrayList<>();

                while (resultSet.next()) {
                    String teacher = resultSet.getString("teacher");
                    String info = resultSet.getString("info");
                    String urgency = resultSet.getString("urgency");
                    notifications.add(new String[]{teacher, info, urgency});
                }

                for (String[] notification : notifications) {
                    out.println("<div class='notification'>");
                    out.println("<h3>Teacher: " + notification[0] + "</h3>");
                    out.println("<p>Details: " + notification[1] + "</p>");
                    out.println("<p>Urgency: " + notification[2] + "</p>");
                    out.println("</div>");
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            out.println("</div>");
            out.println("</body></html>");
        } else {
            // Redirect to login page if session is not available
        }
    }

    // doPost method remains the same as in your code
}