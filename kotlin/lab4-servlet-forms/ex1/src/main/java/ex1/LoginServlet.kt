package ex1

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class LoginServlet: HttpServlet(){

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {

        val first_name = req?.getParameter("first_name").orEmpty()
        val last_name = req?.getParameter("last_name").orEmpty()


        if( first_name.trim().equals("") || last_name.trim().equals("") )
            resp?.sendRedirect("index.html")
        else {

            resp?.setStatus(200)
            resp?.contentType = "text/html;charset=UTF-8"

            val writer = resp?.writer
            writer?.print("<html><head><title>Exercise 1</title></head><body>")
            writer?.print("<b>First name</b>: ${first_name}<br>")
            writer?.print("<b>Last name</b>: ${last_name}<br>")

            writer?.close()

        }
    }

}