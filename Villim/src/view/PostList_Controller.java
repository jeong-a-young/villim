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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.MethodUtil;
import util.PostUtil;
import util.Singleton;

public class PostList_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		category.setItems(categoryItems);
		setSceneLabel();
		if (!Singleton.getInstance().getCurrentCategory().equals(null)) {
			setCategoryPost();
		}
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

	// 게시물 목록 제목 변경
	@FXML
	private ImageView titleImageView;
	@FXML
	private ComboBox<String> category = new ComboBox<String>();

	private ObservableList<String> categoryItems = FXCollections.observableArrayList("전체", "내가 작성한 글", "찜 목록",
			"의상 / 소품", "음반 / 악기", "전자기기", "헬스 / 요가", "스포츠 / 레저", "등산 / 낚시 / 캠핑", "도서 / 문구", "유아 용품", "반려동물 용품", "기타");

	// 콤보 박스로 판단
	public void setComboLabel() {

		// 전체
		if (category.getValue().equals("전체")) {
			titleImageView.setImage(new Image("/resources/allLabel.png"));
			setComboBoxPost();
		}

		// 내가 작성한 글
		if (category.getValue().equals("내가 작성한 글")) {
			titleImageView.setImage(new Image("/resources/writeLabel.png"));
			setComboBoxPost();
		}

		// 찜 목록
		if (category.getValue().equals("찜 목록")) {
			titleImageView.setImage(new Image("/resources/likeLabel.png"));
		}

		// 의상, 소품
		if (category.getValue().equals("의상 / 소품")) {
			titleImageView.setImage(new Image("/resources/clothesLabel.png"));
			setComboBoxPost();
		}

		// 음반, 악기
		if (category.getValue().equals("음반 / 악기")) {
			titleImageView.setImage(new Image("/resources/instrumentLabel.png"));
			setComboBoxPost();
		}

		// 전자기기
		if (category.getValue().equals("전자기기")) {
			titleImageView.setImage(new Image("/resources/electronicsLabel.png"));
			setComboBoxPost();
		}

		// 헬스, 요가
		if (category.getValue().equals("헬스 / 요가")) {
			titleImageView.setImage(new Image("/resources/healthLabel.png"));
			setComboBoxPost();
		}

		// 스포츠, 레저
		if (category.getValue().equals("스포츠 / 레저")) {
			titleImageView.setImage(new Image("/resources/sportsLabel.png"));
			setComboBoxPost();
		}

		// 등산, 낚시, 캠핑
		if (category.getValue().equals("등산 / 낚시 / 캠핑")) {
			titleImageView.setImage(new Image("/resources/campingLabel.png"));
			setComboBoxPost();
		}

		// 도서, 문구
		if (category.getValue().equals("도서 / 문구")) {
			titleImageView.setImage(new Image("/resources/bookLabel.png"));
			setComboBoxPost();
		}

		// 유아 용품
		if (category.getValue().equals("유아 용품")) {
			titleImageView.setImage(new Image("/resources/kidsLabel.png"));
			setComboBoxPost();
		}

		// 반려동물 용품
		if (category.getValue().equals("반려동물 용품")) {
			titleImageView.setImage(new Image("/resources/animalLabel.png"));
			setComboBoxPost();
		}

		// 기타
		if (category.getValue().equals("기타")) {
			titleImageView.setImage(new Image("/resources/etcLabel.png"));
			setComboBoxPost();
		}
	}

	// 화면 전환으로 판단
	public void setSceneLabel() {

		// 전체
		if (Singleton.getInstance().getCurrentCategory().equals("전체")) {
			titleImageView.setImage(new Image("/resources/allLabel.png"));
			category.setValue("전체");
		}

		// 의상, 소품
		else if (Singleton.getInstance().getCurrentCategory().equals("의상 / 소품")) {
			titleImageView.setImage(new Image("/resources/clothesLabel.png"));
			category.setValue("의상 / 소품");
		}

		// 음반, 악기
		else if (Singleton.getInstance().getCurrentCategory().equals("음반 / 악기")) {
			Singleton.getInstance().debug("test: " + Singleton.getInstance().getCurrentCategory());
			titleImageView.setImage(new Image("/resources/instrumentLabel.png"));
			category.setValue("음반 / 악기");
		}

		// 전자기기
		else if (Singleton.getInstance().getCurrentCategory().equals("전자기기")) {
			titleImageView.setImage(new Image("/resources/electronicsLabel.png"));
			category.setValue("전자기기");
		}

		// 헬스, 요가
		else if (Singleton.getInstance().getCurrentCategory().equals("헬스 / 요가")) {
			titleImageView.setImage(new Image("/resources/healthLabel.png"));
			category.setValue("헬스 / 요가");
		}

		// 스포츠, 레저
		else if (Singleton.getInstance().getCurrentCategory().equals("스포츠 / 레저")) {
			titleImageView.setImage(new Image("/resources/sportsLabel.png"));
			category.setValue("스포츠 / 레저");
		}

		// 등산, 낚시, 캠핑
		else if (Singleton.getInstance().getCurrentCategory().equals("등산 / 낚시 / 캠핑")) {
			titleImageView.setImage(new Image("/resources/campingLabel.png"));
			category.setValue("등산 / 낚시 / 캠핑");
		}

		// 도서, 문구
		else if (Singleton.getInstance().getCurrentCategory().equals("도서 / 문구")) {
			titleImageView.setImage(new Image("/resources/bookLabel.png"));
			category.setValue("도서 / 문구");
		}

		// 유아 용품
		else if (Singleton.getInstance().getCurrentCategory().equals("유아 용품")) {
			titleImageView.setImage(new Image("/resources/kidsLabel.png"));
			category.setValue("유아 용품");
		}

		// 반려동물 용품
		else if (Singleton.getInstance().getCurrentCategory().equals("반려동물 용품")) {
			titleImageView.setImage(new Image("/resources/animalLabel.png"));
			category.setValue("반려동물 용품");
		}

		// 기타
		else if (Singleton.getInstance().getCurrentCategory().equals("기타")) {
			titleImageView.setImage(new Image("/resources/etcLabel.png"));
			category.setValue("기타");
		}

	}

	// 게시물 목록 가져오기
	@FXML
	private TableView<PostUtil> listTableView;
	@FXML
	private TableColumn<PostUtil, String> listCode;
	@FXML
	private TableColumn<PostUtil, String> listTitle;
	@FXML
	private TableColumn<PostUtil, String> listWriter;

	ObservableList<PostUtil> listObservableList;

	// 콤보 박스
	public void setComboBoxPost() {
		int count = 0;
		if (category.getValue().equals("전체")) {
			selectPost("select * from resource", count);
		} else if (category.getValue().equals("내가 작성한 글")) {
			selectPost("select * from resource where id='" + Singleton.getInstance().getAccountNick() + "("
					+ Singleton.getInstance().getAccountId().substring(0, 3) + "****)" + "'", count);
		} else {
			selectPost("select * from resource where category='" + category.getValue() + "'", count);
		}
	}

	// 카테고리
	public void setCategoryPost() {
		int count = 0;
		selectPost("select * from resource where category='" + Singleton.getInstance().getCurrentCategory() + "'",
				count);
	}

	// 게시물 찾아오는 메소드
	public void selectPost(String sql, int count) {
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			listObservableList = FXCollections.observableArrayList();
			while (rs.next()) {
				String writer = rs.getString("id");
				String title = rs.getString("title");
				String code = rs.getString("code");
				listCode.setCellValueFactory(new PropertyValueFactory<>("code"));
				listTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
				listWriter.setCellValueFactory(new PropertyValueFactory<>("writer"));
				listObservableList.add(new PostUtil(code, title, writer));
				listTableView.setItems(listObservableList);
				count++;
				Singleton.getInstance().setCurrentCategory(null);
			}
			if (count == 0) {
				alert("게시물이 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 게시물 화면 전환
	@FXML
	private Button changeViewPostBtn;

	public void changeViewPost() {
		int choicePostIndax = listTableView.getSelectionModel().getSelectedIndex();
		if (choicePostIndax > -1) {
			PostUtil postUtil = listTableView.getSelectionModel().getSelectedItem();
			Singleton.getInstance().setCode(postUtil.getCode());
			methodUtil.changeScene("/view/ViewPost_Layout.fxml", changeViewPostBtn);
		} else {
			alert("게시물을 선택해 주세요.");
		}
	}

	// 이전 화면으로 가는 코드
	@FXML
	private Button backButton;

	public void back() {
		methodUtil.backScene(backButton);
	}

}