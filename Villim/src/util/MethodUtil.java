package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mysql.cj.result.BinaryStreamValueFactory;

import database.JDBCUtill;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;

public class MethodUtil {

	Statement stmt = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

	// 화면 전환

	public void changeScene(String url, Button btn) {
		try {
			Parent main = FXMLLoader.load(getClass().getResource(url));
			Scene scene = new Scene(main);
			Stage primaryStage = (Stage) btn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			Singleton.getInstance().debug("오류[ " + e + " ]");
			e.printStackTrace();
		}
		Singleton.getInstance().sceneList.add(url);
		Singleton.getInstance().debug("scene 추적 기록" + Singleton.getInstance().sceneList);
	}

	public void backScene(Button btn) {
		try {
			Parent main = FXMLLoader.load(getClass()
					.getResource(Singleton.getInstance().sceneList.get(Singleton.getInstance().sceneList.size() - 2)));
			Scene scene = new Scene(main);
			Stage primaryStage = (Stage) btn.getScene().getWindow();
			primaryStage.setScene(scene);
		} catch (Exception e) {
			Singleton.getInstance().debug("오류[ " + e + " ]");
			e.printStackTrace();
		}
		Singleton.getInstance().sceneList.remove(Singleton.getInstance().sceneList.size() - 1);
		Singleton.getInstance().debug("scene 추적 기록" + Singleton.getInstance().sceneList);
	}

	public void changePartScene(String url, BorderPane pane) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(url));
			pane.setCenter(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void popUpScene(Button btn, Stage stage, String url, String title) {

		Stage mainStage = (Stage) btn.getScene().getWindow();
		stage = new Stage(StageStyle.DECORATED);
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(mainStage);

		try {
			Parent root = FXMLLoader.load(getClass().getResource(url));

			Scene sc = new Scene(root);
			sc.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			stage.setScene(sc);
			stage.setResizable(false);
			stage.setTitle(title);
			stage.show();
		} catch (Exception e) {
			Singleton.getInstance().debug("오류[ " + e + " ]");
			e.printStackTrace();
		}
	}

	// 사용자에게 사진 파일 받아오기

	private Stage selectStage;

	public String selectFile() {
		try {
			FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("이미지 파일", "*.png", "*.PNG", "*.jpg", "*.jpeg"));
		File file = fileChooser.showOpenDialog(selectStage);
		return file.getPath();
		} catch (Exception e) {
			return "";
		}
		
	}

	// DB에 사진 저장하고 가져오기

	// 사진 저장
	public void inputPhoto(String a) {
		if(a.isEmpty()) {
			return;
		}
		try {
			File imgfile = new File(a);
			FileInputStream fin = new FileInputStream(imgfile);
			PreparedStatement pre = conn.prepareStatement("insert into image (type, code, image) VALUES (?, ?, ?)");
			//년도:월:일:시:분:초:아이디
			pre.setString(1, "resource"); //이 사진은 게시물 사진
			pre.setString(2, Singleton.getInstance().getNow2()+Singleton.getInstance().getAccountId());
			pre.setBinaryStream(3, fin, (int) imgfile.length());
			pre.executeUpdate();
			Singleton.getInstance().debug("사진 저장 성공");
			pre.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 사진 불러오기

	// 이클립스에서 새로고침을 해야만 사진을 인식함
	// 굳이 파일을 저장하지 않고 바로 세팅하는 법은 없을까

	private static final int BUFFER_SIZE = 4096;
	int numCount = 0;

	public String outputPhoto() {

		numCount += 1;
		String srtCount = Integer.toString(numCount);
		String filePath = "src\\photo\\" + Singleton.getInstance().getAccountId() + srtCount + ".png";
		try {
			sql = "SELECT file FROM photos WHERE"; // sql문 작성 필요
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, ""); // 작성 필요
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Blob blob = result.getBlob("file");
				InputStream inputStream = blob.getBinaryStream();
				OutputStream outputStream = new FileOutputStream(filePath);

				int bytesRead = -1;
				byte[] buffer = new byte[BUFFER_SIZE];
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				inputStream.close();
				outputStream.close();
				Singleton.getInstance().debug("사진 불러오기 성공");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//파일 경로를 웹형식으로 불러와서 출력
		return filePath;
	}
	//리스트를 문자열로 문자열을 리스트로 만드는
	public String test(List<String> list) {
		String result = String.join(", ", list);
		return result;
	}
	public List<String> test(String text) {
		ArrayList<String> result = new ArrayList<String>(Arrays.asList(text.split(", ")));
		return result;
	}

}