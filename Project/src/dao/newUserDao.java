package dao;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

public class newUserDao {

	public Boolean CreateNewUser(String id, String pass, String name, String birthday) {
	Connection conn = null;
	//暗号化の文
	String source = "pass";
	Charset charset = StandardCharsets.UTF_8;
	String algorithm = "MD5";
	byte[] bytes = null;
	try {
		bytes = MessageDigest.getInstance(algorithm).digest(source.getBytes(charset));
	} catch (NoSuchAlgorithmException e1) {
		// TODO 自動生成された catch ブロック
		e1.printStackTrace();
	}
	String result = DatatypeConverter.printHexBinary(bytes);
	System.out.println(result);
	//暗号化終わり。

	Date date = new Date();//入力の日付を取得。
	String str = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
        // データベースへ接続
    try {
    	//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/example?useUnicode=true&characterEncoding=utf8", "root", "password");
    	conn = DBManager.getConnection();

    	//↓ここから下の文では、同じidがないか、検索をしている。
        String sql1 = "select * from user where id = ?";
	    PreparedStatement pStmt2 = conn.prepareStatement(sql1);
		pStmt2.setString(1, id);//1ははセットする順番の値
		ResultSet rs = pStmt2.executeQuery();

		if (rs.next()) {// 主キーに紐づくレコードは1件のみなので、rs.next()は1回だけ行う。一意性制約(UNIQUE)なので同じ数字は存在しない。
			return false;
		}else {

		String sql = "Insert into user (login_id, name, password, birth_date, create_date, update_date)"
	    	    + "values (?,?,?,?,?,?)";
	    	    PreparedStatement pStmt = conn.prepareStatement(sql);

	    		pStmt.setString(1, id);//1ははセットする順番の値
	    		pStmt.setString(2, name);
	    		pStmt.setString(3, result);	//暗号化されたパスワードが入っている。
	    		pStmt.setString(4, birthday);
	    		pStmt.setString(5, str);
	    		pStmt.setString(6, str);
	    		pStmt.executeUpdate();
	    		return true;
		}
    } catch (SQLException e) {

		e.printStackTrace();
	}
    return false;
}
}