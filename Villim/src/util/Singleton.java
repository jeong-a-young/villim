package util;

import java.util.ArrayList;
import java.util.List;

public class Singleton {
	// 싱글톤
	private static Singleton instance;

	private Singleton() {
		sceneList.add("/view/Start_Layout.fxml");
	}

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	// 로그인한 유저의 아이디
	private String accountId;
	// 현재 카테고리 이름 (카테고리 컨트롤러 전용)
	private String currentCategory;
	// 레이아웃 기록 (이전 화면 돌아가기 전용)
	public List<String> sceneList = new ArrayList<>();

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCurrentCategory() {
		return currentCategory;
	}

	public void setCurrentCategory(String currentCategory) {
		this.currentCategory = currentCategory;
	}
	public void debug(String t) {
			System.out.println("[ 디버그 ] " + t);
	}

}