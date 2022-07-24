package com.my.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConnection {
	static {
		// 2. jdbc driver 클래스로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {

		Connection con = null; // 초기화
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "test";
		String password = "test";

		con = DriverManager.getConnection(url, user, password);
		System.out.println("연결 성공!");
		return con;
	}

	public static void close(ResultSet rs, Statement stmt, Connection con) {
//		7. DB연결 해제 : rs, stmt, con 연결해제
		if (rs != null) { // rs기 null면 종료할필요없음
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	
	public static void close(Statement stmt, Connection con) {
		close(null,stmt,con);
	}
}
