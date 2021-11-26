package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
		batchPost();
		// postListPage.set

	}

	@FXML
	public Pane postPane1;
	@FXML
	public Pane postPane2;
	@FXML
	public Pane postPane3;
	@FXML
	public Pane postPane4;
	
	@FXML
	public Text recommand2;
	@FXML
	public Text title2;
	@FXML
	public Text category2;
	@FXML
	public Text writer2;
	@FXML
	public Text time2;
	@FXML
	public ImageView image2;
	
	@FXML
	public Text recommand3;
	@FXML
	public Text title3;
	@FXML
	public Text category3;
	@FXML
	public Text writer3;
	@FXML
	public Text time3;
	@FXML
	public ImageView image3;
	
	@FXML
	public Text recommand4;
	@FXML
	public Text title4;
	@FXML
	public Text category4;
	@FXML
	public Text writer4;
	@FXML
	public Text time4;
	@FXML
	public ImageView image4;

	public void batchPost() {
		List<String> postList = new ArrayList<String>();
		String a = "";
		try {
			Connection conn = JDBCUtill.getInstance().getConnection();
			String sql = "SELECT id, title, content, category, now, recommand, code FROM resource";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println(rs.getString("id"));
				a = rs.getString("title");
				System.out.println(rs.getString("content"));
				System.out.println(rs.getString("category"));
				System.out.println(rs.getString("now"));
				System.out.println(rs.getInt("recommand"));
				postList.add(rs.getString("code"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		Singleton.getInstance().setPostList(postList);
		if (a.length() >= 10) {
			System.out.println(a.substring(0, 10) + "...");
		}
		else {
			System.out.println(a);
		}
	}
	// private final ChangeListener<Number> paginationChangeListener =
	// (observable,oldValue, newValue) -> changePage();

	@FXML
	public Pagination postListPage;

	// private void changePage() { label.setText(String.format("Current Page:
	// %d",postListPage.getCurrentPageIndex())); }

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
		if (searchContent.isEmpty()) {
			alert("검색어를 입력해 주세요.");
		} else {
			methodUtil.changeScene("/view/SearchList_Layout.fxml", searchBtn);
		}
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