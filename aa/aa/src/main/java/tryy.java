import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/PrintHTMLServlet")
public class tryy extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String roomID = request.getParameter("teacherName");
       

        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<link rel=\"icon\" type=\"image/x-icon\" href=\"favicon.ico\">");
        out.println("<title>EchoMeet</title>");
        out.println("<style>");
        out.println("#root {");
        out.println("  width: 100vw;");
        out.println("  height: 100vh;");
        out.println("}");
        out.println("body::-webkit-scrollbar {");
        out.println("  display: none;");
        out.println("}");
        out.println("body {");
        out.println("  -ms-overflow-style: none;");
        out.println("  scrollbar-width: none;");
        out.println("}");
        out.println("</style>");
        out.println("<script>");
        out.println("window.onload = function() {");
        out.println("  const params = new URLSearchParams(window.location.search);");
        out.println("  const teacherName = params.get('teacherName');");
        out.println("  document.getElementById('roomId').innerText = teacherName;");
        out.println("};");
        out.println("</script>");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id=\"root\"></div>");
        out.println("<script src=\"https://unpkg.com/@zegocloud/zego-uikit-prebuilt/zego-uikit-prebuilt.js\"></script>");
        out.println("<script>");
        out.println("const userID = Math.floor(Math.random() * 10000) + '';");
        out.println("const userName = \"I'm Batman 🦇\";");
        out.println("const appID = 1276202388;");
        out.println("const serverSecret = \"e1965162b4785b92a134ba7bb38ba03c\";");
        out.println("const roomID = '" + roomID + "';");
        out.println("const TOKEN = ZegoUIKitPrebuilt.generateKitTokenForTest(");
        out.println("  appID,");
        out.println("  serverSecret,");
        out.println("  roomID,");
        out.println("  userID,");
        out.println("  userName");
        out.println(");");
        out.println("const zp = ZegoUIKitPrebuilt.create(TOKEN);");
        out.println("zp.joinRoom({");
        out.println("  container: document.querySelector(\"#root\"),");
        out.println("  sharedLinks: [");
        out.println("    {");
        out.println("      url:");
        out.println("        window.location.origin +");
        out.println("        window.location.pathname +");
        out.println("        \"?roomID=\" +");
        out.println("        roomID,");
        out.println("    },");
        out.println("  ],");
        out.println("  scenario: {");
        out.println("    mode: ZegoUIKitPrebuilt.VideoConference,");
        out.println("  },");
        out.println("});");
        out.println("</script>");
        out.println("</body>");
        out.println("</html>");
    }
}

