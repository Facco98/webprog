package ex1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Optional;

public class Exercise1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        var firstName = Optional.ofNullable(req.getParameter("first_name")).orElse("").trim();
        var lastName = Optional.ofNullable(req.getParameter("last_name")).orElse("").trim();
        resp.setContentType("text/html");
        resp.setStatus(200);
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(resp.getOutputStream()));
        output.write("<html><head><title>Exercise 1</title></head><body>");
        if( firstName.equals("") || lastName.equals("") )
            output.write("First name or last name is empty");
        else
            output.write("<p>First Name: " + firstName + "</p><p>Last name: " + lastName + "</p>");
        output.write("</body></html>");
        output.newLine();
        output.flush();
        output.close();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        this.doGet(req, resp);

    }

}
