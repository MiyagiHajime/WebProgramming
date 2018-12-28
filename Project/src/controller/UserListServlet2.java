package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DaoClass;
import model.UserBeans;


@WebServlet("/UserListServlet2")
public class UserListServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public UserListServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();//セッションを使う時は必ず使う。

		UserBeans ub = (UserBeans) session.getAttribute("userInfo");

		if(ub == null) {
			response.sendRedirect("LoginServlet2");//sendRedairectとフォワードは被せないようにする。
		}
		else {

		DaoClass DC = new DaoClass();
		//DaoClassのインスタンスを作成

		List<UserBeans> UB = DC.findAll();
		//DaoClassのメソッドfindAll()を実行。実行結果をUserBeans型の配列UBに格納。

		request.setAttribute("list", UB);
		//リクエストスコープでJSPに渡す為に変数名"list"にUBを格納！！
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserList.jsp");
		dispatcher.forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String ID = request.getParameter("id");
		String Name = request.getParameter("name");
		String Bir = request.getParameter("birthday");
		String Bir2 =request.getParameter("birthday2");

		DaoClass DC = new DaoClass();

		List<UserBeans> UB = DC.findSearch(ID, Name, Bir, Bir2);
		//DaoClassのメソッドfindAll()を実行。実行結果をUserBeans型の配列UBに格納。

		request.setAttribute("list", UB);
		//リクエストスコープでJSPに渡す為に変数名"list"にUBを格納！！
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/UserList.jsp");
		dispatcher.forward(request, response);
	}

}
