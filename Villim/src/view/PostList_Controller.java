package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.MethodUtil;

import static view.Category_Controller.clothesCount;
import static view.Category_Controller.instrumentCount;
import static view.Category_Controller.electronicsCount;
import static view.Category_Controller.healthCount;
import static view.Category_Controller.sportsCount;
import static view.Category_Controller.campingCount;
import static view.Category_Controller.bookCount;
import static view.Category_Controller.kidsCount;
import static view.Category_Controller.animalCount;
import static view.Category_Controller.etcCount;
import static view.Main_Controller.postListCount;
import static view.Main_Controller.postListChangeCount;
import static view.Main_Controller.categoryChangeCount;

public class PostList_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		category.setItems(categoryItems);
		setSceneLabel();
	}

	MethodUtil methodUtil = new MethodUtil();

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
		}

		// 내가 작성한 글
		if (category.getValue().equals("내가 작성한 글")) {
			titleImageView.setImage(new Image("/resources/writeLabel.png"));
		}
		
		// 찜 목록
		if (category.getValue().equals("찜 목록")) {
			titleImageView.setImage(new Image("/resources/likeLabel.png"));
		}
		
		// 의상, 소품
		if (category.getValue().equals("의상 / 소품")) {
			titleImageView.setImage(new Image("/resources/clothesLabel.png"));
		}

		// 음반, 악기
		if (category.getValue().equals("음반 / 악기")) {
			titleImageView.setImage(new Image("/resources/instrumentLabel.png"));
		}

		// 전자기기
		if (category.getValue().equals("전자기기")) {
			titleImageView.setImage(new Image("/resources/electronicsLabel.png"));
		}

		// 헬스, 요가
		if (category.getValue().equals("헬스 / 요가")) {
			titleImageView.setImage(new Image("/resources/healthLabel.png"));
		}

		// 스포츠, 레저
		if (category.getValue().equals("스포츠 / 레저")) {
			titleImageView.setImage(new Image("/resources/sportsLabel.png"));
		}

		// 등산, 낚시, 캠핑
		if (category.getValue().equals("등산 / 낚시 / 캠핑")) {
			titleImageView.setImage(new Image("/resources/campingLabel.png"));
		}

		// 도서, 문구
		if (category.getValue().equals("도서 / 문구")) {
			titleImageView.setImage(new Image("/resources/bookLabel.png"));
		}

		// 유아 용품
		if (category.getValue().equals("유아 용품")) {
			titleImageView.setImage(new Image("/resources/kidsLabel.png"));
		}

		// 반려동물 용품
		if (category.getValue().equals("반려동물 용품")) {
			titleImageView.setImage(new Image("/resources/animalLabel.png"));
		}

		// 기타
		if (category.getValue().equals("기타")) {
			titleImageView.setImage(new Image("/resources/etcLabel.png"));
		}
	}

	// 화면 전환으로 판단
	public void setSceneLabel() {

		// 전체
		if (postListCount == 1) {
			titleImageView.setImage(new Image("/resources/allLabel.png"));
			category.setValue("전체");
			postListCount = 0;
		}

		// 의상, 소품
		if (clothesCount == 1) {
			titleImageView.setImage(new Image("/resources/clothesLabel.png"));
			category.setValue("의상 / 소품");
			clothesCount = 0;
		}

		// 음반, 악기
		if (instrumentCount == 1) {
			titleImageView.setImage(new Image("/resources/instrumentLabel.png"));
			category.setValue("음반 / 악기");
			instrumentCount = 0;
		}

		// 전자기기
		if (electronicsCount == 1) {
			titleImageView.setImage(new Image("/resources/electronicsLabel.png"));
			category.setValue("전자기기");
			electronicsCount = 0;
		}

		// 헬스, 요가
		if (healthCount == 1) {
			titleImageView.setImage(new Image("/resources/healthLabel.png"));
			category.setValue("헬스 / 요가");
			healthCount = 0;
		}

		// 스포츠, 레저
		if (sportsCount == 1) {
			titleImageView.setImage(new Image("/resources/sportsLabel.png"));
			category.setValue("스포츠 / 레저");
			sportsCount = 0;
		}

		// 등산, 낚시, 캠핑
		if (campingCount == 1) {
			titleImageView.setImage(new Image("/resources/campingLabel.png"));
			category.setValue("등산 / 낚시 / 캠핑");
			campingCount = 0;
		}

		// 도서, 문구
		if (bookCount == 1) {
			titleImageView.setImage(new Image("/resources/bookLabel.png"));
			category.setValue("도서 / 문구");
			bookCount = 0;
		}

		// 유아 용품
		if (kidsCount == 1) {
			titleImageView.setImage(new Image("/resources/kidsLabel.png"));
			category.setValue("유아 용품");
			kidsCount = 0;
		}

		// 반려동물 용품
		if (animalCount == 1) {
			titleImageView.setImage(new Image("/resources/animalLabel.png"));
			category.setValue("반려동물 용품");
			animalCount = 0;
		}

		// 기타
		if (etcCount == 1) {
			titleImageView.setImage(new Image("/resources/etcLabel.png"));
			category.setValue("기타");
			etcCount = 0;
		}
	}

	// 이전 화면 전환
	@FXML
	private Button changeBackBtn;

	public void changeBack() {
		if (postListChangeCount == 1) {
			methodUtil.changeScene("/view/Main_Layout.fxml", changeBackBtn);
			postListChangeCount = 0;
		} else if (categoryChangeCount == 1) {
			methodUtil.changeScene("/view/Category_Layout.fxml", changeBackBtn);
		}
	}

}