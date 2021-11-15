package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import static view.Login_Controller.userId;

public class WritePost_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		categoryComboBox.setItems(categoryItems);
	}

	MethodUtil methodUtil = new MethodUtil();

	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

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

	public void writePost() {	
		try {
			String title = write_title.getText();
			String content = write_content.getText();
			String category = categoryComboBox.getValue();
			String writer_id = userId;
			int recommend = 0;
			Date date_now = new Date(System.currentTimeMillis());
			SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyy년 MM월 dd일");
			String now = fourteen_format.format(date_now);

			sql = "INSERT INTO post VALUES(?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, category);
			pstmt.setInt(4, recommend);
			pstmt.setString(5, now);
			pstmt.setString(6, writer_id);

			int r = pstmt.executeUpdate();
			System.out.println("작성 성공 " + r);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("작성 실패");
		}
	}

	// 홈 화면 전환
	@FXML
	private Button changeHomeBtn;

	public void changeHome() {
		methodUtil.changeScene("/view/Home_Layout.fxml", changeHomeBtn);
	}

}