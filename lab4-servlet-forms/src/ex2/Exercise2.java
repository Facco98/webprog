package ex2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Optional;

public class Exercise2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var abilitato = !Optional.ofNullable(req.getParameter("abilitato")).isEmpty();
        var proseguire = !Optional.ofNullable(req.getParameter("proseguire")).isEmpty();
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
