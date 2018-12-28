//新規登録を行うサーブレット。
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.newUserDao;
import model.UserBeans;

@WebServlet("/NewUser")
public class NewUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewUser() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBeans UB = (UserBeans) session.getAttribute("userInfo");

		if (UB.getLoginId().equals("admin")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserNew.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("LoginServlet2");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String loginid = request.getParameter("id");
		String pass = request.getParameter("pass");
		String passConfi = request.getParameter("pass2");
		String userName = request.getParameter("name");
		String barthday = request.getParameter("birthday");

		if (loginid.length() == 0 || pass.length() == 0 || passConfi.length() == 0 || userName.length() == 0
				|| barthday.length() == 0) {
			request.setAttribute("errMsg", "入力に不備があります。");
			doGet(request, response);
		} else if (!(pass.equals(passConfi))) {
			request.setAttribute("errMsg", "パスワードが一致しません。");
			doGet(request, response);

		} else {

			newUserDao nd = new newUserDao();
			boolean check = nd.CreateNewUser(loginid, pass, userName, barthday);
			if (check == false) {
				request.setAttribute("errMsg", "入力に不備があります。");
				doGet(request, response);
			} else {
				response.sendRedirect("UserListServlet2");
			}
			//doGet(request, response);
		}

	}
}