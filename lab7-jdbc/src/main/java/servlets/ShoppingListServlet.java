package servlets;

import listeners.StartupListener;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var userID = Optional.ofNullable(req.getParameter("user"));
        resp.setContentType("text/html;charset=UTF-8");
        var writer = resp.getWriter();
        var output = new StringBuilder();

        userID.ifPresentOrElse(
                (id) -> {

                    try {

                        var resultSet = StartupListener.databaseManager.getShoppingList(Integer.parseInt(id));
                        output.append("<table>");
                        while( resultSet.next() ){

                            var name = resultSet.getString("sl_name");
                            var desc = resultSet.getString("sl_desc");

                            output.append("<tr><td><i>" + name +"</i></td><td>" + desc + "</td></tr>");

                        }

                        resultSet.close();

                        output.append("</table>");
                        resp.setStatus(200);

                    } catch (SQLException e) {
                        e.printStackTrace();
                        output.replace(0, output.length(), "");
                        output.append("<b>Error during query</b>");
                    }


                },

                () -> {
                    resp.setStatus(400);
                    output.append("<b>400: Malformed Request</b><br>You <i>need</i> to provide a user ID.");
                }
        );

        writer.write("<html><head><title>Shopping List</title></head><body>");
        writer.write(output.toString());
        writer.write("<a href=\""+resp.encodeURL(this.getServletContext().getContextPath())+"\">Indietro</a>");
        writer.write("</body></html>");

    }
}
