package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;

public class Main_Controller {
	MethodUtil methodUtil = new MethodUtil();
	// 3. Main

	// 검색
	@FXML
	private TextField searchTextField;
	@FXML
	private Button searchBtn;

	public void search() {
		String searchContent = searchTextField.getText();

		// 검색 버튼 누르면 화면 전환하기
		methodUtil.changeScene("/view/Search_Layout.fxml", searchBtn);
	}

	// 버튼 화면 전환
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

	public static int postListCount = 0;
	public static int postListChangeCount = 0;

	public void changePostList() {
		postListCount++;
		postListChangeCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changePostListBtn);
	}

	// 카테고리
	@FXML
	private Button changeCategoryBtn;

	public static int categoryChangeCount = 0;

	public void changeCategory() {
		categoryChangeCount++;
		methodUtil.changeScene("/view/Category_Layout.fxml", changeCategoryBtn);
	}

}