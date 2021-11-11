package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.cj.xdevapi.Statement;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

	
public class WritePost_Controller {
	@FXML
	private TextField write_title;
	@FXML
	private TextArea write_content;
	@FXML
	private Button write_success;

	Statement stmt = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getConnection();

	public void writePost() {

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
}
