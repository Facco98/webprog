package ex3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Exercise3 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        var params = req.getParameterMap();
        resp.setStatus(200);
        resp.setContentType("text/html");
        var output = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()));
        output.write("<html><head><title>Exercise 3</title></head><body>");
        params.forEach((key, values) -> {

            try {
                output.write("<span><b>" +key+"</b><br>" );
                for( var i : values )
                    output.write(i + "<br>");
                output.write("</span>");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        output.write("</body></html>");
        output.newLine();
        output.flush();
        output.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
