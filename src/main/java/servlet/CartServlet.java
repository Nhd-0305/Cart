package servlet;

import data.ProductDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.LineItem;
import model.Product;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showCart(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productCode"));
            cart.add(productId);
        } else if ("update".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productCode"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            cart.update(productId, quantity);
        } else if ("remove".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productCode"));
            cart.remove(productId);
        } else if ("checkout".equals(action)) {
            session.removeAttribute("cart");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/thanks.jsp");
            dispatcher.forward(request, response);
            return;
        } else if ("continue".equals(action)) {
            response.sendRedirect(request.getContextPath() + "/index");
            return;
        }

        showCart(request, response);
    }

    private void showCart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        List<CartRow> cartRows = new ArrayList<>();
        double cartTotal = 0;
        if (cart != null) {
            for (LineItem item : cart.getItems()) {
                Product product = ProductDB.getProduct(item.getProductId());
                if (product != null) {
                    CartRow row = new CartRow(product, item.getQuantity());
                    cartRows.add(row);
                    cartTotal += row.getTotal();
                }
            }
        }
        request.setAttribute("cartRows", cartRows);
        request.setAttribute("cartTotal", cartTotal);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cart.jsp");
        dispatcher.forward(request, response);
    }
}
