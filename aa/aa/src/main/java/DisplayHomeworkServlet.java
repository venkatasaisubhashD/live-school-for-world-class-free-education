import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/DisplayHomeworkServlet")
public class DisplayHomeworkServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");

            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "");
                PreparedStatement teacherStudentStatement = connection.prepareStatement("SELECT DISTINCT teacher FROM teacher_student WHERE student = ?");
                teacherStudentStatement.setString(1, username);

                ResultSet teacherResult = teacherStudentStatement.executeQuery();
                while (teacherResult.next()) {
                    String teacherName = teacherResult.getString("teacher");
                    PreparedStatement notificationStatement = connection.prepareStatement("SELECT * FROM notification WHERE teacher = ? AND type = 'homework' ORDER BY id DESC");
                    notificationStatement.setString(1, teacherName);
                    ResultSet notificationResult = notificationStatement.executeQuery();

                    out.println("<div style='text-align: center; border: 1px solid black; margin: 10px; padding: 10px;'>");
                    out.println("<h3>" + teacherName + "</h3>");

                    int count = 1;
                    while (notificationResult.next()) {
                        String info = notificationResult.getString("info");
                        String topic = notificationResult.getString("topic");
                        out.println("<p>" + count + ") HOMEWORK: " + info + "</p>");
                        out.println("<p>   Topic: " + topic + "</p>");
                        count++;
                    }

                    out.println("</div>");
                    notificationResult.close();
                    notificationStatement.close();
                }

                teacherResult.close();
                teacherStudentStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            // Redirect to login page if session is not available
        }
    }
}