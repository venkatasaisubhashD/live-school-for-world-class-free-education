import java.io.IOException;
import java.net.URLEncoder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CreateFilePathServlet")
public class CreateFilePathServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the HttpSession
        HttpSession session = request.getSession(false); // false means it won't create a new session if one doesn't exist

        // Check if the session exists and if the username attribute is set
        if (session != null && session.getAttribute("username") != null) {
            // Retrieve the username from the session
            String username = (String) session.getAttribute("username");

            // Construct the file path with the username
            String filePath = "C:\\Users\\Administrator\\Desktop\\ff\\" + username;

            // Encode the file path
            String encodedFilePath = URLEncoder.encode(filePath, "UTF-8");

            // Redirect to the 'tf' page with the constructed file path using GET
            response.sendRedirect("tf?filepath=" + encodedFilePath);
        } else {
            // Handle the case when the session or username is not available
            response.getWriter().println("Session not available or username not set.");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // For handling POST requests if needed
        doGet(request, response);
    }
}