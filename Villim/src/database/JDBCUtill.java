package database;

import util.Singleton;

import java.sql.*;

public class JDBCUtill {
	// 싱글톤
	private static JDBCUtill instance;

	private JDBCUtill() {
	}

	public static JDBCUtill getInstance() {
		if (instance == null) {
			instance = new JDBCUtill();
		}
		return instance;
	}

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
			
			//자동 생성
			String profile = "(nick text not null, id text not null, password text not null, email text not null, image text)";
			String resource = "(nick text, title text, lore text, category text, image text)";
			CreateTable("villim", "profile", profile);
			CreateTable("villim", "resource", resource);
			
			
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
					System.out.println("데이터베이스 생성 실패" + re);
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

	public void CreateTable(String DbName, String tName, String info) {
		try {
			CreateOrChangeDatabase(DbName);
			String tableSql = "SELECT table_name FROM information_schema.tables where table_schema = ? and table_name = ?";
			pstmt = conn.prepareStatement(tableSql);
			pstmt.setString(1, DbName);
			pstmt.setString(2, tName);
			rs = pstmt.executeQuery();
			if (!rs.next()) {
				Statement stmt = conn.createStatement();
				String sql = "create table " + tName
						+ info;
				rs2 = stmt.executeUpdate(sql);
				stmt.close();
			}
		} catch (Exception e) {
			System.out.println("CreateTable err : " + e);
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