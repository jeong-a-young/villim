package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import util.MethodUtil;

public class Information_Controller {
	MethodUtil methodUtil = new MethodUtil();
	//이전 화면으로 가는 코드
		@FXML
		private Button backButton;
		
		public void back() {
			methodUtil.backScene(backButton);
		}
}
