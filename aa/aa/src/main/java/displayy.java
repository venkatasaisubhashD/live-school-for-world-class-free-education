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
public class displayy extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

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

                    out.println("<!DOCTYPE html>");
                    out.println("<html><head><title>User Details</title></head><body>");

                    if (rs.next()) {
                        // Display user details
                        out.println("<h2>User Detailss</h2>");
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

                        // Check streaming table for the current user
                        PreparedStatement checkStream = con.prepareStatement("SELECT * FROM streaming WHERE username = ?");
                        checkStream.setString(1, username);
                        ResultSet streamRs = checkStream.executeQuery();

                        if (!streamRs.next()) {
                            // No record found, create one
                            PreparedStatement createStream = con.prepareStatement("INSERT INTO streaming (username, val) VALUES (?, 'no')");
                            createStream.setString(1, username);
                            createStream.executeUpdate();
                        }

                        // Get the current value for the user's stream
                        PreparedStatement getStreamVal = con.prepareStatement("SELECT val FROM streaming WHERE username = ?");
                        getStreamVal.setString(1, username);
                        ResultSet valRs = getStreamVal.executeQuery();

                        String streamVal = "";
                        if (valRs.next()) {
                            streamVal = valRs.getString("val");
                        }

                        // Create the form with the button based on the stream value
                        String buttonLabel = "";
                        String buttonStyle = "";

                        if ("now".equals(streamVal)) {
                            buttonLabel = "Stop Class";
                            buttonStyle = "background-color: yellow;";
                            out.println("<a href='tryy?teacherName=" + username + "' target='rightFrame' style='display: inline-block; background-color: red; padding: 5px;'>");
                            out.println("<span style='font-size: 20px; color: white;'>&#128218;</span>");
                            out.println("<span style='color: white;'>Go to Class</span>");
                            out.println("</a>");// Yellow background for stopping stream
                        } else {
                            buttonLabel = "Start Class";
                            buttonStyle = "background-color: red;"; // Red background for starting stream
                        }

                        out.println("<form action='' method='post'>");
                        out.println("<input type='submit' name='streamButton' value='" + buttonLabel + "' style='" + buttonStyle + "'>");
                        out.println("</form>");

                        // Handle button click and update streaming table
                        String streamButton = request.getParameter("streamButton");
                        if (streamButton != null) {
                            String newStreamVal = ("now".equals(streamVal)) ? "no" : "now";
                            PreparedStatement updateStream = con.prepareStatement("UPDATE streaming SET val = ? WHERE username = ?");
                            updateStream.setString(1, newStreamVal);
                            updateStream.setString(2, username);
                            updateStream.executeUpdate();
                         
                            response.sendRedirect(request.getRequestURI()); // Refresh the page after updating the value
                          
                        }
                    } else {
                        out.println("<p>User not found!</p>");
                    }

                    out.println("</body></html>");

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

        out.close();
    }
}