package accesscounter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class AccessCounter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var session = req.getSession(true);
        var counter = Optional.ofNullable((Integer) session.getAttribute("counter")).orElse(0);

        var id = session.getId();
        var message = "";

        if( counter == 0 )
            message = "Welcome, my friend";
        else {
            message = "Welcome back for the " + counter;
            switch( (counter) % 10 ) {

                case 1:
                    message = message.concat("st ");
                    break;

                case 2:
                    message = message.concat("nd ");
                    break;

                case 3:
                    message = message.concat("rd ");
                    break;

                default:
                    message = message.concat("th ");

            }

            message = message.concat("time.");
        }

        session.setAttribute("counter", counter+1);
        System.out.println(session.getAttribute("counter"));
        var writer = new PrintWriter(resp.getOutputStream());
        writer.print("<html><head><title>Access counter</title></head><body>" +
                "<p>" + message + "</p>");
        writer.print("<p>Session ID: " +id + "</p></body></html>" );
        writer.close();


    }
}
