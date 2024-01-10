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

@WebServlet("/DisplayServlet")
public class studentdis extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Set the HTML page background to black and text color to white
        out.println("<!DOCTYPE html>");
        out.println("<html><head><title>User Details</title>");
        out.println("<style>body { background-color: black; color: white; }</style>");
        out.println("</head><body>");

        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            if (username != null) {
                try {
                    // Replace with your database credentials
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "");

                    PreparedStatement ps = con.prepareStatement("SELECT * FROM accounts WHERE username = ?");
                    ps.setString(1, username);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        out.println("<h2>User Details</h2>");
                        out.println("<p>Name: " + rs.getString("username") + "</p>");
                        out.println("<p>Email: " + rs.getString("email") + "</p>");
                        out.println("<p>Phone Number: " + rs.getString("phone") + "</p>");
                        out.println("<p>College: " + rs.getString("college") + "</p>");
                        out.println("<p>Age: " + rs.getInt("age") + "</p>");

                        // Check if subject is not null
                        String subject = rs.getString("subject");
                        if (subject != null && !subject.isEmpty()) {
                            out.println("<p>Subject: " + subject + "</p>");
                        }
                    } else {
                        out.println("<p>User not found!</p>");
                    }

                    con.close();
                } catch (ClassNotFoundException | SQLException e) {
                    out.println(e.getMessage());
                }
            } else {
                out.println("<p>Username not found in session!</p>");
            }
        } else {
            out.println("<p>Session expired or not found!</p>");
        }

        out.println("</body></html>");

        out.close();
    }
}