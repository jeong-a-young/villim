package view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import database.JDBCUtill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;
import util.Singleton;

public class WritePost_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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

	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();
	
	public String file = "";
	
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
	@FXML
	private ListView<String> photoList;
	private ObservableList<String> photoListItems = FXCollections.observableArrayList();

	// 사진 추가
	public void addPhoto() {
		file = methodUtil.selectFile();
		if (file.equals("NO IMAGE")) {
			alert("사진 파일이 아닙니다");
			return;
		} else {
			photoListItems.add(file);
			photoList.setItems(photoListItems);
		}	
	}

	// 게시글 작성
	public void writePost() {
		String title = write_title.getText();
		String content = write_content.getText();
		String category = categoryComboBox.getValue();
		String writer = Singleton.getInstance().getAccountNick() + "(" + Singleton.getInstance().getAccountId().substring(0, 3) + "****)";
		if (title.isEmpty()) {
			alert("제목을 입력해주세요");
		} else if (content.isEmpty()) {
			alert("설명을 입력해주세요");
		} else if (category == null) {
			alert("카테고리를 설정해주세요");
		} else {
			try {
				int recommend = 0;
				Date date_now = new Date(System.currentTimeMillis());
				SimpleDateFormat fourteen_format = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분");
				SimpleDateFormat fourteen_format2 = new SimpleDateFormat("yyyyMMddHHmmss");
				String now = fourteen_format.format(date_now);
				Singleton.getInstance().setNow2((String)fourteen_format2.format(date_now));

				sql = "INSERT INTO resource(id, title, content, category, now, recommand, code) VALUES(?, ?, ?, ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, writer);
				pstmt.setString(2, title);
				pstmt.setString(3, content);
				pstmt.setString(4, category);
				pstmt.setString(5, now);
				pstmt.setInt(6, recommend);
				pstmt.setString(7, Singleton.getInstance().getNow2() + Singleton.getInstance().getAccountId());
				pstmt.executeUpdate();
				methodUtil.inputPhoto(file, "resources");
				Singleton.getInstance().debug("작성 성공");
				Singleton.getInstance().setWriteSuccess(true);
				methodUtil.backScene(write_success);
			} catch (Exception e) {
				e.printStackTrace();
				Singleton.getInstance().debug("작성 실패");
			}
		}
	}

	// 이전 화면으로 가는 코드
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
	}
	
}