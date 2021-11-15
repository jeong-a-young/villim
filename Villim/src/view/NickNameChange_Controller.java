package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import util.AlertUtil;

import static view.Login_Controller.userId;

public class NickNameChange_Controller {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getConnection();

	// 닉네임 변경
	@FXML
	private TextField memberNickName;

	public void nickNameChange() {

		String NickName = memberNickName.getText();
		sql = "update users set name='" + NickName + "' WHERE id='" + userId + "'";

		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			// 변경된 회원정보를 새로고침 하려면?
			AlertUtil.informationAlert("변경에 성공했습니다.", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}