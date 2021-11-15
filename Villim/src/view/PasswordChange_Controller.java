package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import util.AlertUtil;

import static view.Login_Controller.userId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import database.JDBCUtill;

public class PasswordChange_Controller {

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getConnection();
	
	// 비밀번호 변경
	@FXML
	private TextField MemberPassword;
	@FXML
	private TextField MemberPasswordCheck;

	public void passwordChange() {

		String password = MemberPassword.getText();
		String passwordCheck = MemberPasswordCheck.getText();
		sql = "update users set password='" + password + "' WHERE id='" + userId + "'";

		try {
			if (passwordCheck.equals(password)) {
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				// 변경된 회원정보를 새로고침 하려면?
				AlertUtil.informationAlert("변경에 성공했습니다.", null);
			} else {
				AlertUtil.warningAlert("비밀번호가 일치하지 않습니다.", "비밀번호 변경 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}