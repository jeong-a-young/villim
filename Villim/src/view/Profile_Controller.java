package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.MethodUtil;

public class Profile_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getInformation();
	}

	MethodUtil methodUtil = new MethodUtil();

	// 1. Information

	public void getInformation() {
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