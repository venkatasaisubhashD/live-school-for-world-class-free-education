import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GenerateRoomServlet")
public class wase extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("<meta charset='UTF-8'>");
        out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        out.println("<title>EchoMeet</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id='root'></div>");

        String teacherName = request.getParameter("teacherName"); // Get teacherName from the POST request

        out.println("<script>");
        out.println("window.onload = function() {");
        out.println("const teacherName = '" + teacherName + "';"); // Set teacherName obtained from the POST request
        out.println("const roomID = teacherName || 'defaultRoomID';"); // Set roomID as teacherName or defaultRoomID if teacherName is null
        out.println("document.getElementById('roomId').innerText = roomID;"); // Update the HTML element with the roomID
        
        // Rest of your JavaScript code goes here...
        // Example: ZegoUIKitPrebuilt initialization
        out.println("const userID = Math.floor(Math.random() * 10000) + '';");
        out.println("const userName = 'I\\'m Batman 🦇';");
        out.println("const appID = 1276202388;");
        out.println("const serverSecret = 'e1965162b4785b92a134ba7bb38ba03c';");
        out.println("const TOKEN = ZegoUIKitPrebuilt.generateKitTokenForTest(appID, serverSecret, roomID, userID, userName);");
        
        out.println("const zp = ZegoUIKitPrebuilt.create(TOKEN);");
        out.println("zp.joinRoom({");
        out.println("container: document.querySelector('#root'),");
        out.println("sharedLinks: [{");
        out.println("url: window.location.origin + window.location.pathname + '?roomID=' + roomID,");
        out.println("}],");
        out.println("scenario: {");
        out.println("mode: ZegoUIKitPrebuilt.VideoConference,");
        out.println("},");
        out.println("});");
        
        out.println("}");
        out.println("</script>");
        
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
