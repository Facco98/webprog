package login;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Optional;

public class LoginServlet extends HttpServlet {

    private final Hashtable<String, String> sessionIDToUser;

    public LoginServlet() {
        this.sessionIDToUser = new Hashtable<String, String>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<String> sessionID = Optional.empty();
        for( var cookie : req.getCookies() ) {
            if (cookie.getName().equals("loginCookie"))
                sessionID = Optional.ofNullable(cookie.getValue());

        }

        try{

            resp.setStatus(200);
            PrintWriter writer = new PrintWriter(resp.getOutputStream());
            resp.setContentType("text/html;charset=UTF-8");
            writer.write("<html><head><titleLogin</title></head><body>");
            if (sessionID.isPresent()) {

                var username = Optional.ofNullable(this.sessionIDToUser.get(sessionID.get())).orElse("error");
                writer.write("<p><b>Username: </b> " + username + " </p>" +
                        "<p><a href='/logout'>Logout</a></p>");

            }
            else{

                writer.write("<form action='/login' method='POST'>" +
                        "Username: <input type='text' name='username'><br>" +
                        "Password: <input type='password' name='psw'><br>" +
                        "<input type='submit' value='login'></form>");


            }

            writer.write("</body></html>");
            writer.close();


        } catch ( Exception ex ){
            ex.printStackTrace();
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var username = Optional.ofNullable(req.getParameter("username")).orElse("").trim();
        var password = Optional.ofNullable(req.getParameter("psw")).orElse("").trim();

        if(!username.equals("") && !password.equals("")){

            var session = username+System.nanoTime();
            this.sessionIDToUser.put(session, username);

            resp.setStatus(200);
            var cookie = new Cookie("loginCookie", session);
            cookie.setMaxAge(60);
            resp.addCookie(cookie);

        }

        resp.sendRedirect("/login");

    }
}
