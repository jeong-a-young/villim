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
import javafx.scene.text.TextAlignment;
import util.MethodUtil;

import static view.Main_Controller.searchContent;

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

	// 메인 화면 전환
	@FXML
	private Button changeMainBtn;

	public void changeMain() {
		methodUtil.changeScene("/view/Main_Layout.fxml", changeMainBtn);
	}
	
}