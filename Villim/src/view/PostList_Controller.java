package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.MethodUtil;

public class PostList_Controller implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setLabel();
	}

	MethodUtil methodUtil = new MethodUtil();

	@FXML
	private Button changeMainBtn;

	public void changeMain() {
		methodUtil.changeScene("/view/Main_Layout.fxml", changeMainBtn);
	}

	@FXML
	private Button changeCategoryBtn;

	public void changeCategory() {
		methodUtil.changeScene("/view/Category_Layout.fxml", changeCategoryBtn);
	}

	// 게시물 목록 제목 판단
	@FXML
	private ImageView labelImageView;

	public void setLabel() {
		
		// 의상, 소품
		if (clothesCount == 1) {
			labelImageView.setImage(new Image("/resources/clothesLabel.png"));
			clothesCount = 0;
		}

		// 음반, 악기
		else if (instrumentCount == 1) {
			labelImageView.setImage(new Image("/resources/instrumentLabel.png"));
			instrumentCount = 0;
		}

		// 전자기기
		if (electronicsCount == 1) {
			labelImageView.setImage(new Image("/resources/electronicsLabel.png"));
			electronicsCount = 0;
		}

		// 헬스, 요가
		if (healthCount == 1) {
			labelImageView.setImage(new Image("/resources/healthLabel.png"));
			healthCount = 0;
		}

		// 스포츠, 레저
		if (sportsCount == 1) {
			labelImageView.setImage(new Image("/resources/sportsLabel.png"));
			sportsCount = 0;
		}

		// 등산, 낚시, 캠핑
		if (campingCount == 1) {
			labelImageView.setImage(new Image("/resources/campingLabel.png"));
			campingCount = 0;
		}

		// 도서, 문구
		if (bookCount == 1) {
			labelImageView.setImage(new Image("/resources/bookLabel.png"));
			bookCount = 0;
		}

		// 유아 용품
		if (kidsCount == 1) {
			labelImageView.setImage(new Image("/resources/kidsLabel.png"));
			kidsCount = 0;
		}

		// 반려동물 용품
		if (animalCount == 1) {
			labelImageView.setImage(new Image("/resources/animalLabel.png"));
			animalCount = 0;
		}

		// 기타
		if (etcCount == 1) {
			labelImageView.setImage(new Image("/resources/etcLabel.png"));
			etcCount = 0;
		}
	}

	// 의상, 소품
	@FXML
	private Button changeClothesBtn;

	public static int clothesCount = 0;

	public void changeClothes() {
		clothesCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeClothesBtn);
	}

	// 음반, 악기
	@FXML
	private Button changeInstrumentBtn;

	public static int instrumentCount = 0;

	public void changeInstrument() {
		instrumentCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeInstrumentBtn);
	}

	// 전자기기
	@FXML
	private Button changeElectronicsBtn;

	public static int electronicsCount = 0;

	public void changeElectronics() {
		electronicsCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeElectronicsBtn);
	}

	// 헬스, 요가
	@FXML
	private Button changeHealthBtn;

	public static int healthCount = 0;

	public void changeHealth() {
		healthCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeHealthBtn);
	}

	// 스포츠, 레저
	@FXML
	private Button changeSportsBtn;

	public static int sportsCount = 0;

	public void changeSports() {
		sportsCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeSportsBtn);
	}

	// 등산, 낚시, 캠핑
	@FXML
	private Button changeCampingBtn;

	public static int campingCount = 0;

	public void changeCamping() {
		campingCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeCampingBtn);
	}

	// 도서, 문구
	@FXML
	private Button changeBookBtn;

	public static int bookCount = 0;

	public void changeBook() {
		bookCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeBookBtn);
	}

	// 유아 용품
	@FXML
	private Button changeKidsBtn;

	public static int kidsCount = 0;

	public void changeKids() {
		kidsCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeKidsBtn);
	}

	// 반려동물 용품
	@FXML
	private Button changeAnimalBtn;

	public static int animalCount = 0;

	public void changeAnimal() {
		animalCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeAnimalBtn);
	}

	// 기타
	@FXML
	private Button changeEtcBtn;

	public static int etcCount = 0;

	public void changeEtc() {
		etcCount++;
		methodUtil.changeScene("/view/PostList_Layout.fxml", changeEtcBtn);
	}

}
