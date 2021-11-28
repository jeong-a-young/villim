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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;
import util.Singleton;

public class ViewPost_Controller implements Initializable {
	// 이 클래스가 실행되면 호출되는 메소드 ^ 이거 있어야함 ^
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getPost();
		getPhoto();
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

	// 게시글 불러오기
	@FXML
	private Label titleLabel;
	@FXML
	private Label writerLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label contentLabel;

	public void getPost() {
		sql = "select * from resource where code='" + Singleton.getInstance().getCode() + "'";
		System.out.println(Singleton.getInstance().getCode());
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				System.out.println("ㅅㄷㄴㅆㄴㄱㅁㄱㅁㅇㅁㄴㅇ");
				writerLabel.setText(rs.getString("id"));
				titleLabel.setText(rs.getString("title"));
				contentLabel.setText(rs.getString("content"));
				dateLabel.setText(rs.getString("now"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 이미지 불러오기
	@FXML
	private ImageView postImageView;

	public void getPhoto() {
		methodUtil.getResourcePhoto(Singleton.getInstance().getCode(), postImageView);
		
	}

	// 수정 화면 전환
	@FXML
	private Button changeEditPostBtn;

	public void changeEditPost() {
		if (checkId() > 0) {
			methodUtil.changeScene("/view/EditPost_Layout.fxml", changeEditPostBtn);
		} else {
			alert("작성자만 수정할 수 있습니다.");
		}
	}

	// 삭제
	@FXML
	private Button deletePostBtn;
	
	public void deletePost() {
		if (checkId() > 0) {
			sql = "delete from resource where code='" + Singleton.getInstance().getCode() + "'";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.executeUpdate();
				methodUtil.backScene(deletePostBtn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			alert("작성자만 삭제할 수 있습니다.");
		}
	}

	// 게시글 작성자와 로그인 한 사용자의 아이디가 같은가 체크
	public int checkId() {

		int count = 0;
		sql = "select * from resource where code='" + Singleton.getInstance().getCode() + "'";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String writerId = rs.getString("id");
				if (writerId.equals(Singleton.getInstance().getAccountNick() + "("
						+ Singleton.getInstance().getAccountId().substring(0, 3) + "****)")) {
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public void name() {
		
	}
	// 이전 화면으로 가는 코드
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
		Singleton.getInstance().setCode(null);
	}

}