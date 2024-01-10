import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String searchTerm = request.getParameter("searchField");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE username = ? OR subject = ?");
            ps.setString(1, searchTerm);
            ps.setString(2, searchTerm);

            ResultSet rs = ps.executeQuery();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Search Results</title>");
            out.println("<style>");
            out.println(".teacher-tile {");
            out.println("    background-color: #ffffff;");
            out.println("    border-radius: 10px;");
            out.println("    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);");
            out.println("    padding: 20px;");
            out.println("    margin-bottom: 20px;");
            out.println("}");
            out.println(".teacher-tile p {");
            out.println("    margin: 5px 0;");
            out.println("}");
            out.println(".teacher-tile form {");
            out.println("    display: flex;");
            out.println("    justify-content: space-between;");
            out.println("    align-items: center;");
            out.println("    margin-top: 10px;");
            out.println("}");
            out.println(".teacher-tile input[type='submit'] {");
            out.println("    padding: 8px 20px;");
            out.println("    border: none;");
            out.println("    border-radius: 5px;");
            out.println("    background-color: #66b3ff;");
            out.println("    color: #fff;");
            out.println("    cursor: pointer;");
            out.println("    transition: background-color 0.3s ease;");
            out.println("}");
            out.println(".teacher-tile input[type='submit']:hover {");
            out.println("    background-color: #3385ff;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            while (rs.next()) {
                String username = rs.getString("username");
                String subject = rs.getString("subject");

                out.println("<div class='teacher-tile'>");
                out.println("<p>Teacher Name: " + username + "</p>");
                out.println("<p>Subject: " + subject + "</p>");
                out.println("<form action='JoinServlet' method='post'>");
                out.println("<input type='hidden' name='teacherName' value='" + username + "'>");
                out.println("<input type='submit' value='Join'>");
                out.println("</form>");
                out.println("<form action='' method='post' style='display: flex; justify-content: space-between; align-items: center; margin-top: 20px;'>"); // Removed action attribute to point to the same servlet
                out.println("<input type='hidden' name='teacherName' value='" + username + "'>");
                
                out.println("<input type='submit' name='enrollButton' value='Enroll'>"); // Added 'Enroll' button
                out.println("</form>");
                out.println("</div>");
            }

            String unenrollButton = request.getParameter("unenrollButton");
            String enrollButton = request.getParameter("enrollButton"); // New enroll button parameter

            // Logic for unenrolling (same as before)

            if (enrollButton != null) {
            	  HttpSession session = request.getSession(false);
              
                      String username = (String) session.getAttribute("username");
                 
                String teacherName = request.getParameter("teacherName");
                PreparedStatement psEnroll = con.prepareStatement("INSERT INTO teacher_student (teacher, student) VALUES (?, ?)");
                psEnroll.setString(1, teacherName);
                psEnroll.setString(2, username); // Replace "student_name" with the actual student's name

                int insertedRows = psEnroll.executeUpdate();

                if (insertedRows > 0) {
                    out.println("<p>Successfully enrolled with the teacher.</p>");
                    // Optionally, you can add a redirect or display a success message here
                } else {
                    out.println("<p>Failed to enroll with the teacher.</p>");
                    // Optionally, you can add an error message or handle failure scenarios here
                }
            }

            out.println("</body>");
            out.println("</html>");

            con.close();
        } catch (Exception e) {
            out.println(e);
        }

        out.close();
    }
}