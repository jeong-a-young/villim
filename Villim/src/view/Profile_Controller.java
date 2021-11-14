package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.JDBCUtill;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.AlertUtil;
import util.MethodUtil;

import static view.Login_Controller.userId;

public class Profile_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getInformation();
	}

	MethodUtil methodUtil = new MethodUtil();

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getConnection();

	// 1. Information

	// 회원정보 가져오기
	@FXML
	private Label memberName;
	@FXML
	private Label memberId;
	@FXML
	private Label memberEmail;

	public void getInformation() {

		sql = "select id, name, email from users where id='" + userId + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				memberId.setText(rs.getString("id"));
				memberName.setText(rs.getString("name"));
				memberEmail.setText(rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 2. NickName Change

	// 닉네임 변경 화면 팝업
	@FXML
	private Button popUpNickNameChangeBtn;
	@FXML
	private Stage popUpNickNameStage;

	public void popUpNickNameChange() {
		methodUtil.popUpScene(popUpNickNameChangeBtn, popUpNickNameStage, "/view/NickNameChange_Layout.fxml", "닉네임 변경");
	}

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

	// 3. Password Change

	// 비밀번호 변경 화면 팝업
	@FXML
	private Button popUpPasswordChangeBtn;
	@FXML
	private Stage popUpPasswordStage;

	public void popUpPasswordChange() {
		methodUtil.popUpScene(popUpPasswordChangeBtn, popUpPasswordStage, "/view/PasswordChange_Layout.fxml",
				"비밀번호 변경");
	}

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

	// 4. Profile Change

	// 5. Logout

	@FXML
	private Button logoutBtn;

	public void Logout() {
		userId = "";
		AlertUtil.informationAlert("로그아웃 되었습니다.", null);
		methodUtil.changeScene("/view/Start_Layout.fxml", logoutBtn);
	}

	// 6. Delete Account

	@FXML
	private Button deleteAccountBtn;

	public void deleteAccount() {
		sql = "delete from users where id='" + userId + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			userId = "";
			AlertUtil.informationAlert("탈퇴 처리되었습니다.", null);
			methodUtil.changeScene("/view/Start_Layout.fxml", deleteAccountBtn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 메인 화면 전환
	@FXML
	private Button changeMainBtn;

	public void changeMain() {
		methodUtil.changeScene("/view/Main_Layout.fxml", changeMainBtn);
	}

}