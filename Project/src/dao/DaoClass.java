//自分で作った方　Dao DBM接続後

package dao;//このクラスは接続後のクラスです。SQL接続後に、DB（データベース）からどのような情報をとって来るのか

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//また、どのような形（クラス・インスタンス）に加工するのかを決めるクラス。

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import model.UserBeans;

public class DaoClass {
	//* ログインIDとパスワードに紐づくユーザ情報を返す
	//* @param loginId
	//* @param password
	//* @return　loginId, loginPass
	//このメソッドはログインの確認をして自分の情報を返すメソッド
	public UserBeans LoginInfo(String loginId, String loginPass) {//user（返す型は自分で決める）型で返すので、返す型を決める、またモデルを、インポートしないとエラーがでる。
		Connection conn = null;
		try {
			// データベースへ接続
			conn = DBManager.getConnection();//ここは定文型。DBManagerのメソッドgetConnection()が使えるようになる

			// SELECT文を準備
			String sql = "SELECT * FROM user WHERE login_id = ? and password = ?";
			//insert into user  (id, login_id, name, password, birth_date create_date,update_date)
			//vluse(loginid,)
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginId);//1ははセットする順番の値
			pStmt.setString(2, loginPass);
			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {// 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う。一意性制約(UNIQUE)なので同じ数字は存在しない。
				return null;
			}
			//String型の変数を作り、ResultSet(rs)のgetStringメソッドを使いDBのカラムlogin_idの値をとってきている
			String loginIdData = rs.getString("login_id");
			String nameData = rs.getString("name");
			String birthDate = rs.getString("birth_date");

			return new UserBeans(loginIdData, nameData, birthDate);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}

	}

	public List<UserBeans> findAll() {//sqlのuserテーブルの全てを配列でセット。
		Connection conn = null;
		List<UserBeans> userList = new ArrayList<UserBeans>();

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM user WHERE login_id not in ('admin')";

			PreparedStatement pStmt = conn.prepareStatement(sql);
			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				String birthDate = rs.getString("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				UserBeans user = new UserBeans(id, loginId, name, birthDate, password, createDate, updateDate);

				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}

	//このメソッドは自分の情報を保持するメソッドです。
	public UserBeans findInfoByID(String iD) {
		Connection conn = DBManager.getConnection();
		String sql = "SELECT * FROM user WHERE id = ?";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, iD);

			ResultSet rs = pStmt.executeQuery();

			if (!rs.next()) {
				return null;
			}
			int id = rs.getInt("id");
			String loginId = rs.getString("login_id");
			String name = rs.getString("name");
			String birthDate = rs.getString("birth_date");
			String password = rs.getString("password");
			String createDate = rs.getString("create_date");
			String updateDate = rs.getString("update_date");
			UserBeans user = new UserBeans(id, loginId, name, birthDate, password, createDate, updateDate);
			return user;

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();

				}
			}
		}
		return null;
	}

	//Delete.javaで使うメソッド　機能は削除。DeleteのdoPostで受け取った値を引数として持っている。戻り値は無し。
	public void Delete(String iD) {
		Connection conn = DBManager.getConnection(); //1
		//↑Connection conn = null;
		// try {
		// conn = DBManager.getConnection();//ここは定文型。DBManagerのメソッドgetConnection()が使えるようになる
		//１はこの文の略しの文です。
		String sql = "DELETE FROM user WHERE id = ?";
		try {
			PreparedStatement pSmt = conn.prepareStatement(sql);
			pSmt.setString(1, iD);
			pSmt.executeUpdate();

			//DELETE FROM テーブル名 WHERE 検索条件
			//DELETE FROM Product WHERE ProductID=2

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();

				} finally {
					// データベース切断
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	//このメソッドは、ユーザーの情報の更新です。
	public void Updata(String pa, String na, String bir, String id) {
		Connection conn = DBManager.getConnection();
		//暗号化
		String source = "pa";
		Charset charset = StandardCharsets.UTF_8;
		Date date = new Date();
		String algorithm = "MD5";
		byte[] bytes = null;
		try {
			bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		String result = DatatypeConverter.printHexBinary(bytes);
		System.out.println(result);
		//暗号化終わり。

		String str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);

		String sql = "UPDATE user SET name = ?, password = ?, birth_date = ?, update_date = ? WHERE id = ?";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, na);
			pStmt.setString(2, result);
			pStmt.setString(3, bir);
			pStmt.setString(4, str);
			pStmt.setString(5, id);
			pStmt.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void Updata2(String na, String bir, String id) {
		Connection conn = DBManager.getConnection();

		Date date = new Date();
		//日付を取得。
		String str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
		String sql = "UPDATE user SET name =?, birth_date = ?, update_date =? WHERE id =?";

		try {
			PreparedStatement pStm = conn.prepareStatement(sql);
			pStm.setString(1, na);
			pStm.setString(2, bir);
			pStm.setString(3, str);
			pStm.setString(4, id);
			pStm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<UserBeans> findSearch(String ID, String Name, String Bir, String Bir2 ) {//sqlのuserテーブルの全てを配列でセット。
		Connection conn = null;
		List<UserBeans> userList = new ArrayList<UserBeans>();

		try {
			conn = DBManager.getConnection();

			String sql = "SELECT * FROM user WHERE login_id not in ('admin')";
//" "ダブルクォーテーションの囲みはSQLの文
//' 'は？

//文字列の結合　"SELECT * FROM user WHERE login_id not in ('admin')"　に " AND login_id = '"  + ID +  "'"
//		上の結果がこの文	SELECT * FROM user WHERE login_id not in ('admin') AND login_id = 'ID'
			if(!ID.equals("")) {
				sql = sql +  " AND login_id = '"  + ID +  "'";
			}

			if(!Name.equals("")) {
				sql += " AND name like '%"  + Name +  "%'";
			}
			if(!Bir.equals("")) {
				sql += " AND birth_date > ' "  + Bir +  " ' ";
			}

			if(!Bir2.equals("")) {
				sql += " AND birth_date < '" + Bir2 + "'";
			}
			Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

			//System.out.println(sql);



			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String name = rs.getString("name");
				String birthDate = rs.getString("birth_date");
				String password = rs.getString("password");
				String createDate = rs.getString("create_date");
				String updateDate = rs.getString("update_date");
				UserBeans user = new UserBeans(id, loginId, name, birthDate, password, createDate, updateDate);

				userList.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// データベース切断
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return userList;
	}
}
