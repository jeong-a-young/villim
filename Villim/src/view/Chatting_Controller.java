package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import util.MethodUtil;

public class Chatting_Controller implements Initializable {
	// 이 클래스가 실행되면 호출되는 메소드 ^ 이거 있어야함 ^
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	MethodUtil methodUtil = new MethodUtil();
	
	// 채팅
	@FXML
	private TextField chattingContent;
	@FXML
	private ListView<String> chattingListView;
	
	public void send() {
		
	}
	
	// 이전 화면 전환
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
	}
	
}