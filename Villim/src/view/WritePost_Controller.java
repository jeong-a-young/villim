package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Statement;

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

public class WritePost_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		category.setItems(categoryItems);
	}

	MethodUtil methodUtil = new MethodUtil();

	Statement stmt = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getConnection();

	@FXML
	private TextField write_title;
	@FXML
	private TextArea write_content;
	@FXML
	private Button write_success;
	@FXML
	private ComboBox<String> category = new ComboBox<String>();

	private ObservableList<String> categoryItems = FXCollections.observableArrayList("의상 / 소품", "음반 / 악기", "전자기기",
			"헬스 / 요가", "스포츠 / 레저", "등산 / 낚시 / 캠핑", "도서 / 문구", "유아 용품", "반려동물 용품", "기타");

	public void writePost() {

		// 카테고리 값 가져오기
		String selectCategory = category.getValue();

		// DB에 넣기
		try {
			String title = write_title.getText();
			String content = write_content.getText();
			int recommend = 0;
			String category = "";
			String writer_id = "정은교";
			System.out.println(title + content);
			Date date_now = new Date(System.currentTimeMillis());
			SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyy년 MM월 dd일");
			String now = fourteen_format.format(date_now);

			sql = "INSERT INTO post VALUES(?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			if (title == "") {
				pstmt.setString(1, title);
			}
			if (content == "") {
				pstmt.setString(2, content);
			}
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

	// 메인 화면 전환
	@FXML
	private Button changeMainBtn;

	public void changeMain() {
		methodUtil.changeScene("/view/Main_Layout.fxml", changeMainBtn);
	}

}