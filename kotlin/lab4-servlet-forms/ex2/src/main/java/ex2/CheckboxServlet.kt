package ex2

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class CheckboxServlet: HttpServlet(){

    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {

        val abilitato = !req?.getParameter("abilitato").orEmpty().trim().isEmpty()
        val proseguire = !req?.getParameter("proseguire").orEmpty().trim().isEmpty()

        resp?.setStatus(200)
        resp?.contentType = "text/html;charset=UTF-8"

        val writer = resp?.writer
        writer?.println("<html><head><title>Exercise 2</title></head><body>")
        writer?.println("<b>Abilitato: </b> ${abilitato}<br>")
        writer?.println("<b>Proseguire: </b> ${proseguire}<br>")
        writer?.println("</body></html>")


        writer?.close()
    }

}