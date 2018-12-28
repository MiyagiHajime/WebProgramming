package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoClass;
import model.UserBeans;

@WebServlet("/More")
public class More extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public More() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBeans ub = (UserBeans) session.getAttribute("userInfo");

		if (ub == null) {
			response.sendRedirect("LoginServlet2");
		}
		else {
			String ID = request.getParameter("id");
			DaoClass DC = new DaoClass();
			UserBeans UB = DC.findInfoByID(ID);
			request.setAttribute("A", UB);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Userreference.jsp");
			dispatcher.forward(request, response);

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
