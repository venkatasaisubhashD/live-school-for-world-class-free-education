import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CombinedServlet")
public class JoinServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String teacherName = request.getParameter("teacherName");
        boolean showWatchButton = false; // Flag to check condition for showing button

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "");

            // Retrieve teacher's information based on the username
            PreparedStatement psTeacher = con.prepareStatement("SELECT * FROM accounts WHERE username = ? AND type = 'teacher'");
            psTeacher.setString(1, teacherName);
            ResultSet rsTeacher = psTeacher.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Teacher Information</title>");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=0.2\">");
            out.println("<style>");
            out.println(".teacher-info { padding: 20px; background-color: #f0f0f0; margin-bottom: 20px; }");
            out.println("#iframeContainer { margin-top: 20px; }");
            out.println("body {");
            out.println("    transform: scale(0.8);");
            out.println("    transform-origin: 0 0;");
            out.println("}");
            out.println("</style>");
            out.println("<script>");
            out.println("function toggleIframe() {");
            out.println("var iframeContainer = document.getElementById('iframeContainer');");
            out.println("if (iframeContainer.style.display === 'none') {");
            out.println("iframeContainer.style.display = 'block';");
            out.println("} else {");
            out.println("iframeContainer.style.display = 'none';");
            out.println("}");
            out.println("}");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");

            // Display teacher's information
            out.println("<div class='teacher-info'>");
            out.println("<h1>Teacher Profile: " + teacherName + "</h1>");

            if (rsTeacher.next()) {
                String subject = rsTeacher.getString("subject");
                Date doj = rsTeacher.getDate("doj");
                String email = rsTeacher.getString("email");
                long phone = rsTeacher.getLong("phone");
                String college = rsTeacher.getString("college");
                int age = rsTeacher.getInt("age");
                String qualifications = rsTeacher.getString("qualifications");

                // Display teacher's details
                out.println("<p>Subject: " + subject + "</p>");
                out.println("<p>Date of Joining: " + doj + "</p>");
                out.println("<p>Email: " + email + "</p>");
                out.println("<p>Phone: " + phone + "</p>");
                out.println("<p>College: " + college + "</p>");
                out.println("<p>Age: " + age + "</p>");
                out.println("<p>Qualifications: " + qualifications + "</p>");

                // Check if the corresponding val part of the table has a value
                PreparedStatement psStreaming = con.prepareStatement("SELECT * FROM streaming WHERE username = ?");
                psStreaming.setString(1, teacherName);
                ResultSet rsStreaming = psStreaming.executeQuery();
                if (rsStreaming.next()) {
                    String val = rsStreaming.getString("val");
                    if (val != null && !val.isEmpty()) {
                        showWatchButton = true;
                    }
                }

                // Display "Watch Stream" button if conditions are met
                if (showWatchButton) {
                    out.println("<div'>");
                    out.println("<h2>Teacher is having a class: </h2>");
                    out.println("<form action='tryy' method='get'>");
                    out.println("<input type='hidden' name='teacherName' value='" + teacherName + "'>");
                    out.println("<input type='submit' value='Watch Now'>");
                    out.println("</form>");
                    out.println("</div>");
                }
            } else {
                out.println("<p>Teacher details not found!</p>");
            }

            out.println("</div>");

            // Button to toggle display of iframe
            out.println("<button onclick=\"toggleIframe()\">Show/Hide File Operations</button>");
            out.println("<div id='iframeContainer' style='display:none'>");
            HttpSession session = request.getSession(false);
            if (session != null) {
                String username = (String) session.getAttribute("username");
                if (username != null) {
                    String filePath = "C:\\Users\\Administrator\\Desktop\\ff\\" + teacherName;

                    out.println("<iframe id='fileOperations' src='ListFilesServlet?filepath=" + URLEncoder.encode(filePath, StandardCharsets.UTF_8) + "' width='100%' height='300'></iframe>");
                }
            }
            out.println("</div>");

            out.println("</body>");
            out.println("</html>");

            con.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.close();
        }
    }
}