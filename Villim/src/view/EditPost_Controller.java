package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import database.JDBCUtill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;
import util.Singleton;

public class EditPost_Controller implements Initializable {
	// 이 클래스가 실행되면 호출되는 메소드 ^ 이거 있어야함 ^
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getPost();
		categoryComboBox.setItems(categoryItems);
	}

	// 알림창
	@FXML
	public Pane alertPane;
	@FXML
	public Text alertText;
	public boolean isAliveThread;

	public void alert(String text) {
		Thread t = new Thread() {
			public void run() {
				try {
					isAliveThread = true;
					Thread.sleep(500);
					for (int i = -15; i <= 0; i++) {
						alertPane.setLayoutY(i * 4);
						Thread.sleep(25);
					}
					Thread.sleep(2000);
					for (int i = 0; i >= -15; i--) {
						alertPane.setLayoutY(i * 4);
						Thread.sleep(25);
					}
					isAliveThread = false;
				} catch (Exception e) {
					Singleton.getInstance().debug(getClass().getName() + " 쓰레드 오류: " + e);
				}

			}
		};
		alertText.setText(text);
		if (isAliveThread) {
			return;
		}
		t.start();
	}

	MethodUtil methodUtil = new MethodUtil();

	ResultSet rs = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

	// 원래 게시물 내용 가져오기
	@FXML
	private TextField write_title;
	@FXML
	private TextArea write_content;
	@FXML
	private Button write_success;
	@FXML
	private ComboBox<String> categoryComboBox = new ComboBox<String>();

	private ObservableList<String> categoryItems = FXCollections.observableArrayList("의상 / 소품", "음반 / 악기", "전자기기",
			"헬스 / 요가", "스포츠 / 레저", "등산 / 낚시 / 캠핑", "도서 / 문구", "유아 용품", "반려동물 용품", "기타");

	public void getPost() {
		sql = "select * from resource where id='" + Singleton.getInstance().getAccountNick() + "("
				+ Singleton.getInstance().getAccountId().substring(0, 3) + "****)" + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				write_title.setText(rs.getString("title"));
				write_content.setText(rs.getString("content"));
				categoryComboBox.setValue(rs.getString("category"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updatePost() {
		sql = "update resource set title='" + write_title.getText() + "',content='" + write_content.getText()
				+ "',category='" + categoryComboBox.getValue() + "' WHERE id='"
				+ Singleton.getInstance().getAccountNick() + "("
				+ Singleton.getInstance().getAccountId().substring(0, 3) + "****)" + "'";
		try {
			if (write_title.getText().isEmpty() || write_content.getText().isEmpty()
					|| categoryComboBox.getValue().equals(null)) {
				alert("공백인 칸이 있습니다.");
			} else {
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				alert("수정에 성공했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 이전 화면으로 가는 코드
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
	}

}