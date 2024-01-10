import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/DisplayImageServlet")
public class cx extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filePath = request.getParameter("filepath"); // Path to the image file

        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        // Set the content type based on the image type
        String mimeType = getServletContext().getMimeType(filePath);
        if (mimeType == null) {
            // Set as binary data if MIME type not found
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);

        // Set content length
        response.setContentLength((int) file.length());

        // Copy the image content to the response output stream
        OutputStream os = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        fis.close();
        os.flush();
        os.close();
    }
}