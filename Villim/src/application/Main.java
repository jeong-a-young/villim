package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.JDBCUtill;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Singleton;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/Start_Layout.fxml"));
			AnchorPane mainLayout = (AnchorPane) loader.load();
			Scene scene = new Scene(mainLayout);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setTitle("Villim");
			primaryStage.setScene(scene);
			primaryStage.setMaxHeight(935);
			primaryStage.setMaxWidth(1600);
			primaryStage.setMinHeight(935);
			primaryStage.setMinWidth(1600);
			primaryStage.show();
			createAdminAccount();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	Statement stmt = null;
	PreparedStatement pstmt = null;
	String sql = "";
	Connection conn = JDBCUtill.getInstance().getConnection();

	public void setAdmin() {

	}

	public boolean join_id_check() {
		sql = "SELECT count(*) cnt from profile where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "admin");
			ResultSet rs = pstmt.executeQuery();
			while (true) {
				if (rs.next()) {
					int cnt = rs.getInt("cnt");
					if (cnt > 0) {
						return true;
					} else {
						break;
					}
				}
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	public void createAdminAccount() {
		if (!join_id_check()) {
			sql = "INSERT INTO profile(nick, id, password, email) VALUES (?,?,?,?)";
			try {

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, "admin");
				pstmt.setString(2, "admin");
				pstmt.setString(3, "admin");
				pstmt.setString(4, "admin@");
				pstmt.executeUpdate();

				Singleton.getInstance().debug("어드민 계정 최초 생성 완료");
			} catch (Exception e) {
				Singleton.getInstance().debug("실패");
				e.printStackTrace();
			}
		}
	}

}