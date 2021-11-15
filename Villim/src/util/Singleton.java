package util;

public class Singleton {
	//싱글톤
	private static Singleton instance;
	private Singleton(){
	}
	public static Singleton getInstance(){
		if(instance == null){
			instance = new Singleton();
		}
		return instance;
	}
	//로그인한 유저의 아이디
	private String accountId;
	//현재 화면 레이아웃의 컨트롤러 클래스 이름
	private String currentLayoutClass;
	//이전 화면 레이아웃의 컨트롤러 클래스 이름
	private String previousLayoutClass;
	//현재 카테고리 이름 (카테고리 컨트롤러 전용)
	private String currentCategory;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPreviousLayoutClass() {
		return previousLayoutClass;
	}

	public void setPreviousLayoutClass(String previousLayoutClass) {
		this.previousLayoutClass = previousLayoutClass;
	}

	public String getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(String currentCategory) {
		this.currentCategory = currentCategory;
	}

	public void setCLC(String text) {
		currentLayoutClass = text;
	}

	public String getCLC() {
		return currentLayoutClass;
	}

}