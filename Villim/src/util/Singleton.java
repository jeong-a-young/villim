package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Singleton {
	// 싱글톤
	private static Singleton instance;

	private Singleton() {
		sceneList.add("/view/Start_Layout.fxml");
		writeSuccess = false;
	}

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

	// 로그인한 유저의 아이디
	private String accountId;
	private String accountNick;
	private String now2;
	private boolean writeSuccess;

	public boolean isWriteSuccess() {
		return writeSuccess;
	}

	public void setWriteSuccess(boolean writeSuccess) {
		this.writeSuccess = writeSuccess;
	}

	public String getNow2() {
		return now2;
	}

	public void setNow2(String now2) {
		this.now2 = now2;
	}

	public String getAccountNick() {
		return accountNick;
	}

	public void setAccountNick(String accountNick) {
		this.accountNick = accountNick;
	}

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
	
	private List<String> postList = new ArrayList<String>();
	public void setPostList(List<String> t) {
		Collections.sort(t, Collections.reverseOrder());
		this.postList = postList;
	}
	public List<String> getPostList(){
		return this.postList;
	}


}