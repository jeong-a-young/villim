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
	private TextField nickName;

	public void nickNameChange() {
	}

	// 3. Password Change
	
	// 비밀번호 변경 화면 팝업
	@FXML
	private Button popUpPasswordChangeBtn;
	@FXML
	private Stage popUpPasswordStage;

	public void popUpPasswordChange() {
		methodUtil.popUpScene(popUpPasswordChangeBtn, popUpPasswordStage, "/view/PasswordChange_Layout.fxml", "비밀번호 변경");
	}

	// 비밀번호 변경
	@FXML
	private TextField password;
	@FXML
	private TextField passwordCheck;

	public void passwordChange() {
	}

	// 메인 화면 전환
	@FXML
	private Button changeMainBtn;

	public void changeMain() {
		methodUtil.changeScene("/view/Main_Layout.fxml", changeMainBtn);
	}

}