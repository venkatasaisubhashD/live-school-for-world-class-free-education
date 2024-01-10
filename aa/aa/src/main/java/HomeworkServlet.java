import java.io.Console;
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

@WebServlet("/HomeworkServlet")
public class HomeworkServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");

            out.println("<html><head><title>Homework Form</title>");
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
            out.println("<h1>HOMEWORK FOR TODAY</h1>");
            out.println("<form method='post'>");
            out.println("<textarea name='homework' rows='4' cols='50'></textarea><br><br>");
            out.println("Topic: <input type='text' name='topic'><br><br>");
            out.println("<input type='submit' value='Send to Students'>");
            out.println("</form>");
            out.println("</div>");
            
            out.println("</body></html>");
        } else {
            // Redirect to login page if session is not available
        }
    

    // doPost method remains the same as in your code
}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String homework = request.getParameter("homework");
        String topic = request.getParameter("topic");

        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");

            try {
                // Establish connection to your database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "");

                // Insert data into the 'notification' table
                String sql = "INSERT INTO notification (teacher, type, info, topic, urgency) VALUES (?, ?, ?, ?, NULL)";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, username);
                statement.setString(2, "homework");
                statement.setString(3, homework);
                statement.setString(4, topic);
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