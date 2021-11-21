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
			e.printStackTrace();
		}
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
			e.printStackTrace();
		}
	}

	// 사용자에게 사진 파일 받아오기
	
	private Stage selectStage;
	
	public String selectFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("이미지 파일", "*.png", "*.PNG", "*.jpg", "*.jpeg"));
		File file = fileChooser.showOpenDialog(selectStage);
		return file.getPath();
	}
	
	// DB에 사진 저장하고 가져오기

	// 사진 저장
	public void inputPhoto() {
			
		try {
			File imgfile = new File(selectFile());
			FileInputStream fin = new FileInputStream(imgfile);
			PreparedStatement pre = conn.prepareStatement("insert into photos (id, name, file) VALUES (?, ?, ?)");
			pre.setInt(1, 5); // test
			pre.setString(2, "test");
			pre.setBinaryStream(3, fin, (int) imgfile.length());
			pre.executeUpdate();
			System.out.println("사진 저장 성공");
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

	public void outputPhoto() {

		try {
			sql = "SELECT file FROM photos WHERE"; // sql문 작성 필요
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, ""); // 작성 필요
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				Blob blob = result.getBlob("file");
				InputStream inputStream = blob.getBinaryStream();
				OutputStream outputStream = new FileOutputStream(""); // 사용자에게 받은 사진을 저장할 경로

				int bytesRead = -1;
				byte[] buffer = new byte[BUFFER_SIZE];
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}

				inputStream.close();
				outputStream.close();
				System.out.println("사진 불러오기 성공");
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}