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

@WebServlet("/UserUpdataSrevlet")
public class UserUpdataSrevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserUpdataSrevlet() {
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
			request.setAttribute("UserST", UB);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserUpdata.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String Pa = request.getParameter("Pass");
		String Pa2 = request.getParameter("Pass2");
		String Na = request.getParameter("UserName");
		String Bir = request.getParameter("Birthday");

		//名前、誕生日が合わないと更新出来ない
		if ( Na.length() == 0 || Bir.length() == 0) {
			request.setAttribute("errMsg", "入力に不備があります。");
			doGet(request, response);
		}
		//パスワードがnullなら。パスワード以外を更新。
		else if (Pa.length()==0) {
			DaoClass DC2 = new DaoClass();
			DC2.Updata2(Na, Bir, id);
			response.sendRedirect("UserListServlet2");
		}
		//パスワードが一致しないと変更出来ない。
		else if (!(Pa.equals(Pa2))) {
			request.setAttribute("errMsg", "パスワードに不備があります。");
			doGet(request, response);
		}
		//パスワードも変更。
		else {
			DaoClass DC = new DaoClass();
			DC.Updata(Pa, Na, Bir, id);
			response.sendRedirect("UserListServlet2");
		}

	}
}