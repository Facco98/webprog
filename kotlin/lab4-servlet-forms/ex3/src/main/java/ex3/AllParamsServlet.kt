package ex3

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AllParamsServlet: HttpServlet(){

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {

        val params = req?.parameterNames!!

        resp?.setStatus(200)
        resp?.contentType = "text/html;charset=UTF-8"

        val outputString = StringBuffer()

        for( k in params ) {

            outputString.append("<li><b>${k}</b>: ${req?.getParameter(k.toString())}</li>")

        }

        val writer = resp?.writer!!
        writer.println("<html><head><title>All parameters</title></head><body><ul>")
        writer.println("")
        writer.println("${outputString}")
        writer.println("</ul></body></html>")

        writer.close()

    }

}