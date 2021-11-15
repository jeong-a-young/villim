package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.MethodUtil;
import util.Singleton;

public class Home_Controller implements Initializable {
	// 이 클래스가 실행되면 호출되는 메소드 ^ 이거 있어야함 ^
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// 현재 화면의 이전 화면을 변수
		Singleton.getInstance().setPreviousLayoutClass(Singleton.getInstance().getCLC());
		// 현재 화면을 나타내는 변수
		Singleton.getInstance().setCLC(getClass().getSimpleName());

	}

	MethodUtil methodUtil = new MethodUtil();

	// 검색
	@FXML
	private TextField searchTextField;
	@FXML
	private Button searchBtn;

	public static String searchContent = "";

	public void changeSearch() {
		searchContent = searchTextField.getText();
		methodUtil.changeScene("/view/SearchList_Layout.fxml", searchBtn);
	}

	// 게시물 작성
	@FXML
	private Button changeWritePostBtn;

	public void changeWritePost() {
		methodUtil.changeScene("/view/WritePost_Layout.fxml", changeWritePostBtn);
	}

	// 채팅
	@FXML
	private Button changeChattingBtn;

	public void changeChatting() {
		methodUtil.changeScene("/view/Chatting_Layout.fxml", changeChattingBtn);
	}

	// 게시물 목록
	@FXML
	private Button changePostListBtn;

	public static String ChangeCheck;

	public void changePostList() {
		methodUtil.changeScene("/view/PostList_Layout.fxml", changePostListBtn);
	}

	// 카테고리
	@FXML
	private Button changeCategoryBtn;

	public void changeCategory() {
		methodUtil.changeScene("/view/Category_Layout.fxml", changeCategoryBtn);
	}

}