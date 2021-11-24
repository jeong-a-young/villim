package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;
import util.Singleton;

public class Home_Controller implements Initializable {
	// 이 클래스가 실행되면 호출되는 메소드 ^ 이거 있어야함 ^
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (Singleton.getInstance().sceneList.get(Singleton.getInstance().sceneList.size() - 1)
				.equals("/view/Login_Layout.fxml")) {
			alert("반갑습니다 " + Singleton.getInstance().getAccountId() + "님");
		}
		if (Singleton.getInstance().isWriteSuccess()) {
			alert("게시물을 업로드하였습니다");
			Singleton.getInstance().setWriteSuccess(false);
		}
		//postListPage.set
		
	}
	//private final ChangeListener<Number> paginationChangeListener = (observable, oldValue, newValue) -> changePage();

	@FXML
	public Pagination postListPage;
	// 알림창
	@FXML
	public Pane alertPane;
	@FXML
	public Text alertText;
	public boolean isAliveThread;

	public void alert(String text) {
		Thread t = new Thread() {
			public void run() {
				try {
					isAliveThread = true;
					Thread.sleep(500);
					for (int i = -15; i <= 0; i++) {
						alertPane.setLayoutY(i * 4);
						Thread.sleep(25);
					}
					Thread.sleep(2000);
					for (int i = 0; i >= -15; i--) {
						alertPane.setLayoutY(i * 4);
						Thread.sleep(25);
					}
					isAliveThread = false;
				} catch (Exception e) {
					Singleton.getInstance().debug(getClass().getName() + " 쓰레드 오류: " + e);
				}

			}
		};
		alertText.setText(text);
		if (isAliveThread) {
			return;
		}
		t.start();
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

	public void changePostList() {
		Singleton.getInstance().setCurrentCategory("전체");
		methodUtil.changeScene("/view/PostList_Layout.fxml", changePostListBtn);
	}

	// 카테고리
	@FXML
	private Button changeCategoryBtn;

	public void changeCategory() {
		methodUtil.changeScene("/view/Category_Layout.fxml", changeCategoryBtn);
	}

	// 이전 화면으로 가는 코드
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
	}
}