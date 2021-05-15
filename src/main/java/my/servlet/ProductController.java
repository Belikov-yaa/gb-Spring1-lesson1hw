package my.servlet;

import my.servlet.classes.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductController", urlPatterns = "/products")
public class ProductController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(FirstServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int count = 10;
        try {
            count = Integer.parseInt(req.getParameter("count"));
            if (count < 0 || count > 25) count = 10;
        } catch (NumberFormatException e) {
            logger.debug(e.getMessage());
        }

//        getServletContext().getRequestDispatcher("/header.html").include(req, resp); //

        resp.getWriter().printf("<h1>New Products list:</h1>");
        resp.getWriter().printf("<p>id - title     : cost</p>");

        for (int i = 0; i < count; i++) {
            Product product = new Product(i, "Product N" + (i + 1), Math.random() * 10.0 * i);
            resp.getWriter().printf("<p>%2d - %s : %.2f</p>", product.id, product.title, product.cost);
        }

//        getServletContext().getRequestDispatcher("/footer.html").include(req, resp);
    }
}
