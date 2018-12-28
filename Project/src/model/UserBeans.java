package model;//javaBeansのクラス。DB（データベース）から取得した値を格納するクラスです。使うときはこのクラスをインスタンス化して使う。

public class UserBeans {
	///フィールド
	private int id;
	private String loginId;
	private String name;
	private String birthDate;
	private String password;
	private String createDate;
	private String updateDate;

	///ログインセッションを保存する為のコンストラクタ
	public UserBeans(String loginId, String name ,String birthDate) {//// ログインセッションを保存するためのコンストラクタ
		this.setLoginId(loginId);
		this.setName(name);
		this.setBirthDate(birthDate);
	}
	//このメソッドは、フィールドの値を保存するコンストラクタ
	public UserBeans(int id, String loginId, String name, String birthDate, String password, String createDate,
				String updateDate) {
			this.setId(id);
			this.setLoginId(loginId);
			this.setName(name);
			this.setBirthDate(birthDate);
			this.setPassword(password);
			this.setCreateDate(createDate);
			this.setUpdateDate(updateDate);
		}
	public UserBeans() {
		// TODO 自動生成されたコンストラクター・スタブ
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
}

