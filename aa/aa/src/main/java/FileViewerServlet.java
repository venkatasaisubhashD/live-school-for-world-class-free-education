import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

@jakarta.servlet.annotation.MultipartConfig
public class FileViewerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String FILE_DIRECTORY = "C:/Users/Administrator/Desktop/ff";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Filed Viewer</title></head><body>");
        out.println("<h1>File Viewer</h1>");

        File dir = new File(FILE_DIRECTORY);
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            out.println("<h2>Files:</h2>");
            out.println("<ul>");
            for (File file : files) {
                out.println("<li><a href='" + request.getContextPath() + "/FileViewerServlet?fileName=" + file.getName() + "'>" + file.getName() + "</a></li>");
            }
            out.println("</ul>");
        } else {
            out.println("<p>No files found.</p>");
        }

        // Display file content if a specific file is clicked
        String fileName = request.getParameter("fileName");
        if (fileName != null && !fileName.isEmpty()) {
            File selectedFile = new File(FILE_DIRECTORY + File.separator + fileName);
            if (selectedFile.exists()) {
                out.println("<hr>");
                out.println("<h2>File: " + fileName + "</h2>");

                String mimeType = getServletContext().getMimeType(fileName);
                if (mimeType != null) {
                    if (mimeType.startsWith("text")) {
                        BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                        out.println("<pre>");
                        String line;
                        while ((line = reader.readLine()) != null) {
                            out.println(line);
                        }
                        out.println("</pre>");
                        reader.close();
                    } else if (mimeType.equals("application/pdf")) {
                        out.println("<embed src='" + request.getContextPath() + "/download?fileName=" + fileName + "' type='" + mimeType + "' width='100%' height='800px' />");
                    } else if (mimeType.startsWith("image")) {
                        out.println("<img src='" + request.getContextPath() + "/download?fileName=" + fileName + "' alt='Image' style='max-width:100%; height:auto;'>");
                    } else if (mimeType.startsWith("audio")) {
                        out.println("<audio controls><source src='" + request.getContextPath() + "/download?fileName=" + fileName + "' type='" + mimeType + "'>Your browser does not support the audio element.</audio>");
                    } else if (mimeType.startsWith("video")) {
                        out.println("<video width='100%' height='auto' controls><source src='" + request.getContextPath() + "/download?fileName=" + fileName + "' type='" + mimeType + "'>Your browser does not support the video element.</video>");
                    } else if (mimeType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") || mimeType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
                        out.println("<iframe src='https://view.officeapps.live.com/op/view.aspx?src=" + request.getContextPath() + "/download?fileName=" + fileName + "' width='100%' height='800px'></iframe>");
                    } else {
                        out.println("<p>File type not supported for online viewing.</p>");
                    }
                } else {
                    out.println("<p>Unable to determine the file type.</p>");
                }
            } else {
                out.println("<p>File not found.</p>");
            }
        }

        out.println("</body></html>");
    }

    // Implement doPost method for handling file uploads if needed
    // ...

}