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

@WebServlet("/LoginServlet2")
public class LoginServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet2() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Userlogin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");//requestの文字コードを指定する定文

		String loginId = request.getParameter("id");//jspのfromタグで受け取った値を入れる。
		String loginPass = request.getParameter("pass");//getgetParameterの中はjspのnameの名前

		DaoClass DC = new DaoClass();// Daoクラスを使う為にインスタンスを作成。
		UserBeans user = DC.LoginInfo(loginId, loginPass);//DaoのfindByLoginInfoに上のJSPで受け取った値を引数として渡す。

		/** テーブルに該当のデータが見つからなかった場合 **/
		if (user == null) {
			// リクエストスコープにエラーメッセージをセット
			request.setAttribute("errMsg", "ログインに失敗しました。");//(errMsgにログインに失敗しました。入れる、jspでerrMsgを呼ぶ)

			// ログインjspにフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/Userlogin.jsp");//ここのフォワード先を変える。
			dispatcher.forward(request, response);
			return;
		}

		// セッションにユーザの情報をセット セッションの中身はどこでも取り出せる（セッションスコープ）
		HttpSession session = request.getSession();
		session.setAttribute("userInfo", user);

		// ユーザ一覧のサーブレットにリダイレクト
		response.sendRedirect("UserListServlet2");//ここのリダイレクト先を変える
	}
}