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

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Delete() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBeans UB = (UserBeans) session.getAttribute("userInfo");
		if (UB == null) {
			response.sendRedirect("LoginServlet2");
		}
		else {
			String ID = request.getParameter("id");
			DaoClass D = new DaoClass();
			UserBeans ub = D.findInfoByID(ID);
			request.setAttribute("A", ub);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserDelete.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String ID = request.getParameter("id");
		DaoClass DC = new DaoClass();
		DC.Delete(ID);
		response.sendRedirect("UserListServlet2");
	}

	// TODO 自動生成されたメソッド・スタブ

}
