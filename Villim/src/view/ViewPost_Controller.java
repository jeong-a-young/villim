package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import util.MethodUtil;

public class ViewPost_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		changeBtn();
	}

	MethodUtil methodUtil = new MethodUtil();

	// 내가 쓴 게시글인지 다른 사람이 쓴 게시글인지 판단
	@FXML
	private BorderPane btnPane;

	int meWrite = 0;
	int otherWrite = 0;

	public void changeBtn() {
		// DB를 이용해서 내가 쓴 게시글인지 다른 사람이 쓴 게시글인지 판단

		// 만약 내가 쓴 게시글이면
		// meWrite++;
		// 다른 사람이 쓴 게시글이면
		// otherWrite++;

		if (meWrite == 1) {
			methodUtil.changePartScene("/view/MeWrite_Layout.fxml", btnPane);
		} else if (otherWrite == 1) {
			methodUtil.changePartScene("/view/otherWrite_Layout.fxml", btnPane);
		}
	}

	// 메인 화면 전환
	@FXML
	private Button changeMainBtn;

	public void changeMain() {
		methodUtil.changeScene("/view/Main_Layout.fxml", changeMainBtn);
	}
	
}