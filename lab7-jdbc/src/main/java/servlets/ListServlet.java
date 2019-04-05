package servlets;

import listeners.StartupListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setStatus(200);
        resp.setContentType("text/html;charset=UTF-8");

        var writer = resp.getWriter();
        writer.write("<html><head><title>User List</title></head><body><table><tbody>");
        var output = new StringBuilder();

        try{

            var resultSet = StartupListener.databaseManager.getUsers("name", "lastName", "id");
            while( resultSet.next() ){

                var name = resultSet.getString("name");
                var lastName = resultSet.getString("lastName");
                var id = resultSet.getString("id");

                output.append("<tr><td><a href=\" " + resp.encodeURL("showList?user=" + id) +"\">" + name + " " + lastName + "</a></td></tr>");

            }

            resultSet.close();
            writer.write(output.toString());
            writer.write("</tbody></table></body></html>");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
