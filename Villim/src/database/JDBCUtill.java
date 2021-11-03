package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCUtill {
//	private Connection conn = null;
//	private PreparedStatement pstmt;
//	private ResultSet rs;
//	int rs2;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		String connectionString = "jdbc:mysql://localhost/villim";
		String userId = "root";
		String password = "";
		
		Connection con = null;
		try {
			System.out.println("연결하는중  .....");
			con = DriverManager.getConnection(connectionString, userId, password);
			System.out.println("연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		return con;
	}

//	public Connection getConnection() {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			String connectionString = "jdbc:mysql://localhost";
//			String userId = "root";
//			String password = "";
//			conn = DriverManager.getConnection(connectionString, userId, password);
//			System.out.println("�����ͺ��̽� �����");
//			!�߿�! �Ʒ� �޼ҵ带 ���� �����ͺ��̽��� ���̺��� ������ Ȯ���ϰ� ���� ���������� �����ͺ��̽��� ���̺��� ���� �ڵ� �����մϴ�!
//			xmpp�� �����ϸ� �ƹ��͵� ���ϼŵ� �˴ϴ�
//
//			������ ���̽��� ���� Ȯ�� �� ������ �ڵ� �����մϴ�
//			(������ ���̽� �̸� dbcord)
//			���̺� ���� Ȯ�� �� ������ �ڵ� �����մϴ�
//			(���̺� �̸� UserLoginData)
//
//			������ String connectionString = "jdbc:mysql://localhost";
//			localhostó�� ���ּ���
//			�ּ� �ڿ� /(�����ͺ��̽� �̸�) �� �ʿ�����ϴ�
//			CreateTable("dbcord", "UserLoginData");
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("�����ͺ��̽� ���� ����");
//		}
//		return conn;
//	}
//	public void CreateOrChangeDatabase(String dbName) {
//		try {
//			String dbSql = "SELECT * FROM Information_schema.SCHEMATA WHERE SCHEMA_NAME = ?";
//			pstmt = conn.prepareStatement(dbSql);
//			pstmt.setString(1, dbName);
//			rs = pstmt.executeQuery();
//			if (!rs.next()) {
//				Statement stmt = conn.createStatement();
//				String sql = "create database " + dbName;
//				boolean re = stmt.execute(sql);
//				if (!re)
//					System.out.println("������ ���̽� ���� ���� �Ϸ�" + re);
//				stmt.close();
//			}
//			conn.setCatalog(dbName);
//		} catch (Exception e) {
//			System.out.println("CreateOrChangeDatabase err : " + e);
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (pstmt != null)
//					pstmt.close();
//				if (conn != null)
//					pstmt.close();
//			} catch (Exception e) {
//			}
//		}
//	}
//	public void CreateTable(String DbName, String tName) {
//		try {
//			CreateOrChangeDatabase(DbName);
//			String tableSql = "SELECT table_name FROM information_schema.tables where table_schema = ? and table_name = ?";
//			pstmt = conn.prepareStatement(tableSql);
//			pstmt.setString(1, DbName);
//			pstmt.setString(2, tName);
//			rs = pstmt.executeQuery();
//			if (!rs.next()) {
//				Statement stmt = conn.createStatement();
//				String sql = "create table " + tName + "(id text not null, password text not null, nick text, lore text, theme text)";
//				rs2 = stmt.executeUpdate(sql);
//				stmt.close();
//			}
//		} catch (Exception e) {
//			System.out.println("������ ���̽� ���̺� ���� ����... ����: " + e);
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (pstmt != null)
//					pstmt.close();
//				if (conn != null)
//					pstmt.close();
//			} catch (Exception e) {
//			}
//		}
//	}
	
}