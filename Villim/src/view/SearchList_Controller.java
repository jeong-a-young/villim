package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Statement;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

	public void search() {
		
	}

	// 메인 화면 전환
	@FXML
	private Button changeMainBtn;

	public void changeMain() {
		methodUtil.changeScene("/view/Main_Layout.fxml", changeMainBtn);
	}
	
}