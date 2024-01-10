import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SignupServlet")
public class signup extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("usernameSignup");
        String password = request.getParameter("passwordSignup");
        String userType = request.getParameter("userType");
        String subject = request.getParameter("subject");
        String email = request.getParameter("email");
        long phone = Long.parseLong(request.getParameter("phone"));
        String college = request.getParameter("college");
        int age = Integer.parseInt(request.getParameter("age"));
        String secQuestion = request.getParameter("secQuestion");
        String secAnswer = request.getParameter("secAnswer");
        String qualifications = request.getParameter("qualifications");

        // Get the current date
        LocalDate currentDate = LocalDate.now();
        Date dateOfJoining = Date.valueOf(currentDate);

        // Database connection details
        String jdbcURL = "jdbc:mysql://localhost:3306/school";
        String dbUsername = "root";
        String dbPassword = "";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);

            String sql = "INSERT INTO accounts (username, password, type, subject, email, phone, college, age, secqn, secans, qualifications, doj) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, userType);
            statement.setString(4, subject);
            statement.setString(5, email);
            statement.setLong(6, phone);
            statement.setString(7, college);
            statement.setInt(8, age);
            statement.setString(9, secQuestion);
            statement.setString(10, secAnswer);
            statement.setString(11, qualifications);
            statement.setDate(12, dateOfJoining); // Setting the Date of Joining

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                // Creating a folder with the username on the desktop
                String folderPath = "C:\\Users\\Administrator\\Desktop\\ff\\" + username;
                File folder = new File(folderPath);
                if (!folder.exists()) {
                    folder.mkdirs(); // Create the folder if it doesn't exist
                }

                if ("student".equals(userType)) {
                    response.sendRedirect("student.html");
                } else if ("teacher".equals(userType)) {
                    response.sendRedirect("teacher.html");
                }
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle exceptions and errors accordingly
        }
    }
}