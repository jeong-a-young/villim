package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Statement;

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

	Statement stmt = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getConnection();

	@FXML
	private Label titleLabel;
	
	public void search() {
		// 제목 변환
		titleLabel.setText("'" + searchContent + "'의 검색 결과입니다.");
		titleLabel.setAlignment(Pos.CENTER);
	}

	// 홈 화면 전환
	@FXML
	private Button changeHomeBtn;

	public void changeHome() {
		methodUtil.changeScene("/view/Home_Layout.fxml", changeHomeBtn);
	}
	
}