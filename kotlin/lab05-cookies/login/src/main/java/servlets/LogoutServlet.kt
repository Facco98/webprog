package servlets

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LogoutServlet: HttpServlet() {

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {

        val cookies = req?.cookies.orEmpty()
        cookies.forEach {

            if (it.name == "jSessionID") {
                it.maxAge = 0
                resp?.addCookie(it)
            }

        }
        resp?.sendRedirect("login")
    }
}

