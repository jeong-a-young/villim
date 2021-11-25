package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.JDBCUtill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import util.MethodUtil;
import util.Singleton;

public class EditPost_Controller implements Initializable {
	// 이 클래스가 실행되면 호출되는 메소드 ^ 이거 있어야함 ^
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getPost();
		categoryComboBox.setItems(categoryItems);
	}

	MethodUtil methodUtil = new MethodUtil();

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

	// 원래 게시물 내용 가져오기
	@FXML
	private TextField write_title;
	@FXML
	private TextArea write_content;
	@FXML
	private Button write_success;
	@FXML
	private ComboBox<String> categoryComboBox = new ComboBox<String>();

	private ObservableList<String> categoryItems = FXCollections.observableArrayList("의상 / 소품", "음반 / 악기", "전자기기",
			"헬스 / 요가", "스포츠 / 레저", "등산 / 낚시 / 캠핑", "도서 / 문구", "유아 용품", "반려동물 용품", "기타");

	public void getPost() {
		sql = "select * from post where writer_id='" + Singleton.getInstance().getAccountId() + "'"; // 해당 게시글을 찾아오는
																										// sql문을 어떻게 짤까
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				write_title.setText(rs.getString("title"));
				write_content.setText(rs.getString("content"));
				categoryComboBox.setValue(rs.getString("category"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게시물 화면 전환
	@FXML
	private Button changeViewPostBtn;

	public void changeViewPost() {
		methodUtil.changeScene("/view/ViewPost_Layout.fxml", changeViewPostBtn);
	}

	// 이전 화면으로 가는 코드
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
	}

}