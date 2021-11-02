package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtill {
	private Connection conn = null;
	private PreparedStatement pstmt;
	private ResultSet rs;
	int rs2;

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String connectionString = "jdbc:mysql://localhost";
			String userId = "root";
			String password = "";
			conn = DriverManager.getConnection(connectionString, userId, password);
			System.out.println("데이터베이스 연결됨");
//			!중요! 아래 메소드를 통해 데이터베이스와 테이블의 유무를 확인하고 존재 하지않을시 데이터베이스와 테이블을 전부 자동 생성합니다!
//			xmpp만 실행하면 아무것도 안하셔도 됩니다
//
//			데이터 베이스의 유무 확인 후 없을시 자동 생성합니다
//			(데이터 베이스 이름 dbcord)
//			테이블도 유무 확인 후 없을시 자동 생성합니다
//			(테이블 이름 UserLoginData)
//
//			수정시 String connectionString = "jdbc:mysql://localhost";
//			localhost처럼 써주세요
//			주소 뒤에 /(데이터베이스 이름) 이 필요없습니다
			CreateTable("dbcord", "UserLoginData");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("데이터베이스 연결 실패");
		}
		return conn;
	}

	public void CreateOrChangeDatabase(String dbName) {
		try {
			String dbSql = "SELECT * FROM Information_schema.SCHEMATA WHERE SCHEMA_NAME = ?";
			pstmt = conn.prepareStatement(dbSql);
			pstmt.setString(1, dbName);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				Statement stmt = conn.createStatement();
				String sql = "create database " + dbName;
				boolean re = stmt.execute(sql);
				if (!re)
					System.out.println("데이터 베이스 최초 생성 완료" + re);
				stmt.close();
			}
			conn.setCatalog(dbName);
		} catch (Exception e) {
			System.out.println("CreateOrChangeDatabase err : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					pstmt.close();
			} catch (Exception e) {
			}
		}
	}
	public void CreateTable(String DbName, String tName) {
		try {
			CreateOrChangeDatabase(DbName);
			String tableSql = "SELECT table_name FROM information_schema.tables where table_schema = ? and table_name = ?";
			pstmt = conn.prepareStatement(tableSql);
			pstmt.setString(1, DbName);
			pstmt.setString(2, tName);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				Statement stmt = conn.createStatement();
				String sql = "create table " + tName + "(id text not null, password text not null, nick text, lore text, theme text)";
				rs2 = stmt.executeUpdate(sql);
				stmt.close();
			}
		} catch (Exception e) {
			System.out.println("데이터 베이스 테이블 생성 실패... 원인: " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					pstmt.close();
			} catch (Exception e) {
			}
		}
	}
}