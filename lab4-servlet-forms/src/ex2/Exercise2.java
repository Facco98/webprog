package ex2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Optional;

@WebServlet(name = "Exercise2", urlPatterns = "/ex2/compute")
public class Exercise2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var abilitato = Optional.ofNullable(req.getParameter("abilitato")).orElse("off").equals("off");
        var proseguire = Optional.ofNullable(req.getParameter("proseguire")).orElse("off").equals("off");
        var output = new BufferedWriter( new OutputStreamWriter(resp.getOutputStream()));
        output.write("<html><head><title>Exercise 2</title></head><body><b>Valore delle checkboxes: </b><br>");
        output.write("abilitato: " + Boolean.toString(abilitato) +" </br>");
        output.write("proseguire: " + Boolean.toString(proseguire) +" </br>");
        output.write("</body></html>");
        output.flush();
        output.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
