package project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project.dao.POrderDAO;
import project.dao.POrderDetailDAO;
import project.dao.PProductDAO;
import project.dto.PCartSession;
import project.model.POrder;
import project.model.POrderDetail;
import project.model.PProduct;

/**
 * Servlet implementation class PAddToCart
 */
@WebServlet("/PCart")
public class PCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PCart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String command = request.getParameter("command");
			int productId = 0;
			PProductDAO pProductDao = new PProductDAO();
			// int quantity = Integer.parseInt(request.getParameter("quantity"));
			if (command != null && command.equals("ADD_TO_CART")) {
				productId = Integer.parseInt(request.getParameter("productId"));
				PProduct product = pProductDao.getProductsById(productId);

				HttpSession session = request.getSession();
				PCartSession cart = (PCartSession) session.getAttribute("cart");

				if (cart == null) {
					cart = new PCartSession();
				}
				boolean isAddedSuccess = cart.getProducts().add(product);
				if (isAddedSuccess) {
					// cart.setSubTotalPrice(product.getPrice()*quantity);
					// cart.setTotalPrice(cart.getTotalPrice()+cart.getSubTotalPrice());
					cart.setTotalPrice(cart.getTotalPrice() + product.getDiscountedPrice());

					session.setAttribute("cart", cart);
					session.setAttribute("product", product);
					response.sendRedirect("PShop");
				}

			} else if (command != null && command.equals("REMOVE")) {
				HttpSession session = request.getSession();
				PCartSession cart = (PCartSession) session.getAttribute("cart");
				productId = Integer.parseInt(request.getParameter("productId"));
				PProduct product = pProductDao.getProductsById(productId);
				cart.getProducts().remove(product);
				cart.setTotalPrice(cart.getTotalPrice() - product.getDiscountedPrice());
				response.sendRedirect("cart.jsp");

			} else if (command != null && command.equals("CHECK_OUT")) {
				
				HttpSession session = request.getSession();
				PCartSession cart = (PCartSession) session.getAttribute("cart");
				
				// create order - return order id
				int userId = (int) session.getAttribute("userId");
				POrder order = new POrder(userId, false);
				int orderId = new POrderDAO().addOrder(order);
				System.out.println(orderId);
				
				// create order detail
				for(PProduct product : cart.getProducts()) {
					POrderDetail orderDetail = new POrderDetail(orderId, product.getId());
					POrderDetailDAO addOrder = new POrderDetailDAO();
					addOrder.inputOrderToDB(orderDetail);
					System.out.println(orderDetail.getProductId());
				}
				// create order summary:
				session.setAttribute("orderSummary", cart);				
				session.removeAttribute("cart");
				response.sendRedirect("checkout.jsp");				
			}			
			else {
				response.sendRedirect("cart.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
