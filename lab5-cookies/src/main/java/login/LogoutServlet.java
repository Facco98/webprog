package login;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        for( var cookie : req.getCookies() ) {

            if (cookie.getName().equals("loginCookie")) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }

        }

        resp.sendRedirect("/login");

    }
}
