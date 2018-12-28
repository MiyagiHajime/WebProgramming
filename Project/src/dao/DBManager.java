package dao;//このクラスはデータベースへ接続する為のクラスです。データベースに接続するときはデータベース名だけ変えて使い回す。

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//DB接続処理の定文
public class DBManager {
	final private static String URL = "jdbc:mysql://localhost/";
	final private static String DB_NAME = "usermanagement"; //←作ったデータベースの名前
	final private static String USER = "root";
	final private static String PASS = "password";

	//DBへ接続するコネクションを返す
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL + DB_NAME, USER, PASS);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
}