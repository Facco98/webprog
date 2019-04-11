package servlets

import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginServlet(private val sessionToUsername: HashMap<String, String> = HashMap()): HttpServlet(){

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {

        val cookies = req?.cookies.orEmpty()
        var sessionID: String = ""
        for( cookie in cookies ){

            if( cookie.name == "jSessionID" )
                sessionID = cookie.value

        }

        val writer = resp?.writer!!

        writer.println("<html><head><title>Login</title></head><body>")

        if( sessionID.trim().isBlank() ){

            writer.println("<form action='/login' method='POST'>" +
                    "Username: <input type='text' name='username'><br>" +
                    "Password: <input type='password' name='psw'><br>" +
                    "<input type='submit' value='login'></form>")



        } else{

            val username = this.sessionToUsername[sessionID].orEmpty()
            if(!username.isEmpty())
                writer.println("<p><b>Username: </b> ${username}</p>" +
                    "<p><a href='/logout'>Logout</a></p>")

        }
    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {

        val username = req?.getParameter("username").orEmpty().trim()
        val psw = req?.getParameter("psw").orEmpty().trim()
        if( !username.isEmpty() && !psw.isEmpty() ) {
            val id = java.util.UUID.randomUUID().toString()
            this.sessionToUsername[id] = username
            val cookie = Cookie("jSessionID",id)
            cookie.maxAge = 60
            resp?.addCookie(cookie)
        }
        resp?.sendRedirect("login")

    }
}