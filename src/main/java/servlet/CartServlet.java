package servlet;

import data.CDDB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import model.CD;
import model.Cart;

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
            CD cd = CDDB.getCD(request.getParameter("id"));
            if (cd != null) {
                cart.add(cd);
            }
        } else if ("update".equals(action)) {
            String id = request.getParameter("id");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            cart.update(id, quantity);
        } else if ("remove".equals(action)) {
            cart.remove(request.getParameter("id"));
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/cart.jsp");
        dispatcher.forward(request, response);
    }
}
