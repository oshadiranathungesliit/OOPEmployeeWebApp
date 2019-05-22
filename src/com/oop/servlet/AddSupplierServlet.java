package com.oop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oop.model.Supplier;
import com.oop.service.SupplierServiceImpl;
import com.oop.service.ISupplierService;

/**
 * Servlet implementation class LoginServlet
 */
public class AddSupplierServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSupplierServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		Supplier supplier = new Supplier();
		
		supplier.setName(request.getParameter("supplierName"));
		supplier.setAddress(request.getParameter("address"));
		supplier.setDesignation(request.getParameter("designation"));
		supplier.setStockName(request.getParameter("StockName"));
		supplier.setDepartment(request.getParameter("department"));
		supplier.setStockKeeper(request.getParameter("StockKeeper"));
		supplier.setOrigin(request.getParameter("origin"));

		ISupplierService iSupplierService = new SupplierServiceImpl();
		iSupplierService.addSupplier(supplier);

		request.setAttribute("supplier", supplier);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListSupplier.jsp");
		dispatcher.forward(request, response);
	}

}
