package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import util.MethodUtil;

import static view.Home_Controller.searchContent;

public class SearchList_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		search();
	}

	MethodUtil methodUtil = new MethodUtil();

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

	@FXML
	private Label titleLabel;
	
	public void search() {
		
		// 제목 변환
		titleLabel.setText("'" + searchContent + "'의 검색 결과입니다.");
		titleLabel.setAlignment(Pos.CENTER);
		
		// 검색한 게시글 가져오기
		sql = "select title from post where title like '" + searchContent + "%'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 테스트를 위해 출력문으로 작성한 것
				// 나중에 게시물 목록에 게시글이 나타나게 수정
				String title = rs.getString("title");
				System.out.println(title);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 홈 화면 전환
	@FXML
	private Button changeHomeBtn;

	public void changeHome() {
		methodUtil.changeScene("/view/Home_Layout.fxml", changeHomeBtn);
	}
	
}