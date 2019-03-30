package simplecart;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

public class SimpleCart extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var session = req.getSession(true);
        var items = Optional.ofNullable((HashMap<String, Integer>) session.getAttribute("items")).orElse(new HashMap<>());
        var writer = resp.getWriter();
        writer.println("<html><head><title>Shopping Cart</title></head><body>");
        System.out.println(items);
        if( !items.isEmpty() ) {
            writer.print("<h3>Shopping List</h3><ul>");
            items.forEach((product, qty) -> {

                writer.println("<li>" + product + " x " + qty + "</li>");

            });
            writer.println("</ul><br>");
        }

        writer.println("<form action='buyItems' method='POST'>" +
                "Insert the product: <input type='text' name='item'><br>" +
                "<input type='submit' value='Compra'><br></body></html>");
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var session = req.getSession(true);
        var items = Optional.ofNullable((HashMap<String, Integer>) session.getAttribute("items")).orElse(new HashMap<>());
        var item = Optional.ofNullable(req.getParameter("item")).orElse("").trim();

        if(!item.equals("")){

            if(!items.containsKey(item))
                items.put(item, 0);
            items.replace(item, items.get(item) + 1);

        }

        session.setAttribute("items", items);

        resp.sendRedirect(resp.encodeURL("buyItems"));

    }
}
