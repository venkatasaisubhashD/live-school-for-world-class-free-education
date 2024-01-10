import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@WebServlet("/tf")
@MultipartConfig
public class tf extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	
	    String filePath = request.getParameter("filepath");
	
	    out.println("<html><head><title>Files List</title>");
	    out.println("<style>");
	    out.println(".file-symbol { color: blue; font-size: 20px; }");
	    out.println(".folder-symbol { color: brown; font-size: 20px; }");
	    out.println("</style>");
	    out.println("</head><body>");
	    out.println("<input type='button' value='Go Back' class='s1' onclick='window.history.back()' style='color: white; background-color: black; border: none; padding: 6px 12px; text-align: center; text-decoration: none; display: inline-block; font-size: 12px; margin-top: 20px; cursor: pointer; border-radius: 3px;'>");
	    File file = new File(filePath);
	    if (file.exists() && file.isDirectory()) {
	    	out.println("<h2 style='display:inline;'>File explorer:</h2>");
	    	out.println("<h2>create files and folders</h2>");
	    	out.println("<form action='tf' method='post' style='display:inline;'>");
	    	out.println("<input type='hidden' name='action' value='createFolder'>");
	    	out.println("<input type='hidden' name='filepath' value='" + filePath + "'>");
	    	out.println("<label for='folderName'>Folder Name:</label>");
	    	out.println("<input type='text' id='folderName' name='folderName'>");
	    	out.println("<input type='submit' value='Create Folder'>");
	    	out.println("</form>");
	
	    	out.println("<form action='tf' method='post' enctype='multipart/form-data' style='display:inline;'>");
	    	out.println("<input type='hidden' name='action' value='uploadFile'>");
	    	out.println("<input type='hidden' name='filepath' value='" + filePath + "'>");
	    	out.println("<input type='file' name='file'>");
	    	out.println("<input type='submit' value='Upload File'>");
	    	out.println("</form>");
	
	    	out.println("<ul>");
	
	
	        File[] files = file.listFiles();
	
	        if (files != null) {
	            for (File f : files) {
	                String encodedPath = URLEncoder.encode(f.getAbsolutePath(), StandardCharsets.UTF_8);
	                String icon = f.isDirectory() ? "<span class='folder-symbol'>&#128193;</span> " : "<span class='file-symbol'>&#128462;</span> ";
	
	                if (f.isDirectory()) {
	                    out.println("<li><a href='tf?filepath=" + encodedPath + "'>" + icon + f.getName() + "</a></li>");
	                } else {
	                	out.println("<li><a href='cx?filepath=" + encodedPath + "' target='_blank'>" + icon + f.getName() + "</a></li>");
	                }
	            }
	        } else {
	            out.println("<p>No files or folders found in the directory.</p>");
	        }
	
	        out.println("</ul>");
	    } else {
	        out.println("<p>No such directory exists or it is not a directory.</p>");
	    }
	
	
	    out.println("</body></html>");
	    out.close();
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String filePath = request.getParameter("filepath");

        if ("uploadFile".equals(action)) {
            Part filePart = request.getPart("file");
            String fileName = getFileName(filePart);
            if (fileName != null && !fileName.isEmpty()) {
                String savePath = filePath + File.separator + fileName;
                filePart.write(savePath);
            }
        } else if ("createFolder".equals(action)) {
            String newFolderName = request.getParameter("folderName");
            if (newFolderName != null && !newFolderName.isEmpty()) {
                File newFolder = new File(filePath + File.separator + newFolderName);
                boolean created = newFolder.mkdir();
                if (created) {
                    System.out.println("Folder created successfully!");
                } else {
                    System.out.println("Failed to create folder.");
                }
            }
        }
        response.sendRedirect("tf?filepath=" + URLEncoder.encode(filePath, "UTF-8"));
    }

    private String getFileName(Part part) {
        // Get the submitted file name
        // This method should be implemented to extract the file name from the Part
        // For example:
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return null;
    }
}